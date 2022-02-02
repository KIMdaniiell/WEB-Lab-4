<template>
  <div id="interactive">
    <form @submit.prevent>
      <div id="form">
        <div style="display: flex" class="labels">
          <label>Логин: </label>
          <label>Пароль: </label>
        </div>
        <div style="display: flex" class="fields">
          <my-input v-model="inputtedUsername" placeholder="Введите логин"></my-input>
          <my-input v-model="inputtedPassword" placeholder="Введите пароль"></my-input>
        </div>
      </div>
      <div class="btns">
        <MyButton @click="userFormSubmit" class="bnt"  >ПОДТВЕРДИТЬ</MyButton>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "UserForm",
  data() {
    return{
      inputtedUsername: '',
      inputtedPassword: '',
    }
  },
  methods: {
    userFormSubmit() {
      this.$emit('userSubmit',this.inputtedUsername, this.inputtedPassword);
    }
  }
}
</script>

<style scoped>
#interactive {
  width: max-content;
  border: 0;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
#interactive form #form{
  padding: 10px;
  min-height: 200px;
  min-width: 300px;

  border: 5px solid black;
  border-radius: 15px;

  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-content: center;
  align-items: stretch;
  background-color: #aab6c3;
}
#interactive form #form .labels{
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  flex-wrap: nowrap;
  align-items: center;
  flex-grow: 1;
}
#interactive form #form .fields{
  display: flex;
  flex-direction: column;
  align-content: center;
  align-items: stretch;
  justify-content: space-evenly;
  flex-grow: 4;
}
#interactive form .btns {
  padding: 10px;
  display: flex;
  flex-direction: column;

}
#interactive form .btns .bnt {
  margin: 10px;
  padding: 10px;
  border: 5px solid black;
  border-radius: 15px;
  font-weight: bold;
  background-color: #aab6c3;
}
</style>