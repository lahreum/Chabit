<template>
<v-app>
  <v-container fluid>
    <p>{{ selected }}</p>
    <v-checkbox
      v-model="selected"
      :label=label
      :value= value
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
        name: "Checkbox",
        selected: [],
        props: ['label', 'value'],
      }
    },
  }
</script>

<style>

</style>