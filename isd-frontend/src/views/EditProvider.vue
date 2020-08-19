<template>
<div>
  {{provider}}
</div>
</template>

<script>
export default {
  provider: this.$route.params.item,
  name: "EditProvider",

}
</script>

<style scoped>

</style>