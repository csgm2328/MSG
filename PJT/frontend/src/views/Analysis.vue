<template>
  <div class="flex flex-col h-screen w-auto">
    <Header />
    <div class="flex flex-col justify-center items-center flex-grow bg-blue-100 w-auto pt-10 pb-10">
      <div class="w-11/12 bg-white border-2 border-blue-500 border-opacity-50 rounded-lg mb-6">
        <button class="bg-white hover:bg-blue-200 font-bold py-2 px-4 rounded mr-2" @click="key">
          키워드 분석
        </button>
        <button class="bg-white hover:bg-blue-200 font-bold py-2 px-4 rounded mr-2" @click="comp">
          비교 분석
        </button>
        <button
          class="bg-white hover:bg-blue-200 font-bold py-2 px-4 rounded mr-2"
          @click="mention"
        >
          언급량 분석
        </button>
        <button class="bg-white hover:bg-blue-200 font-bold py-2 px-4 rounded mr-2" @click="info">
          맛집 정보
        </button>
        <!-- <button>키워드 분석</button> -->
      </div>
      <keyword v-if="btn[0]" />
      <compare v-if="btn[1]" />
      <mention v-if="btn[2]" />
      <info v-if="btn[3]" />
    </div>
    <Footer />
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import Keyword from "@/views/Keyword.vue";
import Compare from "@/views/Compare.vue";
import Info from "@/views/Info.vue";
import Mention from "@/views/Mention.vue";
import { mapActions } from "vuex";

export default {
  name: "Analysis",
  components: {
    Header,
    Footer,
    Keyword,
    Compare,
    Info,
    Mention,
  },
  data() {
    return {
      btn: [],
    };
  },
  created() {
    this.btn = [true, false, false, false];
    this.set_analysisType(1);
  },
  methods: {
    ...mapActions(['set_analysisType']),
    key() {
      if (!this.btn[0]) {
        for (var i = 0; i < 4; i++) {
          this.btn[i] = false;
        }
        this.set_analysisType(1);
        this.btn[0] = true;
      }
    },
    comp() {
      if (!this.btn[1]) {
        for (var i = 0; i < 4; i++) {
          this.btn[i] = false;
        }
        this.set_analysisType(2);
        this.btn[1] = true;
      }
    },
    mention() {
      if (!this.btn[2]) {
        for (var i = 0; i < 4; i++) {
          this.btn[i] = false;
        }
        this.set_analysisType(3);
        this.btn[2] = true;
      }
    },
    info() {
      if (!this.btn[3]) {
        for (var i = 0; i < 4; i++) {
          this.btn[i] = false;
        }
        this.set_analysisType(4);
        this.btn[3] = true;
      }
    },
  },
};
</script>

<style></style>
