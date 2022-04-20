<template>
    <div class="">
        <i class="pi pi-times product-remove-cart" @click="removeFromCart(productIndex)">X</i>
    </div>
</template>
<script>

export default {
    name : "RemoveFromCart",
    props : {
        productIndex : Number
    },
    data(){
        return {
        }
    },
    methods: {
        removeFromCart(index) {
            this.$store.dispatch('cart/removeProduct', index);
        }
    }
}
</script>