<template>
  <div v-if="list.length > 0" class="rounded-lg z-10 relative">
    <div
      class="
        absolute
        bg-blue-50
        w-full
        h-auto
        flex flex-col
        border-2
        top-px
        rounded
        border-msg-content
      "
    >
      <div
        class="flex pl-10 py-2 h-full w-full hover:bg-gray-300 cursor-pointer"
        v-for="(item, index) in list"
        :key="index"
        :ref="`${index}`"
        @click="go(item)"
      >
        {{ item.name }} {{ item.area }}
      </div>
    </div>
  </div>
  <div v-else class="rounded-lg z-10 relative">
    <div
      class="
        absolute
        bg-blue-50
        w-full
        h-auto
        flex flex-col
        border-2
        top-px
        rounded
        border-msg-content
      "
    >
      <div
        class="flex pl-10 py-2 h-full w-full hover:bg-gray-300 cursor-pointer"
        @click="addStore"
      >
        추가하기
      </div>
    </div>
  </div>
</template>

<script>
import { updateSearch } from "@/api/search.js";
import { mapActions, mapGetters } from "vuex";
import { getSearchWithDong } from "@/api/search.js";

export default {
  name: "SearchList",
  props: ["list", "idx"],
  methods: {
    ...mapActions(["set_store"]),
    go(item) {
      console.log("item", item);
      updateSearch(
        item,
        () => {
          console.log("언급량 최신화 성공!");
        },
        () => {
          alert("언급량 최신화 실패!");
          return;
        }
      );

      getSearchWithDong(
        item,
        (res) => {
          this.set_store(res.object);
          console.log(res.object)
          console.log("vuex", this.store);
          this.$router.push("Analysis");
        },
        () => {
          alert("오류가 발생했습니다.");
        }
      );
    },
    addStore() {
      this.$router.push("AddStore");
    },
  },
  computed: {
    ...mapGetters(['store']),
  },
  watch: {
    idx: function (val, oldVal) {
      if (!this.list || this.list.length <= 0) {
        return;
      }

      if (oldVal >= 0) {
        this.$refs[oldVal].classList.remove("bg-gray-300");
      }
      this.$refs[val].classList.add("bg-gray-300");
    },
  },
};
</script>
