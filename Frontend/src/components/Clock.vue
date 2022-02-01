<template>
  <div id="clock">
    <div class="clockBlock" id="hours">
      <p>{{hours}}</p>
    </div>
    <div class="clockDelimiter">
      <p>:</p>
    </div>
    <div class="clockBlock" id="minutes">
      <p>{{minutes}}</p>
    </div>
    <div class="clockDelimiter">
      <p>:</p>
    </div>
    <div class="clockBlock" id="seconds">
      <p>{{seconds}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "Clock",
  data() {
    return {
      date: '',
      hours: '',
      minutes: '',
      seconds: '',
    }
  },
  methods:{
    updateTime(){
      this.date = new Date();
      this.hours = String(this.date.getHours()).padStart(2, '0');
      this.minutes = String(this.date.getMinutes()).padStart(2, '0');
      this.seconds = String(this.date.getSeconds()).padStart(2, '0');
    }
  },
  mounted() {
    this.updateTime();
    setInterval(this.updateTime,1000);
  }
}
</script>

<style scoped>
#clock{
  width: max-content;
  min-width: 600px;
  display: flex;
  flex-direction: row;
  justify-content: center;

  border: 5px solid black;
  border-radius: 15px;
  background-color: #aab6c3;
}
.clockBlock {
  margin: 0 5px;
  height: 150px;
  width: 150px;

  display: flex;
  justify-content: center;
  align-items: center;
}
.clockDelimiter {
  margin: 0 5px;
  height: 150px;
  width: 10px;

  display: flex;
  justify-content: center;
  align-items: center;
}
div p {
  font-size: 100px;
  color: black;
}
</style>