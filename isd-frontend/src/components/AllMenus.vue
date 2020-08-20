<template>
    <div>
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-warning">Menus</h6>

            </div>
            {{menus}}
            <div class="card-body">
                <div  class="table-responsive">
                    <div  class="tab-content" id="pills-tabContent">
                        <!---Current--->
                        <div  class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                            <table class="table table-bordered"  width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Provider</th>
                                    <th>Day</th>
                                    <th>Available</th>
                                    <th>Delete</th>
                                    <th>Edit</th>
                                </tr>
                                </thead>
                                <tbody v-for="menu of menus" :key="menu.id">
                                    <td>{{menu.name}}</td>
                                    <td>{{menu.provider.name}}</td>
                                    <td>{{menu.dayOfWeek}}</td>
                                    <td>Available</td>
                                    <td>Delete</td>
                                    <td>Edit</td>
<!--                                    <td class="text-center">-->
<!--                                        <button type="submit" @click="deleteOrder(order.id)" onclick="window.location.reload();">-->
<!--                                            <v-icon  data-toggle="modal"  data-target="#exampleModal">fas fa-trash-alt</v-icon>-->
<!--                                        </button>-->
<!--                                    </td>-->
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

    import api from './backend-api';

    export default {

        name: "Home",
        data (){
            return{
                menus: [],
                userHistory:[],
                getHistory: false,
            }
        },
        methods:{

            callMenuApi () {
                api.getMenu().then(response => {
                    this.products = response.data;
                    console.log(response.data)
                })
                    .catch(error => {
                        this.errors.push(error)
                    })
            },

            deleteOrder(id){
                api.deleteOrder(id)
            }
        },
        beforeCreate() {

            api.getMenu().then(response => {
                this.menus = response.data;
                console.log(response.data)
            })
                .catch(error => {
                    this.errors.push(error)
                })
        }
    };

</script>

<style>

</style>