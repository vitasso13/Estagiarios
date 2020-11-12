import Vue from 'vue'
import App from './App.vue'
import ListaEstagiarios from './components/listaEstagiarios.vue'
//import ListaEstagiarios from './components/listaEstagiarios.vue'
Vue.config.productionTip = false



new Vue({
  render: h => h(App),
}).$mount('#app');


new Vue({
  render: h => h(ListaEstagiarios),
}).$mount('#lista');
