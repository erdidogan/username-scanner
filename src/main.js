import Vue from 'vue'
import App from './App.vue'
import store from './store'
import webSocketService from './services/webSocketService'

import Buefy from 'buefy';
import 'buefy/dist/buefy.css';

Vue.config.productionTip = false;
Vue.use(Buefy);

Vue.config.productionTip = false

Vue.use(webSocketService, {
  store,
  url: ''
})

new Vue({
  store,
  render: h => h(App)
}).$mount('#app')
