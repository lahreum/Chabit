<template>
  <v-form>
    <v-container>
        <!-- <v-icon>fas fa-search</v-icon>  -->
      <v-text-field
            solo
            label="검색"
            append-icon="fas fa-search"
          ></v-text-field>
    </v-container>
  </v-form>
</template>

<script>
export default {

}
</script>

<style>
.searchBtn {
  color: gray;
  vertical-align: middle;
  /* float: right; */
}
</style>