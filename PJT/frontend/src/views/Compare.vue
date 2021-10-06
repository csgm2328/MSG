<template>
  <div class="w-11/12 h-full">
    <div class="grid grid-cols-2 h-full">
      <div class="flex flex-col mr-2">
        <div
          id="test2"
          class="
            w-full
            h-12
            bg-white
            border-2 border-blue-500 border-opacity-50
            rounded-lg
            flex
            mr-2
            justify-start
          "
        >
          <div v-if="isPos">
            <span style="color: dodgerblue; font-weight: 1000"
              >긍정적인 평가 {{ wordPercent }}%</span
            >
            <div style="font-size: small; opacity: 0.8; color: black">
              {{ store.name }}의 대표 긍,부정
            </div>
          </div>
          <div v-else-if="isPos == false">
            <span style="color: red; font-weight: 1000">부정적인 평가 {{ wordPercent }}%</span>
            <div style="font-size: small; opacity: 0.8; color: black">
              {{ store.name }}의 대표 긍,부정
            </div>
          </div>
          <div v-else>기다려주세요</div>
        </div>
        <div class="flex w-full h-80 mt-2 mb-2 justify-center">
          <wordcloud />
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
      </div>
      <div class="flex flex-col">
        <div
          id="test2"
          class="
            w-full
            h-12
            bg-white
            border-2 border-blue-500 border-opacity-50
            rounded-lg
            flex
            mr-2
            justify-start
          "
        >
          <div v-if="waitForvsWords == false">
            <div v-if="vsIsPos">
              <span style="color: dodgerblue; font-weight: 1000"
                >긍정적인 평가 {{ vsWordsPercent }}%</span
              >
            </div>
            <div v-else>
              <span style="color: red; font-weight: 1000">부정적인 평가 {{ vsWordsPercent }}%</span>
            </div>
            <div style="font-size: small; opacity: 0.8; color: black">
              {{ vsStore.name }}의 대표 긍,부정
            </div>
          </div>
        </div>
        <div class="flex w-full h-80 mt-2 mb-2">
          <wordcloud2 />
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
      </div>
    </div>
  </div>
  <div class="w-11/12 h-full">
    <compare-chart />
  </div>
</template>

<script>
import Wordcloud from "@/components/Analysis/Wordcloud.vue";
import Wordcloud2 from "@/components/Analysis/Wordcloud2.vue";
import CompareChart from "@/components/Analysis/CompareChart.vue";
import { mapGetters, mapActions } from "vuex";
import { getKeywords } from "@/api/search.js";
export default {
  components: { Wordcloud, Wordcloud2, CompareChart },
  name: "Compare",
  data() {
    return {
      waitForWords: true,
      isPos: true,
      wordPercent: 0,
      waitForvsWords: true,
      vsIsPos: true,
      vsWordsPercent: 0,
    };
  },
  watch: {
    vsStore() {
      getKeywords(this.vsStore, (res) => {
        this.vsWords = res.object;
        this.countPosNegVS();
        this.set_vsWords(res.object);
      });
    },
  },
  created() {
    this.countPosNeg();
    this.set_vsWords([]);
  },
  computed: {
    ...mapGetters(["words", "store", "vsStore"]),
  },
  methods: {
    ...mapActions(["set_words", "set_vsWords"]),
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
      if (total != 0) {
        if (pos >= neg) {
          this.wordPercent = (pos * 100) / total;
        } else {
          this.isPos = false;
          this.wordPercent = (neg * 100) / total;
        }
      } else {
        this.wordPercent = 0;
      }
      this.waitForWords = false;
    },
    countPosNegVS() {
      let pos = 0;
      let neg = 0;
      let total = 0;
      for (let i = 0; i < this.vsWords.length; i++) {
        total += this.vsWords[i].count;
        if (this.vsWords[i].sentiment == "긍정" || this.vsWords[i].sentiment == "매우긍정") {
          pos += this.vsWords[i].count;
          continue;
        } else if (this.vsWords[i].sentiment == "부정" || this.vsWords[i].sentiment == "매우부정") {
          neg += this.vsWords[i].count;
          continue;
        }
      }
      if (total != 0) {
        if (pos >= neg) {
          this.vsIsPos = true;
          this.vsWordsPercent = (pos * 100) / total;
        } else {
          this.vsIsPos = false;
          this.vsWordsPercent = (neg * 100) / total;
        }
      } else {
        this.vsWordsPercent = 0;
      }
      this.waitForvsWords = false;
    },
  },
};
</script>

<style></style>
