<template>
  <div id="header">
    <div class="heading">
      <p>КИМ ДАНИИЛ КВАНХЕНОВИЧ</p>
    </div>
    <div id="info">
      <div class="heading">
        <p>P3231</p>
      </div>
      <div class="heading">
        <p>33001</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header"
}
</script>

<style scoped>
#header {
  font-family: sans-serif;
  white-space: nowrap;
  border-bottom: 5px solid black;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-color: #aab6c3;
}
#header #info{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.heading {
  margin: 0 30px;
}
p {
  margin: 16px 0 16px 0;
}
</style>