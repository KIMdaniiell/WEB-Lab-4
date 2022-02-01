<template>
  <div>
    <div class="topBlock">
      <Graph
          style="border: 1px solid red"
          :radius="currentR"
          :dots="dots"
      ></Graph>
      <DotForm
          style="border: 1px solid red"
          @rHasChanged="updateR"
      ></DotForm>
    </div>
    <DotForm
        style="border: 1px solid red; display: none"
    ></DotForm>

    <div class="botBlock">
      <ResultTable :dots="dots"></ResultTable>
    </div>
  </div>
</template>

<script>
import Graph from "../components/Graph";
import DotForm from "../components/DotForm";
import ResultTable from "../components/ResultTable";
export default {
  components: {ResultTable, DotForm, Graph },
  name: "MainPage",
  data(){
    return {
      dots: [
        {x: 1,y: 1,r: 1,result: 1,ctime: 1,ptime: 1},
        {x: 2,y: 2,r: 2,result: 2,ctime: 2,ptime: 2},
      ],
      currentR: '0.5',
    }
  },
  methods: {
    updateR(newValue) {
      this.currentR = newValue;
    }
  }
}
</script>

<style scoped>
.topBlock {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  margin: 10px 0;
}
.botBlock {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>