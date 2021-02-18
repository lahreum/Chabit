<template>
<div class="intro-container" @click="onSkip">
	<h1 class="intro-h1">CHABIT</h1>
	<svg class="pulse" viewBox="0 0 1024 1024">
        <circle id="Oval" cx="-512" cy="-512" r="512"></circle>
        <circle id="Oval" cx="1024" cy="1024" r="512"></circle>
        <circle id="Oval" cx="-512" cy="1024" r="512"></circle>
        <circle id="Oval" cx="1024" cy="-512" r="512"></circle>
        <circle id="Oval" cx="512" cy="512" r="512"></circle>
    </svg>
</div>
</template>
<script>
import "./Intro.scss";
export default {
    methods : {
      onSkip(){
        this.$router.push("/home");
      }
    }
}
</script>