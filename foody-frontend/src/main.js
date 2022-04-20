import { createApp } from "vue";
import 'primeflex/primeflex.css';

import App from "./App.vue";
import router from "./routes";
import store from "./store";

const app = createApp(App);

app.use(router);
app.use(store);
app.mount('#app')
