<template>
  <section class="hero is-small is-light"  ref="element">
    <div class="hero-body">
      <div class="container is-max-desktop" >
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
        setTimeout(() => loadingComponent.close(),  2100)
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



