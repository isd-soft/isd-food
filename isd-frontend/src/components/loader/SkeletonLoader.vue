<template>
  <v-sheet
      class="px-3 pt-3 pb-3"
  >
    <div class="row" v-for="n in rows" :key="n">
      <v-skeleton-loader

          class="mx-auto"
          max-width="600"
          type="list-item"
      ></v-skeleton-loader>
    </div>

  </v-sheet>
</template>
<script>
export default {
  name: "SkeletonLoader",
  props: ['rows'],
  data() {
    return {}
  },
  methods: {}

}
</script>