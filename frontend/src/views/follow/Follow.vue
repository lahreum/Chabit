<template>
    <v-app>
        <v-container fluid>
        <v-layout column>
            <!-- searchBar -->
            <v-flex class="section">
                <div class="searchbar" style="margin:0 auto">
                    <v-container style="width:85%;">
                        <v-text-field
                                clearable
                                placeholder="유저 검색"
                                append-icon="fas fa-search"
                                v-on:keyup.enter="search"
                                v-model="searchItem" 
                        ></v-text-field>
                    </v-container>
                </div>
            </v-flex>
            <!-- 팔로우, 팔로잉 -->
            <v-flex class="section">
                <v-card elevation="2" class="mx-auto rounded-lg" max-width="80%" outlined>
                    <v-list-item three-line>
                        <v-list-item-content>
                            <v-list-item-title class="headline mb-1 text-center">
                            <strong style="margin-right:15px;"><router-link :to="{ name: 'FollowList' }">{{ followerCnt }}</router-link></strong>
                            <strong style="margin-left:15px;"><router-link :to="{ name: 'FollowingList' }">{{ followingCnt }}</router-link></strong>
                            </v-list-item-title>
                            <v-list-item-subtitle class="mb-1 text-center">
                                <span style="margin:10px;">팔로워</span>
                                <span style="margin:10px;">팔로잉</span>
                            </v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                </v-card>
            </v-flex>
        </v-layout>
      </v-container>
            <!-- 검색 안할때에는 팔로워, 팔로잉 보여줌 -->
            <div v-if="!searchItem">
                <router-view/>
            </div>
            <!-- 검색 할때에는 검색내역 보여줌 -->
            <div v-else>
                <follow-search-list v-if="isSearch" :searchWord="searchItem"/>
            </div>
    </v-app>
</template>

<script>
import { mapGetters } from 'vuex'
import FollowSearchList from './FollowSearchList.vue';

export default {
    components: {
        FollowSearchList
    },   
    data() {
        return {
            followerCnt: 0,
            followingCnt: 0,
            isSearch: false,
            searchItem: '',
        }
    },
    computed: {
    ...mapGetters({ email: 'getUserEmail'}),
  },
  methods: {
      getFollowCnt() {
          this.$Axios
          .get(`${this.$store.state.host}/v1/follow/` + this.email)
          .then((res) => {
              if(res.data.status === "success") {
                  console.log("팔로우 카운트 잘 넘어옴");
                  this.followerCnt = res.data.data.followers.followerCnt;
                  this.followingCnt = res.data.data.followings.followingCnt;
              }
          })
          .catch((error)=> {
              console.log(error);
          })
      },
      search: function() {
      if(this.searchItem !== ''){   // 무언가 입력되었을때
        this.isSearch = true
      }
    },
  },
  watch: {
    searchItem: function () {
      if (this.searchItem === '') {
        this.isSearch = false
      }
    }
  },
  created() {
      this.getFollowCnt();
  }
};
</script>
