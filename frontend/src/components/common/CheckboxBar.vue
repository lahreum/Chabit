<template>
<v-app>
  <v-container fluid>
    <p>{{ selected }}</p>
    <v-checkbox
      v-model="selected"
      label="A"
      value="A"
      color="dark red"
      hide-details
    ></v-checkbox>
    <v-checkbox
      v-model="selected"
      label="B"
      value="B"
      color="red"
      hide-details
    ></v-checkbox>
  </v-container>
</v-app>
</template>

<script>
  export default {
    data () {
      return {
        selected: ['A'],
      }
    },
  }
</script>

<style>

</style>