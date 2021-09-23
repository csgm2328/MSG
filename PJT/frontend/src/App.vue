<template>
  <div class="flex flex-col h-screen w-auto">
    <Header />
    <router-view class="" />
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
