<template>
  <section class="hero ">
    <div class="hero-body">
      <div class="columns is-variable bd-klmn-columns is-4 is-multiline">
        <div v-for="(data,index) in results" v-bind:key="index" class="column is-3" >
          <a :href="data.registerUrl" style="text-decoration:none;" target="_blank">
            <div class="notification" :class="{'is-success': data.statusCode === 0, 'is-warning': data.statusCode === 1, 'is-danger': data.statusCode === '2'}"
            >
              <div class="media">
                <div class="media-left">
                  <figure class="image is-48x48">
                    <img :src="data.siteIconUrl" alt="Site Icon">
                  </figure>
                </div>
                <div class="media-content">
                  <p class="title is-5">{{ data.siteName }}</p>
                  <p class="subtitle is-6">@{{ data.username }}</p>
                </div>
              </div>
            </div>
          </a>
        </div>
      </div>
      <div class="media" v-show="results.length===0">
        <div class="media-content">
          <figure class="image is-3by1">
            <img src="https://www.flaticon.com/svg/static/icons/svg/1076/1076336.svg" alt="A Flaticon Icon">
          </figure>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
export default {
  name: "Result",

  computed: {
    results () {
      return this.$store.getters['sites/getSites']
    }
  },

}
</script>



