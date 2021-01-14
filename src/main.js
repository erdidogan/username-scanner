import Vue from 'vue'
import App from './App.vue'
import Buefy from 'buefy';
import 'buefy/dist/buefy.css';
import store from './store'
import apiService from './services/api'

Vue.config.productionTip = false
Vue.use(Buefy);

Vue.use(apiService, {
    store,
    baseURL: 'https://uname-api.herokuapp.com'
})

new Vue({
    store,
    render: h => h(App)
}).$mount('#app')

