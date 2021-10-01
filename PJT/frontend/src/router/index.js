import { createRouter, createWebHistory } from 'vue-router';
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
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: () => import('@/views/Mypage.vue'),
  },
  {
    path: '/keyword',
    name: 'Keyword',
    component: () => import('@/views/Keyword.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  duplicateNavigationPolicy: 'ignore',
});

export default router;
