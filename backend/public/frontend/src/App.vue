<template>
  <div id="app">
    <ControleEstagiarios/>
  </div>
</template>
<script>
import ControleEstagiarios from './components/controleEstagiarios.vue'

export default {
  name: 'app',
  components: {
    ControleEstagiarios,
  }
}
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>