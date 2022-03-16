<template>
  <div>
    <div class="topBlock" >
      <Graph
          :radius="currentR"
          :dots="dots"
          @dotSubmit="send"
      ></Graph>
      <DotForm
          @rHasChanged="updateR"
          @dotSubmit="send"
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
  mounted() {
    console.log("Main Page is mounted!");
    let requstBody = {
      'username': this.username,
      'password': this.password,
    }
    let url = '/api/points/observe';
    fetch(url, {
      method: 'POST',
      body: JSON.stringify(requstBody),
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
      },
    }).then(response => response.json())
        .then(data => {
          if (data.updateStatus === "true"){
            this.dots = [];
            data.dots.forEach( ddot => {
              this.dots.push(
                  {x: parseFloat(ddot.coordinateX.replace(',','.')),
                    y: parseFloat(ddot.coordinateY.replace(',','.')),
                    r: parseFloat(ddot.radius.replace(',','.')),
                    result: ddot.result,
                    ctime: ddot.currentTime,
                    ptime: parseFloat(ddot.processingTime.replace(',','.'))
                  })
            });
          } else {
          }
        });
  },
  data(){
    return {
      dots: [],
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
    send(x,y,r) {
      let requstBody = {
        'coordinateX': parseFloat(x),
        'coordinateY': parseFloat(y),
        'radius': parseFloat(r),
        'username': this.username,
        'password': this.password,
      }
      let url = '/api/points/add';
      fetch(url, {
        method: 'POST',
        body: JSON.stringify(requstBody),
        headers: {
          'Content-type': 'application/json; charset=UTF-8',
        },
      }).then(response => response.json())
          .then(data => {
            if (data.updateStatus === "true"){
              this.dots = [];
              data.dots.forEach( ddot => {
                this.dots.push(
                    {x: parseFloat(ddot.coordinateX.replace(',','.')),
                      y: parseFloat(ddot.coordinateY.replace(',','.')),
                      r: parseFloat(ddot.radius.replace(',','.')),
                      result: ddot.result,
                      ctime: ddot.currentTime,
                      ptime: parseFloat(ddot.processingTime.replace(',','.'))
                    })
              });
            } else {
            }
          });
    },
    resetDots(){
      let requstBody = {
        'username': this.username,
        'password': this.password,
      }
      let url = '/api/points/reset';
      fetch(url, {
        method: 'POST',
        body: JSON.stringify(requstBody),
        headers: {
          'Content-type': 'application/json; charset=UTF-8',
        },
      });
      this.dots = [];
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