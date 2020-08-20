<template>
  <v-sheet
      class="px-3 pt-3 pb-3"
  >
    <v-skeleton-loader
        class="mx-auto"
        max-width="300"
        type="list-item"
    ></v-skeleton-loader>
  </v-sheet>
</template>
<script>
export default {
  name: "SkeletonLoader",
  data() {
    return {}
  },
  methods: {}

}
</script>