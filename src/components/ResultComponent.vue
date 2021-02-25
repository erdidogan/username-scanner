<template>
  <div class="p-8 bg-gray-100 dark:bg-gray-800 ">
    <div>
      <nav>
        <div class="max-w-7xl mx-auto px-8">
          <div class="flex items-center justify-between h-16">
            <div class="w-full justify-between flex items-center">
              <a class="hidden md:block flex-shrink-0" href="/">
                <img alt="img" class="h-8 w-8" src="../assets/icon.svg"/>
              </a>
              <div class="md:block">
                <div class="ml-10 flex items-baseline space-x-4">
                  <label class="inline-flex items-center">
                    <input
                        v-model="search"
                        :checked="search === 0"
                        class="h-5 w-5 text-red-600"
                        name="vehicle"
                        type="radio"
                        value="0"
                    />
                    <span class="ml-2 text-gray-700"> All </span>
                  </label>
                  <label class="inline-flex items-center">
                    <input
                        v-model="search"
                        :checked="search === 404"
                        class="h-5 w-5 text-red-600"
                        name="vehicle"
                        type="radio"
                        value="404"
                    />
                    <span class="ml-2 text-gray-700"> Available </span>
                  </label>
                  <label class="inline-flex items-center">
                    <input
                        v-model="search"
                        :checked="search === 200"
                        class="h-5 w-5 text-red-600"
                        name="vehicle"
                        type="radio"
                        value="200"
                    />
                    <span class="ml-2 text-gray-700"> Taken </span>
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>
      </nav>
    </div>

    <div
        v-if="this.results.length === 0"
        class="rounded-2xl p-4  dark:bg-gray-800 w-64 m-auto"
    >
      <div class="w-full h-full text-center">
        <div class="flex h-full flex-col justify-between">
          <p class="text-gray-600 dark:text-gray-400 text-xs py-2 px-6">
            . . .
          </p>
        </div>
      </div>
    </div>
    <div class="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-6 gap-4">
      <div v-for="data in resultList" class="p-4">
        <div
            class="flex-col flex justify-center items-center transition duration-500 ease-in-out transform hover:-translate-y-1 select-none cursor-pointer"
        >
          <a
              :href="data.registerUrl"
              target="_blank"
          >
            <div class="flex-shrink-0">
              <img :src="data.siteIconUrl" alt="icon" class="mx-auto object-cover rounded-full h-5 w-5"/>
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
