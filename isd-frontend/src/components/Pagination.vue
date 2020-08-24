<template>
  <div class="text-center">
    <v-pagination
        v-model="page"
        :length="totalPages"
        :total-visible="7"
        @input="$emit('pageChanged', page);"
        circle
        color="warning"
    ></v-pagination>
  </div>
</template>
<script>
export default {
  name: "DatePicker",
  props: ['totalPages'],
  data () {
    return {
      page: 1,
    }
  },
  methods: {}
}
</script>