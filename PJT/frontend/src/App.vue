<template>
  <div id="nav bg-gray">
    <Header />
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
    <router-view />
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import Header from '@/components/Header.vue';
import Footer from '@/components/Footer.vue';

export default {
  name: 'APP',
  components: {
    Header,
    Footer,
  },
};
</script>

<style src="./assets/tailwind.css" />
