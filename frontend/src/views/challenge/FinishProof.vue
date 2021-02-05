<template>
  <div class="finish-proof">
    <h1>인증 완료</h1>
    <h3>인증사진이</h3>
    <h3>업로드 되었습니다.</h3>
    <p></p>
    <v-card flat>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          rounded
          color="red darken-4"
          dark
          width="30%"
          height="60px"
        >
          홈으로
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
          rounded
          color="white"
          width="30%"
          height="60px"
        >
          인증 목록
        </v-btn>
        <v-spacer></v-spacer>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.finish-proof {
  text-align: center;
  margin-top: 55%;
}

.finish-proof > h1 {
  font-size: 45px;
  margin-bottom: 10px;
}

.finish-proof > h3 {
  font-size: 25px;
  color: gray;
}

.finish-proof > p {
  margin-bottom: 50px;
}
</style>