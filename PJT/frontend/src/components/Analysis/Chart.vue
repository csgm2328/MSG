<template>
  <!-- <div class="flex w-full h-32 mt-2 justify-center"> -->
    <div
      class="
        w-full
        h-64
        bg-white
        border-2 border-blue-500 border-opacity-50
        rounded-lg
        pt-2
        mr-2
        flex
        justify-center
        inline-block
        align-middle
      "
      ref="chart"
    >
      <div
        id="columnOrientedData"
        class="flex flex-wrap content-center w-11/12 h-full"
      ></div>
    </div>
  <!-- </div> -->
</template>

<script>
import bb, { line } from "billboard.js";
import "billboard.js/dist/theme/insight.css";

export default {
  name: "Chart",
  data() {
    return {
      data: undefined,
      types: { line: line() },
    };
  },
  mounted() {
    this.genChart();
  },
  methods: {
    genChart() {
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
    },
  },
};
</script>

<style></style>
