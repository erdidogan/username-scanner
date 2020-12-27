<template>
  <section class="hero">
    <div class="hero-body">
      <div class="container is-max-desktop" v-show="results.length !== 0">
        <div class="columns is-multiline">
          <div class="column is-12">
            <!-- Main container -->
            <nav class="level notification is-light">
              <!-- Left side -->
              <div class="level-left">
                <div class="level-item">
                  <p class="subtitle is-5">
                    <strong>{{ results.length }}</strong> sites
                  </p>
                </div>
              </div>
              <!-- Right side -->
              <div class="level-right">
                <button class="level-item button is-primary" @click="listAll">List All</button>
                <button class="level-item button is-success" @click="listAvailable">List Available</button>
                <button class="level-item button is-warning" @click="listTaken">List Taken</button>
              </div>
            </nav>
          </div>
          <div v-for="(data,index) in results" v-bind:key="index" class="column is-3">
            <a :href="data.registerUrl" style="text-decoration:none;" target="_blank">
              <div :class="{'is-success': data.statusCode === 404 , 'is-warning': data.statusCode === 200,}"
                   class="tile notification">
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
    listAll(){
      this.sort = 0;
    },
    listAvailable(){
      this.sort = 1;
    },
    listTaken(){
      this.sort = 2;

    }
  },
  computed: {
    results() {
      if (this.sort === 1) {
        return this.$store.getters['sites/getSites'].filter(item => String(item.statusCode).includes("404"));
      }else if (this.sort === 2) {
        return this.$store.getters['sites/getSites'].filter(item => String(item.statusCode).includes("200"));
      }else {
        return this.$store.getters['sites/getSites']
      }
    },
  },

}
</script>



