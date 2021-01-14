<template>
  <section class="hero">
    <div class="hero-body">
      <div v-show="results.length !== 0" class="container is-max-desktop">
        <div class="columns is-multiline">
          <div class="column is-12">
            <nav class="level notification ">
              <!-- Left side -->
              <div class="level-left">
                <div class="level-item">
                  <p class="subtitle is-5" v-if="results[0]">
                    Result: <strong>{{ results[0].length }}</strong> sites
                  </p>
                </div>
              </div>
              <!-- Right side -->
              <div class="level-right">
                <div class="level-item">
                  <p class="subtitle">
                    Available:
                    <b-tag type="is-success"></b-tag>
                  </p>
                </div>
                <div class="level-item">
                  <p class="subtitle">
                    Taken:
                    <b-tag type="is-warning"></b-tag>
                  </p>
                </div>
              </div>
            </nav>
          </div>
          <div v-for="(data,index) in results[0]" v-bind:key="index" class="column is-3">
            <a :href="data.registerUrl" style="text-decoration:none;" target="_blank">
              <div :class="{'is-success': data.statusCode === 404 , 'is-warning': data.statusCode === 200}"
                   class="tile notification">
                <article class="media">
                  <figure class="media-left">
                    <p class="image is-32x32">
                      <img :src="data.siteIconUrl" alt="Site Icon">
                    </p>
                  </figure>
                  <div class="media-content">
                    <div class="content">
                      <p>
                        <strong>{{ data.siteName }}</strong>
                      </p>
                    </div>
                  </div>
                </article>
              </div>
            </a>
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



