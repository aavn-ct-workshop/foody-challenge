<template>
    <div class="">
       <button class="add-btn" label="Add to Cart" icon="pi pi-shopping-cart" iconPos="left" @click="addToCart(product)">Add</button>
    </div>
</template>
<script>

export default {
    name : "AddToCart",
    props : {
        product : Object
    },
    data(){
        return {
        }
    },
    methods: {
        addToCart(product) {
            this.$store.dispatch('cart/addProduct', product);
        }
    }
}
</script>