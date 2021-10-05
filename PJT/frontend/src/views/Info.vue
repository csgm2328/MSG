<template>
  <div class="w-11/12">
    <div class="flex w-full justify-start">
    </div>
    <div class="flex w-full h-80 mt-2 mb-2 justify-start">
      <div
        id="googleMap"
        class="
          w-3/4
          h-80
          bg-white
          border-2 border-blue-500 border-opacity-50
          rounded-lg
          pt-2
          mr-2
          flex
          justify-center
        "
        ref="inbox"
      >
      <!-- <GmapMap id="map"
        :center="center"
        :zoom="zoom"
        map-type-id="roadmap">
      </GmapMap> -->
      <GoogleMap
        style="width: 100%; height: 100%"
        :center="center"
        :zoom="10"
        >
      </GoogleMap>
      </div>
      <div
        class="
          w-2/5
          h-80
          bg-white
          border-2 border-blue-500 border-opacity-50
          rounded-lg
          pt-2
          flex
          justify-center
        "
      >
        맛집 정보
      </div>
    </div>
    <!-- 차트 -->
    <chart />
  </div>
</template>
<script>
import Chart from "@/components/Analysis/Chart.vue";
import { GoogleMap } from 'vue3-google-map'

export default {
  name: "info",
  components:{
    Chart,
    GoogleMap,
  },
  data: function(){
    return {
      center: {
        lat: 37.5343722,
        lng: 126.9688505,
      },
      infoContent: '',
      infoWindowPos: {lat: 0, lng: 0},
      infoWindowOption: {pixelOffset: {width: 0, height: -35}},
      infoWinOpen: false,
      currentMidx: null,
    }
  }
};
</script>
<style>
  
</style>