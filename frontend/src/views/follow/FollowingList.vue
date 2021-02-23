<template>
    <sequential-entrance fromBottom> 
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
                        <v-list-item-avatar style="width:45px; height:45px; margin-top:10px;margin-bottom:10px;" @click="setInfo(following.userEmail)">
                            <v-img :alt="`${following.userImage} avatar`" :src="following.userImage" ></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content @click="setInfo(following.userEmail)">
                            <!-- <router-link :to="{ name: 'YourFeed' }" style="text-decoration:none;"> -->
                                <v-list-item-title  class="fix-font-semi-big-bold-half black--text" v-text="following.userNickname" >
                                </v-list-item-title>
                            <!-- </router-link> -->
                        </v-list-item-content>
                        <v-btn @click="cancelFollowing(following.userEmail)" 
                        color="#fff1f1" small
                        elevation="0"
                        >
                        <span style="color:#B71C1C;" class="fix-font-small">언팔로우</span></v-btn>
                    </v-list-item>
                </v-list>
            </v-flex>
        </v-layout>
    </v-container>
    </sequential-entrance> 
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
        },
        cancelFollowing(userEmail) {
            this.$Axios
            .delete(`${this.$store.state.host}/v1/follow`,{
                data:{          //////// 질문 필요
                    "followingEmail": userEmail, 
                    "userEmail": this.email
                }
            })
            .then((res)=> {
                if(res.data.status === "success") {
                    window.location.reload();
                    console.log('팔로우 취소 성공');
                } else {
                    console.log('팔로우 취소 실패');
                }
            })
            .catch((error)=>{
                console.log(error);
            })
        },
        setInfo(userEmail) {
            this.$Axios
            .get(`${this.$store.state.host}/v1/users/` + userEmail)
            .then((res)=> {
                if(res.data.status === "success") {
                    console.log('유저 한명 정보 저장 성공!')
                    this.$store.commit("SETYOURINFO", res.data.data );
                    setTimeout(this.tempfunc, 2000);
                    this.$router.push('/your-feed');
                } else {
                    console.log('유저 한명 정보 저장 실패');
                }
            })
            .catch((error)=> {
                console.log(error);
            })
        },
        tempfunc() {
            console.log('시간벌기~');
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
}
.v-list-item__title.msg{
    font-weight: 400;
    font-size: 0.3rem;
    color: #757575;
}
</style>