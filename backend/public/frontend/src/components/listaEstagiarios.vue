<template>
  <div class="infos">
    <div v-for="estagiario in estagiarios" :key="estagiario.id">
      <div class="estagiarioMostra">
        <div id="imgbox">
          <img
            height="200"
            alt="Image preview..."
            v-bind:src="getImage(estagiario.foto)"
          />
        </div>

        <div>
          <p id="estilo2">Nome:</p>
          <input v-model="estagiario.nome" />
        </div>

        <div>
          <p id="estilo2">Email:</p>
          <input v-model="estagiario.email" />
        </div>

        <div>
          <p id="estilo2">Telefone:</p>
          <input v-model="estagiario.telefone" />
        </div>

        <div>
          <p id="estilo2">Comprovante de Matrícula:<a v-bind:href="estagiario.comprovanteMatricula" title='Baixar comprovante de matrícula' v-bind:download="estagiario.nome+'_ComprovanteDeMatricula'"> Baixar</a></p>
          
        </div>

        <div>
          <p id="estilo2">Interesses:</p>
          <textarea v-model="estagiario.interesses"></textarea>
        </div>

        <div>
          <p id="estilo2">Domínios:</p>
          <textarea v-model="estagiario.dominios"></textarea>
        </div>

        <button id="atualiza" @click="updateEstagiario(estagiario.id, estagiario)">
          Atualizar</button
        ><button id="remove" @click="deleteEstagiario(estagiario.id)">
          Remover
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import api from "./api";
export default {
  name: "ListaEstagiarios",
  data() {
    return {
      estagiarios: [],
    };
  },
  methods: {
    previewFiles() {
      const preview = document.querySelector("img");
      const file = document.querySelector("input[type=file]").files[0];
      const reader = new FileReader();
      var that = this;
      reader.addEventListener(
        "load",
        function () {
          // convert image file to base64 string
          preview.src = reader.result;
          that.salvaImagem(JSON.stringify(preview.src))
        },
        false
      );

      if (file) {
        reader.readAsDataURL(file)
      }
    },
    salvaImagem(file){ 
      this.applicants.foto = file;
      console.log(file);

    },
    dataToFile(dataurl) {
      var arr = dataurl.split(","),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);

      while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
      }

      return new File([u8arr], "foto-perfil", { type: mime });
    },
    getImage(data) {
      return JSON.parse(data);
    },
    showEstagiarios: async function () {
      var teste = await axios
        .get("http://localhost:9000/api/v1/estagiario")
        .then((response) => {
          this.estagiarios = response.data;
          console.log(response);
          this.estagiarios = response.data;
          this.$forceUpdate();
        })
        .catch((err) => {
          throw err;
        });
      console.log(teste);
      console.log(this.estagiarios);
    },
    addCampo(estagiarios) {
      estagiarios.push({
        infos: "",
        id: "",
        nome: "",
        email: "",
        telefone: "",
        foto: "",
        comprovanteMatricula: "",
        interesses: "",
        dominios: "",
      });
    },
    updateEstagiario(id, estagiario) {
      api.updateForId(id, estagiario)
      
    },
    deleteEstagiario: async function(id) {
      await api.removeForId(id);
      this.showEstagiarios();
    },
    resetaLista() {
      this.estagiario.splice(1);
    },
  },
  async mounted() {
    this.showEstagiarios();
  },
};
</script>



<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#visa {
  margin: 20px auto;
  max-width: 700px;
}
label {
  display: block;
  margin: 20px 0 10px;
}
input {
  size: auto;
  font-size: 20px;
  border: 1px double rgb(102, 97, 96);
  border-radius: 4px;
}
textarea {
  size: auto;
  height: 100px;
  font-size: 20px;
  border: 1px double rgb(102, 97, 96);
  border-radius: 4px;
}
#atualiza {
  font-size: 16px;
  background: rgb(64, 171, 179);
  padding: 0.4rem 1.3rem;
  display: inline-block;
  text-align: center;
  border: none;
  cursor: pointer;
  border-radius: 4px;
  margin: 10px;
}
#remove {
  font-size: 16px;
  background: rgb(179, 64, 64);
  padding: 0.4rem 1.3rem;
  display: inline-block;
  text-align: center;
  border: none;
  cursor: pointer;
  border-radius: 4px;
  margin: 10px;
}
span {
  width: 30px;
  float: right;
  cursor: pointer;
}
span:hover {
  color: brown;
}
.estagiarioMostra {
  float: left;
  margin: 20px 10px;
  max-width: 700px;
  border: 1.5px solid;
  border-radius: 6px;
  padding: 5px;
}
img{
  max-width: 100%;
        max-height: 100%;
        object-fit: cover;
}
#imgbox{
  top: 5px;
  left: 3px;
  border: 5px solid black;
  max-width: 300px;
        max-height: 400px;
}
</style>