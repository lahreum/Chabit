<template>
  <div>
    <sequential-entrance fromBottom> 
    <v-card
      class="mx-auto"
    >
      <v-img
        height="300"
        :src="item.challengeThumbnail"
        fixed
        top
      ></v-img>
    </v-card>

    <v-card
      class="mx-auto rounded-t-xl challenge-content"
    >
      <v-card-text class="challenge-detail">
        <p style="font-size: 1.4rem; font-weight: 600; margin-top: 1rem; margin-left: 0.5rem; color: black;">
          {{item.challengeName}}
        </p>
        <i class="far fa-smile" style="font-size: 1.2rem; margin-left: 0.6rem;"></i>
        <span style="font-size: 1.1rem; margin-left: 0.5rem;"><span style="font-weight: 600; color: #B71C1C;">{{item.challengeUserCount}}</span>명 참여 중</span>
        <div class="hash-tag-bundle">
          <hash-tag :content="item.hashtags.hashtags[0].hashtagName"/>
          <hash-tag :content="item.hashtags.hashtags[1].hashtagName"/>
        </div>
        <p style="margin-top: 1rem"></p>
        <hr>
        <p style="margin-top: 1rem"></p>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">챌린지 기간</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.challengeStartDate.substr(0,10)}} ~ {{item.challengeEndDate.substr(0,10)}}</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">카테고리</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.categoryName}}</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">참가비</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.challengePoint}}채빗</span>
          </v-col>
        </v-row>
        <!-- <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">보상</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>인증서 수여</span>
          </v-col>
        </v-row> -->
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">공휴일 인증</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span v-if="item.authHoliday">O</span>
            <span v-else>X</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">인증시간</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.authStarttime.substr(0,5)}} ~ {{item.authEndtime.substr(0,5)}} 인증 가능</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">인증 방법</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.authWay}}</span>
          </v-col>
        </v-row>
        <p style="margin-top: 2rem"></p>
        <hr>
        <p style="margin-top: 2rem"></p>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">챌린지 설명</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.challengeDesc}}</span>
          </v-col>
        </v-row>
        <p style="margin-top: 2rem"></p>
        <hr>
        <p style="margin-top: 2rem"></p>
        <div>
          <span style="font-size: 1.1rem; margin-left: 1.1rem; font-weight: 600;">올바른 인증 예시</span>
        </div>
        <p></p>
        <div style="display: flex; flex-direction: column; align-items: center;">
          <v-img
            class="rounded-lg"
            width="90%"
            :src="item.authExample"
          ></v-img>
        </div>
        <p></p>
        <div style="text-align: center;">
          <span class="py-1">오늘의 제스처와 함께 사진을 찍어 올려주세요.</span>
        </div>
        <p style="margin: 1rem 0 7rem 0"></p>
      </v-card-text>
    </v-card>
    </sequential-entrance> 
            <div style="margin-left: 7%;" v-if="item.challengeJoinStatus === 'NO'">
          <v-btn
            rounded
            color="red darken-4"
            dark
            width="80%"
            height="60px"
            fixed
            bottom
            @click="participate"
          >
            참여하기
          </v-btn>
        </div>
        <div style="margin-left: 2%;" v-else-if="item.challengeJoinStatus === 'JOIN'">
          <v-row>
            <v-col cols="6" class="mx-0">
              <v-btn
                rounded
                color="red darken-4"
                dark
                width="40%"
                height="40px"
                fixed
                bottom
                @click="proof(item)"
              >
                인증하기
              </v-btn>
            </v-col>
            <v-col cols="6">
              <v-btn
                rounded
                color="red darken-4"
                dark
                width="40%"
                height="40px"
                fixed
                bottom
                @click="proofList(item)"
              >
                인증목록
              </v-btn>
            </v-col>
          </v-row>
        </div>
        <div style="margin-left: 7%;" v-else-if="item.challengeJoinStatus === 'SUCCESS' && item.reviewStatus === 'YET'">
          <v-btn
            rounded
            color="red darken-4"
            dark
            width="80%"
            height="60px"
            fixed
            bottom
            @click="writeReview(item)"
          >
            리뷰 작성
          </v-btn>
        </div>
        <div style="margin-left: 7%;" v-else-if="(item.challengeJoinStatus === 'SUCCESS' && item.reviewStatus === 'DONE') || (item.challengeJoinStatus === 'FAIL')">
          <v-btn
            rounded
            color="grey lighten-1"
            class="white--text"
            width="80%"
            height="60px"
            fixed
            bottom
            disabled
          >
            종료
          </v-btn>
        </div>
  </div>
</template>

<script>
import HashTag from '../../components/common/HashTag.vue'
export default {
  components: { HashTag },
  data() {
    return {
      item: {}
    }
  },
  created() {
    const challengeId = this.$store.state.currentChallengeDetailId
    // console.log(challengeId)
    // console.log(this.$store.state.user)
    this.$Axios.get(`${this.$store.state.host}/v1/challenges/${challengeId}?userEmail=${this.$store.state.user.userEmail}`)
      .then(res => {
        console.log(res.data)
        this.item = res.data.data
      })
      .catch(err => {
        console.log(err)
      })
  },
  methods:{
    participate() {
      if(confirm("챌린지에 참여하시겠습니까?")){
        this.$Axios.get(`${this.$store.state.host}/v1/challenges/${this.item.challengeID}/${this.$store.state.user.userEmail}`)
          .then(res => {
            console.log(res.data.data)
            history.go(0);
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    proof(item) {
      const today = new Date();

      const startDates = item.challengeStartDate
      const year1 = startDates.slice(0,4)
      const month1 = startDates.slice(5,7)
      const day1 = startDates.slice(8,10)

      const endDates = item.challengeEndDate
      const year2 = endDates.slice(0,4)
      const month2 = endDates.slice(5,7)
      const day2 = endDates.slice(8,10)

      const startHour = parseInt(item.authStarttime.slice(0,2))
      const endHour = parseInt(item.authEndtime.slice(0,2))
      const startMinute = parseInt(item.authStarttime.slice(3,5))
      const endMinute = parseInt(item.authEndtime.slice(3,5))
      
      let startDate = new Date(year1, month1-1, day1, startHour, startMinute)
      let endDate = new Date(year2, month2-1, day2, endHour, endMinute)
      
      console.log(today.getTime())
      console.log(startDate.getTime())
      console.log(endDate.getTime())


      if((today.getTime() >= startDate.getTime()) && (endDate.getTime() >= today.getTime())) {
        alert("인증페이지로 이동합니다.")
        this.$store.commit("MOVETOPROOF", item.challengeID);
        this.$router.push("/proof")
      } else {
        alert("아직 인증 시간이 아닙니다.")
      }
    },
    writeReview(item) {
      this.$store.commit("MOVETOWRITEREVIEW", item.challengeID);
      this.$router.push("/write-challenge-review");
    },
    proofList(item) {
      alert("인증목록으로 이동합니다.")
      this.$store.commit("MOVETOPROOFLIST", item.challengeID);
      this.$router.push('/proof-list')
    }
  }
}
</script>

<style scoped>
.challenge-content {
  margin: -50px 0 0 0;
}

.hash-tag-bundle {
  display: flex;
  align-items: flex-start;
}

.challenge-detail {
  font-size: 1.1rem;
}
.py-1{
  font-size: 1.9vh;
  font-weight: 600;
}
</style>