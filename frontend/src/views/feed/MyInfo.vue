 <template>
  <div
    class="overflow-hidden"
    style="margin-left:5%;margin-right:5%;"
  >
    <v-toolbar
      flat
      color="white" style="width:100%; margin-bottom:10px;"
    >
      <v-toolbar-title class="font-weight-light" >
            <div style="margin-top: 30px;">
                <Profile src="https://ifh.cc/g/MHQ1jy.jpg" alt="profile" style="width:70px;height:70px;"/>
                <span style="color:#424242;font-weight:600; margin-left:15px;">dlwlrma(200/3000p)</span>
            </div>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        color="gray darken-4"
        fab
        small
        @click="isEditing = !isEditing"
      >
        <v-icon v-if="isEditing">
          mdi-close
        </v-icon>
        <v-icon v-else>
          mdi-pencil
        </v-icon>
      </v-btn>
    </v-toolbar>
    <v-card-text>
        <!-- 해시태그 input -->
      <v-combobox
            v-model="chips"
            
            :disabled="!isEditing"
            chips
            clearable
            label="해쉬태그"
            multiple
            solo style="height:70px;margin-top:15px;" 
        >
            <template v-slot:selection="{ attrs, item, selected }">
            <v-chip
                v-bind="attrs"
                :input-value="selected"
                @click="remove(item)" v-model="chips"
            >
                <strong>{{ item }}</strong>&nbsp;
            </v-chip>
            </template>
        </v-combobox>
        <!-- 상태메세지 input -->
      <v-text-field
        :disabled="!isEditing"
        label="" v-model="status"
     
      ></v-text-field>
    </v-card-text>
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
  export default {
    components: {
        Profile
    },
    data () {
      return {
        hasSaved: false,
        isEditing: null,
        model: null,
        hashtag:'',
        status:'이 하루, 이 지금, 우리,눈부셔 아름다워',
        chips: ['왼손으로그린별하나','내손을잡아'],
        // chips: ['Programming', 'Playing video games', 'Watching movies', 'Sleeping'],
        // items: ['Streaming', 'Eating'],
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