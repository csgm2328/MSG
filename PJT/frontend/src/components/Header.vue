<template>
  <div class="flex justify-between bg-msg-header h-16">
    <div class="flex ml-5">
      <router-link to="/" class="h-full flex items-center">
        <img class="h-3/5 w-auto flex items-center" src="@/images/logo.png" />
        <span class="font-extrabold text-3xl items-center flex">MSG</span>
      </router-link>

      <div class="hidden items-center ml-10 md:flex">
        <div
          class="
            flex
            items-center
            bg-white
            w-80
            h-4/5
            rounded-lg
            border-2 border-msg-content
            focus-within:ring-2 focus-within:ring-indigo-600
          "
        >
          <button class="h-full w-12 border-r-2 border-msg-content">
            <i class="fas fa-search text-2xl" />
          </button>
          <input
            type="text"
            class="
              py-2
              px-2
              bg-white
              w-4/5
              lg:w-5/6
              placeholder-gray-400
              text-gray-900
              appearance-none
              focus:outline-none
            "
            placeholder="맛집을 입력해주세요."
          />
        </div>
      </div>
    </div>
    <div class="flex">
      <button class="h-full mr-7 flex md:hidden items-center justify-center">
        <i class="fas fa-bars font-bold text-2xl" />
      </button>
      <router-link
        to="/"
        tag="div"
        class="
          h-full
          w-24
          mr-2
          hidden
          md:flex
          items-center
          justify-center
          font-bold
          text-base
          hover:underline
        "
      >
        로그인
      </router-link>
      <router-link
        to="/"
        tag="div"
        class="
          h-full
          w-24
          mr-7
          hidden
          md:flex
          items-center
          justify-center
          font-bold
          text-base
          hover:underline
        "
      >
        가입하기
      </router-link>
    </div>
  </div>
</template>

<script>
export default {};
</script>
