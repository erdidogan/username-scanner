<template>
  <div class="p-8 bg-white dark:bg-gray-800 rounded-lg shadow">
    <div class="w-full flex flex-row items-center p-2 justify-between bg-white">
      <div class="ml-8 text-lg text-black md:flex">Results</div>

      <div class="flex items-center gap-8">
        <label class="inline-flex items-center">
          <input
            type="radio"
            name="vehicle"
            value="0"
            :checked="search === 0"
            class="h-5 w-5 text-red-600"
            v-model="search"
          />
          <span class="ml-2 text-gray-700"> All </span>
        </label>
        <label class="inline-flex items-center">
          <input
            type="radio"
            name="vehicle"
            value="404"
            :checked="search === 404"
            class="h-5 w-5 text-red-600"
            v-model="search"
          />
          <span class="ml-2 text-gray-700"> Available </span>
        </label>
        <label class="inline-flex items-center">
          <input
            type="radio"
            name="vehicle"
            value="200"
            :checked="search === 200"
            class="h-5 w-5 text-red-600"
            v-model="search"
          />
          <span class="ml-2 text-gray-700"> Taken </span>
        </label>
      </div>
    </div>
    <div
      v-if="this.results.length == 0"
      class="rounded-2xl p-4 bg-white dark:bg-gray-800 w-64 m-auto"
    >
      <div class="w-full h-full text-center">
        <div class="flex h-full flex-col justify-between">
          <p class="text-gray-600 dark:text-gray-400 text-xs py-2 px-6">
            List is empty !
          </p>
        </div>
      </div>
    </div>
    <div class="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-6 gap-4">
      <div v-for="data in resultList" class="p-4">
        <div
          class="flex-col flex justify-center items-center transition duration-500 ease-in-out transform hover:-translate-y-1 hover:shadow-lg select-none cursor-pointer"
        >
          <a
            :href="data.registerUrl"
            style="text-decoration: none"
            target="_blank"
          >
            <div class="flex-shrink-0">
              <img :src="data.siteIconUrl" alt="icon" class="mx-auto h-8 w-8" />
            </div>
            <div class="mt-2 text-center flex flex-col">
              <span class="text-gray-600 dark:text-white text-lg font-medium">
                {{ data.siteName }}
              </span>
              <span
                v-show="data.statusCode === 404"
                class="text-xs text-green-600"
              >
                Available
              </span>
              <span
                v-show="data.statusCode === 200"
                class="text-xs text-yellow-600"
              >
                Taken
              </span>
            </div>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ResultComponent.vue",
  props: {
    results: {
      type: Array,
      default: [],
    },
    search: {
      type: Number,
      default: [],
    },
  },
  data() {
    return {};
  },
  computed: {
    resultList() {
      if (this.search == 200) {
        return this.results.filter((r) => {
          return r.statusCode === 200;
        });
      } else if (this.search == 404) {
        return this.results.filter((r) => {
          return r.statusCode === 404;
        });
      } else {
        return this.results;
      }
    },
  },
};
</script>
