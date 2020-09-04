<template>
    <v-dialog
            v-model="$store.state.menu.createMenuError"
            persistent
            max-width="290"
            class="err-modal"
    >
        <v-card>
            <v-alert type="warning">
                Error
            </v-alert>
            <v-card-text>
                Click ok to continue
            </v-card-text>

            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                        class="white--text grey darken-2 mr-1"
                        depressed
                        @click="clickOk()"
                >Ok
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>

    export default {
        data() {
            return {};
        },
        methods: {
            clickOk(){
                this.$store.state.menu.createMenuError = false
                // this.$router.push("/allmenus");
            }
        }
    };
</script>
