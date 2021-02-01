<template>
<v-app>
  <v-container fluid>
    <p>{{ selected }}</p>
    <v-checkbox
      v-model="selected"
      label="A"
      value="A"
      color="grey darken-3"
      hide-details
    ></v-checkbox>
  </v-container>
</v-app>
</template>

<script>
  export default {
    data () {
      return {
        selected: [],
      }
    },
  }
</script>

<style>

</style>