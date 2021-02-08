<template>
  <v-card class="mx-auto" tile style="width:90%; margin-top:20px; padding:10px;">
            <v-app>
                <v-list rounded>
                    <v-subheader style="font-weight:600">현재 진행중인 챌린지</v-subheader>
                    <v-list-item-group
                        v-model="selectedItem"
                        color="#B71C1C"
                    >
                        <v-list-item
                        v-for="(item, i) in items"
                        :key="i"
                        >
                        <v-list-item-content>
                            <v-list-item-title v-text="item.text" style="text-align:left; padding-left:15px; font-size:13px;"></v-list-item-title>
                        </v-list-item-content>
                        </v-list-item>
                    </v-list-item-group>
                </v-list>
            </v-app>
        </v-card>
</template>

<script>
export default {
    data: () => ({
      selectedItem: 1,
      items: [
        { text: '아침 6시 기상 챌린지'},
        { text: '중국어 공부 1시간'},
        { text: '제로 웨이스트'},
        { text: '깃에 1일 1커밋 올리기'},
      ],
    })
}
</script>

<style>

</style>