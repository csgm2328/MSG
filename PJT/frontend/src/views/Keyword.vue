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
        긍정 or 부정
      </div>
    </div>
    <div class="flex w-full h-80 mt-2 mb-2 justify-start">
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
    <!-- 차트 -->
    <chart />
  </div>
</template>

<script>
import Chart from "@/components/Analysis/Chart.vue";
import Wordcloud from "@/components/Analysis/Wordcloud.vue";

export default {
  name: "Keyword",
  components:{
    Chart,
    Wordcloud
  },
};
</script>

<style></style>
