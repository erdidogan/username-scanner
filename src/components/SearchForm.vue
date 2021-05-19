<template>
  <section class="flex items-center justify-center w-screen min-h-screen bg-gray-100 text-gray-800 p-8">
    <!-- Component Start -->
    <div class="grid lg:grid-cols-3 md:grid-cols-2 gap-8 w-full max-w-screen-lg">
      <div>
        <h2 class="text-sm font-medium">Search</h2>
        <div class="bg-white rounded mt-4 shadow-lg py-6">
          <div class="px-8">
            <div class="flex items-end">
              <span class="font-semibold">Username Scanner</span>

            </div>
            <span class="text-xs text-gray-500 mt-2"> Type a username and click <strong>Submit</strong> button</span>
          </div>

          <div class="px-8 mt-4 border-t pt-4">
            <div class="flex items-end justify-between">
              <input class=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none
                            focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                     maxlength="10"
                     v-model="username"
                     @keyup.enter="submitButton"
                     placeholder="Type a username.."
                     type="search"/>
            </div>
          </div>

          <div class="flex flex-col px-8 pt-4">
            <button  @click="submitButton"  :disabled="isDisabled"
                    class="flex items-center justify-center bg-blue-600 text-sm font-medium
                     w-full h-10 disabled:opacity-50 rounded text-blue-50 hover:bg-blue-700">
              Start Search
            </button>
          </div>
        </div>
        <div v-show="isLoading" class="loader">Loading...</div>
      </div>
      <div class="lg:col-span-2">
        <h2 class="text-sm font-medium">Results</h2>
        <ResultComponent :results="items[0]" :search="0"/>
      </div>

    </div>
    <!-- Component End  -->

  </section>


</template>

<script>
import ResultComponent from "./ResultComponent.vue";
import {mapState} from "vuex";

export default {
  name: "SearchForm",
  components: {ResultComponent},
  data() {
    return {
      username: "",
      isLoading: false,
      isDisabled :true,
    };
  },
  methods: {
    submitButton() {
      this.isLoading =true;
      this.isDisabled=true;
      let userName = this.username;
      this.$store.dispatch("fetchItems", userName);
      setTimeout(() => this.isLoading = false, 3000);
      setTimeout(() => this.isDisabled = false, 3000);

    },
  },
  computed: {
    ...mapState(["items"]),
  },
  watch: {
    username: function (val) {
      this.username = val.replace(/[^a-zA-Z0-9-_.]/g, "");
      if(val.length > 3) this.isDisabled =false
    },

  },
};
</script>
<style>
.loader,
.loader:before,
.loader:after {
  border-radius: 50%;
  width: 2.5em;
  height: 2.5em;
  -webkit-animation-fill-mode: both;
  animation-fill-mode: both;
  -webkit-animation: load7 1.8s infinite ease-in-out;
  animation: load7 1.8s infinite ease-in-out;
}
.loader {
  color: #000000;
  font-size: 10px;
  margin: 80px auto;
  position: relative;
  text-indent: -9999em;
  -webkit-transform: translateZ(0);
  -ms-transform: translateZ(0);
  transform: translateZ(0);
  -webkit-animation-delay: -0.16s;
  animation-delay: -0.16s;
}
.loader:before,
.loader:after {
  content: '';
  position: absolute;
  top: 0;
}
.loader:before {
  left: -3.5em;
  -webkit-animation-delay: -0.32s;
  animation-delay: -0.32s;
}
.loader:after {
  left: 3.5em;
}
@-webkit-keyframes load7 {
  0%,
  80%,
  100% {
    box-shadow: 0 2.5em 0 -1.3em;
  }
  40% {
    box-shadow: 0 2.5em 0 0;
  }
}
@keyframes load7 {
  0%,
  80%,
  100% {
    box-shadow: 0 2.5em 0 -1.3em;
  }
  40% {
    box-shadow: 0 2.5em 0 0;
  }
}

</style>

