<template>
  <section class="flex items-center justify-center w-screen min-h-screen bg-gray-100 text-gray-800 p-8">

    <!-- Component Start -->
    <div class="grid lg:grid-cols-3 md:grid-cols-2 gap-8 w-full max-w-screen-lg">

      <div>

        <h2 class="text-sm font-medium"></h2>
        <div class="bg-white rounded mt-4 shadow-lg py-6">
          <div class="px-8">
            <img alt="img" class="h-8 w-8" src="../assets/icon.svg"/>
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
                     type="search"  />
            </div>
          </div>

          <div class="flex flex-col px-8 pt-4">
            <button  @click="submitButton"
                      class="flex items-center justify-center bg-blue-600 text-sm font-medium w-full h-10 rounded text-blue-50 hover:bg-blue-700">Start Search</button>

          </div>
        </div>
      </div>
      <div class="lg:col-span-2">
        <h2 class="text-sm font-medium">Results</h2>
        <div class="bg-white rounded mt-4 shadow-lg">
          <div class="border-t">

            <div class="grid grid-cols-2 gap-4 px-8 pb-8">
              <ResultComponent :results="items[0]" :search="0"/>

            </div>
          </div>
        </div>
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
    };
  },
  methods: {
    submitButton() {
      this.isLoading = true;
      let userName = this.username.replace(/[^a-zA-Z0-9-_.]/g, "");
      this.$store.dispatch("fetchItems", userName);
    },
  },
  computed: {
    ...mapState(["items"]),
  },
  watch: {
    username: function (val) {
      this.username = val.replace(/[^a-zA-Z0-9-_.]/g, "");
    },
  },
};
</script>
