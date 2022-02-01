<template>
  <div id="resultTable">
    <table id="result_table">
      <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Результат</th>
        <th>Время</th>
        <th>Выполнение</th>
      </tr>
      <tr v-for="dot in dots" :key="dot.ctime">
        <td>{{dot.x}}</td>
        <td>{{dot.y}}</td>
        <td>{{dot.r}}</td>
        <td>{{dot.result}}</td>
        <td>{{dot.ctime}}</td>
        <td>{{dot.ptime}}</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: "ResultTable",
  data() {
    return{

    }
  },
  props: {
    dots: {
      type: Array,
      required: true,
    }
  }
}
</script>

<style scoped>
table {
  min-width: 700px;
  text-align: center;
  border-collapse: collapse;
}
table, th, td {
  border: 5px solid black;

}
table th{
   background-color: #aab6c3;
 }
</style>