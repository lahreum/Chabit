<template>
  <button class="btn-tiny">
    {{text}}
  </button>
</template>

<script>
export default {
  name: "BtnTiny",
  props: ['text'],
}
</script>

<style scoped>
.btn-tiny {
  background-color: #942706;
  color: white;
  border-radius: 8px;
  width: 70px;
  height: 25px;
}
</style>