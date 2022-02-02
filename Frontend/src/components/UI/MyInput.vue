<template>
  <input :value="modelValue" @input="updateInput" class="input" type="text" maxlength="20">
</template>

<script>
export default {
  name: "my-input",
  props: {
    modelValue: [String, Number]
  },
  methods: {
    updateInput(event){
      this.$emit('update:modelValue',event.target.value)
    }
  }
}
</script>

<style scoped>
input {
  border: 3px solid black;

}
</style>