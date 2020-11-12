<template>
  <div id="geral">
    <h1>Controle de candidatos para estágio</h1>

    <br />
    <div class="previous">
      <p>Adicionar um candidato</p>
      
      <div id="left">
        <label for="foto">Foto:</label>
        <input
        name="foto"
        type="file"
        id="Image"
        class="custom-file-input"
        accept="image/*"
        @change="previewImage($event)"
        required
      /><br>
        <div id="imgbox">
          <img src="" height="200" alt="Selecione uma foto" />
        </div><br>
        
        
        <label for="duration"> Nome:</label>
        <input name="nome" type="text" v-model="applicants.nome" required />
        <label for="duration">Email:</label>
        <input type="text" v-model="applicants.email" required />
        <label for="duration">Telefone:</label>
        <input type="text" v-model="applicants.telefone" required />
      </div>
      <div id="right">
        <label for="duration">Comprovante de Matricula:</label>
        
        <div id="imgbox">
        
        
        <input
          type="file"
          id="pdf"
          class="custom-file-input"
          accept=".pdf"
          @change="previewComprovante($event)"
          required
        />
        </div>
        <label for="duration">Areas de interesses:</label>
        <textarea type="text" v-model="applicants.interesses" required>
        </textarea>
        <label for="duration">Áreas de domínio:</label>
        <textarea type="text" v-model="applicants.dominios" required>
        </textarea>
        <button @click="addEstagiario">Adicionar um estagiário</button><br>
      </div>
    </div>
    <ListaEstagiarios ref="Lista"/>
  </div>
</template>

<script>
import api from "./api";
import $ from 'jquery'
import ListaEstagiarios from './listaEstagiarios.vue'
//const emitter = mitt()

export default {
  name: "ControleEstagiarios",
  props: {
    msg: String,
  },
  components: {
    ListaEstagiarios,
  },
  data() {
    return {
      applicants: [
        {
          previous: "",
          nome: "",
          email: "",
          telefone: "",
          foto: "",
          comprovanteMatricula: "",
          interesses: "",
          dominios: "",
        },
      ],
    };
  },
  methods: {
    previewImage() {
      const preview = document.querySelector("img");
      const file = document.querySelector("input[type=file]").files[0];
      const reader = new FileReader();
      var that = this;
      reader.addEventListener(
        "load",
        function () {
          // convert image file to base64 string
          preview.src = reader.result;
          that.salvaImagem(JSON.stringify(preview.src));
        },
        false
      );

      if (file) {
        reader.readAsDataURL(file);
      }
    },
    salvaImagem(file) {
      this.applicants.foto = file;
      console.log(file);
    },
    previewComprovante(event) {
      //Read File
      var selectedFile = event.target.files;
      //Check File is not Empty
      var that = this;
      if (selectedFile.length > 0) {
        // Select the very first file from list
        var fileToLoad = selectedFile[0];
        // FileReader function for read the file.
        var fileReader = new FileReader();
        var base64;
        // Onload of file read the file content
        fileReader.onload = function (fileLoadedEvent) {
          base64 = fileLoadedEvent.target.result;
          // Print data in console
          that.salvaComprovante(base64);
        };
        // Convert data to base64
        fileReader.readAsDataURL(fileToLoad);
      }
    },
    salvaComprovante(file) {
      this.applicants.comprovanteMatricula = file;
      console.log(file);
    },
    addEstagiario: async function() {
      
      
      if (
        !this.applicants.nome ||
        !this.applicants.email ||
        !this.applicants.telefone ||
        !this.applicants.foto ||
        !this.applicants.comprovanteMatricula ||
        !this.applicants.interesses ||
        !this.applicants.dominios
      ) {
        alert("Preencha todos os campos");
        return ;
      }
      var dadosEstagiario = {
        nome: this.applicants.nome,
        email: this.applicants.email,
        telefone: this.applicants.telefone,
        foto: this.applicants.foto,
        comprovanteMatricula: this.applicants.comprovanteMatricula,
        interesses: this.applicants.interesses,
        dominios: this.applicants.dominios,
      };
      console.log(dadosEstagiario);
      await api.createNew(dadosEstagiario);
      console.log(this.applicants);
      //emitter.emit('bd-modificado');
      await this.$refs.Lista.showEstagiarios();
      this.deleteEstagiario();
    },
    deleteEstagiario() {
      $("[#image][#pdf]").val('');
      const preview = document.querySelector("img");
      preview.src = "";
      this.applicants.previous = "";
      this.applicants.nome = "";
      this.applicants.email = "";
      this.applicants.telefone = "";
      this.applicants.foto = "";
      this.applicants.comprovanteMatricula = "";
      this.applicants.interesses = "";
      this.applicants.dominios = "";
    },
  },
  mounted(){
    console.log("refs", this.$refs)
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#geral {
 font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;

  
}
label {
  display: block;
  margin: 20px 0 10px;
}
input {
  
  size: 30px;
  width: 95%;
  padding: 3px;
  font-size: 15px;
  border: 1px double rgb(102, 97, 96);
  border-radius: 4px;
}
textarea {
  font-size: 15px;
  width: 95%;
  border: 1px double rgb(102, 97, 96);
  border-radius: 4px;
}
button {
  font-size: 16px;
  background: rgb(64, 179, 140);
  padding: 0.4rem 1.3rem;
  display: block;
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
.previous {
  display: inline-block;
  border: 1.5px solid;
  border-radius: 6px;
  padding: 5px;
  margin-bottom: 10px;
}
#left {
  border: 1px solid;
  float: left;
  left: 0;
  
}
#right {
  border: 1px solid;
  float: right;
  bottom: 2px;
  margin: 5px;
}
img {
  max-width: 100%;
  max-height: 100%;
  border-radius: 7px;
  object-fit: cover;
}
#imgbox {
  top: 5px;
  left: 3px;
  border: 2px solid black;
  min-width: 300px;
  max-height: 400px;
  max-width: 300px;
  max-height: 400px;
}
.custom-file-input{
  max-width: 300px;
}
h1{
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>