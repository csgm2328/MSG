<template>
  <div>
    <div class="flex flex-col">
      <router-link to="/" class="h-full flex justify-center items-center p-5 border-b-2">
        <img class="h-10 w-auto flex justify-items-center" src="@/images/logo.png" />
        <span class="font-extrabold text-3xl items-center flex">MSG</span>
      </router-link>
      <div v-if="!isLogin">
        <router-link
          to="/login"
          tag="div"
          class="
            flex
            items-center
            justify-center
            h-full
            w-full
            font-bold
            text-base
            hover:underline hover:bg-gray-300
            p-5
          "
        >
          <p>로그인</p>
        </router-link>
        <router-link
          to="/signup"
          tag="div"
          class="
            h-full
            w-dull
            flex
            items-center
            justify-center
            font-bold
            text-base
            p-5
            hover:underline hover:bg-gray-300
          "
        >
          <p>가입하기</p>
        </router-link>
      </div>
    </div>
    <div class="" v-if="isLogin">
      <div class="h-full w-full items-center justify-center">
        <button
          class="h-full w-full font-bold text-base hover:underline hover:bg-gray-300 p-5"
          @click="logout"
        >
          로그아웃
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { mapActions } from 'vuex';

export default {
  name: 'SIDEBAR',
  methods: {
    ...mapActions(['toggle_isLoginToSideBar', 'toggle_isOpen']),
    logout() {
      this.toggle_isOpen(false);
      this.toggle_isLoginToSideBar(false);
      this.$router.push('/');
    },
  },
  computed: {
    ...mapGetters(['isLogin', 'isOpen']),
  },
};
</script>
