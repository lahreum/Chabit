<template>
  <v-container>
    <div class="create-challenge">
      <span>챌린지 생성</span>
    </div>
    <p></p>
    <v-stepper
      v-model="e6"
      vertical
    >
      <v-stepper-step
        :complete="e6 > 1"
        step="1"
        color="red darken-4"
      >
        <span>카테고리</span>
        <span style="font-size:0.5rem; margin-top: 0.3rem; color: grey;">원하는 카테고리가 무엇인가요?</span>
      </v-stepper-step>

      <v-stepper-content step="1">
        <v-card
          flat
          class="mb-12"
          height="300px"
        >
          <v-row>
            <v-col
              cols="4"
            >
              <v-btn
                depressed
                width="80"
                height="80"
                ripple
                v-ripple="{class: 'error--text'}"
              >
                운동
              </v-btn>
            </v-col>
            <v-col
              cols="4"
            >
              <v-btn
                depressed
                width="80"
                height="80"
                ripple
                v-ripple="{class: 'error--text'}"
              >
                공부
              </v-btn>
            </v-col>
            <v-col
              cols="4"
            >
              <v-btn
                depressed
                width="80"
                height="80"
                ripple
                v-ripple="{class: 'error--text'}"
              >
                돌봄
              </v-btn>
            </v-col>
            <v-col
              cols="4"
            >
              <v-btn
                depressed
                width="80"
                height="80"
                ripple
                v-ripple="{class: 'error--text'}"
              >
                취미
              </v-btn>
            </v-col>
            <v-col
              cols="4"
            >
              <v-btn
                depressed
                width="80"
                height="80"
                ripple
                v-ripple="{class: 'error--text'}"
              >
                생활습관
              </v-btn>
            </v-col>
            <v-col
              cols="4"
            >
              <v-btn
                depressed
                width="80"
                height="80"
                ripple
                v-ripple="{class: 'error--text'}"
              >
                다이어트
              </v-btn>
            </v-col>
            <v-col
              cols="4"
            >
              <v-btn
                depressed
                width="80"
                height="80"
                ripple
                v-ripple="{class: 'error--text'}"
              >
                독서
              </v-btn>
            </v-col>
            <v-col
              cols="4"
            >
              <v-btn
                depressed
                width="80"
                height="80"
                ripple
                v-ripple="{class: 'error--text'}"
              >
                돈관리
              </v-btn>
            </v-col>
            <v-col
              cols="4"
            >
              <v-btn
                depressed
                width="80"
                height="80"
                ripple
                v-ripple="{class: 'error--text'}"
              >
                감정관리
              </v-btn>
            </v-col>
          </v-row>
        </v-card>
        <v-btn
          color="red darken-4"
          dark
          @click="e6 = 2"
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

      <v-stepper-step
        :complete="e6 > 2"
        step="2"
        color="red darken-4"
      >
        <span>챌린지 소개</span>
        <span style="font-size:0.5rem; margin-top: 0.3rem; color: grey;">챌린지를 소개해주세요!</span>
      </v-stepper-step>

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
          dark
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

      <v-stepper-step
        :complete="e6 > 3"
        step="3"
        color="red darken-4"
      >
        <span>인증 방식</span>
        <span style="font-size:0.5rem; margin-top: 0.3rem; color: grey;">인증 방식을 지정해주세요!</span>
      </v-stepper-step>

      <v-stepper-content step="3">
        <v-card
          color="grey lighten-5"
          flat
          class="mb-12"
          height="650px"
        >
          <v-card-text>
            <p style="font-size: 1rem; font-weight: 600;">인증 방식</p>
            <v-textarea
              flat
              solo
              height="100"
              v-model="proofType"
            ></v-textarea>
            <p style="font-size: 1rem; font-weight: 600;">인증샷 예시</p>
            <v-row>
              <v-col
                cols="8"
              >
                <v-file-input
                  label="터치하세요"
                  outlined
                  dense
                  accept="image/png, image/jpeg, image/bmp"
                ></v-file-input>
              </v-col>
              <v-col
                cols="4"
              >
                <v-btn
                  rounded-lg
                  color="red darken-4"
                  dark
                  height="40px"
                >
                  업로드
                </v-btn>
              </v-col>
            </v-row>
            <p style="font-size: 1rem; font-weight: 600;">공휴일 인증</p>
            <v-switch
              color="red darken-4"
              v-model="switch2"
              inset
              hide-details
            >
            </v-switch>
            <p></p>
            <p style="font-size: 1rem; font-weight: 600;">인증 빈도</p>
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
            <p></p>
            <p style="font-size: 1rem; font-weight: 600;">인증 시간</p>
            <el-time-select
              size="small"
              placeholder="시작"
              v-model="startTime"
              :picker-options="{
                start: '00:00',
                step: '00:10',
                end: '24:00'
              }">
            </el-time-select>
            ~
            <el-time-select
              size="small"
              placeholder="마감"
              v-model="endTime"
              :picker-options="{
                start: '00:00',
                step: '00:10',
                end: '24:00',
                minTime: startTime
              }">
            </el-time-select>
          </v-card-text>
        </v-card>
        <v-btn
          color="red darken-4"
          dark
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

      <v-stepper-step
        :complete="e6 > 4" 
        step="4"
        color="red darken-4"
      >
        <span>챌린지 기간</span>
        <span style="font-size:0.5rem; margin-top: 0.3rem; color: grey;">챌린지 기간을 지정해주세요!</span>
      </v-stepper-step>
      <v-stepper-content step="4">
        <v-card
          color="grey lighten-5"
          class="mb-12"
          height="500px"
          flat
        >
          <v-card-text>
            <p style="font-size: 1rem; font-weight: 600;">챌린지 기간</p>
            <v-row>
              <v-col
                cols="12"
                sm="6"
              >
                <v-date-picker
                  v-model="dates"
                  range
                  no-title
                  color="red darken-4"
                ></v-date-picker>
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
          dark
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

      <v-stepper-step
        :complete="e6 > 5"
        step="5"
        color="red darken-4"
      >
        <span>대표 이미지</span>
        <span style="font-size:0.5rem; margin-top: 0.3rem; color: grey;">챌린지를 잘 나타내는 이미지를 올려주세요!</span>
      </v-stepper-step>
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
                ></v-file-input>
              </v-col>
              <v-col
                cols="4"
              >
                <v-btn
                  rounded-lg
                  color="red darken-4"
                  dark
                  height="40px"
                >
                  업로드
                </v-btn>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
        <v-btn
          color="red darken-4"
          dark
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
    </v-stepper>
  </v-container>
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
        dates: ['2021-02-07', '2021-02-14'],
      }
    },
    computed: {
      dateRangeText () {
        return this.dates.join(' ~ ')
      },
    },
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
</style>