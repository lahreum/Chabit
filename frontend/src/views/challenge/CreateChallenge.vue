<template>
  <sequential-entrance fromBottom> 
  <v-container>
    <div class="create-challenge">
      <span>챌린지 생성</span>
    </div>
    <p></p>
    <v-stepper
      v-model="e6"
    >
      <v-stepper-header>
        <v-stepper-step
          :complete="e6 > 1"
          step="1"
          color="red darken-4"
        >
          <span>카테고리</span>
          <span style="font-size:0.5rem; margin-top: 0.3rem; color: grey;">원하는 카테고리가 무엇인가요?</span>
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          :complete="e6 > 2"
          step="2"
          color="red darken-4"
        >
          <span>챌린지 소개</span>
          <span style="font-size:0.5rem; margin-top: 0.3rem; color: grey;">챌린지를 소개해주세요!</span>
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          :complete="e6 > 3"
          step="3"
          color="red darken-4"
        >
          <span>인증 방식</span>
          <span style="font-size:0.5rem; margin-top: 0.3rem; color: grey;">인증 방식을 지정해주세요!</span>
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          :complete="e6 > 4" 
          step="4"
          color="red darken-4"
        >
          <span>챌린지 기간</span>
          <span style="font-size:0.5rem; margin-top: 0.3rem; color: grey;">챌린지 기간을 지정해주세요!</span>
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          :complete="e6 > 5"
          step="5"
          color="red darken-4"
        >
          <span>대표 이미지</span>
          <span style="font-size:0.5rem; margin-top: 0.3rem; color: grey;">챌린지를 잘 나타내는 이미지를 올려주세요!</span>
        </v-stepper-step>

      </v-stepper-header>

      <v-stepper-items>

        <v-stepper-content step="1">
          <v-card
            flat
            class="mb-12"
            height="275"
          >
            <!-- <v-container> -->
              <v-row>
                <v-col
                  cols="4"
                >
                  <v-btn
                    depressed
                    width="100%"
                    height="70"
                    ripple
                    v-ripple="{class: 'error--text'}"
                    @click="selected.categoryName = '운동'"
                  >
                    운동
                  </v-btn>
                </v-col>
                <v-col
                  cols="4"
                >
                  <v-btn
                    depressed
                    width="100%"
                    height="70"
                    ripple
                    v-ripple="{class: 'error--text'}"
                    @click="selected.categoryName = '공부'"
                  >
                    공부
                  </v-btn>
                </v-col>
                <v-col
                  cols="4"
                >
                  <v-btn
                    depressed
                    width="100%"
                    height="70"
                    ripple
                    v-ripple="{class: 'error--text'}"
                    @click="selected.categoryName = '돌봄'"
                  >
                    돌봄
                  </v-btn>
                </v-col>
                <v-col
                  cols="4"
                >
                  <v-btn
                    depressed
                    width="100%"
                    height="70"
                    ripple
                    v-ripple="{class: 'error--text'}"
                    @click="selected.categoryName = '취미'"
                  >
                    취미
                  </v-btn>
                </v-col>
                <v-col
                  cols="4"
                >
                  <v-btn
                    depressed
                    width="100%"
                    height="70"
                    ripple
                    v-ripple="{class: 'error--text'}"
                    @click="selected.categoryName = '생활습관'"
                  >
                    생활습관
                  </v-btn>
                </v-col>
                <v-col
                  cols="4"
                >
                  <v-btn
                    depressed
                    width="100%"
                    height="70"
                    ripple
                    v-ripple="{class: 'error--text'}"
                    @click="selected.categoryName = '다이어트'"
                  >
                    다이어트
                  </v-btn>
                </v-col>
                <v-col
                  cols="4"
                >
                  <v-btn
                    depressed
                    width="100%"
                    height="70"
                    ripple
                    v-ripple="{class: 'error--text'}"
                    @click="selected.categoryName = '독서'"
                  >
                    독서
                  </v-btn>
                </v-col>
                <v-col
                  cols="4"
                >
                  <v-btn
                    depressed
                    width="100%"
                    height="70"
                    ripple
                    v-ripple="{class: 'error--text'}"
                    @click="selected.categoryName = '돈관리'"
                  >
                    돈관리
                  </v-btn>
                </v-col>
                <v-col
                  cols="4"
                >
                  <v-btn
                    depressed
                    width="100%"
                    height="70"
                    ripple
                    v-ripple="{class: 'error--text'}"
                    @click="selected.categoryName = '감정관리'"
                  >
                    감정관리
                  </v-btn>
                </v-col>
              </v-row>
            <!-- </v-container> -->
          </v-card>
          <p style="color: black; font-size: 1rem;">'{{selected.categoryName}}' 선택</p>
          <v-btn
            color="red darken-4"
            class="white--text"
            @click="e6 = 2"
            :disabled="this.selected.categoryName === ''"
          >
            다음
          </v-btn>
          <v-btn 
            text
            disabled
          >
            이전
          </v-btn>
        </v-stepper-content>

        

        <v-stepper-content step="2">
          <v-card
            color="grey lighten-5"
            flat
            class="mb-12"
            height="500"
          > 
            <v-card-text>
              <p style="font-size: 1rem; font-weight: 600;">챌린지명</p>
              <v-text-field
                flat
                solo
                v-model="title"
              ></v-text-field>
              <p style="font-size: 1rem; font-weight: 600;">챌린지 설명</p>
              <v-textarea
                flat
                solo
                height="300"
                :rules="textRules"
                v-model="texts"
              ></v-textarea>
            </v-card-text>
          </v-card>
          <v-btn
            color="red darken-4"
            class="white--text"
            :disabled="this.title === '' || this.texts === ''"
            @click="e6 = 3"
          >
            다음
          </v-btn>
          <v-btn 
            text
            @click="e6 = 1"
          >
            이전
          </v-btn>
        </v-stepper-content>

        

        <v-stepper-content step="3">
          <v-card
            color="grey lighten-5"
            flat
            class="mb-12"
            max-height="850"
          >
            <v-card-text>
              <p style="font-size: 1rem; font-weight: 600;">인증 방식</p>
              <v-textarea
                placeholder="예) 영단어 5개를 메모한 노트와 오늘의 손동작을 함께 찍은 사진을 올려주세요."
                flat
                solo
                height="100"
                v-model="proofType"
              ></v-textarea>
              <p style="font-size: 1rem; font-weight: 600; margin-top: 1rem;">인증샷 예시</p>
              
              <v-row>
                <v-col
                  cols="8"
                >
                  <v-file-input
                    label="터치하세요"
                    outlined
                    dense
                    accept="image/png, image/jpeg, image/bmp"
                    v-model="proofFiles"
                  ></v-file-input>
                </v-col>
                <v-col
                  cols="4"
                >
                  <v-btn
                    rounded-lg
                    color="red darken-4"
                    class="white--text"
                    width="20"
                    height="40"
                    :disabled="this.proofFiles.length === 0"
                    @click="proofUpload"
                  >
                    업로드
                  </v-btn>
                </v-col>
              </v-row>
              <v-img
                v-if="this.selected.authExample"
                :src="this.selected.authExample"
                width="100"
                height="100"
              >
              </v-img>
              <p style="font-size: 1rem; font-weight: 600; margin-top: 1rem;">공휴일 인증</p>
              <v-switch
                color="red darken-4"
                v-model="switch2"
                inset
                hide-details
              >
              </v-switch>
              
              <p style="font-size: 1rem; font-weight: 600; margin-top: 2.5rem;">인증 빈도</p>
              <div>
                <span>주 {{count+1}}회</span>
                <v-slider
                  v-model="count"
                  :tick-labels="ticksLabels"
                  :max="6"
                  step="1"
                  ticks="always"
                  tick-size="4"
                ></v-slider>
              </div>
              
              <p style="font-size: 1rem; font-weight: 600; margin-top: 2rem;">인증 시간</p>
              <el-time-select
                size="small"
                placeholder="시작"
                v-model="startTime"
                :picker-options="{
                  start: '00:00',
                  step: '00:15',
                  end: '23:59'
                }">
              </el-time-select>
              ~
              <el-time-select
                size="small"
                placeholder="마감"
                v-model="endTime"
                :picker-options="{
                  start: '00:00',
                  step: '00:15',
                  end: '23:59',
                  minTime: startTime
                }">
              </el-time-select>
            </v-card-text>
          </v-card>
          <v-btn
            color="red darken-4"
            class="white--text"
            :disabled="this.proofType === '' || this.selected.authExample === '' || this.startTime === '' || this.endTime === ''"
            @click="e6 = 4"
          >
            다음
          </v-btn>
          <v-btn 
            text
            @click="e6 = 2"
          >
            이전
          </v-btn>
        </v-stepper-content>

        
        <v-stepper-content step="4">
          <v-card
            color="grey lighten-5"
            class="mb-12"
            height="70%"
            flat
          >
            <v-card-text>
              <p style="font-size: 1rem; font-weight: 600;">챌린지 기간</p>
              <p>챌린지 기간은 <b>1주일</b> 단위로만 가능합니다. 예) 시작일이 화요일이면 종료일은 월요일이어야 함.</p>
              <v-row>
                <v-col
                  cols="12"
                  sm="6"
                >
                <div style="display: flex; justify-content: center;">
                  <v-date-picker
                    width = "80%"
                    v-model="dates"
                    range
                    no-title
                    color="red darken-4"
                  ></v-date-picker>
                </div>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                >
                  <div class="text-center">
                    <p style="font-weight: 600; font-size: 1rem;">얼마나 진행하실 건가요?</p>
                    <p>시작일: {{ dates[0] }}</p> 
                    <p>종료일: {{ dates[1] }}</p>
                  </div>
                  
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
          <v-btn
            color="red darken-4"
            @click="e6 = 5"
            class="white--text"
            :disabled="this.dates[0] === '' || this.dates[1] === ''"
          >
            다음
          </v-btn>
          <v-btn
            text
            @click="e6 = 3"
          >
            이전
          </v-btn>
        </v-stepper-content>

        
        <v-stepper-content step="5">
          <v-card
            color="grey lighten-5"
            flat
            class="mb-12"
            height="250px"
          >
            <v-card-text>
              <p style="font-size: 1rem; font-weight: 600;">챌린지 대표 이미지</p>
              <v-row>
                <v-col
                  cols="8"
                >
                  <v-file-input
                    label="터치하세요"
                    outlined
                    dense
                    accept="image/png, image/jpeg, image/bmp"
                    v-model="challengeFiles"
                  ></v-file-input>
                </v-col>
                <v-col
                  cols="4"
                >
                  <v-btn
                    rounded-lg
                    color="red darken-4"
                    class="white--text"
                    :disabled="this.challengeFiles.length === 0"
                    width="20"
                    height="40"
                    @click="challengeUpload"
                  >
                    업로드
                  </v-btn>
                </v-col>
              </v-row>
              <v-img
                v-if="this.selected.challengeThumbnail"
                :src="this.selected.challengeThumbnail"
                width="100"
                height="100"
              >
              </v-img>
            </v-card-text>
          </v-card>
          <v-btn
            color="red darken-4"
            class="white--text"
            :disabled="this.selected.challengeThumbnail === ''"
            @click="createChallenge"
          >
            완료
          </v-btn>
          <v-btn 
            text
            @click="e6 = 4"
          >
            이전
          </v-btn>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-container>
  </sequential-entrance> 
</template>

<script>
  export default {
    data () {
      return {
        e6: 1,
        title: '',
        textRules: [v => v.length <= 1000 || '1000자 이상 입력할 수 없습니다'],
        texts: '',
        proofType: '',
        proofFiles: [],
        rating: 0,
        switch1: true,
        switch2: false,
        count: 0,
        ticksLabels: [
          '1',
          '2',
          '3',
          '4',
          '5',
          '6',
          '7'
        ],
        startTime: '',
        endTime: '',
        dates: ['', ''],
        challengeFiles: [],
        selected: {
          "authEndtime": "",
          "authExample": "",
          "authFrequency": 0,
          "authHoliday": false,
          "authStarttime": "",
          "authWay": "",
          "categoryName": "",
          "challengeDesc": "",
          "challengeEnddate": "",
          "challengeName": "",
          "challengeStartdate": "",
          "challengeThumbnail": "",
          "hashtags": [],
          "userEmail": ""
        }
      }
    },
    computed: {
      dateRangeText () {
        return this.dates.join(' ~ ')
      },
    },
    watch: {
      dates: function() {
        let year1 = parseInt(this.dates[0].slice(0,4));
        let month1 = parseInt(this.dates[0].slice(5,7));
        let day1 = parseInt(this.dates[0].slice(8,10));
        let date1 = new Date(year1, month1-1, day1);

        let year2 = parseInt(this.dates[1].slice(0,4));
        let month2 = parseInt(this.dates[1].slice(5,7));
        let day2 = parseInt(this.dates[1].slice(8,10));
        let date2 = new Date(year2, month2-1, day2);

        const elapsedMSec = date2.getTime() - date1.getTime();
        const elapsedDay = elapsedMSec / (1000 * 60 * 60 * 24);

        if (elapsedDay < 0) {
          alert("시작일은 종료일보다 앞서 있어야 합니다.")
          this.dates[0] = ""
          this.dates[1] = ""
        } else if ((elapsedDay + 1) % 7 !== 0) {
          alert("챌린지는 1주일 단위로 선택하셔야 합니다.")
          this.dates[0] = ""
          this.dates[1] = ""
        }  
      }
    },
    updated() {
      this.$nextTick(function() {
        this.selected.challengeName = this.title
        this.selected.challengeDesc = this.texts
        this.selected.authWay = this.proofType
        if (this.switch2 === false) {
          this.selected.authHoliday = false
        } else {
          this.selected.authHoliday = true
        }
        this.selected.authFrequency = this.count + 1
        this.selected.authStarttime = this.startTime
        this.selected.authEndtime = this.endTime
        this.selected.challengeStartdate = this.dates[0]
        this.selected.challengeEnddate = this.dates[1]
      })
    },
    created() {
      this.selected.userEmail = this.$store.state.user.userEmail
    },
    methods: {
      async proofUpload() {
        let fd = new FormData();
        fd.append('authExample', this.proofFiles)

        await this.$Axios.post(`${this.$store.state.host}/v1/challenges/authExample`,
              fd, {
                headers: {
                  'Content-Type': 'multipart/form-data'
                }
              }
            ).then(res => {
              // console.log('전송 성공');
              console.log(res.data)
              alert("파일이 성공적으로 업로드 되었습니다.")
              this.selected.authExample = res.data.data
            }).catch(function () {
              console.log('전송 실패')
              alert("파일 업로드에 실패하였습니다.")
            })
          
      },
      async challengeUpload() {
        let fd = new FormData();
        fd.append('thumbnail', this.challengeFiles)

        await this.$Axios.post(`${this.$store.state.host}/v1/challenges/thumbnail`,
              fd, {
                headers: {
                  'Content-Type': 'multipart/form-data'
                }
              }
            ).then(res => {
              // console.log('전송 성공');
              console.log(res.data)
              this.selected.challengeThumbnail = res.data.data
              alert("파일이 성공적으로 업로드 되었습니다.")
            }).catch(function () {
              console.log('전송 실패')
              alert("파일 업로드에 실패하였습니다.")
            })
          
      },
      async createChallenge() {
        const categoryName = this.selected.categoryName
        await this.$Axios.post(`${this.$store.state.host}/v1/hashtag?hashtagName=${categoryName}`
        ).then(res => {
          console.log(res.data)
          this.selected.hashtags.push(res.data.data)
          console.log(this.selected.hashtags)
        }).catch(function () {
          console.log('전송 실패')
        })

        const proofFrequency = "주 " + this.selected.authFrequency + "회 인증"
        // console.log(proofFrequency) 
        await this.$Axios.post(`${this.$store.state.host}/v1/hashtag?hashtagName=${proofFrequency}`
        ).then(res => {
          console.log(res.data)
          this.selected.hashtags.push(res.data.data)
          console.log(this.selected.hashtags)
        }).catch(function () {
          console.log('전송 실패')
        })

        // console.log(this.selected)
        await this.$Axios.post(`${this.$store.state.host}/v1/challenges`, 
          this.selected
        ).then(res => {
          console.log(res.data)
          alert("챌린지 생성이 완료되었습니다.")
          this.$router.push("/challenge");
        }).catch(function () {
          console.log('전송 실패')
        })
      }
    }
  }
</script>

<style scoped>
.create-challenge {
  top: 3rem;
  width: 100%;
  text-align: center; 
  font-size: 1.7rem; 
  font-weight: bold;
}
.col{
  text-align: center;
}
</style>