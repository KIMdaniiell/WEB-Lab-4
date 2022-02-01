<template>
  <button class="btn">
    <p><slot></slot></p>
  </button>
</template>

<script>
export default {
  name: "MyButton"
}
</script>

<style scoped>
.btn {
  padding: 10px;
  border: 5px solid black;
  border-radius: 15px;
  font-weight: bold;
  background-color: #aab6c3;
}
</style>