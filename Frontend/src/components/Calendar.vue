<template>
  <div id="calendar">
    <div class="calendarBlock" id="day">
      <p>{{day}}</p>
    </div>
    <div class="calendarDelimiter">
      <p>.</p>
    </div>
    <div class="calendarBlock" id="month">
      <p>{{month}}</p>
    </div>
    <div class="calendarDelimiter">
      <p>.</p>
    </div>
    <div class="calendarBlock" id="year">
      <p>{{year}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "Calendar",
  data() {
    return {
      date: '',
      day: '',
      month: '',
      year: '',
    }
  },
  methods:{
    updateTime(){
      this.date = new Date();
      this.day = String(this.date.getDate()).padStart(2, '0');
      this.month = String(this.date.getMonth() + 1).padStart(2, '0');
      this.year = this.date.getFullYear();
    }
  },
  mounted() {
    this.updateTime();
    setInterval(this.updateTime,60000);
  }
}
</script>

<style scoped>
#calendar {
  display: flex;
  flex-direction: row;
}
#calendar p {
  font-size: 30px;
  color: black;
}
</style>