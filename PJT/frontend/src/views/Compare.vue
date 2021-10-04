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
          긍정 or 부정
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
          긍정 or 부정
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
    <chart />
  </div>
</template>

<script>
import Wordcloud from "@/components/Analysis/Wordcloud.vue";
import Wordcloud2 from "@/components/Analysis/Wordcloud2.vue";
import Chart from "@/components/Analysis/Chart.vue";

export default {
  components: { Wordcloud, Wordcloud2, Chart },
  name: "Compare",
};
</script>

<style></style>
