<template>
  <div class="hero is-light">
    <div class="hero-body">
      <div class="container is-max-desktop">
        <div class="field is-grouped is-grouped-centered">
          <div class="control is-expanded ">
            <label class="label">Username</label>
            <input v-model="username"
                   class="input is-rounded"
                   placeholder="Type a username"
                   type="text">
            <p class="help">Type a username and press return button</p>
          </div>
        </div>
        <div class="field is-grouped">
          <div class="control">
            <button class="button is-dark" :disabled="valid" @click="enterClicked">Submit</button>
          </div>
          <div class="control">
            <button class="button is-danger is-light" @click="clear">Cancel</button>
          </div>
        </div>
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
      valid: false,
    };
  },
  methods: {
    enterClicked() {
      this.valid = true;
      this.$webSocketsSend(this.username.replace(/[^a-zA-Z0-9-_.]/g, ''));
      setTimeout(()=>{ this.valid = false; }, 12000);
    },

    clear() {
      this.valid = false;
      this.$webSocketsDisconnect()
    },


  },
  watch: {
    username: function (val) {
      this.username = val.replace(/[^a-zA-Z0-9-_.]/g, '');
    },
  },
}
</script>



