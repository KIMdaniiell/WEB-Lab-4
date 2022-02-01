<template>
  <div>
    <div class="body">
      <Clock></Clock>
      <Calendar></Calendar>
      <MyButton @click="toMain" >НА ГЛАВНУЮ</MyButton>
    </div>
  </div>
</template>

<script>
import Clock from "../components/Clock";
import Calendar from "../components/Calendar";

export default {
  components: { Calendar, Clock, },
  methods: {
    toMain() {
      this.$router.push({name: 'main'});
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
</style>
