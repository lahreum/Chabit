<template>
  <div class="top-navbar">
    <span>{{text}}</span>
  </div>
</template>

<script>
export default {
  name: "TopNavbar",
  props: ['text'],
}
</script>

<style scoped>
.top-navbar {
  background-color:white;
  color: #942706;
  height: 70px;
  width: 100%;
  position: fixed;
  z-index: 1;
  border-radius: 0 0 20px 20px / 0 0 20px 20px;
  box-shadow: 1px 1px 8px gray;;
}

.top-personal-challenge-type > span {
  position: fixed;
  top: 20px;
  left: 50%;
  transform: translate(-50%, 0);
}
</style>