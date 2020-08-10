<template>
  <v-app>
    <v-main>
      <Header/>
      <router-view/>
      <Footer/>
    </v-main>
  </v-app>
</template>

<script>

import Footer from './components/Footer';
import Header from './components/Header';

export default {
  name: 'App',

  components: {
    Footer,
    Header,
  },

  data: () => ({
    //
  }),
};
</script>
