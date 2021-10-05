<template>
  <div>
    <router-view />
    <Loading v-if="isLoading" />
  </div>
</template>

<script>
import Loading from '@/components/Loading.vue';
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'APP',
  components: {
    Loading,
  },
  created(){
    this.set_analysisType(1);
  },
  computed: {
    ...mapGetters(['isLoading']),
  },
  methods:{
    ...mapActions(['set_analysisType']),
  }
};
</script>

<style src="./assets/tailwind.css" />
