<template>
  <div class="w-11/12 h-full">
    <div class="flex w-full h-40 mb-4 justify-start">
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
        <div flex items-stretch>
          <div class="py-4 text-center text-lg text-gray-400">분석 기간</div>
          <div class="py-4 text-center text-2xl font-bold text-purple-500">{{ period }}</div>
        </div>
        <div id="today-amount"></div>
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
        <div flex items-stretch>
          <div class="py-4 text-center text-lg text-gray-400">NAVER 데이터랩 오늘의 언급량</div>
          <div class="py-4 text-center text-3xl font-bold text-red-400">{{ today }} 회</div>
        </div>
      </div>
      <div
        class="
          w-1/3
          h-full
          bg-white
          border-2 border-blue-500 border-opacity-50
          rounded-lg
          pt-2
          flex
          justify-center
        "
      >
        <div flex items-stretch>
          <div class="py-4 text-center text-lg text-gray-400">가장 많이 언급된 날</div>
          <div class="py-4 text-center text-3xl font-bold text-green-400">{{ maxdate }}</div>
        </div>
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
          flex
          justify-center
        "
      >
        <div flex items-stretch>
          <div class="text-center text-lg text-gray-400">{{ keyword }}</div>
          <div
            class="
              mt-2
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              text-center text-3xl
              font-bold
              text-blue-400
            "
          >
            Crawling Data
          </div>
          <div
            class="
              mt-2
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              text-center text-3xl
              font-bold
              text-blue-400
            "
          >
            Crawling Data
          </div>
          <div
            class="
              mt-2
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              text-center text-3xl
              font-bold
              text-blue-400
            "
          >
            Crawling Data
          </div>
          <div
            class="
              mt-2
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              text-center text-3xl
              font-bold
              text-blue-400
            "
          >
            Crawling Data
          </div>
          <div
            class="
              mt-2
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              text-center text-3xl
              font-bold
              text-blue-400
            "
          >
            Crawling Data
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import bb, { areaSpline } from "billboard.js";
import { getMention } from "@/api/mention.js";
import "billboard.js/dist/theme/insight.css";
import { mapGetters } from "vuex";

export default {
  name: "MentionAmount",
  components: {},
  data() {
    return {
      keyword: "",
      types: { "area-spline": areaSpline() },
      mention: [],
      today: "",
      period: "",
      maxdate: "",
      preMonth: "",
    };
  },
  computed: {
    ...mapGetters(["store"]),
  },
  mounted() {
    (this.keyword = this.store.name),
      (this.mention = getMention(
        this.keyword,
        (res) => {
          this.setValue(res.object);
          this.mention = [this.keyword].concat(res.object);

          var dates = ["x"];
          for (let i = 0; i < 30; i++) {
            var temp = new Date(this.preMonth.setDate(this.preMonth.getDate() + 1));
            dates.push(temp.toISOString().split("T")[0]);
          }
          // console.log(dates);
          // console.log(this.mention);
          this.data = bb.generate({
            data: {
              x: "x",
              columns: [dates, this.mention],
              type: "area-spline",
            },
            axis: {
              x: {
                type: "category",
                label: "NAVER SEARCH AD API",
                tick: {
                  rotate: -75,
                  multiline: false,
                  tooltip: true,
                },
              },
            },
            bindto: "#columnOrientedData",
          });
        },
        () => {
          alert("네이버 API 호출 중 오류가 발생했습니다.");
        }
      ));
  },
  methods: {
    setValue(obj) {
      this.today = obj[29];
      var max = 0;
      var maxidx = -1;
      for (let i = 0; i < obj.length; i++) {
        if (max < obj[i]) {
          max = obj[i];
          maxidx = i;
        }
      }
      var now = new Date(); // 현재 날짜 및 시간
      var oneMonthAgo = new Date(now.setMonth(now.getMonth() - 1)); // 한달 전
      this.preMonth = oneMonthAgo;
      now = new Date();
      var now_t = now.toISOString().split("T")[0];
      var oneMonthAgo_t = oneMonthAgo.toISOString().split("T")[0];
      this.period = oneMonthAgo_t + " ~ " + now_t; //분석기간

      var maxday = new Date(now.setDate(now.getDate() + maxidx)); //최대 언급량 날짜
      this.maxdate = maxday.getMonth() + "월 " + maxday.getDate() + "일";
    },
  },
};
</script>
<style lang=""></style>
