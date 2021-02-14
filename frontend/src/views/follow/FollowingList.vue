<template>
    <v-container>
        <v-layout>
            <v-flex class="section">
                <v-list subheader>
                    <v-subheader>
                        <div class="text-darken-1 mb-2 font-weight-black black--text" id="title">
                            <span>팔로잉</span>
                        </div>
                    </v-subheader>
                    <v-list-item v-for="following in this.followings" :key="following.userEmail">
                        <v-list-item-avatar>
                            <v-img :alt="`${following.userImage} avatar`" :src="following.userImage"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title  class="name black--text" v-text="following.userNickname"></v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
    data: () => ({
      followings: [],
    }),
    created() {
      this.getFollowingList();
    },
    methods: {
      getFollowingList() {
        this.$Axios
          .get(`${this.$store.state.host}/v1/follow/` + this.email)
          .then((res) => {
              if(res.data.status === "success") {
                  console.log("팔로잉 리스트 잘 넘어옴");
                  this.followings = res.data.data.followings.followings;
              } else {
                console.log("팔로잉 리스트 받아오기 실패");
              }
          })
          .catch((error)=> {
              console.log(error);
          })
      }
    },
    computed: {
    ...mapGetters({ email: 'getUserEmail'}),
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