<template>
  <v-container fluid>
        <v-layout column>
            <!-- searchBar -->
            <v-flex class="section">
                <div class="searchbar" style="margin:0 auto">
                    <search-bar></search-bar>
                </div>
            </v-flex>
            <!-- 팔로우, 팔로잉 -->
            <v-flex class="section">
                <v-card elevation="2" class="mx-auto rounded-lg" max-width="80%" outlined>
                    <v-list-item three-line>
                        <v-list-item-content>
                            <v-list-item-title class="headline mb-1 text-center">
                            <strong style="margin-right:15px;"><router-link :to="{ name: 'FollowList' }">80</router-link></strong>
                            <strong style="margin-left:15px;"><router-link :to="{ name: 'FollowingList' }">80</router-link></strong>
                            </v-list-item-title>
                            <v-list-item-subtitle class="mb-1 text-center">
                                <span style="margin:10px;">팔로우</span>
                                <span style="margin:10px;">팔로잉</span>
                            </v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                </v-card>
            </v-flex>
        </v-layout>
      </v-container>
</template>

<script>
import SearchBar from '../common/SearchBar.vue';

export default {
    components: {
        SearchBar
    },
};
</script>

<style>
.searchbar {
    width: 80%;
}    
</style>