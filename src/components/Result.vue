<template>
  <section class="hero">
    <div class="hero-body">
      <div class="container is-max-desktop">
        <div class="columns is-multiline">
          <div v-for="(data,index) in results" v-bind:key="index" class="column is-3">
            <div class="card " style="background-color: whitesmoke">
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
                      <p v-if="data.statusCode === 404" class="card-header-title">
                        AVAILABLE
                      </p>
                      <p v-if="data.statusCode === 200" class="card-header-title">
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
</template>
<script>
export default {
  name: "Result",

  computed: {
    results() {
      return this.$store.getters['sites/getSites']
    },
  },

}
</script>



