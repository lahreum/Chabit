<template>
<v-app>
  <div class="text-center">
    <v-chip
      class="ma-2"
      color="secondary"
      label="false"
    >
      {{ content }}
    </v-chip>
  </div>
  </v-app>
</template>

<script>
  export default {
    name: "HashTag",
    props: ['content'],
  }
</script>

<style>

</style>