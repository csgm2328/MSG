<template>
  <div class="flex flex-col h-screen w-auto">
    <Header />
    <div class="flex flex-col justify-center items-center flex-grow bg-blue-100 w-auto pt-10 pb-10">
      <div class="w-11/12 h-full">
        <div class="flex w-full justify-start">
          <div
            id="analysis_type"
            class="
              w-1/8
              h-auto
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              flex
              mr-2
              justify-center
            "
          >
            언급량 분석
          </div>
        </div>

        <div class="flex w-2/8 h-1/4 mt-2 mb-4 justify-start">
          <div
            class="
              w-1/3
              h-full
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              pt-2
              mr-4
              flex
              justify-center
            "
            ref="inbox"
          >
            NAVER 데이터랩 오늘의 언급량
            <div id="word-cloud"></div>
          </div>
          <div
            class="
              w-1/3
              h-full
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              pt-2
              mr-4
              flex
              justify-center
            "
          >
            일주일 누적 언급량
          </div>
          <div
            class="
              w-1/3
              h-full
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              pt-2
              mr-2
              flex
              justify-center
            "
          >
            언급량 최대 일자
          </div>
        </div>
        <!-- 차트 -->
        <div class="flex w-full h-full mt-2 justify-start">
          <div
            class="
              w-2/3
              h-2/3
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              pt-2
              mr-4
              flex
              justify-start
            "
          >
            <div id="columnOrientedData"></div>
          </div>
          <div
            class="
              w-1/3
              h-2/3
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              pt-2
              mr-2
              flex
              justify-center
            "
          >
            크롤링한 데이터 샘플
          </div>
        </div>
      </div>
    </div>
    <Footer />
  </div>
</template>
<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import bb, { line } from "billboard.js";
import { getMention } from "@/api/mention.js";
import "billboard.js/dist/theme/insight.css";

export default {
  name: "MentionAmount",
  components: {
    Header,
    Footer,
  },
  data() {
    return {
      chart: undefined,
      keyword: "",
      types: { line: line() },
      mention: [],
    };
  },
  mounted() {
    (this.keyword = "맥도날드"),
      (this.mention = getMention(
        this.keyword,
        (res) => {
          this.mention = [this.keyword].concat(res.object);
          console.log(this.mention);
          this.data = bb.generate({
            data: {
              columns: [this.mention],
              type: "area-spline",
            },
            bindto: "#columnOrientedData",
          });
        },
        () => {
          alert("네이버 API 호출 중 오류가 발생했습니다.");
        }
      ));
    // this.data = bb.generate({
    //   data: {
    //     columns: [
    //       ["data1", 30, 20, 50, 40, 60, 50],
    //       ["data2", 200, 130, 90, 240, 130, 220],
    //       ["data3", 300, 200, 160, 400, 250, 250],
    //     ],
    //     type: "line", // for ESM specify as: line()
    //   },
    //   bindto: "#columnOrientedData",
    // });
    //   console.log(width, height)
  },
  methods: {},
};
</script>
<style lang=""></style>
