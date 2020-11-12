import Vue from 'vue'
import ControleEstagiarios from './components/controleEstagiarios.vue'


//import ListaEstagiarios from './components/listaEstagiarios.vue'
Vue.config.productionTip = false



new Vue({
  render: h => h(ControleEstagiarios),
}).$mount('#app');



