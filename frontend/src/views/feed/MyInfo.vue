 <template>
  <div
    class="overflow-hidden"
    style="margin-left:8%;margin-right:8%; margin-bottom:-40px;"
  >
    <div>
            <div style="margin-top: 20px; width:100%;">
                <div style="float:left;">
                  <Profile :src="userImage" alt="profile" style="width:70px;height:70px;"/>
                </div>
                <div>
                  <span style="color:#424242;font-weight:600; margin-left:10px; font-size:20px;"> {{ userNickname }} <br></span>
                  <span style="color:#424242;font-weight:600; margin-left:10px; font-size:20px;">({{ userPoints }}/{{ userMaxPoint }})p<br></span>
                  <span style="color:#424242;margin-left:10px; font-size:12px;">{{ userLevel }} </span>
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
            <template v-slot:selection="{ attrs, item }">
            <v-chip
                v-bind="attrs"
                color="#212121" text-color="white"
                @click="remove(item)" 
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
    // computed: {
    //   ...mapGetters(['getUserNickname','getUserPoints','getUserHashtags','getUserLevel','getUserMaxPoint','getUserLevelImage']),
    // },
    computed: {
      ...mapGetters({ userEmail: "getUserEmail" }),
      ...mapGetters({ userImage: "getUserImage" }),
      ...mapGetters({ userNickname: "getUserNickname" }),
      ...mapGetters({ userPoints: "getUserPoints" }),
      ...mapGetters({ userHashtags: "getUserHashtags" }),
      ...mapGetters({ userLevel: "getUserLevel" }),
      ...mapGetters({ userMaxPoint: "getUserMaxPoint" }),
  },
    data () {
      return {
        hasSaved: false,
        isEditing: null,
        model: null,
        hashtag:'',
        status:'',
        chips: [],
      }
    },

    methods: {
      save () {
        this.isEditing = !this.isEditing
        this.hasSaved = true

        this.$Axios
        // .post(`${this.$store.state.host}/v1/users/hashtag/${this.$store.state.user.userEmail}`, this.chips)
        .post(`${this.$store.state.host}/v1/users/hashtag/`+ this.userEmail  + '?hashtagName=' + `${this.chips}`)
        .then(({data}) => {
          if(data.status === "success") {
            console.log('해쉬태그 저장잘됨');
          } else {
            console.log('해쉬태그 안보내짐....');
          }
        })
        .catch((error) => {
          console.log(error);
        });
      },
      remove (item) {
        this.chips.splice(this.chips.indexOf(item), 1)
        this.chips = [...this.chips]
      },
    },
    // addhashtag(item) {

    // }
    created() {
      this.chips = '';
    }
  }
</script>

<style>
.v-text-field {
    padding: 0;
    margin-top: 0;
}

</style>