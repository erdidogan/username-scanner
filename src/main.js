import { createApp } from 'vue'
import App from './App.vue'
import './index.css'
import store from "./store";

const app = createApp(App);
app.config.productionTip = true;
app.config.devtools = true;
app.use(store);
app.mount("#app");