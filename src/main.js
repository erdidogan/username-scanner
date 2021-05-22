import { createApp } from 'vue'
import App from './App.vue'
import './index.css'

const app = createApp(App);
app.config.productionTip = true;
app.config.devtools = true;
app.mount("#app");