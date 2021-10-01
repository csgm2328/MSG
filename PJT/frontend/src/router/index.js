import { createRouter, createWebHistory } from 'vue-router';
// import Home from '../views/Home.vue';

const routes = [
  {
    path: '/',
    name: 'Main',
    component: () => import('@/components/Main.vue'),
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/components/Login.vue'),
  },
  {
    path: '/signup',
    name: 'Signup',
    component: () => import('@/components/Signup.vue'),
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
  },
  {
    path: '/review',
    name: 'Review',
    component: () => import( '@/components/review/Review.vue'),
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: () => import('@/components/Mypage.vue'),
  },
  {
    path: '/analysis',
    name: 'Analysis',
    component: () => import('@/components/analysis/Analysis.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  duplicateNavigationPolicy: 'ignore',
});

export default router;
