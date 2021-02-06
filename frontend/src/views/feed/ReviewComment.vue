<template>
  <div>
    <div style="margin-left:15px;display:flex;">
      <div style="float:left;"><Profile src="https://www.shareicon.net/data/128x128/2016/09/01/822711_user_512x512.png" style="margin:10px;"/></div>
      <div >saljjingae<br><p>집밥 해먹기 챌린지를 통해 몸도 마음도 건강해질 수 있었던 기회였다. :)
      앞으로도 다른 챌린지에 꾸준히 참여해봐야겠다.<br><br></p>
      </div>
    </div>
    <div>
      <TalkComponent v-for="talk in talks" :talk="talk" v-bind:key="talk.id"/>
    </div>

    <v-bottom-navigation
    color="primary"
    horizontal fixed height="80"
    >
        <Profile src="https://www.shareicon.net/data/128x128/2016/09/01/822711_user_512x512.png" style="margin:10px;"/><br>
        <v-text-field label="댓글 달기..." v-model="oneComment"></v-text-field>
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
        name: 'James',
        handle: '@james',
        img: 'https://www.shareicon.net/data/128x128/2016/09/01/822711_user_512x512.png',
        talk: 'Puppy kitty ipsum dolor sit good dog tigger good boy furry teeth purr lazy cat run fast fish critters string.',
      },
      {
        id: 2,
        name: 'Jessica',
        handle: '@jessica',
        img: 'https://www.shareicon.net/data/128x128/2016/09/01/822739_user_512x512.png',
        talk: 'Wag Tail tuxedo run bedding head chew food purr drool kisses carrier chirp toy ID tag slobbery smooshy.',
      },
      {
        id: 3,
        name: 'Heather',
        handle: '@heather',
        img: 'https://www.shareicon.net/data/128x128/2016/09/01/822761_user_512x512.png',
        talk: 'Rover maine coon cat speak harness whiskers mouse.',
      },
      {
        id: 4,
        name: 'Arthur',
        handle: '@arthur',
        img: 'https://www.shareicon.net/data/128x128/2016/09/01/822745_user_512x512.png',
        talk: 'Toys stay finch polydactyl stay barky bark pet supplies food Buddy chirp Spike nap stick dog house throw.Tail collar leash Rover meow catch Scooby snacks.',
      },
      {
        id: 5,
        name: 'Francesca',
        handle: '@francesca',
        img: 'https://www.shareicon.net/data/128x128/2016/09/01/822715_user_512x512.png',
        talk: 'Kitty bark string shake litter box toys polydactyl yawn polydactyl scratcher water dog stay cage nest slobber chirp water.',
      },
      {
        id: 6,
        name: 'Tina',
        handle: '@tina',
        img: 'https://www.shareicon.net/data/128x128/2016/09/01/822726_user_512x512.png',
        talk: 'Birds fur collar fluffy collar parakeet barky dog house run sit Buddy purr. Bird wag tail small animals groom vitamins Tigger.',
      }
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
