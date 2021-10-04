import { createRouter, createWebHistory } from 'vue-router';
import store from '@/store/index';
import { logout } from '@/common/logout.js';
import { reissuUser } from '@/api/auth.js';
// import Home from '../views/Home.vue';

const routes = [
  {
    path: '/',
    alias: ['/main'],
    name: 'Main',
    component: () => import('@/views/Main.vue'),
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
  },
  {
    path: '/signup',
    name: 'Signup',
    component: () => import('@/views/Signup.vue'),
  },
  {
    path: '/review',
    name: 'Review',
    component: () => import('@/views/Review.vue'),
    meta: { auth: true },
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: () => import('@/views/Mypage.vue'),
    meta: { auth: true },
  },
  {
    path: '/analysis',
    name: 'Analysis',
    component: () => import('@/views/Analysis.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  duplicateNavigationPolicy: 'ignore',
});

router.beforeEach((to, from, next) => {
  if (to.meta.auth) {
    if (!store.getters.isLogin) {
      alert('로그인이 필요한 서비스입니다.');
      next('/login');
      return;
    }
  }

  if (store.getters.isLogin && !store.getters.type) {
    // 재발급 요청
    reissuUser(
      store.getters.id,
      (res) => {
        // 재발급 요청에 성공할 경우
        if (res.object) {
          console.log('토큰 재발급 성공');
        } else {
          // 재발급 요청에 실패했을 경우
          alert('로그인 유효기간이 만료되었습니다. \n다시 로그인해주세요.');
          store.dispatch('set_type', 'logout');
          store.dispatch('toggle_isLoading', true);
          logout(store.getters.id);
        }
      },
      (error) => {
        alert('문제가 발생했습니다. 다시 시도해주세요.');
        console.log(error);
        router.push('Main');
      }
    );
  }

  next();
});

export default router;
