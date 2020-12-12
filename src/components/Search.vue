<template>
  <div class="hero is-small is-light">
    <div class="hero-body">
      <div class="container is-max-desktop" v-show="!isOpsStarted">
        <b-field label="Username" grouped message="Type a username and press submit button" >
          <b-input  v-model="username" expanded
                    placeholder="Type a username"
                    type="text"></b-input>
          <p class="control">
            <button class="button is-dark" :disabled="valid" @click="enterClicked">Submit</button>
          </p>
        </b-field>
      </div>
      <div class="container has-text-centered" v-show="isOpsStarted">
          <button class="button is-dark" @click="clear">Start New Search</button>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  name: 'Search',
  data() {
    return {
      username: '',
      valid: true,
      isOpsStarted: false
    };
  },
  created() {
    this.$webSocketsConnect()
  },
  methods: {
    enterClicked() {
      this.$webSocketsSend(this.username.replace(/[^a-zA-Z0-9-_.]/g, ''));
      this.isOpsStarted=true
    },

    clear() {
      this.username = ''
      this.$webSocketsDisconnect()
      this.isOpsStarted = false;
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



