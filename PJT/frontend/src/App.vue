<template>
  <div id="nav bg-gray">
    <Header />
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </div>

  <router-view />
</template>

<script>
// @ is an alias to /src
import Header from '@/components/Header.vue';

export default {
  name: 'APP',
  components: {
    Header,
  },
};
</script>

<style src="./assets/tailwind.css" />
