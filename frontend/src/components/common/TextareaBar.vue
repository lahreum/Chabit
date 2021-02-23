<template>
  <div id="app">
    <v-container fluid>
      <v-textarea
        counter
        label="Text"
        :rules="rules"
        :value="value"
      ></v-textarea>
    </v-container>
  </div>
</template>

<script>
  export default {
    data: () => ({
      rules: [v => v.length <= 25 || 'Max 25 characters'],
      value: '',
    }),
  }
</script>

<style scoped>
#app{
    margin: 20px;
    width: 80%;
}
</style>