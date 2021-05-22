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
            <button @click="submitButton"
                    class="flex items-center justify-center bg-blue-600 text-sm font-medium
                     w-full h-10 disabled:opacity-50 rounded text-blue-50 hover:bg-blue-700">
              Submit
            </button>
          </div>
        </div>

        <div class="bg-white rounded mt-4 shadow-lg py-6">
          <div class="px-8">
            <div class="flex items-end">
              <span class="font-semibold">Site Count</span>

            </div>
            <span class="text-xs text-gray-500 mt-2">Site Count Result </span>
          </div>
          <div v-show="isLoading" class="loader">Loading...</div>
          <div v-show="!isLoading" class="flex flex-col px-8 pt-4">
            <p class="text-md text-gray-700 text-4xl text-center dark:text-gray-50 ml-2">
              {{ items.length }}
            </p>
          </div>
        </div>
      </div>
      <div class="lg:col-span-2">
        <h2 class="text-sm font-medium">Results</h2>
        <div class="bg-white rounded mt-4 shadow-lg">

          <div class="gap-4 px-8 ">
            <div class="container flex flex-col px-5 py-24 mx-auto lg:items-center" v-if="items.length === 0">
              <img alt="img" class="h-12 w-12" src="./assets/icon.svg"/>
              <div class="flex flex-col w-full mb-4 text-left lg:text-center">
                <h3 class="mb-4 text-xl font-semibold tracking-tighter text-black sm:text-5xl title-font">
                  Username Scanner
                </h3>
                <p class="mx-auto text-base font-medium leading-relaxed text-gray-700 lg:w-2/3">This application
                  finds available usernames on popular websites. It searches more than 30 web sites and lists results.
                  It is an open source application. Please checkout <a href="https://github.com/erdidogan"
                                                                       target="_blank">
                    <b>Github</b></a> username-scanner project for more information.
                </p>
              </div>

            </div>

          </div>
        </div>

        <div class="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-4 gap-4">
          <div v-for="data in items" class="shadow-lg rounded-2xl w-36 p-4 bg-white dark:bg-gray-800
transition duration-400 ease-in-out transform hover:-translate-y-1">
            <div class="flex flex-col mx-auto">
              <a :href="data.registerUrl" target="_blank">
                <img :src="data.siteIconUrl" alt="icon" class="mx-auto object-cover rounded-full h-8"/>
                <p class="text-gray-800 text-md text-center dark:text-white font-bold my-4">
                  {{ data.siteName }}
                </p>
                <p class="text-md  text-center text-gray-800-700 ml-2" v-show="data.status === 200">
                  Taken
                </p>
                <p class="text-md  text-center text-gray-800-700 ml-2" v-show="data.status === 404">
                  Available
                </p>
              </a>
            </div>
          </div>
        </div>
      </div>

    </div>
    <!-- Component End  -->

  </section>


</template>

<script>

export default {
  data() {
    return {
      username: "",
      isLoading: false,
      items: []
    };
  },
  methods: {
    submitButton() {
      if (this.username.length > 3)
        this.isLoading = true;
      fetch("https://username-scanner-core-reactive-43dd7.ondigitalocean.app/api/v1/find/all?username=" + this.username)
          .then(response => response.json())
          .then(data => (this.items = data.list));
      setTimeout(() => this.isLoading = false, 2700);

    },
  },

  watch: {
    username: function (val) {
      this.username = val.replace(/[^a-zA-Z0-9-_.]/g, "");
    },

  },
};
</script>

<style>
.loader {
  color: #000000;
  font-size: 5px;
  margin: 25px auto;
  width: 1em;
  height: 1em;
  border-radius: 50%;
  position: relative;
  text-indent: -9999em;
  -webkit-animation: load4 1.3s infinite linear;
  animation: load4 1.3s infinite linear;
  -webkit-transform: translateZ(0);
  -ms-transform: translateZ(0);
  transform: translateZ(0);
}

@-webkit-keyframes load4 {
  0%,
  100% {
    box-shadow: 0 -3em 0 0.2em, 2em -2em 0 0em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 0;
  }
  12.5% {
    box-shadow: 0 -3em 0 0, 2em -2em 0 0.2em, 3em 0 0 0, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 -1em;
  }
  25% {
    box-shadow: 0 -3em 0 -0.5em, 2em -2em 0 0, 3em 0 0 0.2em, 2em 2em 0 0, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 -1em;
  }
  37.5% {
    box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0em 0 0, 2em 2em 0 0.2em, 0 3em 0 0em, -2em 2em 0 -1em, -3em 0em 0 -1em, -2em -2em 0 -1em;
  }
  50% {
    box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 0em, 0 3em 0 0.2em, -2em 2em 0 0, -3em 0em 0 -1em, -2em -2em 0 -1em;
  }
  62.5% {
    box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 0, -2em 2em 0 0.2em, -3em 0 0 0, -2em -2em 0 -1em;
  }
  75% {
    box-shadow: 0em -3em 0 -1em, 2em -2em 0 -1em, 3em 0em 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 0, -3em 0em 0 0.2em, -2em -2em 0 0;
  }
  87.5% {
    box-shadow: 0em -3em 0 0, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 0, -3em 0em 0 0, -2em -2em 0 0.2em;
  }
}

@keyframes load4 {
  0%,
  100% {
    box-shadow: 0 -3em 0 0.2em, 2em -2em 0 0em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 0;
  }
  12.5% {
    box-shadow: 0 -3em 0 0, 2em -2em 0 0.2em, 3em 0 0 0, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 -1em;
  }
  25% {
    box-shadow: 0 -3em 0 -0.5em, 2em -2em 0 0, 3em 0 0 0.2em, 2em 2em 0 0, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 -1em;
  }
  37.5% {
    box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0em 0 0, 2em 2em 0 0.2em, 0 3em 0 0em, -2em 2em 0 -1em, -3em 0em 0 -1em, -2em -2em 0 -1em;
  }
  50% {
    box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 0em, 0 3em 0 0.2em, -2em 2em 0 0, -3em 0em 0 -1em, -2em -2em 0 -1em;
  }
  62.5% {
    box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 0, -2em 2em 0 0.2em, -3em 0 0 0, -2em -2em 0 -1em;
  }
  75% {
    box-shadow: 0em -3em 0 -1em, 2em -2em 0 -1em, 3em 0em 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 0, -3em 0em 0 0.2em, -2em -2em 0 0;
  }
  87.5% {
    box-shadow: 0em -3em 0 0, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 0, -3em 0em 0 0, -2em -2em 0 0.2em;
  }
}
</style>