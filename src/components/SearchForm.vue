<template>
  <section class="dark:bg-gray-800 bg-white">
    <div class="max-w-3xl px-6 py-16 mx-auto text-center border-b">
      <h1 class="text-3xl font-semibold text-gray-800 dark:text-gray-100">
        Want to see it in action?
      </h1>
      <p class="max-w-md mx-auto mt-5 text-gray-500 dark:text-gray-400">
        Type a username and click <strong>Submit</strong> button
      </p>
      <div
        class="flex flex-col mt-8 space-y-3 sm:space-y-0 sm:flex-row sm:justify-center sm:space-x-4"
      >
        <label>
          <input
            v-model="username"
            class="px-4 py-2 text-gray-700 bg-white border border-gray-300 rounded-md focus:border-blue-500 focus:outline-none focus:ring dark:bg-gray-800 dark:text-gray-300 dark:border-gray-600 dark:focus:border-blue-500"
            maxlength="10"
            placeholder="Type a username.."
            type="search"
          />
        </label>

        <button
          type="button"
          @click="submitButton"
          class="px-4 py-2 flex transition-colors duration-200 transform bg-indigo-700 rounded-md hover:bg-indigo-600 focus:outline-none focus:bg-indigo-600 text-white ease-in text-center text-base font-semibold shadow-md focus:ring-2 focus:ring-offset-2"
        >
          <svg
            v-if="!items[0] && isLoading"
            width="20"
            height="20"
            fill="currentColor"
            class="mr-2 animate-spin"
            viewBox="0 0 1792 1792"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M526 1394q0 53-37.5 90.5t-90.5 37.5q-52 0-90-38t-38-90q0-53 37.5-90.5t90.5-37.5 90.5 37.5 37.5 90.5zm498 206q0 53-37.5 90.5t-90.5 37.5-90.5-37.5-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm-704-704q0 53-37.5 90.5t-90.5 37.5-90.5-37.5-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm1202 498q0 52-38 90t-90 38q-53 0-90.5-37.5t-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm-964-996q0 66-47 113t-113 47-113-47-47-113 47-113 113-47 113 47 47 113zm1170 498q0 53-37.5 90.5t-90.5 37.5-90.5-37.5-37.5-90.5 37.5-90.5 90.5-37.5 90.5 37.5 37.5 90.5zm-640-704q0 80-56 136t-136 56-136-56-56-136 56-136 136-56 136 56 56 136zm530 206q0 93-66 158.5t-158 65.5q-93 0-158.5-65.5t-65.5-158.5q0-92 65.5-158t158.5-66q92 0 158 66t66 158z"
            ></path>
          </svg>
          Submit
        </button>
      </div>
    </div>
  </section>
  <ResultComponent :results="items[0]" :search="0" />
</template>

<script>
import ResultComponent from "./ResultComponent.vue";
import { mapState } from "vuex";
export default {
  name: "SearchForm",
  components: { ResultComponent },
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
