<template>
  <div>
    <div style="margin-left:15px;display:flex;">
      <div style="float:left;"><Profile src="https://ifh.cc/g/MHQ1jy.jpg" style="margin:10px;"/></div>
      <div >saljjingae<br><p>집밥 해먹기 챌린지를 통해 몸도 마음도 건강해질 수 있었던 기회였다. :)
      앞으로도 다른 챌린지에 꾸준히 참여해봐야겠다.<br></p>
      <div style="color:gray; font-size:10px; font-weight:500;margin-bottom:10px;">2021.02.08</div>
      </div>
    </div>
    <div>
      <TalkComponent v-for="talk in talks" :talk="talk" v-bind:key="talk.id"/>
    </div>
    <br><br><br><br>

    <v-bottom-navigation
    color="primary"
    horizontal fixed height="80"
    >
        <Profile src="https://ifh.cc/g/MHQ1jy.jpg" style="margin:20px;"/><br>
        <v-text-field label="댓글 달기..." v-model="oneComment" v-on:keyup.enter="addComment" style="margin-top:20px;"></v-text-field>
        <v-btn @click="addComment" >게시</v-btn>
  
    </v-bottom-navigation>
  </div>
</template>

<script>
import TalkComponent from './TalkComponent.vue'
import Profile from '../../components/common/Profile.vue'

export default {
  components: {
    TalkComponent,
    Profile
  },
  data: () => ({
    user_type: 'all',
    oneComment: '',
    talks: [
      {
        id: 1,
        name: 'gogogooo',
        img: 'https://ifh.cc/g/z3nk1K.jpg',
        talk: '헉... 집밥 챌린지 재밌어 보이네요.. 저같은 똥손도 참여해도될까요,,ㅎㅎㅎ',
      },
      {
        id: 2,
        name: 'sujinnn',
        img: 'https://ifh.cc/g/hkmcm5.jpg',
        talk: '우왕!!! 저랑 같은 챌린지 참여하셨었네요 ㅎㅎㅎ 잘 보고갑니다~ 제 피드도 들려주세요 >.<',
      },
      {
        id: 3,
        name: '6seongjae',
        img: 'https://ifh.cc/g/LAsIsp.jpg',
        talk: '금손이세요..... ',
      },
    ],
  }),
    methods: {
      addComment: function() {
        console.log(this.oneComment);
        this.clearInput();
      },
      clearInput: function() {
        this.oneComment = '';
      },
    }
}
</script>
