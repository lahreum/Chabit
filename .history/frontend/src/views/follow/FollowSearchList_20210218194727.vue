<template>
    <sequential-entrance fromBottom> 
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
                        <v-list-item-avatar style="width:45px; height:45px; margin-top:10px;margin-bottom:10px;" @click="setInfo(item.userEmail)">
                            <v-img :alt="`${item.userImage} avatar`" :src="item.userImage"/>
                        </v-list-item-avatar>
                        <v-list-item-content @click="setInfo(item.userEmail)">
                            <v-list-item-title  class="name black--text" v-text="item.userNickname" style="font-size:17px;"></v-list-item-title>
                        </v-list-item-content>
                        <v-btn v-if="!checkFollowing(item.userEmail)" @click="doFollowing(item.userEmail)"
                        color="#e1f5fe" 
                        small
                        elevation="0">
                        <span style="color:#1565c0;" class="font-size-sub-subtitle">팔로우</span></v-btn>
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
  props: ['searchWord'],
  data: () => ({
    items: [],
    followers: [],
    followings: [],
  }),
  created() {
    this.$Axios
    .get(`${this.$store.state.host}/v1/users/search/` + this.searchWord)
    .then((res) => {
      if(res.data.status == "success"){
        this.items = res.data.data;
        this.getFollowList(); // 처음 팔로우 & 팔로잉 명단 가져와 비교
      } else {
        console.log('검색 실패');
      }
    })
    .catch((error)=> {
            console.log(error);
    })
  },
  computed: {
        ...mapGetters({ email: 'getUserEmail'}),
    },
  methods: {
    getFollowList() {
    this.$Axios
        .get(`${this.$store.state.host}/v1/follow/` + this.email)
        .then((res) => {
            if(res.data.status === "success") {
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
                    this.getFollowList();
                    this.$emit('doFollowing');
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
  }
}
</script>

<style scoped>
.section {
    margin: 20px;
}
#title{
    font-size: 1.5rem;
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