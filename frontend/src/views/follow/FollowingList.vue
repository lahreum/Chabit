<template>
    <v-container>
        <v-layout>
            <v-flex class="section">
                <v-list subheader>
                    <v-subheader>
                        <div class="text-darken-1 mb-2 font-weight-black black--text" id="title">
                            <span>팔로잉</span>
                        </div>
                    </v-subheader>
                    <v-list-item v-for="user in users" :key="user.name">
                        <v-list-item-avatar>
                            <v-img :alt="`${user.name} avatar`" :src="user.avatar"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title  class="name black--text" v-text="user.name"></v-list-item-title>
                            <v-list-item-title class="msg gray--text" v-text="user.msg"></v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
export default {
    data: () => ({
      users: [
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          name: 'walkinglife',
          msg: '맨날 걷는 사람'
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          name: 'workaholic',
          msg: '워커홀릭...'
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
          name: 'ILoveChicken',
          msg: '치킨 도살자'
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
          name: 'LoveEating',
          msg: '예비 푸드파이터'
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
          name: 'goodFriend',
          msg: '으리으리'
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
          name: 'goodFriend2',
          msg: '의리빼면 시체'
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          name: 'saljjingae',
          msg: '소통하면서 지내요 :)'
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          name: 'ssafy4th',
          msg: '싸피4기 친추해주세요 :)'
        },
      ],
    }),
}
</script>

<style scoped>
.section {
    margin: 20px;
}
#title{
    font-size: 1.5rem;
    margin-top: 30px;
}
.v-subheader{
    margin-bottom: 30px;
}
.name{
    font-weight: 500;
    font-size: 1rem;
}
.v-list-item__title.msg{
    font-weight: 400;
    font-size: 0.3rem;
    color: #757575;
}
</style>