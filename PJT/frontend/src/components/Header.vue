<template>
  <div class="flex justify-between bg-blue-300 h-16">
    <div
      v-if="isOpen"
      class="fixed top-0 left-0 right-0 bottom-0 w-full h-screen z-30 bg-gray-700 opacity-75"
    ></div>
    <div class="flex items-center ml-5">
      <router-link to="/" class="h-full flex items-center">
        <img class="h-3/5 w-auto flex items-center" src="@/images/logo.png" />
        <span class="font-extrabold text-3xl items-center flex">MSG</span>
      </router-link>

      <div class="hidden ml-10 md:flex items-center">
        <Search-bar class="w-80" />
      </div>
    </div>
    <div class="flex md:hidden" v-click-away="onClickOutside">
      <SideBar
        class="
          transform
          top-0
          right-0
          w-64
          bg-white
          fixed
          h-full
          overflow-auto
          ease-in-out
          transition-all
          duration-300
          z-30
        "
        :class="isOpen ? 'translate-x-0' : 'translate-x-full'"
      />

      <button class="h-full mr-7 items-center justify-center" @click="toggleSideBar">
        <i class="fas fa-bars font-bold text-2xl" />
      </button>
    </div>
    <div class="hidden md:flex" v-if="!isLogin">
      <router-link
        to="/login"
        tag="div"
        class="
          h-full
          w-24
          mr-2
          flex
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
          flex
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
    <div class="md:flex hidden" v-if="isLogin">
      <router-link
        to="/"
        tag="div"
        class="
          h-full
          w-28
          mr-2
          flex
          items-center
          justify-center
          font-bold
          text-base
          hover:underline
        "
      >
        마이페이지
      </router-link>
      <div
        class="
          h-full
          w-28
          mr-7
          flex
          items-center
          justify-center
          font-bold
          text-base
          hover:underline
          cursor-pointer
        "
        @click="logout"
      >
        로그아웃
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { mapActions } from 'vuex';
import SearchBar from '@/components/SearchBar.vue';
import SideBar from '@/components/Header/SideBar.vue';

export default {
  name: 'HEADER',
  components: {
    SearchBar,
    SideBar,
  },
  data() {
    return {
      immediate: false,
    };
  },
  methods: {
    ...mapActions(['toggle_isLogin', 'toggle_isOpen']),
    logout() {
      this.toggle_isLogin(false);
      this.$router.push('/');
    },
    toggleSideBar() {
      this.toggle_isOpen(true);
    },
    onClickOutside() {
      if (this.isOpen) {
        this.toggle_isOpen(false);
      }
    },
  },
  computed: {
    ...mapGetters(['isLogin', 'isOpen']),
  },
  watch: {
    isOpen: {
      immediate: true,
      handler(isOpen) {
        if (process.client) {
          if (isOpen) {
            document.body.style.setProperty('overflow', 'hidden');
            document.body.classList.remove();
          } else document.body.style.removeProperty('overflow');
        }
      },
    },
    $route(to, from) {
      if (to.path != from.path) {
        this.toggle_isOpen(false);
      }
    },
  },
};
</script>
