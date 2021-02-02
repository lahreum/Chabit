<template>
  <button class="btn-small">
    {{text}}
  </button>
</template>

<script>
export default {
  name: "BtnSmall",
  props: ['text'],
}
</script>

<style scoped>
.btn-small {
  background-color: #942706;
  color: white;
  border-radius: 30px;
  width: 85px;
  height: 35px;
}
</style>