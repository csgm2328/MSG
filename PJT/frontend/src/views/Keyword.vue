<template>
  <div class="w-11/12">
    <div class="flex w-full justify-start">
      <div
        id="test2"
        class="
          w-1/3
          h-12
          bg-white
          border-2 border-blue-500 border-opacity-50
          rounded-lg
          flex
          mr-2
          justify-center
        "
      >
        <div v-if="waitForWords == false">
          <div v-if="isPos">
            <span style="color: dodgerblue; font-weight: 1000">긍정적인 평가 20%</span>
          </div>
          <div v-else>
            <span>부정적인 평가 {{ wordPercent }}%</span>
          </div>
          <div style="font-size: small; opacity: 0.8; color: black">{{ name }}의 대표 긍,부정</div>
        </div>
      </div>
    </div>
    <div class="flex w-full h-80 mt-2 mb-2 justify-start">
      <wordcloud v-bind:words="words" />
      <div
        class="
          w-2/5
          h-80
          bg-white
          border-2 border-blue-500 border-opacity-50
          rounded-lg
          pt-2
          flex
          justify-center
        "
      >
        리뷰
      </div>
    </div>
    <div class="flex w-full h-80 mt-2 mb-2 justify-start">
      <div
        class="
          w-3/5
          h-80
          bg-white
          border-2 border-blue-500 border-opacity-50
          rounded-lg
          pt-2
          mr-2
          flex
          justify-center
        "
      >
        <div class="object-top">키워드 감성 통계</div>
        <chart v-bind:words="words" />
      </div>
      <div
        class="
          w-2/5
          h-80
          bg-white
          border-2 border-blue-500 border-opacity-50
          rounded-lg
          pt-2
          flex
          justify-center
        "
      >
        <div class="overflow-auto" v-if="words.length != 0">
          <table class="w-32 md:w-48 lg:w-60 table-fixed">
            <thead>
              <tr style="border-bottom: darkgrey solid 1px; border-top: black solid 1px">
                <th class="w-3/6" style="text-align: center; boarder-bottom">단어</th>
                <th class="w-1/6" style="text-align: center">수</th>
                <th class="w-2/6" style="text-align: center">긍.부정</th>
              </tr>
            </thead>
            <tr v-for="(word, index) in words" v-bind:key="index">
              <td style="text-align: center">{{ word.keyword }}</td>
              <td style="text-align: center">{{ word.count }}</td>
              <td style="text-align: center">{{ word.sentiment }}</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <!-- 차트 -->
    <!-- <chart /> -->
  </div>
</template>

<script>
// import Chart from "@/components/Analysis/Chart.vue";
import Wordcloud from "@/components/Analysis/Wordcloud.vue";
import Chart from "../components/Analysis/Chart.vue";
import { getKeywords } from "@/api/search.js";
import { mapGetters, mapActions } from "vuex";
export default {
  name: "Keyword",
  components: {
    // Chart,
    Wordcloud,
    Chart,
  },
  data() {
    return {
      words: [],
      waitForWords: true,
      isPos: true,
      wordPercent: 0,
    };
  },
  props: {
    name: {
      type: String,
      default: "커피빈",
    },
    area: {
      type: String,
      default: "원마운트",
    },
  },
  methods: {
    ...mapActions(["get_words"]),
    countPosNeg() {
      let pos = 0;
      let neg = 0;
      let total = 0;
      for (let i = 0; i < this.words.length; i++) {
        total += this.words[i].count;
        if (this.words[i].sentiment == "긍정" || this.words[i].sentiment == "매우긍정") {
          pos += this.words[i].count;
          continue;
        } else if (this.words[i].sentiment == "부정" || this.words[i].sentiment == "매우부정") {
          neg += this.words[i].count;
          continue;
        }
      }
      if (pos >= neg) {
        this.wordPercent = (pos * 100) / total;
      } else {
        this.isPos = false;
        this.wordPercent = (neg * 100) / total;
      }
      this.waitForWords = false;
    },
  },
  created() {
    let store = { name: this.name, area: this.area };
    getKeywords(store, (res) => {
      this.words = res.object;
      this.countPosNeg();
      this.get_words(res.object);
    });
  },
  computed:{
    ...mapGetters(['store'])
  }
};
</script>

<style></style>
