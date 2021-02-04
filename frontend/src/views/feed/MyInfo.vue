<template>
  <div>
      <div style="width:390px; height:200px;margin: 0 auto">
        <div style="float:left; margin-left:20px;width:260px;">
            <p class="userid">
                <strong>saljjingae(200/3000p)</strong>
                <v-icon>mdi-pencil</v-icon>
            </p>
            <v-text-field
            label="#운동, #생활습관"
            single-line
            solo height="30px" style="width:90%;"
          ></v-text-field>
            <v-textarea
          solo
          name="input-7-4"
          label="운동에 미쳐라" height="60" style="width:90%;"
        ></v-textarea>
        
        </div>
        <div style="float:right;">
            <Profile src="https://cdn.vuetifyjs.com/images/lists/1.jpg" style="width:90px;height:90px; margin-top:30px;margin-right:20px;"/><br>
            <v-btn color="#B71C1C" class="saveBtn" height="30" style="margin-top:34px;color:white;">
                save
            </v-btn>
        </div>
      </div>
  </div>
</template>
 
<script>
import Profile from '../../components/common/Profile.vue'

export default {
    components: {
        Profile
    }
}
</script>

<style>
.userid{
    font-size: 19px;
    margin: 20px;
}
.v-text-field .v-input__control .v-input__slot {
    min-height: auto !important;
    display: flex !important;
    align-items: center !important;
}
.container {
    padding: 0px;
}
.v-label.theme--light {
    font-size: 10px;
}
</style>