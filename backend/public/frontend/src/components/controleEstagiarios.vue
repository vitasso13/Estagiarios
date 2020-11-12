<template>
  <div id="visa">
    <h1>Controle de candidatos para estágio</h1>

    <br />
    <div
      class="previous"      
    >
      <label for="duration">Foto:</label>
      
      <input
        type="file"
        id="file"
        class="custom-file-input"
        accept="image/*"
        @change="previewImage($event)"
        required
      />
      <div id="left">
        <div id="imgbox">
      <img src="" height="200" alt="Image preview..." />
        </div>
      <label for="duration"> Nome:</label>
      <input name="nome" type="text" v-model="applicants.nome" required />
      <label for="duration">Email:</label>
      <input type="text" v-model="applicants.email" required />
      <label for="duration">Telefone:</label>
      <input type="text" v-model="applicants.telefone" required />
      </div>
      <div id="right">
      <label for="duration">Comprovante de Matricula:</label>
      <input
        type="file"
        id="pdf"
        class="custom-file-input"
        accept=".pdf"
        @change="previewComprovante($event)"
        required
      />
      
      <label for="duration">Interesses:</label>
      <textarea type="text" v-model="applicants.interesses" required> </textarea>
      <label for="duration">Domínios:</label>
      <textarea type="text" v-model="applicants.dominios" required> </textarea>
      <button @click="addEstagiario">Adicionar um estagiário</button>
      </div>
      
    
    </div>
  </div>
</template>

<script>
import api from "./api";
export default {
  name: "ControleEstagiarios",
  props: {
    msg: String,
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
            fileReader.onload = function(fileLoadedEvent) {
                base64 = fileLoadedEvent.target.result;
                // Print data in console
                that.salvaComprovante(base64)
            };
            // Convert data to base64
            fileReader.readAsDataURL(fileToLoad);
        }
    },
    salvaComprovante(file){ 
      this.applicants.comprovanteMatricula = file;
      console.log(file);

    },
    addEstagiario() {
      if (
        !this.applicants.nome ||
        !this.applicants.email ||
        !this.applicants.telefone ||
        !this.applicants.foto ||
        !this.applicants.comprovanteMatricula ||
        !this.applicants.interesses ||
        !this.applicants.dominios
      ) {
        return;
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
      api.createNew(dadosEstagiario);
      console.log(this.applicants);

      this.deleteEstagiario();
    },
    deleteEstagiario() {
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
  size: 30px;
  font-size: 15px;
  border: 1px double rgb(102, 97, 96);
  border-radius: 4px;
}
textarea {
  font-size: 15px;
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
  float: left;
  bottom: 2px;
  margin: 20px 10px;
  max-width: 700px;
  padding: 5px;
}
#right {
  float: left;
  margin: 20px 10px;
  max-width: 700px;
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