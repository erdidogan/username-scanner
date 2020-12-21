<template>
  <section>
    <section class="hero" v-if="results.length !== 0">
      <div class="hero-body">
        <div class="container is-max-desktop">
          <div class="columns is-multiline">
            <div class="column is-4" v-for="(data,index) in results" v-bind:key="index">
              <div class="card " style="background-color: whitesmoke" >
                <a :href="data.registerUrl" target="_blank">
                <div class="card-content ">
                  <div class="media">
                    <div class="media-left">
                      <figure class="image is-32x32">
                        <img :src="data.siteIconUrl" alt="Site Icon">
                      </figure>
                    </div>
                    <div class="media-content">
                      <p class="title is-6"><strong>{{ data.siteName }}</strong></p>
                      <p class="subtitle is-6">@ {{ data.username }}</p>
                      <p class="card-header-title" v-if="data.statusCode === 404">
                        AVAILABLE
                      </p>
                      <p class="card-header-title" v-if="data.statusCode === 200">
                        TAKEN
                      </p>
                    </div>
                  </div>
                </div>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="hero is-medium" v-if="results.length===0">
      <div class="hero-body">
        <div class="container is-max-desktop">
          <article class="media">
            <figure class="media-left">
              <p class="image is-128x128">
                <img src="https://www.flaticon.com/svg/static/icons/svg/1076/1076336.svg" alt="A Flaticon Icon">
              </p>
            </figure>
            <div class="media-content">
              <div class="content">
                <p>
                  <strong>Username Radar</strong> by <a href="https://erdidogan.com"><small>@erdidogan</small></a>
                  <br>
                  Username radar checks 20 popular websites and tells you that a given username is available or taken on that site.
                  Type a username and hit submit button to view results.
                </p>
              </div>
            </div>
          </article>
        </div>
      </div>
    </section>
  </section>
</template>
<script>
export default {
  name: "Result",

  computed: {
    results () {
      return this.$store.getters['sites/getSites']
    },
  },

}
</script>



