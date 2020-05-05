<template>
  <div id="app">
    <img style="margin-bottom: 30px;" alt="Vue logo" src="./assets/logo.png">
    <div style="margin-bottom: 10px; border-bottom: aqua solid 3px;"></div>
    <div style="width: 750px; text-align: center; margin-left: 350px;">
      <div v-for="(item, idx) of items" :key="idx" style="display: inline-flex;">
        <img :src="'data:image/jpeg;base64,' + item" alt="image"
             style="margin-right: 50px; width: 200px; margin-top: 50px;">
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'App',
  data() {
    return {
      items: [],
    }
  },
  mounted() {
    axios
      .get('/api/images')
      .then((res) => {
        this.items = res.data
      })
  },
  methods: {}
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 30px;
}
</style>
