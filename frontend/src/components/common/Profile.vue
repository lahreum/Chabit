<template>
  <v-avatar>
      <img
        :src= src
        :alt= alt style="margin:0;"
      >
    </v-avatar>
</template>

<script>
export default {
  name: "Profile",
  props: ['src', 'alt'],
}
</script>

<style>

</style>