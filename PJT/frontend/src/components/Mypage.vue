<template>
  <div>
    <div class="absolute top-40 text-4xl font-black">마이페이지</div>
    <table
      class="w-4/6 border-collapse border-2 border-gray-400 border-opacity-50"
    >
      <thead
        class="border-collapse border border-gray-400 border-b-2 bg-gray-200"
      >
        <tr>
          <th class="w-2/12 border-collapse border border-gray-400">맛집</th>
          <th class="w-5/12 border-collapse border border-gray-400">리뷰</th>
          <th class="w-2/12 border-collapse border border-gray-400">별점</th>
          <th class="w-2/12 border-collapse border border-gray-400">등록일</th>
          <th class="w-1/12 border-collapse border border-gray-400">삭제</th>
        </tr>
      </thead>
      <tbody class="bg-gray-100">
        <tr
          v-for="(review, index) in reviewList"
          :key="index"
          class="border-collapse border border-gray-400"
        >
          <td class="text-center border-collapse border border-gray-400">
            {{ review.store }}
          </td>
          <td
            class="text-center border-collapse border border-gray-400"
            v-if="review.content.length < 40"
          >
            {{ review.content }}
          </td>
          <td class="text-center border-collapse border border-gray-400" v-else>
            {{ review.content.substring(0, 40)
            }}<button class="text-blue-900">...</button>
          </td>
          <td class="border-collapse border border-gray-400">
            <star-rating
              class="justify-center"
              :increment="0.5"
              @update:rating="rating = $event"
              :show-rating="false"
              :rating="review.star_score"
              :starSize="20"
              :read-only="true"
            ></star-rating>
          </td>
          <td class="text-center border-collapse border border-gray-400">
            {{ review.reg_date.split("T")[0] }}
          </td>
          <td class="text-center border-collapse border border-gray-400">
            <button @click="del(review.rid)">
              <i class="fas fa-trash" style="color: red"></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import StarRating from "vue-star-rating";
import { getReview, delReview } from "@/api/review.js";

export default {
  name: "Mypage",
  components: {
    StarRating,
  },
  data(){
    return {
      mid: "",
      reviewList: [],
    };
  },
  created() {
    getReview(
        123123,
        (res) => {
          console.log(res);
          
          res.object.forEach(element => {
              this.reviewList.push(element)
          });
        //   res.object.forEach((element) => {
        //     this.reviewList.push(element)
        //   });

          console.log(this.reviewList);

        //   this.reviewList = arr
        // this.reviewList = JSON.parse(res.object)
        },
        () => {
          alert("리뷰를 가져오는데에 실패했습니다.");
        }
      );
  },
  methods: {
    del(rid) {
      delReview(
        rid,
        () => {
          alert("삭제 되었습니다.");

            this.reviewList = this.reviewList.filter(element => 
                element.rid != rid
            )

        },
        () => {
          alert("오류가 발생했습니다.");
        }
      );
    },
  },
};
</script>

<style></style>
