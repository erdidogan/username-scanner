<template>
  <section class="hero">
    <div class="hero-body">
      <div v-show="results.length !== 0" class="container is-max-desktop">
        <div class="columns is-multiline">
          <div class="column is-12">
            <nav class="level notification is-light">
              <!-- Left side -->
              <div class="level-left">
                <div class="level-item">
                  <p class="subtitle is-5">
                    Result: <strong>{{ results.length }}</strong> sites
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
          <div v-for="(data,index) in results" v-bind:key="index" class="column is-3">
            <a :href="data.registerUrl" style="text-decoration:none;" target="_blank">
              <div :class="{'is-success': data.statusCode === 404 , 'is-warning': data.statusCode === 200,}"
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
                        <strong v-if="data.siteName === 'Buy Me A Coffee' ">BuyMeACoffee</strong>
                        <strong v-else>{{ data.siteName }}</strong>
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
  data() {
    return {
      sort: 0,
    };
  },
  methods: {
    listAll() {
      this.sort = 0;
    },
    listAvailable() {
      this.sort = 1;
    },
    listTaken() {
      this.sort = 2;

    }
  },
  computed: {
    results() {
      if (this.sort === 1) {
        return this.$store.getters['sites/getSites'].filter(item => String(item.statusCode).includes("404"));
      } else if (this.sort === 2) {
        return this.$store.getters['sites/getSites'].filter(item => String(item.statusCode).includes("200"));
      } else {
        return this.$store.getters['sites/getSites']
      }
    },
  },

}
</script>



