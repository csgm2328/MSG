<template>
  <div class="flex justify-between bg-blue-300 h-16">
    <div class="flex ml-5">
      <router-link to="/" class="h-full flex items-center">
        <img class="h-3/5 w-auto flex items-center" src="@/images/logo.png" />
        <span class="font-extrabold text-3xl items-center flex">MSG</span>
      </router-link>

      <div class="hidden items-center ml-10 md:flex">
        <Search-bar class="w-80" />
      </div>
    </div>
    <div class="flex">
      <button class="h-full mr-7 flex md:hidden items-center justify-center">
        <i class="fas fa-bars font-bold text-2xl" />
      </button>
      <router-link
        to="/login"
        tag="div"
        class="
          h-full
          w-24
          mr-2
          hidden
          md:flex
          items-center
          justify-center
          font-bold
          text-base
          hover:underline
        "
      >
        로그인
      </router-link>
      <router-link
        to="/signup"
        tag="div"
        class="
          h-full
          w-24
          mr-7
          hidden
          md:flex
          items-center
          justify-center
          font-bold
          text-base
          hover:underline
        "
      >
        가입하기
      </router-link>
    </div>
  </div>
</template>

<script>
import SearchBar from '@/components/SearchBar.vue';
export default {
  name: 'HEADER',
  components: {
    SearchBar,
  },
};
</script>
