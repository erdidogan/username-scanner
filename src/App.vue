<template>
  <div id="app">
    <section class="hero is-bold">
      <div class="hero-body">
        <div class="container is-max-desktop">
          <article class="media">
            <figure class="media-left">
              <p class="image is-128x128">
                <img alt="A Flaticon Icon" src="https://www.flaticon.com/svg/static/icons/svg/1076/1076336.svg">
              </p>
            </figure>
            <div class="media-content">
              <div class="content">
                <p>
                  <strong>Username Radar</strong> by <a href="https://erdidogan.com"><small>@erdidogan</small></a>
                  <br>
                  Username radar checks popular websites and tells you that a given username is available or taken on
                  that site.
                  Type a username and hit submit button to view results.
                </p>
              </div>
            </div>
          </article>
        </div>
      </div>
    </section>
    <Search/>
    <Result/>
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
