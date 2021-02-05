<template>
  <v-stepper v-model="e1">
    <v-stepper-header>
      <v-stepper-step
        :complete="e1 > 1"
        step="1"
        color="#a62f22"
      >

      </v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step
        :complete="e1 > 2"
        step="2"
        color="#a62f22"
      >
      
      </v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step
        :complete="e1 > 3"
        step="3"
        color="#a62f22"
      >

      </v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step 
        :complete="e1 > 4"
        step="4"
        color="#a62f22"
      >
      
      </v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step 
        :complete="e1 > 5"
        step="5"
        color="#a62f22"
      >
      
      </v-stepper-step>
    </v-stepper-header>

    <v-stepper-items>
      <v-stepper-content step="1">
        <v-card
          class="mb-12 rounded-xl"
          color="white lighten-1"
          height="500px"
          elevation="50px"
        >
          <p>카테고리</p>
          <p>원하는 카테고리가 무엇인가요?</p>
          <v-container class="lighten-5">
            <v-row>
              <v-col
                cols="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >
                  운동
                </v-card>
              </v-col>
              <v-col
                cols="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >
                  공부
                </v-card>
              </v-col>
              <v-col
                cols="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >
                  돌봄
                </v-card>
              </v-col>
              <v-col
                cols="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >
                  취미
                </v-card>
              </v-col>
              <v-col
                cols="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >
                  생활습관
                </v-card>
              </v-col>
              <v-col
                cols="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >
                  다이어트
                </v-card>
              </v-col>
              <v-col
                cols="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >
                  독서
                </v-card>
              </v-col>
              <v-col
                cols="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >
                  돈관리
                </v-card>
              </v-col>
              <v-col
                cols="4"
              >
                <v-card
                  class="pa-2"
                  outlined
                  tile
                >
                  감정관리
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-card>
        
        <v-btn
          color="#a62f22"
          @click="e1 = 2"
        >
          Continue
        </v-btn>

        <v-btn
        >
          Cancel
        </v-btn>
      </v-stepper-content>

      <v-stepper-content step="2">
        <v-card
          class="mb-12 rounded-xl"
          color="white lighten-1"
          height="500px"
        >
          <p>챌린지 명</p>
          <v-text-field
            v-model="message2"
            solo
            label="Solo"
            clearable
          ></v-text-field>

          <p>챌린지 설명</p>
          <v-textarea
            solo
            name="input-7-4"
            label="Solo textarea"
            clearable
          ></v-textarea>
        </v-card>
        <v-btn
          color="#a62f22"
          @click="e1 = 3"
        >
          Continue
        </v-btn>

        <v-btn
          @click="e1 = 1"
        >
          Cancel
        </v-btn>
      </v-stepper-content>

      <v-stepper-content step="3">
        <v-card
          class="mb-12 rounded-xl"
          color="white lighten-1"
          height="500px"
        >
          <p>인증 방식</p>
          <v-text-field
            v-model="message2"
            solo
            label="Solo"
            clearable
          ></v-text-field>
          <p>인증샷 예시</p>
          <BtnAddPhoto />
          <p>인증빈도</p>

          <p>공휴일 인증</p>

          <p>인증 시간</p>
        </v-card>

        <v-btn
          color="#a62f22"
          @click="e1 = 4"
        >
          Continue
        </v-btn>

        <v-btn
          @click="e1 = 2"
        >
          Cancel
        </v-btn>
      </v-stepper-content>

      <v-stepper-content step="4">
        <v-card
          class="mb-12 rounded-xl"
          color="white lighten-1"
          height="500px"
        >
          <v-row>
            <v-col
              cols="12"
              sm="6"
            >
              <v-date-picker
                v-model="dates"
                range
                color="#FFEBEE"
              ></v-date-picker>
            </v-col>
            <v-col
              cols="12"
              sm="6"
            >
              <v-text-field
                v-model="dateRangeText"
                label="Date range"
                prepend-icon="mdi-calendar"
                readonly
              ></v-text-field>
            </v-col>
          </v-row>
        </v-card>

        <v-btn
          color="#a62f22"
          @click="e1 = 5"
        >
          Continue
        </v-btn>

        <v-btn
          @click="e1 = 3"
        >
          Cancel
        </v-btn>
      </v-stepper-content>

      <v-stepper-content step="5">
        <v-card
          class="mb-12 rounded-xl"
          color="white lighten-1"
          height="500px"
        >
        <p>챌린지 대표 이미지</p>
        <BtnAddPhoto />

        <p>연관 태그</p>
        <v-container fluid>
          <v-combobox
            v-model="model"
            :items="items"
            :search-input.sync="search"
            hide-selected
            hint="Maximum of 5 tags"
            multiple
            persistent-hint
            small-chips
          >
            <template v-slot:no-data>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>
                    No results matching "<strong>{{ search }}</strong>". Press <kbd>enter</kbd> to create a new one
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </template>
          </v-combobox>
        </v-container>
        </v-card>

        <v-btn
          color="#a62f22"
        >
          Continue
        </v-btn>

        <v-btn
          @click="e1 = 4"
        >
          Cancel
        </v-btn>
      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>
</template>

<script>
import BtnAddPhoto from '../../components/common/BtnAddPhoto.vue'
export default {
  components: { BtnAddPhoto },
  data () {
    return {
      e1: 1,
      start: null,
      end: null,
      items: ['Gaming', 'Programming', 'Vue', 'Vuetify'],
      model: ['Vuetify'],
      search: null,
      dates: [],
      message2: ''
    }
  },
  watch: {
    model (val) {
      if (val.length > 5) {
        this.$nextTick(() => this.model.pop())
      }
    },
  },
  computed: {
    dateRangeText () {
      return this.dates.join(' ~ ')
    },
  },
}
</script>

<style scoped>
.v-stepper {
  border-radius: 20px;
}
</style>