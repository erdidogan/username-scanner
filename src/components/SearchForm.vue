<template>
  <section>
    <div class="max-w-3xl px-6 py-16 mx-auto text-center">
      <h1 class="text-3xl font-semibold text-gray-800 dark:text-gray-100">
        Want to see it in action?
      </h1>
      <p class="max-w-md mx-auto mt-5 text-gray-500 dark:text-gray-400">
        Type a username and click <strong>Submit</strong> button
      </p>

      <div class="mx-auto text-center flex flex-col max-w-md px-4 py-4 bg-white rounded-lg sm:px-6 md:px-8 lg:px-10">
        <div class="flex flex-col mb-2 mt-8">
          <div class=" relative ">
            <input class=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none
                            focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                   maxlength="10"
                   v-model="username"
                   placeholder="Type a username.."
                   type="search"/>
          </div>
        </div>
        <div class="flex w-full my-4 ">
          <button
              @click="submitButton"
              v-if="this.username.length >= 4"
              class="py-2 px-4 flex justify-center items-center  bg-purple-600 hover:bg-purple-700 focus:ring-purple-500 focus:ring-offset-purple-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2  rounded-lg "
              type="button">
            <svg v-if="!items[0] && isLoading" width="20" height="20" fill="currentColor" class="mr-2 animate-spin" viewBox="0 0 1792 1792" xmlns="http://www.w3.org/2000/svg">
              <path d="M526 1394q0 53-37.5 90.5t-90.5 37.5q-52 0-90-38t-38-90q0-53 37.5-90.5t90.5-37.5 90.5 37.5 37.5 90.5zm498 206q0 53-37.5 90.5t-90.5 37.5-90.5-37.5-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm-704-704q0 53-37.5 90.5t-90.5 37.5-90.5-37.5-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm1202 498q0 52-38 90t-90 38q-53 0-90.5-37.5t-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm-964-996q0 66-47 113t-113 47-113-47-47-113 47-113 113-47 113 47 47 113zm1170 498q0 53-37.5 90.5t-90.5 37.5-90.5-37.5-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm-640-704q0 80-56 136t-136 56-136-56-56-136 56-136 136-56 136 56 56 136zm530 206q0 93-66 158.5t-158 65.5q-93 0-158.5-65.5t-65.5-158.5q0-92 65.5-158t158.5-66q92 0 158 66t66 158z"/>
            </svg>
            Submit
          </button>
          <button
              v-if="this.username.length < 4"
              class="py-2 px-4 opacity-50 flex justify-center items-center  bg-purple-600 hover:bg-purple-700 focus:ring-purple-500 focus:ring-offset-purple-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2  rounded-lg "
              type="button"
              disabled>
            Submit
          </button>
        </div>
      </div>
    </div>
  </section>

  <ResultComponent :results="items[0]" :search="0"/>


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
