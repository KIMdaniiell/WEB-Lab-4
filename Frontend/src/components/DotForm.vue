<template>
  <div id="interactive">
    <form>
      <div id="form">
          <div style="display: flex" class="labels">
            <label>X</label>
            <label>X</label>
            <label>R</label>
          </div>
          <div style="display: flex" class="fields">
            <my-select v-model="selectedX" :options="xOptions"></my-select>
            <my-input v-model="inputY" placeholder="Введите значение Y" ></my-input>
            <my-select v-model="selectedR" :options="rOptions" @change="$emit('rHasChanged',selectedR)"></my-select>
          </div>
      </div>
      <div class="btns">
        <MyButton class="bnt" @click="toWelcome" >ОТПРАВИТЬ</MyButton>
        <MyButton class="bnt" @click="toWelcome" >ОЧИСТИТЬ</MyButton>
        <MyButton class="bnt" @click="toWelcome" >В НАЧАЛО</MyButton>
      </div>
    </form>
    <div style="border: 1px solid red">
      <span>Ошибок нетОшибок нетОшибок нетОшибок нетОшибок нетОшибок нет</span>
    </div>
  </div>
</template>

<script>
import MyInput from "./UI/MyInput";
export default {
  name: "DotForm",
  components: {MyInput},
  data () {
    return {
      xOptions: [
        {value:'-2'},
        {value:'-1.5'},
        {value:'-1'},
        {value:'-0.5'},
        {value:'0'},
        {value:'0.5'},
        {value:'1'},
        {value:'1.5'},
        {value:'2'},
      ],
      selectedX: '',
      rOptions: [
        {value:'-2'},
        {value:'-1.5'},
        {value:'-1'},
        {value:'-0.5'},
        {value:'0'},
        {value:'0.5'},
        {value:'1'},
        {value:'1.5'},
        {value:'2'},
      ],
      selectedR: '',
      inputY: '',
    }
  },
  methods: {
    toWelcome() {
      this.$router.push({name: 'welcome'});
    }
  }
}
</script>

<style scoped>
#interactive {
  width: max-content;
  border: 0;
  min-height: 350px;
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
  flex-direction: row;
  justify-content: space-between;
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