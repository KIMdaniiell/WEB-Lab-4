<template>
  <div class="graph">
    <canvas id = "canvas" width="512" height="512" @click="submitGraph">
      <img ref="curImg" :src="selectedURL" alt="img loading failed" width="512" height="512"/>
    </canvas>
  </div>
</template>

<script>
export default {
  name: "Graph",
  props: {
    radius: {
      type: Number,
    },
    dots: {
      type: Array,
      required: true,
    }
  },
  mounted() {
    var c = document.getElementById("canvas");
    var ctx = c.getContext("2d");
    var img = this.$refs.curImg;
    var self = this;

    img.onload = function () {
      ctx.clearRect(0, 0, 512, 512);
      ctx.drawImage(img, 0, 0);
      Array.from(self.dots).forEach( dot => {
        if (parseFloat(dot.r+"") === parseFloat(self.radius+"")){
          if (dot.result === 'HIT'){
            ctx.fillStyle = 'lightgreen';
          } else {
            ctx.fillStyle = 'red';
          }
          ctx.beginPath();
          ctx.arc((255+dot.x*200/dot.r), (255-dot.y*200/dot.r), 4, 0, Math.PI * 2, false);
          ctx.closePath();
          ctx.fill();
        }
      })
    }
  },
  computed: {
    selectedURL() {
      switch (this.radius) {
        case 0:
          return require('../assets/graphR0.png');
        case 0.5:
          return require('../assets/graphR05.png');
        case 1:
          return require("../assets/graphR1.png");
        case 1.5:
          return require("../assets/graphR15.png");
        case 2:
          return require("../assets/graphR2.png");
        default:
          return require("../assets/DEF.png");
      }
    },
  },
  methods: {
    submitGraph(event){
      if (this.radius === null) {
        document.getElementById("errorDisplay").innerText = "Выберите радиус!";
      } else {
        document.getElementById("errorDisplay").innerText = "";
        let calculatedX = ((event.offsetX-255)/200*this.radius).toFixed(3);
        let calculatedY = (-(event.offsetY-255)/200*this.radius).toFixed(3);
        this.$emit('dotSubmit',calculatedX,calculatedY,this.radius);
      }
    },
    showDots() {
      let img = this.$refs.curImg;
      let c = document.getElementById("canvas");
      let ctx = c.getContext("2d");
      ctx.clearRect(0, 0, 512, 512);
      ctx.drawImage(img, 0, 0);
      Array.from(this.dots).forEach( dot => {
        if (dot.r === this.radius){
          if (dot.result === 'HIT'){
            ctx.fillStyle = 'lightgreen';
          } else {
            ctx.fillStyle = 'red';
          }
          ctx.beginPath();
          ctx.arc((255+dot.x*200/dot.r), (255-dot.y*200/dot.r), 4, 0, Math.PI * 2, false);
          ctx.closePath();
          ctx.fill();
        }
      });
    },
  },
  watch: {
    dots() {
      this.showDots();
    }
  }
}
</script>

<style scoped>
.graph {
  display: flex;
  background-color: #2d333b;
  border: 5px solid black;
  border-radius: 15px;
  margin: 0 10px 0 10px;
  width: max-content;
}
</style>