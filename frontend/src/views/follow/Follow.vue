<template>
    <v-app>
        <br>
            <follow-nav></follow-nav>
            <router-view/>
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
