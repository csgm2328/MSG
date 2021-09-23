<template>
  <div
    class="
      flex
      items-center
      bg-blue-50
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
        bg-blue-50
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
</template>

<script>
export default {};
</script>
