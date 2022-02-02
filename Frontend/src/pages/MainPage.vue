<template>
  <div>
    <div class="topBlock" >
      <Graph
          :radius="currentR"
          :dots="dots"
          @DotSubmit="fetch"
      ></Graph>
      <DotForm
          @rHasChanged="updateR"
          @dotSubmit="fetch"
          @dotsReset="resetDots"
          @logout="logout"
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
  props: {
    username: {
      type: String,
    },
    password:{
      type: String,
    },
  },
  data(){
    return {
      dots: [
        {x: 0,y: 1,r: 1,result: "HIT",ctime: 1,ptime: 2},
        {x: 1,y: 0,r: 1,result: "MISS",ctime: 2,ptime: 2},
        {x: 0,y: -1,r: 1,result: "HIT",ctime: 3,ptime: 2},
        {x: -1,y: 0,r: 1,result: "MISS",ctime: 4,ptime: 2},
      ],
      currentR: null,

    }
  },
  methods: {
    updateR(newValue) {
      this.currentR = parseFloat(newValue);
    },
    logout(){
      this.$emit('update:username','');
      this.$emit('update:password','');
    }
    ,
    fetch(x,y,r) {
      let requstBody = {
        'coordinateX': parseFloat(x),
        'coordinateY': parseFloat(y),
        'radius': parseFloat(r),
        'username': this.username,
        'password': this.password,
      }
      console.log(requstBody);
    },
    resetDots(){
      let requstBody = {
        'username': this.username,
        'password': this.password,
      }
      console.log(requstBody);

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