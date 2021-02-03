<template>
    <v-app>
        <br>
        <v-card elevation="4" class="rounded-t-xl card-outside">
            <follow-nav></follow-nav>
            <router-view/>
        </v-card>
    </v-app>
</template>

<script>
import FollowNav from '../../components/include/FollowNav.vue';

export default {
    components: {
        FollowNav,
    },   
};
</script>
