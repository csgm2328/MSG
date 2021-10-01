<template>
  <div class="flex flex-col h-screen w-auto">
    <Header />
    <div class="flex flex-col justify-center items-center flex-grow bg-blue-100 w-auto pt-10 pb-10">
      <div class="w-11/12 h-full">
        <div class="flex w-full justify-start">
          <div
            id="test2"
            class="
              w-1/6
              h-auto
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              flex
              mr-2
              justify-center
            "
          >
            긍정 or 부정
          </div>
        </div>
        <div class="flex w-full h-1/2 mt-2 justify-start">
          <div
            class="
              w-3/4
              h-full
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              pt-2
              mr-2
              flex
              justify-center
            "
            ref="inbox"
          >
            <div id="word-cloud"></div>
          </div>
          <div
            class="
              w-1/4
              h-full
              bg-white
              border-2 border-blue-500 border-opacity-50
              rounded-lg
              pt-2
              flex
              justify-center
            "
          >
            키워드 순위표
          </div>
        </div>
        <!-- 차트 -->
        <div class="flex w-full h-1/2 mt-2 justify-center">
          <div
            class="
              w-full
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
            <div id="columnOrientedData"></div>
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
import "billboard.js/dist/theme/insight.css";

export default {
  name: "Analysis",
  components: {
    Header,
    Footer,
  },
  data() {
    return {
      data: undefined,
      types: { line: line() },
      words: [
        { text: "Hello", size: 20 },
        { text: "Bye", size: 30 },
        { text: "Good", size: 10 },
        { text: "Bad", size: 25 },
        { text: "Title", size: 50 },
        { text: "Style", size: 40 },
        { text: "Vue", size: 30 },
        { text: "Javascript", size: 24 },
        { text: "HTML", size: 10 },
        { text: "CSS", size: 34 },
      ],
    };
  },
  mounted() {
    this.data = bb.generate({
      data: {
        columns: [
          ["data1", 30, 20, 50, 40, 60, 50],
          ["data2", 200, 130, 90, 240, 130, 220],
          ["data3", 300, 200, 160, 400, 250, 250],
        ],
        type: "line", // for ESM specify as: line()
      },
      bindto: "#columnOrientedData",
    });

    //   console.log(width, height)
    this.genLayout();
  },
  methods: {
    genLayout() {
      var d3 = require("d3"),
        cloud = require("d3-cloud");

      var width = parseInt(this.$refs.inbox.clientWidth);
      var height = parseInt(this.$refs.inbox.clientHeight);
      var layout = cloud()
        .size([width, height])
        .words(
          [
            { text: "Hello", size: 20 },
            { text: "Bye", size: 30 },
            { text: "Good", size: 10 },
            { text: "Bad", size: 25 },
            { text: "Title", size: 50 },
            { text: "Style", size: 40 },
            { text: "Vue", size: 30 },
            { text: "Javascript", size: 24 },
            { text: "HTML", size: 10 },
            { text: "CSS", size: 34 },
          ].map(function (d) {
            return { text: d.text, size: d.size, test: "haha" };
          })
        )
        .padding(5)
        .rotate(function () {
          return 0;
        })
        .font("Impact")
        .fontSize(function (d) {
          return d.size;
        })
        .on("end", draw);

      layout.start();

      function draw(words) {
        d3.select("#word-cloud")
          .append("svg")
          .attr("width", layout.size()[0])
          .attr("height", layout.size()[1])
          .append("g")
          .attr(
            "transform",
            "translate(" +
              layout.size()[0] / 2 +
              "," +
              layout.size()[1] / 2 +
              ")"
          )
          .selectAll("text")
          .data(words)
          .enter()
          .append("text")
          .style("font-size", function (d) {
            return d.size + "px";
          })
          .style("font-family", "Impact")
          .attr("text-anchor", "middle")
          .attr("transform", function (d) {
            return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
          })
          .text(function (d) {
            return d.text;
          });
      }
    },
  },
};
</script>

<style></style>
