<template>
  <div id="app">
    <v-text-field
      label="input"
      :rules="rules"
      hide-details="auto"
    ></v-text-field>
  </div>
</template>

<script>
  export default {
    data: () => ({
      rules: [
        value => !!value || '필수값 입니다.',
        value => (value && value.length >= 3) || '최소 3글자 이상 입력해 주세요.',
      ],
    }),
  }
</script>

<style scoped>
#app{
    margin: 30px;
    width: 80%;
}
</style>