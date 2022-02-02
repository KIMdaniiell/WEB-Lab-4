<template>
  <div id="interactive">
    <form @submit.prevent>
      <div id="form">
          <div style="display: flex" class="labels">
            <label>X</label>
            <label>Y</label>
            <label>R</label>
          </div>
          <div style="display: flex" class="fields">
            <my-select v-model="selectedX" :options="xOptions"></my-select>
            <my-input v-model="inputY" placeholder="Введите значение Y"></my-input>
            <my-select v-model="selectedR" :options="rOptions" @change="$emit('rHasChanged',selectedR)"></my-select>
          </div>
      </div>
      <div class="btns">
        <MyButton class="bnt" @click="submitForm" >ОТПРАВИТЬ</MyButton>
        <MyButton class="bnt" @click="toWelcome" >ОЧИСТИТЬ</MyButton>
        <MyButton class="bnt" @click="toWelcome" >В НАЧАЛО</MyButton>
      </div>
    </form>
    <div class="error" style="border: 1px solid red">
      <span id="errorDisplay" ref="errorDisplay">Ошибок нетОшибок нетОшибок нет</span>
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
    },

    validateX() {
      let x = this.selectedX;
      let xIsValid = (x === '-2' ||
          x === '-1.5' ||
          x === '-1' ||
          x === '-0.5' ||
          x === '0' ||
          x === '0.5' ||
          x === '1' ||
          x === '1.5' ||
          x === '2');
      return xIsValid;
    },
    validateY() {
      let y = this.inputY;
      let reg1 = /^-5(\.0+)?$/;           //  [ -5.(0) ; -5 ]
      let reg2 = /^-[34](\.\d+)?$/;       //  ( -5 ; -3 ]
      let reg3 = /^-?[0-2](\.\d+)?$/;     //  ( -3 ; 3 )
      let reg4 = /^3(\.0+)?$/;            //  [ 3 ; 3.(0) )
      let reg5 = /^0+$/;                  //  (0)\
      let yIsValid = (reg1.test(y) || reg2.test(y) || reg3.test(y) || reg4.test(y) || reg5.test(y));
      return yIsValid;
    },
    validateR() {
      let r = this.selectedR;
      let rIsValid = (r === '-2' ||
          r === '-1.5' ||
          r === '-1' ||
          r === '-0.5' ||
          r === '0' ||
          r === '0.5' ||
          r === '1' ||
          r === '1.5' ||
          r === '2');
      return rIsValid;
    },
    validate() {
      let dataIsValid = this.validateX() && this.validateY() && this.validateR();
      return dataIsValid;
    },

    submitForm(){
      let e = this.$refs.errorDisplay;
      if (this.validate()) {
        let x = parseFloat(this.selectedX);
        let y = parseFloat(this.inputY);
        let r = parseFloat(this.selectedR);
        this.$emit('DotSubmit',x,y,r);
        e.innerText = "Ошибок нетОшибок нетОшибок нет";
      } else {
        e.innerText = (!this.validateX() ? 'Введите валидное значение X\n' : '') +
            (!this.validateY() ? 'Введите валидное значение Y\n' : '') +
            (!this.validateR() ? 'Введите валидное значение R\n' : '');
      }
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