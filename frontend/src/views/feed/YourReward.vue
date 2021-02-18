<template>
  <div>
    <!-- 상단 금,은,동 계기판 -->
      <v-card>
        <v-list-item>
            <div class="top-text" style="width:90%; margin:0 auto;text-align:center;">
                <div class = "fix-font-big" style="float:left;width:30%;">{{ badges.userGetSilver}}</div>
                <div class = "fix-font-big" style="float:left;margin-left:5%;width:30%;">{{ badges.userGetGold}}</div>
                <div class = "fix-font-big" style="float:right;width:30%;">{{ badges.userGetBronze}}</div>
            </div>
        </v-list-item>
        <v-list-item>
            <div style="width:90%; margin:0 auto; text-align:center;">
                <div style="float:left;width:30%;"><img src="https://ifh.cc/g/rUZBvq.jpg" style="width:50px;"></div>
                <div style="float:left;margin-left:5%;width:30%;"><img  src="https://ifh.cc/g/wd7WQg.jpg" style="width:50px;"></div>
                <div style="float:right;width:30%;"><img  src="https://ifh.cc/g/pNp80t.jpg" style="width:50px;"></div>
            </div>
        </v-list-item>
      </v-card>
      <!-- 뱃지 리스트 -->
      <div>
        <div style="width:100%; margin-top:30px; text-align:center;">
          <v-row no-gutters>
            <v-col
              v-for="badge in bestBadges"
              :key="badge.badgeId"
              cols="4"
            >
              <div class="text-center">
                <v-dialog v-model="dialog" persistent :retain-focus="false">
                  <template v-slot:activator="{ on, attrs }">
                    <div v-bind="attrs" v-on="on"><img :src="badge.badgeImage" @click="clickModal(badge)"></div>
                  </template>
                  
                  <!-- 뱃지 모음을 보여주는 모달 팝업 -->
                  <v-card>
                    <v-card-title>
                      <span>{{ categoryBadges[0].badgeCategory }}</span>
                    </v-card-title>
                    <v-card-text class="center" style="display:flex;  padding :0px;">
                      <div v-for="oneBadge in categoryBadges" :key="oneBadge.badgeId" >
                              <div style="padding:10px;">
                              <img class ="bedgeImage" :src="oneBadge.badgeImage"/>
                              <p class = "fix-font-small">
                                {{ oneBadge.badgeName }}
                              </p>
                              </div>
                      </div>
                    </v-card-text>
                    <v-divider></v-divider>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <div>
                      <v-btn
                      color="primary"
                        text
                        @click="dialog = false"
                      >
                        확인
                      </v-btn></div>
                    </v-card-actions>
                  </v-card>
                </v-dialog>

                <div><p class = "fix-font-small">{{badge.badgeName}}</p></div>
              </div>
            </v-col>

          </v-row> 
        </div>
      </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  data: () => ({
    badges: {
      userGetBronze: 0,
      userGetSilver: 0,
      userGetGold: 0,
      badge: {
      }
    },
    bestBadges: [

    ],   
    dialog: false,
    category: "",
    // 라이프 사이클 돌 때 데이터가 마운트 되기 전에 템플릿이 먼저 created 되서 그런 것 같습니다!
    categoryBadges: [     // 여기까진 잡을 수 있는데, badgeCategory는 아예 정의가 안되어 있어 못잡음.각 요소 정의필요
      {
        badgeId: '',
        badgeName: '',
        badgeType: '',
        badgeCategory: '',
        userGet: '',
        badgeImage: ''
      }
    ],
  }),
  created(){
    this.getBadge();
  },
  computed: {
    ...mapGetters({
      email: 'getYourEmail'
    })
  },
  methods: {
    clickModal(badge) {
      this.category = badge.badgeCategory;
      this.getCategoryBadges(this.category);
    },
    getBadge() {
      this.$Axios
      .get(`${this.$store.state.host}/v1/users/` + this.email)
      .then((res) => {
        if(res.data.status === "success") {
          this.badges = res.data.data.badges;
          this.getBestBadge();
        } else {
          console.log('뱃지 가져오기 실패');
        }
      })
      .catch((error)=> {
        console.log(error);
      })
      
    },
    getBestBadge() {
        // 독서 카테고리에서의 최고뱃지
        let temp1 = this.badges.badge.독서[0];  // 첫 브론즈 뱃지로 초기화(미획득이라면 미획득 이미지)
        for(let j=0; j<3; j++) {
          if(this.badges.badge.독서[j].userGet) {   // 유저가 가지고 있는 뱃지에 대해서만 
            // 더 큰 뱃지를 temp에 넣고
            temp1 = this.badges.badge.독서[j];
          } else {
            this.badges.badge.독서[j].badgeName = "뱃지 없음";
          }
        }
        this.bestBadges.push(temp1);  // 최종 temp를 bestBadges 배열에 push
        
        // 생활습관 카테고리에서의 최고뱃지
        let temp2 = this.badges.badge.생활습관[0];  // 첫 브론즈 뱃지로 초기화(미획득이라면 미획득 이미지)
        for(let j=0; j<3; j++) {
          if(this.badges.badge.생활습관[j].userGet) {   // 유저가 가지고 있는 뱃지에 대해서만 
            // 더 큰 뱃지를 temp에 넣고
            temp2 = this.badges.badge.생활습관[j];
          } else {
            this.badges.badge.생활습관[j].badgeName = "뱃지 없음";
          }
        }
        this.bestBadges.push(temp2);   // 최종 temp를 bestBadges 배열에 push
        
        // 다이어트 카테고리에서의 최고뱃지
        let temp3 = this.badges.badge.다이어트[0];  // 첫 브론즈 뱃지로 초기화(미획득이라면 미획득 이미지)
        for(let j=0; j<3; j++) {
          if(this.badges.badge.다이어트[j].userGet) {   // 유저가 가지고 있는 뱃지에 대해서만 
            // 더 큰 뱃지를 temp에 넣고
            temp3 = this.badges.badge.다이어트[j];
          } else {
            this.badges.badge.다이어트[j].badgeName = "뱃지 없음";
          }
        }
        this.bestBadges.push(temp3);  // 최종 temp를 bestBadges 배열에 push

        // 취미 카테고리에서의 최고뱃지
        let temp4 = this.badges.badge.취미[0];  // 첫 브론즈 뱃지로 초기화(미획득이라면 미획득 이미지)
        for(let j=0; j<3; j++) {
          if(this.badges.badge.취미[j].userGet) {   // 유저가 가지고 있는 뱃지에 대해서만 
            // 더 큰 뱃지를 temp에 넣고
            temp4 = this.badges.badge.취미[j];
          } else {
            this.badges.badge.취미[j].badgeName = "뱃지 없음";
          }
        }
        this.bestBadges.push(temp4);    // 최종 temp를 bestBadges 배열에 push

        // 감정관리 카테고리에서의 최고뱃지
        let temp5 = this.badges.badge.감정관리[0];  // 첫 브론즈 뱃지로 초기화(미획득이라면 미획득 이미지)
        for(let j=0; j<3; j++) {
          if(this.badges.badge.감정관리[j].userGet) {   // 유저가 가지고 있는 뱃지에 대해서만 
            // 더 큰 뱃지를 temp에 넣고
            temp5 = this.badges.badge.감정관리[j];
          } else {
            this.badges.badge.감정관리[j].badgeName = "뱃지 없음";
          }
        }
        this.bestBadges.push(temp5);  // 최종 temp를 bestBadges 배열에 push

        // 돈관리 카테고리에서의 최고뱃지
        let temp6 = this.badges.badge.돈관리[0];  // 첫 브론즈 뱃지로 초기화(미획득이라면 미획득 이미지)
        for(let j=0; j<3; j++) {
          if(this.badges.badge.돈관리[j].userGet) {   // 유저가 가지고 있는 뱃지에 대해서만 
            // 더 큰 뱃지를 temp에 넣고
            temp6 = this.badges.badge.돈관리[j];
          } else {
            this.badges.badge.돈관리[j].badgeName = "뱃지 없음";
          }
        }
        this.bestBadges.push(temp6);  // 최종 temp를 bestBadges 배열에 push
        
        // 운동 카테고리에서의 최고뱃지
        let temp7 = this.badges.badge.운동[0];  // 첫 브론즈 뱃지로 초기화(미획득이라면 미획득 이미지)
        for(let j=0; j<3; j++) {
          if(this.badges.badge.운동[j].userGet) {   // 유저가 가지고 있는 뱃지에 대해서만 
            // 더 큰 뱃지를 temp에 넣고
            temp7 = this.badges.badge.운동[j];
          } else {
            this.badges.badge.운동[j].badgeName = "뱃지 없음";
          }
        }
        this.bestBadges.push(temp7);  // 최종 temp를 bestBadges 배열에 push

        // 공부 카테고리에서의 최고뱃지
        let temp8 = this.badges.badge.공부[0];  // 첫 브론즈 뱃지로 초기화(미획득이라면 미획득 이미지)
        for(let j=0; j<3; j++) {
          if(this.badges.badge.공부[j].userGet) {   // 유저가 가지고 있는 뱃지에 대해서만 
            // 더 큰 뱃지를 temp에 넣고
            temp8 = this.badges.badge.공부[j];
          } else {
            this.badges.badge.공부[j].badgeName = "뱃지 없음";
          }
        }
        this.bestBadges.push(temp8);  // 최종 temp를 bestBadges 배열에 push

        // 돌봄 카테고리에서의 최고뱃지
        let temp9 = this.badges.badge.돌봄[0];  // 첫 브론즈 뱃지로 초기화(미획득이라면 미획득 이미지)
        for(let j=0; j<3; j++) {
          if(this.badges.badge.돌봄[j].userGet) {   // 유저가 가지고 있는 뱃지에 대해서만 
            // 더 큰 뱃지를 temp에 넣고
            temp9 = this.badges.badge.돌봄[j];
          } else {
            this.badges.badge.돌봄[j].badgeName = "뱃지 없음";
          }
        }
        this.bestBadges.push(temp9);  // 최종 temp를 bestBadges 배열에 push
    },
    getCategoryBadges(aaa) {
      const categorys = this.badges.badge;

      for(var bbb in categorys) {
        if(bbb === aaa) {
          this.categoryBadges = this.badges.badge[bbb];
        }
      }
    }
  }
}
</script>

<style>
.top-text {
    font-size: 20px;
    font-weight: 600;
    padding-bottom: 0;
}
div > .bedgeImage{
  width: 50px;
  height: 50px;
  margin: 0%;
}
img{
  margin: 0%;
}
</style>