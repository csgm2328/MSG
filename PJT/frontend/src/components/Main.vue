<template>
  <div class="flex flex-col justify-center items-center flex-grow bg-blue-100 w-auto pt-10 pb-10">
    <div class="text-2xl sm:text-3xl xl:text-5xl font-bold mb-12">
      빅데이터 맛집 분석 플랫폼 MSG
    </div>
    <div class="h-16 w-full mb-10"><Search-bar class="w-8/12 sm:w-7/12 md:6/12 mx-auto" /></div>
    <div class="divide-y-2 divide-indigo-100 divide-solid w-8/12 md:h-80 bg-blue-50 h-auto">
      <div class="flex items-center h-14 md:h-1/5 text-xl ml-7 font-semibold">
        실시간 검색어 순위
      </div>
      <div class="md:grid md:grid-cols-2 md:divide-x-2 md:divide-indigo-100 h-4/5">
        <div>
          <div
            class="
              flex
              items-center
              pl-7
              h-12
              md:h-1/5
              font-semibold
              hover:bg-indigo-200
              cursor-pointer
            "
          >
            고래감자탕
          </div>
          <div
            class="
              flex
              items-center
              pl-7
              h-12 h-1/12
              md:h-1/5
              font-semibold
              hover:bg-indigo-200
              cursor-pointer
            "
          >
            광주 미남 조용일
          </div>
          <div
            class="
              flex
              items-center
              pl-7
              h-12 h-1/12
              md:h-1/5
              font-semibold
              hover:bg-indigo-200
              cursor-pointer
            "
          >
            할매순대국
          </div>
          <div
            class="
              flex
              items-center
              pl-7
              h-12 h-1/12
              md:h-1/5
              font-semibold
              hover:bg-indigo-200
              cursor-pointer
            "
          >
            짬뽕의전설
          </div>
          <div
            class="
              flex
              items-center
              pl-7
              h-12 h-1/12
              md:h-1/5
              font-semibold
              hover:bg-indigo-200
              cursor-pointer
            "
          >
            백다방
          </div>
        </div>
        <div>
          <div
            class="
              flex
              items-center
              pl-7
              h-12 h-1/12
              md:h-1/5
              font-semibold
              hover:bg-indigo-200
              cursor-pointer
            "
          >
            홍콩반점
          </div>
          <div
            class="
              flex
              items-center
              pl-7
              h-12 h-1/12
              md:h-1/5
              font-semibold
              hover:bg-indigo-200
              cursor-pointer
            "
          >
            임시임시
          </div>
          <div
            class="
              flex
              items-center
              pl-7
              h-12 h-1/12
              md:h-1/5
              font-semibold
              hover:bg-indigo-200
              cursor-pointer
            "
          >
            임시임시
          </div>
          <div
            class="
              flex
              items-center
              pl-7
              h-12 h-1/12
              md:h-1/5
              font-semibold
              hover:bg-indigo-200
              cursor-pointer
            "
          >
            임시임시
          </div>
          <div
            class="
              flex
              items-center
              pl-7
              h-12 h-1/12
              md:h-1/5
              font-semibold
              hover:bg-indigo-200
              cursor-pointer
            "
          >
            임시임시
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SearchBar from '@/components/SearchBar.vue';

export default {
  name: 'MAIN',
  components: {
    SearchBar,
  },
};
</script>
