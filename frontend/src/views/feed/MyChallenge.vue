<template>
  <div>
        <v-card style="width:90%;">
            <v-list-item>
                <div class="top-text" style="width:90%; margin:10px auto;">
                    <div style="float:left;width:30%;">1</div>
                    <div style="float:left;margin-left:5%;width:30%;">2</div>
                    <div style="float:right;width:30%;">0</div>
                </div>
            </v-list-item>
            <v-list-item>
                <div class="bottom-text" style="width:90%; margin:10px auto;">
                    <div style="float:left;width:30%;">참가중</div>
                    <div style="float:left;margin-left:5%;width:30%;">완료</div>
                    <div style="float:right;width:30%;">개설</div>
                </div>
            </v-list-item>
        </v-card>
        <v-card style="width:90%; margin-top:20px;height:350px;">          
            <Calendar style="padding-top:30px;"/>
        </v-card>
        <TodayChallengeList />
  </div>
</template>

<script>
import Calendar from '../../components/common/Calendar.vue';
import TodayChallengeList from './TodayChallengeList.vue';

export default {
    components: {
        Calendar,
        TodayChallengeList
    },
    
}
</script>

<style scoped>
.top-text {
    font-size: 20px;
    font-weight: 600;
    padding-bottom: 0;
}
.bottom-text {
    font-size: 16px;
    font-weight: 500;
    padding-top: 0;
}
.v-list-item {
    padding: 0px;
    text-align: center;
}
.v-sheet.v-card{
    border-radius: 20px;
    margin: 0 auto;
}
.challenge-name {
    font-size: 14px;
    font-weight: 500;
}
</style>