import Vue from 'vue'
import App from './App.vue'
// import VueLazyload from 'vue-lazyload';

Vue.config.productionTip = false;

new Vue({
  render: h => h(App),
}).$mount('#app');


// Vue.use(VueLazyload, {
//   preLoad: 1.3, // 事前ロードする高さの割合指定
//   error: 'images/error.png', // エラー時に表示する画像指定
//   loading: 'images/loading.gif', // ロード中に表示する画像指定
//   attempt: 1 // ロード失敗した時のリトライの上限指定
// });
