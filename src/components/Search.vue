<template>
<section>
  <section class="hero is-small is-light">
    <div class="hero-body">
      <div class="container is-max-desktop">
        <b-field label="Username" grouped>
          <b-input  v-model="username" expanded
                    placeholder="Type a username"
                    type="text"></b-input>
          <p class="control">
            <button class="button is-dark" :disabled="valid" @click="enterClicked">Submit</button>
          </p>
          <p class="control">
            <button class="button is-primary"  @click="clear">Clear</button>
          </p>
        </b-field>
      </div>
    </div>
  </section>
  <section class="hero" >
    <div class="hero-body" >
      <div class="container is-max-desktop" ref="element">
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
  name: 'Search',
  data() {
    return {
      username: '',
      valid: true,
    };
  },
  created() {
    this.$webSocketsConnect()
  },
  methods: {
    enterClicked() {
        this.$webSocketsSend(this.username.replace(/[^a-zA-Z0-9-_.]/g, ''));
        const loadingComponent = this.$buefy.loading.open({
          container: this.$refs.element
        })
        setTimeout(() => loadingComponent.close(), 3 * 1000)
    },

    clear() {
      this.username = ''
      return this.$store.dispatch('sites/clearSites', [])
    },
  },
  watch: {
    username: function (val) {
      this.username = val.replace(/[^a-zA-Z0-9-_.]/g, '');
      this.valid = val.length < 4;

    },
  },
}
</script>



