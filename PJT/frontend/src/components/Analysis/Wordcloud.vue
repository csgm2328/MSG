<template>
  <div
    id="wordcloud"
    class="
      w-3/4
      h-80
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
</template>

<script>
export default {
  name: "Wordcloud",
  mounted() {
    this.genLayout();
  },
  methods: {
    genLayout() {
      var d3 = require("d3"),
        cloud = require("d3-cloud");

      var width = this.$refs.inbox.clientWidth;
      var height = this.$refs.inbox.clientHeight;
      
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
            return { text: d.text, size: d.size};
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
          .style("fill", function (d) {
            return d.size > 25 ? "#ff3300" : "#3333ff";
          })
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
