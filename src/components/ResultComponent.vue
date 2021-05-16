<template>

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
