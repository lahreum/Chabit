<template>
  <div>
    <!-- 상단 금,은,동 계기판 -->
      <v-card>
        <v-list-item>
            <div class="top-text" style="width:90%; margin:0 auto;text-align:center;">
                <div style="float:left;width:30%;">1</div>
                <div style="float:left;margin-left:5%;width:30%;">2</div>
                <div style="float:right;width:30%;">0</div>
            </div>
        </v-list-item>
        <v-list-item>
            <div style="width:90%; margin:0 auto; text-align:center;">
                <div style="float:left;width:30%;"><img src="https://ifh.cc/g/rUZBvq.jpg" style="width:50px;"></div>
                <div style="float:left;margin-left:5%;width:30%;"><img src="https://ifh.cc/g/wd7WQg.jpg" style="width:50px;"></div>
                <div style="float:right;width:30%;"><img src="https://ifh.cc/g/pNp80t.jpg" style="width:50px;"></div>
            </div>
        </v-list-item>
      </v-card>
      <!-- 뱃지 리스트 -->
      <div>
        <div style="width:100%; margin-top:30px; text-align:center;">
          <v-row no-gutters>
            <v-col
              v-for="badge in badges"
              :key="badge.category"
              cols="4"
            >
              <div class="text-center">
                <v-dialog v-model="dialog" width="500" persistent :retain-focus="false">
                  <template v-slot:activator="{ on, attrs }">
                    <div v-bind="attrs" v-on="on"><img :src="badge.src" @click="clickModal(badge)"></div>
                  </template>
                  
                  <!-- 뱃지 모음을 보여주는 모달 팝업 -->
                  <v-card>
                    <v-card-title>
                      <span>{{ badgeData.text }}</span>
                    </v-card-title>
                    <v-card-text>
                      <!-- 이미지 출력 오류 부분 -->
                      <img :src="badgeData.src" :badgeData="badge"/>
                      <!-- <span>{{ badgeData.text }}</span> -->
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

                <div><p>{{badge.text}}</p></div>
              </div>
            </v-col>

          </v-row>
        </div>
      </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    badges: [
      {
        category: '운동',
        text: '운동 중독',
        src: 'https://ifh.cc/g/GcwzlU.png',
      },
      {
        category: '공부',
        text: '공부하는 맛',
        src: 'https://ifh.cc/g/mtZNrP.png',
      },
      {
        category: '돌봄',
        text: '프로 돌봄이',
        src: 'https://ifh.cc/g/UZrUKt.png',
      },
      {
        category: '취미',
        text: '덕밍아웃',
        src: 'https://ifh.cc/g/4YE6Sv.png',
      },
      {
        category: '생활습관',
        text: '바른 생활의 대명사',
        src: 'https://ifh.cc/g/8imbsM.png',
      },
      {
        category: '다이어트',
        text: '프로 다이어터',
        src: 'https://ifh.cc/g/sGCIPu.png',
      },
      {
        category: '독서',
        text: '독서 안하면 가시가 돋아요',
        src: 'https://ifh.cc/g/JsUO4S.png',
      },
      {
        category: '돈관리',
        text: '스크루지의 환생',
        src: 'https://ifh.cc/g/Nx2AKb.png',
      },
      {
        category: '감정관리',
        text: '이너피스',
        src: 'https://ifh.cc/g/IdabxF.png',
      },
      {
        category: '미정',
        text: '획득하러 가기',
        src: 'https://ifh.cc/g/vwL3m1.png',
      },

    ],
    badgeData: {

    },
    dialog: false,
  }),
  methods: {
    clickModal(badge) {
      this.badgeData = badge
      console.log(this.badgeData);
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
</style>