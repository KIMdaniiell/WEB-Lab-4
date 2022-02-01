<template>
  <div class="graph" >
    <canvas id = "canvas" width="512" height="512">
      <img ref="curImg" :src="selectedURL" alt="img loading failed" width="512" height="512"/>
    </canvas>
  </div>
</template>

<script>
export default {
  name: "Graph",
  props: {
    radius: {
      type: String, Number,
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
    const list = this.dots;
    img.onload = function () {
      ctx.clearRect(0, 0, 512, 512);
      ctx.drawImage(img, 0, 0);

      let dot = list[0];
      console.log(dot.x+" "+dot.y+" "+dot.r+" "+dot.result+" "+dot.ctime+" "+dot.ptime);
      dot = list[1];
      console.log(dot.x+" "+dot.y+" "+dot.r+" "+dot.result+" "+dot.ctime+" "+dot.ptime);

    }

  },
  computed: {
    selectedURL() {
      switch (this.radius) {
        case '0.5':
          return require('../assets/graphR1.png');
          break;
        case '1':
          return require("../assets/graphR2.png");
          break;
        case '1.5':
          return require("../assets/graphR3.png");
          break;
        case '2':
          return require("../assets/graphR4.png");
          break;
        default:
          return require("../assets/DEF.png");
          break;
      }
    },
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