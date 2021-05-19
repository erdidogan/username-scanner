<template>

  <div class="bg-white rounded mt-4 shadow-lg">

    <div class="gap-4 px-8 ">
      <div class="container flex flex-col px-5 py-24 mx-auto lg:items-center" v-if="resultList.length === 0">
        <img alt="img" class="h-12 w-12" src="../assets/icon.svg"/>
        <div class="flex flex-col w-full mb-4 text-left lg:text-center">
          <h3 class="mb-4 text-xl font-semibold tracking-tighter text-black sm:text-5xl title-font">
            Username Scanner
          </h3>
          <p class="mx-auto text-base font-medium leading-relaxed text-gray-700 lg:w-2/3">This application
            finds available usernames on popular websites. It searches more than 30 web sites and lists results.
            It is an open source application. Please checkout <a href="https://github.com/erdidogan" target="_blank">
              Github</a> username-scanner project for more information.
          </p>
        </div>

      </div>

    </div>
  </div>

  <div class="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-4 gap-4">
    <div v-for="data in resultList" class="shadow-lg rounded-2xl w-36 p-4 bg-white dark:bg-gray-800
transition duration-400 ease-in-out transform hover:-translate-y-1">
      <div class="flex flex-col mx-auto">
        <a :href="data.registerUrl" target="_blank">
          <img :src="data.siteIconUrl" alt="icon" class="mx-auto object-cover rounded-full h-8"/>
          <p class="text-gray-800 text-md text-center dark:text-white font-bold my-4">
            {{ data.siteName }}
          </p>
          <p class="text-md  text-center text-gray-800-700 ml-2">
            {{ data.status }}
          </p>
        </a>
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
  },
  data() {
    return {};
  },
  computed: {
    resultList() {
      return this.results;
    },
  },
};
</script>
