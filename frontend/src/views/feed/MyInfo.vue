 <template>
  <div
    class="overflow-hidden"
    style="margin-left:8%;margin-right:8%; margin-bottom:-40px;"
  >
    <div>
            <div style="margin-top: 20px; width:100%;">
                <div style="float:left;">
                  <Profile src="https://ifh.cc/g/MHQ1jy.jpg" alt="profile" style="width:70px;height:70px;"/>
                </div>
                <div>
                  <span style="color:#424242;font-weight:600; margin-left:15px; font-size:20px;"> {{ getUserNickname }} <br></span>
                  <span style="color:#424242;font-weight:600; margin-left:15px; font-size:20px;">({{ getUserPoints }}/30000)p<br></span>
                  <span style="color:#424242;margin-left:15px; font-size:12px;">Bronze</span>
                                    <v-btn
        color="gray darken-4"
        fab
        x-small
        @click="isEditing = !isEditing" style="float:right; margin-right:10px;"
      >
        <v-icon v-if="isEditing">
          mdi-close
        </v-icon>
        <v-icon v-else>
          mdi-pencil
        </v-icon>
      </v-btn>
                </div>
            </div>

    
        <!-- 해시태그 input -->
      <v-combobox
            v-model="chips"
            
            :disabled="!isEditing"
            chips
            clearable
            placeholder="해쉬태그"
            multiple
            style="height:70px;margin-top:15px;" 
        >
            <template v-slot:selection="{ attrs, item, selected }">
            <v-chip
                v-bind="attrs"
                :input-value="selected" color="#212121" text-color="white"
                @click="remove(item)" v-model="chips"
            >
                <strong>{{ item }}</strong>&nbsp;
            </v-chip> 
            </template>
        </v-combobox>
        <!-- 상태메세지 input -->
      <v-text-field
        :disabled="!isEditing"
        placeholder="상태메세지" v-model="status"
     
      ></v-text-field>
    </div>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        v-if="isEditing"
        :disabled="!isEditing"
        color="primary"
        @click="save"
      >
        Save
      </v-btn>
    </v-card-actions>
    <v-snackbar
      v-model="hasSaved"
      :timeout="1000"
      absolute
      top
    >
      저장되었습니다.
    </v-snackbar>
  </div>
</template>

<script>
import Profile from '../../components/common/Profile.vue'
import { mapGetters } from 'vuex'

  export default {
    components: {
        Profile
    },
    computed: {
      ...mapGetters(['getUserNickname','getUserPoints','getUserHashtags','getUserLevel']),
    },
    data () {
      return {
        hasSaved: false,
        isEditing: null,
        model: null,
        hashtag:'',
        status:'Carpe diem',
        chips: ['이지금','내손을잡아'],
      }
    },

    methods: {
      save () {
        this.isEditing = !this.isEditing
        this.hasSaved = true
      },
      remove (item) {
        this.chips.splice(this.chips.indexOf(item), 1)
        this.chips = [...this.chips]
      },
    },
  }
</script>

<style>
.v-text-field {
    padding: 0;
    margin-top: 0;
}
</style>