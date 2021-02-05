<template>
  <router-link to="/create-challenge">
    <button class="btn-create-challenge">
      <i class="fas fa-plus"></i>  
    </button>
  </router-link>
</template>

<script>
export default {

}
</script>

<style scoped>
.btn-create-challenge {
  background-color: #942706;
  border-radius: 30px;
  border: 2px solid white;
  width: 60px;
  height: 60px;
}

.btn-create-challenge > .fa-plus {
  color: white;
  font-size: 40px;
}
</style>