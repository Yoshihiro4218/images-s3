<template>
  <div id="app">
    <img style="margin-bottom: 30px;" alt="Vue logo" src="./assets/logo.png">
    <div style="text-align: right">
      <label v-if="!value" class="upload-content-space user-photo default">
        <input ref="file" class="file-button" type="file" @change="upload"/>
      </label>
    </div>

    <div style="margin-bottom: 10px; border-bottom: aqua solid 3px;"></div>
    <div style="width: 750px; text-align: center; margin-left: 350px;">
      <div v-for="(item, idx) of items" :key="idx" style="display: inline-flex;">
        <img :src="'data:image/jpeg;base64,' + item" alt="image"
             style="margin-right: 50px; margin-top: 50px; width: 200px; height: 200px;">
<!--        <img :src="item" alt="image"-->
<!--             style="margin-right: 50px; width: 200px; margin-top: 50px;">-->
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
  methods: {
    async upload(event) {
      const files = event.target.files || event.dataTransfer.files;
      const file = files[0];
      if (this.checkFile(file)) {
        const picture = await this.getBase64(file);
        axios.post('/api/images', {
          image: picture
        })
          .then(() => {
            location.reload();
          })
      }
    },
    getBase64(file) {
      return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error)
      })
    },
    checkFile(file) {
      let result = true;
      const SIZE_LIMIT = 5000000; // 5MB
      // キャンセルしたら処理中断
      if (!file) {
        result = false
      }
      // jpeg か png 関連ファイル以外は受付けない
      // if (file.type !== 'image/jpeg' && file.type !== 'image/png') {
      //   result = false
      // }

      // jpeg 以外は受付けない
      if (file.type !== 'image/jpeg') {
        result = false
      }
      // 上限サイズより大きければ受付けない
      if (file.size > SIZE_LIMIT) {
        result = false
      }
      return result
    }
  }
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
