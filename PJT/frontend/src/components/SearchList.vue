<template>
  <div
    v-if="list.length > 0"
    class="
      flex 
      flex-col
      bg-blue-50
      rounded-lg
      border-2 border-msg-content
      focus-within:ring-2 focus-within:ring-indigo-600
      z-10
    "
  >
    <div class="flex" v-for="(item, index) in list" :key="index" @click="go(item)">
        <div class="
            ml-10 
            pl-4
            flex
            h-full
            w-4/5
            border-r-2
            border-msg-content
            hover:bg-gray-300
        "
        >
            <p>{{item.name}} {{item.area}}</p>
        </div>
    </div>
  </div>
</template>

<script>
import { updateSearch } from '@/api/search.js';

export default {
  name:"SearchList",
  props:["list"],
  methods:{
    go(item){
        updateSearch(item,
        (() =>{
            // console.log("언급량 최신화 성공!")
        }),
        (() => {
            alert("언급량 최신화 실패!")
        })
        )
    }
  }
};
</script>
