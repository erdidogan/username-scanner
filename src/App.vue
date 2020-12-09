<template>
  <div id="app">
    <section class="hero is-small is-black is-bold">
      <div class="hero-body">
        <div class="container">
          <div class="container has-text-centered">
            <p class="title">
              Username Radar
            </p>
            <p class="subtitle">
              Check available usernames for popular social platforms
            </p>
          </div>
        </div>
      </div>
    </section>
    <Search/>
    <Result />
  </div>
</template>

<script>

import Search from "@/components/Search";
import Result from "@/components/Result";


export default {
  name: 'App',
  components: {
    Result,
    Search,
  }
}
</script>
