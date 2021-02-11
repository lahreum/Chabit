<template>
  <v-badge
    avatar
    bottom
    color="none"
    offset-x="30"
    offset-y="30"> 
    <template v-slot:badge>
      <v-avatar>
        <v-img :src="userLevelImage" />
      </v-avatar>
    </template>

    <v-avatar size="60">
        <img
          :src= src
          :alt= alt style="margin:0 auto;"
        >
      </v-avatar>
    </v-badge>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: "Profile",
  props: ['src', 'alt'],
  computed: {
      ...mapGetters({userLevelImage: 'getUserLevelImage'}),
    },
}
</script>

<style>

</style>