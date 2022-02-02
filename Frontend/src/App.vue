<template>
  <div class="app">
    <Header></Header>
    <router-view v-model:username="username" v-model:password="password"></router-view>
  </div>
</template>

<script>
import Header from "./components/Header";
export default {
  components: {Header},
  name: "App",
  data() {
    return {
      username: '',
      password: '',
    }
  }
}
</script>

<style>
html, body {
  width: 100%;
  height: 100%;
}
.app {
  display: flex;
  flex-direction: column;
  height: max-content;

}
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: sans-serif;
  white-space: nowrap;
}
</style>