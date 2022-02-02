<template>
  <div>
    <div class="topBlock" >
      <Graph
          style="border: 1px solid red"
          :radius="currentR"
          :dots="dots"
          @DotSubmit="fetch"
      ></Graph>
      <DotForm
          style="border: 1px solid red"
          @rHasChanged="updateR"
          @DotSubmit="fetch"
      ></DotForm>
    </div>
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
        {x: 0,y: 1,r: 1,result: "HIT",ctime: 1,ptime: 2},
        {x: 1,y: 0,r: 1,result: "MISS",ctime: 2,ptime: 2},
        {x: 0,y: -1,r: 1,result: "HIT",ctime: 3,ptime: 2},
        {x: -1,y: 0,r: 1,result: "MISS",ctime: 4,ptime: 2},
      ],
      currentR: null,
      username: null,
      password: null,
    }
  },
  methods: {
    updateR(newValue) {
      this.currentR = parseFloat(newValue);
    },
    fetch(x,y,r) {
      let requstBody = {
        'coordinateX': parseFloat(x),
        'coordinateY': parseFloat(y),
        'radius': parseFloat(r),
        'username': this.username,
        'password': this.password,
      }
    }
  },
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