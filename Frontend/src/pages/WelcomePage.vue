<template>
  <div>
    <div class="body">
      <Clock></Clock>
      <Calendar></Calendar>
      <div class="btns">
        <MyButton class="btn" @click="toMain" >НА ГЛАВНУЮ</MyButton>
        <MyButton class="btn" @click="signIn" >АВТОРИЗАЦИЯ</MyButton>
        <MyButton class="btn" @click="signUp" >РЕГИСТРАЦИЯ</MyButton>
        <MyDialog v-model:show="dialogVisible">
          <UserForm @userSubmit="userFormHandle" />
        </MyDialog>
      </div>
    </div>
  </div>
</template>

<script>
import Clock from "../components/Clock";
import Calendar from "../components/Calendar";
import MyDialog from "../components/UI/MyDialog";
import UserForm from "../components/UserForm";

export default {
  components: {UserForm, MyDialog, Calendar, Clock, },
  name: "WelcomePage",
  props: {
    username: {
      type: String,
    },
    password:{
      type: String,
    },
  },
  data() {
    return {
      dialogVisible: false,
      entranceMode: 'registration',
    }
  },
  methods: {
    toMain() {
      this.$router.push({name: 'main'});
    },
    signIn() {
      this.entranceMode = 'authorization';
      this.dialogVisible=true;
    },
    signUp() {
      this.entranceMode = 'registration';
      this.dialogVisible=true;
    },
    userFormHandle(login, password) {
      console.log(`${login}  : ${password}`)
      this.dialogVisible = false
      this.$emit('update:username',login);
      this.$emit('update:password',password);
      let request = {
        'username': login,
        'password': password,
      }
      console.log( this.entranceMode, request );

      if (true){
        this.toMain();
      }
    }
  }
}
</script>

<style scoped>
.body {
  height: 100%;
  min-height: 400px;
  margin-top: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
.btns {
  display: flex;
  flex-direction: row;
}
.btn {
  margin: 0 10px;
}
</style>
