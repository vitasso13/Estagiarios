import $ from 'jquery'

export async function pegaDados(){
    var todo = 0;
    await $.ajax({
        url: "http://localhost:9000/api/v1/estagiario"
    }).then(function(data) {
        //console.log("stats", status);
        //console.log("jqxhr", jqxhr);
        //console.log("data", data);
        todo = data
    })
    console.log("TODO MESMO", todo);
    return todo;
}

export default {
    createNew: (estagiario) => $.ajax({
        type: "POST",
        url: "http://localhost:9000/api/v1/estagiario",
        data: JSON.stringify(estagiario),
        contentType: 'application/json'
    }).then(function(){
        alert("Adicionado com sucesso")
    }),
    // (R)ead
    getAll: () => $.ajax({
      url: "http://localhost:9000/api/v1/estagiario"
  }).then(function(data, status, jqxhr) {
      console.log("stats", status);
      console.log("jqxhr", jqxhr);
      console.log("data", data);
  }),
    // (U)pdate
    updateForId: (id, estagiario) => $.ajax({
        type: "PUT",
        url: `http://localhost:9000/api/v1/estagiario/${id}`,
        contentType: 'application/json',
        data: JSON.stringify(estagiario)
    }).done(function () {
        console.log('SUCCESS');
    }).fail(function () {
        console.log('FAIL');
    }),
    // (D)elete
    removeForId: (id) => $.ajax({
        url: `http://localhost:9000/api/v1/estagiario/${id}`,
        type: 'DELETE',
        success: function(result) {
            console.log(result)
        }
    })
}


/**<template>
  <div id="visa">
    <h1>Controle de Aplicantes para estágio</h1>
    <form>
      <br />
      <div
        class="previous"
        v-for="(applicant, counter) in applicants"
        v-bind:key="counter"
      >
        <div @click="deleteEstagiario(counter)" v-bind="applicant.id">
          deletar
        </div>
        
        <label for="duration">Foto:</label>
        <input name="nome" type="text" v-model="applicant.foto" required />
        <label for="duration">{{ counter + 1 }}. Nome:</label>
        <input name="nome" type="text" v-model="applicant.nome" required />
        <label for="duration">Email:</label>
        <input type="text" v-model="applicant.email" required />
        <label for="duration">Telefone:</label>
        <input type="text" v-model="applicant.telefone" required />
        <label for="duration">Comprovante de Matricula:</label>
        <textarea type="text" v-model="applicant.comprovanteMatricula" required>
        </textarea>
        <label for="duration">Interesses:</label>
        <textarea type="text" v-model="applicant.interesses" required>
        </textarea>
        <label for="duration">Domínios:</label>
        <textarea type="text" v-model="applicant.dominio" required> </textarea>
      </div>
      <button @click="addEstagiario">Adicionar um estagiário</button>

      <div class="todos"></div>
    </form>
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
          nome: '',
          email: '',
          telefone: '',
          foto: '',
          comprovanteMatricula: '',
          interesses: '',
          dominio: '',
        },
      ],
      errors: []
    };
  },
  methods: {
    addEstagiario() {
      
      var oi = api.getAll();
      console.log("oi", oi)
      console.log("All estagiarios", this.applicants);
      
      this.applicants.push({
        nome: '',
        email: '',
        telefone: '',
        foto: '',
        comprovanteMatricula: '',
        interesses: '',
        dominio: '',
      });
    },
    deleteEstagiario(counter) {
      this.applicants.splice(counter, 1);
    },
  },
};
</script> */