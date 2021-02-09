<template>
<div style="width:100%; height:100%;">
  <div>
    <Profile src="https://ifh.cc/g/MHQ1jy.jpg" 
    style="width:45px;height:45px; margin:10px; float:left;"/><br>
    <div style="float:left;"><strong>saljjingae</strong></div>
  </div>
    <v-carousel :show-arrows="false" height="280" >
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
        :src="item.src" 
      ></v-carousel-item>
    </v-carousel>
    <div style="margin: 15px;">
      <span style="font-size:12px;">
        <i class="fa fa-heart fa-2x" style="margin-right:10px;"  v-on:click="addgood" v-if="choose" ></i>
        <i class="far fa-heart fa-2x" style="margin-right:10px;"  v-on:click="addgood" v-if="!choose" ></i>
      </span>
      <span style="font-size:12px;">
        <router-link to="/review-comment" style="text-decoration:none;color:inherit;"><i class="far fa-comment fa-2x" style="margin-right:10px;" ></i></router-link>
      </span>
      <span style="font-size:12px;">
        <i class="fas fa-share-alt fa-2x" ></i>
      </span>
    </div>
    <div style="margin: 15px;">
      <p style="font-weight:600"> 멋져요 {{ count }}개</p>
    </div>
    <div style="margin: 15px;">
      집밥 해먹기 챌린지를 통해 몸도 마음도 건강해질 수 있었던 기회였다. :)
      앞으로도 다른 챌린지에 꾸준히 참여해봐야겠다.
      <br>
    </div>
    <div style="margin-left: 15px;">
      <router-link to="/review-comment" style="text-decoration:none;color:inherit;"><p style="color:gray; font-size:12px;">댓글 {{ count + 4 }} 개 더보기..</p></router-link>
      <p style="color:gray; font-size:10px;">
        2021.02.08
      </p>
      </div>
  </div>
</template>

<script>
import Profile from '../../components/common/Profile.vue'
export default {
  components: {
      Profile
  },
  data: () => ({
      count: 0,
      choose: false,
      items: [
        {
          src: 'https://ifh.cc/g/YYwAT0.jpg',
        },
        {
          src: 'https://ifh.cc/g/xWSN8P.jpg',
        },
        {
          src: 'https://ifh.cc/g/93Zb5I.jpg'
        },
        {
          src: 'https://ifh.cc/g/U6HqMw.jpg'
        },
        {
          src: 'https://ifh.cc/g/3ZgeU1.jpg',
        },
      ],
  }),
  methods: {
    addgood: function() {
      this.count += 1;
      this.choose = !this.choose;
    }
  }
}
</script>

<style>

</style>