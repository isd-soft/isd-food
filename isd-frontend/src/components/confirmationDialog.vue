<template>

    <v-layout row justify-center>
        <v-dialog v-model="dialog1" persistent max-width="290">
            <v-card>
                <v-card-title class="headline">Use Google's location service?</v-card-title>
                <v-card-text>Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.</v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="error" text @click="close">Cancel</v-btn>
                    <v-btn color="green darken-1" text @click="close">Save</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-layout>

</template>

<script>

    export default {
        props: {
            dialog1: {
                default: false
            }
        },
        methods: {
            close() {
                this.$emit('update:dialog1', false)
            }
        }
    }

</script>