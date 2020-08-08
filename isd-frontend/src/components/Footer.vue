<template>
  <v-footer
    absolute
    color="warning"
    padless
  >
    <v-row
      justify="center"
      no-gutters
    >
      <v-btn
        v-for="link in links"
        :key="link"
        color="white"
        text
        rounded
        class="my-2"
      >
        {{ link }}
      </v-btn>
      <v-col
        class="orange py-4 text-center white--text"
        cols="12"
      >
        {{ new Date().getFullYear() }} — <strong>Isd-Food</strong>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script>
  export default {
    data: () => ({
      links: [
        'Home',
        'Menus',
        'About Us',
        'Contact Us',
      ],
    }),
  }
</script>