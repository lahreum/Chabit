<template>
  <div class="talk" style="line-height: 1.2; height: 100%;
    font-family: 'Muli', sans-serif; max-width: 600px;
    " >
        <div class="box">
          <article class="media">
            <div class="media-left">
              <figure class="image">
                <img :src="talk.img">
              </figure>
            </div>
            <div class="media-content" >
              <div class="content">
                <p><strong>{{talk.name}}</strong> <small>{{talk.handle}}</small></p>
                <p>{{talk.talk}}</p>
              </div>
            </div>
          </article>
        </div>
      </div>
</template>

<script>
export default {
    props: {
      talk: Object
    }
}
</script>

<style>
.talk {
  background-color: #fff;
  color: #373737;
  box-shadow: 0 2px 3px rgba(10,10,10,.1), 0 0 0 1px rgba(10,10,10,.1);
  padding: 30px;
  /* + .tweet {
    margin-top: 20px;
  } */
}
.media {
  display: flex;
}
img {
  width: 64px;
  margin-right: 15px;
}
small {
  font-size: 14px;
  color: #657786;
}
</style>