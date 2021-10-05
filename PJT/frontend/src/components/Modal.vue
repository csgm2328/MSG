<template>
  <div
    class="
      flex
      justify-center
      items-center
      fixed
      top-0
      left-0
      right-0
      bottom-0
      w-screen
      h-screen
      z-100
      bg-gray-700 bg-opacity-75
    "
  >
    <div
      class="
        flex flex-col
        w-4/6
        md:w-2/5
        h-4/6
        mx-auto
        fixed
        rounded-lg
        translate-x-1/2 translate-y-1/2
        bg-white
        z-50
      "
      v-click-away="closeModal"
    >
      <div class="flex relative justify-between items-center h-1/6 border-b-2 p-5">
        <div class="flex text-xl font-extrabold">{{ review.dong }} {{ review.store }}</div>
        <div class="absolute right-2 bottom-2">
          <p class="text-xs">작성일 : {{ getDate }}</p>
        </div>
      </div>
      <div class="flex flex-col items-center justify-center px-5 py-3 border-b-2">
        <star-rating
          :increment="0.5"
          @update:rating="rating = $event"
          :show-rating="false"
          :rating="review.starScore"
          :starSize="35"
          :read-only="true"
        ></star-rating>
        <div class="mt-3 text-xl font-black">
          {{ review.starScore }}
        </div>
      </div>
      <div class="flex h-4/6 border-b-2 p-5">{{ review.content }}</div>
      <div class="flex justify-center items-center h-1/6 p-5">
        <button
          class="
            bg-red-600
            text-white text-base
            font-semibold
            w-20
            py-2
            px-4
            rounded-lg
            shadow-md
            hover:bg-red-800
            focus:outline-none
            focus:ring-2
            focus:ring-red-500
            focus:ring-offset-2
            focus:ring-offset-red-200
            mr-10
          "
          @click="deleteReview(review.rid)"
        >
          삭제
        </button>
        <button
          class="
            bg-blue-600
            text-white text-base
            font-semibold
            w-20
            py-2
            px-4
            rounded-lg
            shadow-md
            hover:bg-blue-800
            focus:outline-none
            focus:ring-2
            focus:ring-blue-500
            focus:ring-offset-2
            focus:ring-offset-blue-200
          "
          @click="closeModal"
        >
          닫기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import StarRating from 'vue-star-rating';
export default {
  name: 'MODAL',
  components: {
    StarRating,
  },
  props: ['review'],
  methods: {
    deleteReview(rid) {
      this.$emit('deleteReview', rid);
      this.$emit('closeModal');
    },
    closeModal() {
      this.$emit('closeModal');
    },
  },
  computed: {
    getDate: function () {
      let dateArr = this.review.regDate.split('T');
      return `${dateArr[0]}\n${dateArr[1]}`;
    },
  },
};
</script>
