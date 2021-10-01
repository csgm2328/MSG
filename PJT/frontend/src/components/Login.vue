<template>
  <div>
    <div class="h-20 mb-12 mx-auto">
      <img class="h-full w-auto" src="@/images/banner.png" />
    </div>
    <img class="hover: cursor-pointer" @click="kakaoLogin" src="@/images/kakaoLogin.png" />
    <Loading v-if="loading" />
  </div>
</template>

<script>
import { loginUser } from '@/api/auth.js';
import { mapActions } from 'vuex';
import Loading from '@/components/Loading.vue';

export default {
  name: 'Login',
  components: { Loading },
  data: () => {
    return {
      code: '',
      loading: false,
    };
  },
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
      this.login();
    }
  },
  methods: {
    ...mapActions(['toggle_isLogin']),
    kakaoLogin() {
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
    login() {
      setTimeout(() => {
        loginUser(
          this.code,
          (res) => {
            this.loading = false;
            if (res.object.member) {
              this.toggle_isLogin(true);
              this.$router.push('/');
            } else {
              alert('회원가입이 필요합니다. \n회원가입 페이지로 이동합니다.');
              this.$router.push('/signup');
            }
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
