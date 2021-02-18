<template>
  <div>
      <YourInfo/>
      <sequential-entrance fromBottom> 
      <div id="tabs" class="feedcontainer">
        <div class="tabs" style="text-align:center;">
            <a class="fix-font-semi-mid" v-on:click="activetab=1" v-bind:class="[ activetab === 1 ? 'active' : '' ]">마이리뷰</a>
            <a class="fix-font-semi-mid" v-on:click="activetab=2" v-bind:class="[ activetab === 2 ? 'active' : '' ]">업적</a>
        </div>
        <div class="content">
          <div v-if="activetab === 1" class="tabcontent">
              <YourReview/>
          </div>
          <div v-if="activetab === 2" class="tabcontent">
              <YourReward/>
          </div>
        </div>
      </div>
      </sequential-entrance> 
  </div>
</template>

<script>
import YourInfo from './YourInfo.vue';
import YourReview from './YourReview.vue';
import YourReward from './YourReward.vue';
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      activetab: 1,
    }
  },
  computed: {
      ...mapGetters({ 
        yourEmail: "getYourEmail",
        email: "getUserEmail" }),
  },
  components: {
    YourInfo,
    YourReview,
    YourReward,
  },
  
}
</script>

<style scoped>
.feedcontainer {  
  /* max-width: 620px; 
  min-width: 420px; */
  width: 90%;
  margin: 40px auto;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 0.9em;
  color: #888;
}

/* Style the tabs */
.tabs {
  overflow: hidden;
  /* margin-left: 20px; */
  margin-bottom: -2px; 
}

.tabs ul {
  list-style-type: none;
  margin-left: 20px;
} 

.tabs a{
  float: left;
  cursor: pointer;
  padding: 12px 24px;
  width: 50%;
  transition: background-color 0.2s; 
  border-right: none;
  background-color: #f1f1f1;
  border-radius: 10px 10px 0 0;
  font-weight: bold;
  box-shadow: 0px 3px 6px #ccc
}


/* Change background color of tabs on hover */
.tabs a:hover {
  background-color: #aaa;
  color: #fff;
}

/* Styling for active tab */
.tabs a.active {
  background-color: #fff;
  color: #484848;
  border-bottom: 2px solid #fff;
  cursor: default;
}

/* Style the tab content */
.tabcontent {
  padding: 30px;
  border: 1px solid #fff;
  border-radius: 10px;
  border-top-left-radius: 0px;
  border-top-right-radius: 0px;
  box-shadow: 0 3px 6px #e1e1e1
}

</style>