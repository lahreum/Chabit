<template>
  <button class="btn-medium">
    {{text}}
  </button>
</template>

<script>
export default {
  name: "BtnMedium",
  props: ['text'],
}
</script>

<style scoped>
.btn-medium {
  background-color: #942706;
  color: white;
  border-radius: 30px;
  width: 170px;
  height: 40px;
}
</style>