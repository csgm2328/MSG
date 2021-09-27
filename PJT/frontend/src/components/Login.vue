<template>
  <div class="">
    <div class="h-20 mb-12 mx-auto">
      <img class="h-full w-auto" src="@/images/banner.png" />
    </div>
    <img class="hover: cursor-pointer" @click="login" src="@/images/kakaoLogin.png" />
    <div
      v-if="loading"
      wire:loading
      class="
        fixed
        top-0
        left-0
        right-0
        bottom-0
        w-full
        h-screen
        z-50
        overflow-hidden
        bg-gray-700
        opacity-75
        flex flex-col
        items-center
        justify-center
      "
    >
      <div
        class="
          loader
          ease-linear
          rounded-full
          opacity-100
          border-4 border-t-4 border-gray-200
          h-12
          w-12
          mb-4
        "
      ></div>
      <h2 class="text-center text-white text-xl font-semibold opacity-100">Loading...</h2>
      <p class="w-1/3 text-center text-white opacity-100">잠시만 기다려주세요.</p>
    </div>
  </div>
</template>

<script>
import { loginUser } from '@/api/auth.js';
import { mapActions } from 'vuex';

export default {
  name: 'Login',
  data: () => {
    return {
      code: '',
      loading: false,
    };
  },
  components: {},
  created() {
    this.code = this.$route.query.code;

    if (!this.code) {
      this.loading = false;
    } else {
      this.loading = true;
    }
  },
  mounted() {
    if (this.code) {
      this.kakaoLogin();
    }
  },
  methods: {
    ...mapActions(['toggle_isLogin']),
    login() {
      const CLIENT_ID = process.env.VUE_APP_KAKAO_ID;
      const REDIRECT_URI = process.env.VUE_APP_LOGIN_KAKAO_URI;

      window.location.replace(
        'https://kauth.kakao.com/oauth/authorize?client_id=' +
          CLIENT_ID +
          '&redirect_uri=' +
          REDIRECT_URI +
          '&response_type=code'
      );
    },
    kakaoLogin() {
      setTimeout(() => {
        loginUser(
          this.code,
          (res) => {
            this.loading = false;
            // 삭제
            console.log(res);
            this.toggle_isLogin(true);
            this.$router.push('/');
          },
          (error) => {
            alert('문제가 발생했습니다. 다시 시도해주세요.');
            console.log(error);
            this.$router.push('/');
          }
        );
      }, 1000);
    },
  },
};
</script>
