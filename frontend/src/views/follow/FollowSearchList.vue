<template>
    <v-container>
        <v-layout>
            <v-flex class="section">
                <v-list subheader>
                    <v-subheader>
                        <div class="text-darken-1 mb-2 font-weight-black black--text" id="title">
                            <span>  '{{ this.searchWord }}' 검색결과</span>
                        </div>
                    </v-subheader>
                    <v-list-item v-for="item in this.items" :key="item.userNickname">
                        <v-list-item-avatar>
                            <v-img :alt="`${item.userImage} avatar`" :src="item.userImage"/>
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title  class="name black--text" v-text="item.userNickname"></v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>

export default {
  props: ['searchWord'],
  data: () => ({
    items: [],
  }),
  created() {
    this.$Axios
    .get(`${this.$store.state.host}/v1/users/search/` + this.searchWord)
    .then((res) => {
      if(res.data.status == "success"){
        this.items = res.data.data;
      } else {
        console.log('검색 실패');
      }
    })
    .catch((error)=> {
            console.log(error);
        })
  },
}
</script>

<style scoped>
.section {
    margin: 20px;
}
#title{
    font-size: 1.5rem;
    margin-top: 30px;
}
.v-subheader{
    margin-bottom: 30px;
}
.name{
    font-weight: 500;
    font-size: 1rem;
}
.v-list-item__title.msg{
    font-weight: 400;
    font-size: 0.3rem;
    color: #757575;
}
</style>