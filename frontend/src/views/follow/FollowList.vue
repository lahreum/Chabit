<template>
    <v-container>
        <v-layout>
            <v-flex class="section">
                <v-list subheader>
                    <v-subheader>
                        <div class="text-darken-1 mb-2 font-weight-black black--text" id="title">
                            <span>팔로워</span>
                        </div>
                    </v-subheader>
                    <v-list-item v-for="follower in followers" :key="follower.userEmail">
                        <v-list-item-avatar>
                            <v-img :alt="`${follower.userImage} avatar`" :src="follower.userImage"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title  class="name black--text" v-text="follower.userNickname"></v-list-item-title>
                        </v-list-item-content>
                        <v-btn v-if="!checkFollowing(follower.userEmail)" @click="doFollowing(follower.userEmail)">팔로우 </v-btn>
                    </v-list-item>
                </v-list>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
    data() {
        return {
        followers: [],
        followings: [],
        // bothFollowing: false,
        }
    },
    created() {
        this.getFollowList();
    },
    methods: {
        getFollowList() {
        this.$Axios
            .get(`${this.$store.state.host}/v1/follow/` + this.email)
            .then((res) => {
                if(res.data.status === "success") {
                    console.log("팔로워 리스트 잘 넘어옴");
                    this.followers = res.data.data.followers.followers;
                    this.followings = res.data.data.followings.followings;
                    //   console.log("followings = " + this.followings);
                } else {
                    console.log("팔로워 리스트 받아오기 실패");
                }
            })
            .catch((error)=> {
                console.log(error);
            })
        },
        doFollowing(userEmail) {
            console.log(userEmail);
            console.log(this.email);
            this.$Axios
            .post(`${this.$store.state.host}/v1/follow`,
                {
                    "followingEmail": userEmail,
                    "userEmail": this.email
                }
            )
            .then((res)=> {
                if(res.data.status === "success") {
                    // window.location.reload();
                    console.log('팔로잉 성공');
                } else {
                    console.log('팔로잉 실패');
                }
            })
            .catch((error) => {
                console.log(error);
            })
        },
        checkFollowing(userEmail) {
            let bothFollowing = false;     // 다른 사람 검사마다 초기화

            this.followings.forEach(function(element){
                if(element.userEmail === userEmail) {
                    bothFollowing = true;
                }
            })
            return bothFollowing;
        },
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