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
                <div style="float:left;width:30%;"><img :src="require(`../../assets/img/silver.jpg`)" style="height:40px;"></div>
                <div style="float:left;margin-left:5%;width:30%;"><img :src="require(`../../assets/img/gold.jpg`)" style="height:40px;"></div>
                <div style="float:right;width:30%;"><img :src="require(`../../assets/img/bronze.jpg`)" style="height:40px;"></div>
            </div>
        </v-list-item>
      </v-card>
      <!-- 뱃지 리스트 -->
      <v-card>
        <div style="width:90%; margin:20px; padding:10px;text-align:center;">
          <v-row no-gutters>
            <v-col
              v-for="badge in badges"
              :key="badge.name"
              cols="4"
            >
              <div class="text-center">
                <v-dialog v-model="dialog" width="500" persistent :retain-focus="false">
                  <template v-slot:activator="{ on, attrs }">
                    <div v-bind="attrs" v-on="on"><img :src="require(`@/assets/img/${badge.name}.jpg`)" @click="clickModal(badge)"></div>
                  </template>
                  
                  <!-- 뱃지 모음을 보여주는 모달 팝업 -->
                  <v-card>
                    <v-card-title>
                      <span>{{ badgeData.text }}</span>
                    </v-card-title>
                    <v-card-text>
                      <!-- 이미지 출력 오류 부분 -->
                      <!-- <img :src="require(`@/assets/img/${badgeData.name}.jpg`)"/> -->
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
      </v-card>
  </div>
</template>

<script>
export default {
  data: () => ({
    // done: {
    //   name: '',
    //   text: ''
    // },
    badges: [
      {
        name: 'weight',
        text: '운동 중독'
      },
      {
        name: 'pencil',
        text: '공부하는 맛'
      },
      {
        name: 'plant',
        text: '프로 돌봄이'
      },
      {
        name: 'diet',
        text: '프로 다이어터'
      },
      {
        name: 'clock',
        text: '바른 생활의 대명사'
      },
      {
        name: 'palette',
        text: '덕밍아웃'
      },
      {
        name: 'book',
        text: '독서 안하면 가시가 돋아요'
      },
      {
        name: 'piggybank',
        text: '스크루지의 환생'
      },
      {
        name: 'dove',
        text: '이너피스'
      },
      {
        name: 'question',
        text: '획득하러 가기'
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