<template>
  <div class="custom-hash-tag">
    <div class="text-center hash">
      <v-chip
        class="ma-2"
        color="secondary"
        label
      >
        {{ content }}
      </v-chip>
    </div>
  </div>
</template>

<script>
  export default {
    name: "HashTag",
    props: ['content'],
  }
</script>

<style scoped>
.custom-hash-tag {
  width: fit-content;
}

.hash {
  width: fit-content;

}
</style>