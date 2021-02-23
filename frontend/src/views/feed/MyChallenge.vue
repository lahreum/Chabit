<template>
  <div>
      <!-- 상단 참가중,완료,개설 섹션 -->
      <p></p>
        <v-card flat style="width:100%;">
          <v-row>
            <v-col
              cols="4"
            > 
              <v-btn elevation="0" color="white" width="100%" @click="participate">
                <p class = "fix-font-big">{{item.ongoingChallenge.length}}</p>
              </v-btn>
              <v-btn elevation="0" color="white" width="100%" @click="participate">
                <p class = "fix-font-mid">참가중</p>
              </v-btn>
            </v-col>
            <v-col
              cols="4"
            > 
              <v-btn elevation="0" color="white" width="100%" @click="finish">
                <p class = "fix-font-big">{{item.terminatedChallenge.length}}</p>
              </v-btn>
              <v-btn elevation="0" color="white" width="100%" @click="finish">
                <p class = "fix-font-mid">완료</p>
              </v-btn>
            </v-col>
            <v-col
              cols="4"
            > 
              <v-btn elevation="0" color="white" width="100%" @click="manage">
                <p class = "fix-font-big">{{item.ownChallenge.length}}</p>
              </v-btn>
              <v-btn elevation="0" color="white" width="100%" @click="manage">
                <p class = "fix-font-mid">개설한</p>
              </v-btn>
            </v-col>
          </v-row>
        </v-card>
        <p></p>  
        <!-- 달력 -->
        <v-card flat outlined>
          <v-date-picker
            full-width
            v-model="date"
            no-title
            color="red darken-4"
          ></v-date-picker>
        </v-card>
        <p></p>
        <!-- 현재 진행중인 챌린지 목록 -->
        <TodayChallengeList :date="date" :items="items"/>
  </div>
</template>

<script>
import TodayChallengeList from './TodayChallengeList.vue';

export default {
    components: {
        TodayChallengeList
    },
    methods: {
      participate() {
        this.$store.commit('MOVETOPERSONALCHALLENGE', "참가중인")
        this.$router.push('/personal-challenge')
      },
      finish() {
        this.$store.commit('MOVETOPERSONALCHALLENGE', "완료한")
        this.$router.push('/personal-challenge')
      },
      manage() {
        this.$store.commit('MOVETOPERSONALCHALLENGE', "내가 개설한")
        this.$router.push('/personal-challenge')
      }
    },
    data() {
      return {
        item: {ongoingChallenge: [], terminatedChallenge: [], ownChallenge: []},
        date: "",
        items: []
      }
    },
    created() {
      const today = new Date();
      function leadingZeros(n, digits) {
        let zero = '';
        n = n.toString();

        if (n.length < digits) {
            for (let i = 0; i < digits - n.length; i++)
                zero += '0';
        }
        return zero + n;
      }
      let s = leadingZeros(today.getFullYear(), 4) + '-' + leadingZeros(today.getMonth() + 1, 2) + '-' + leadingZeros(today.getDate(), 2);
      this.date = s
      // console.log(this.date)

      this.$Axios.get(`${this.$store.state.host}/v1/users/${this.$store.state.user.userEmail}/challenges`)
        .then(res => {
          console.log(res.data.data)
          this.item = res.data.data

          const ongoingChallenge = res.data.data.ongoingChallenge
          const date = this.date
          const currentYear = date.slice(0,4)
          const currentMonth = date.slice(5,7)
          const currentDay = date.slice(8,10)

          const currentTime = new Date(currentYear, currentMonth-1, currentDay).getTime()

          for (let i=0; i < ongoingChallenge.length; i++) {
            let startYear = ongoingChallenge[i].challengeStartDate.slice(0,4)
            let startMonth = ongoingChallenge[i].challengeStartDate.slice(5,7)
            let startDay = ongoingChallenge[i].challengeStartDate.slice(8,10)

            let startTime = new Date(startYear, startMonth-1, startDay).getTime()
            
            let endYear = ongoingChallenge[i].challengeEndDate.slice(0,4)
            let endMonth = ongoingChallenge[i].challengeEndDate.slice(5,7)
            let endDay = ongoingChallenge[i].challengeEndDate.slice(8,10)

            let endTime = new Date(endYear, endMonth-1, endDay).getTime()
            // console.log(startTime)
            // console.log(endTime)
            if (startTime <= currentTime && currentTime <= endTime) {
              this.items.push(ongoingChallenge[i])
            }
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    watch: {
      date: function (val) {
        this.items = []
        const ongoingChallenge = this.item.ongoingChallenge
        const date = val
        const currentYear = date.slice(0,4)
        const currentMonth = date.slice(5,7)
        const currentDay = date.slice(8,10)

        const currentTime = new Date(currentYear, currentMonth-1, currentDay).getTime()

        for (let i=0; i < ongoingChallenge.length; i++) {
          let startYear = ongoingChallenge[i].challengeStartDate.slice(0,4)
          let startMonth = ongoingChallenge[i].challengeStartDate.slice(5,7)
          let startDay = ongoingChallenge[i].challengeStartDate.slice(8,10)

          let startTime = new Date(startYear, startMonth-1, startDay).getTime()
          
          let endYear = ongoingChallenge[i].challengeEndDate.slice(0,4)
          let endMonth = ongoingChallenge[i].challengeEndDate.slice(5,7)
          let endDay = ongoingChallenge[i].challengeEndDate.slice(8,10)

          let endTime = new Date(endYear, endMonth-1, endDay).getTime()
          // console.log(startTime)
          // console.log(endTime)
          if (startTime <= currentTime && currentTime <= endTime) {
            this.items.push(ongoingChallenge[i])
          }
        }
      }
    }
}
</script>

<style scoped>
.top-text {
    font-size: 20px;
    font-weight: 600;
    padding-bottom: 0;
}
.bottom-text {
    font-size: 16px;
    font-weight: 500;
    padding-top: 0;
}
.v-list-item {
    padding: 0px;
    text-align: center;
}
.v-sheet.v-card{
    border-radius: 20px;
    margin: 0 auto;
}
.challenge-name {
    font-size: 14px;
    font-weight: 500;
}
</style>