<template>
  <div class="flex flex-col h-screen w-auto">
    <Header />
    <div
      class="
        flex flex-col
        justify-center
        items-center
        flex-grow
        bg-blue-100
        w-auto
        pt-10
        pb-10
      "
    >
      <div class="h-20 mb-12 mx-auto">
        <img class="h-full w-auto" src="@/images/banner.png" />
      </div>
      <div class="h-16 w-full mb-10">
        <Search-bar class="w-8/12 sm:w-7/12 md:6/12 mx-auto z-10" />
      </div>
      <div
        class="
          divide-y-2 divide-indigo-100 divide-solid
          w-8/12
          md:h-80
          bg-blue-50
          h-auto
          z-0
        "
      >
        <div class="flex items-center h-14 md:h-1/5 text-xl ml-7 font-semibold">
          실시간 검색어 순위
        </div>
        <div
          class="
            md:grid md:grid-cols-2 md:divide-x-2 md:divide-indigo-100
            h-4/5
          "
        >
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
              @click="go"
            >
              {{ this.top10[0] }}
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
              @click="go"
            >
              {{ this.top10[1] }}
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
              @click="go"
            >
              {{ this.top10[2] }}
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
              @click="go"
            >
              {{ this.top10[3] }}
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
              @click="go"
            >
              {{ this.top10[4] }}
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
              @click="go"
            >
              {{ this.top10[5] }}
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
              @click="go"
            >
              {{ this.top10[6] }}
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
              @click="go"
            >
              {{ this.top10[7] }}
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
              @click="go"
            >
              {{ this.top10[8] }}
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
              @click="go"
            >
              {{ this.top10[9] }}
            </div>
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
import SearchBar from "@/components/SearchBar.vue";
import { getRealtime } from "@/api/realtime.js";
import { updateSearch } from "@/api/search.js";
import { mapActions } from "vuex";
import { getSearchWithDong } from "@/api/search.js";

export default {
  name: "MAIN",
  components: {
    SearchBar,
    Header,
    Footer,
  },
  data() {
    return {
      top10: [],
    };
  },
  created() {
    getRealtime(
      (res) => {
        this.top10 = [];
        var len = res.object.length;

        // 얻어온 데이터는 top10 배열에 push하고 나머지는 -로 채운다.
        for (var i = 0; i < 10; i++) {
          if (i < len) {
            this.top10.push(res.object[i].name + " " + res.object[i].area);
          } else {
            this.top10.push("-");
          }
        }
      },
      () => {
        alert("오류가 발생했습니다!!!");
      }
    );
  },
  methods: {
    ...mapActions(["set_store"]),
    go(e) {
      var eSplit = e.target.innerText.split(" ");

      var item = {
        name: eSplit[0],
        area: eSplit[1],
      };

      updateSearch(
        item,
        () => {
          // console.log("언급량 최신화 성공!")
        },
        () => {
          alert("언급량 최신화 실패!");
          return;
        }
      );

      getSearchWithDong(
        item,
        (res) => {
          var store = res.object;
          this.set_store(store);
          this.$router.push("Analysis");
        },
        () => {
          alert("오류가 발생했습니다.");
        }
      );
    },
  },
};
</script>
