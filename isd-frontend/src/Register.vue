<template>
  <v-register>
    <v-card width="400" class="mx-auto mt-5">
      <v-card-title>
        <h1 class="display-1">Login</h1>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-text-field 
          type="text"
          label="Email" 
          />
        <v-text-field 
          type="text"
          label="First Name" 
          />
          <v-text-field 
          type="text"
          label="Last Name" 
          />

        </v-form>
      </v-card-text>
      <v-divider></v-divider>
    <v-card-actions>
      <v-btn color="success" rounded>Register</v-btn>
      <v-spacer></v-spacer>
      <v-btn color="info" rounded>Login</v-btn>
    </v-card-actions>
    </v-card>
  </v-register>
</template>

<script>
//import HelloWorld from './components/HelloWorld';

export default {
  name: 'App',

 //components: {
   // HelloWorld,
  //},

  data: () => ({
    showPassword: false
  }),
};
</script>
