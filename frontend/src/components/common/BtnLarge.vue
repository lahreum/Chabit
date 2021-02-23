<template>
  <button class="btn-large">
    {{text}}
  </button>
</template>

<script>
export default {
  name: "BtnLarge",
  props: ['text'],
}
</script>

<style scoped>
.btn-large {
  background-color: #942706;
  color: white;
  border-radius: 30px;
  width: 215px;
  height: 40px;
}
</style>