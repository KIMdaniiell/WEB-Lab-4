<template>
  <div class="app">
    <Header></Header>
    <router-view></router-view>
  </div>
</template>

<script>
import Header from "./components/Header";
export default {
  components: {Header},
  name: "WelcomePage"
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