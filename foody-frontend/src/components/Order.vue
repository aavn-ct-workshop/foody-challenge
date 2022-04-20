<template>
  <h2>Order View</h2>
  <div v-if="order">
  <div>Customer Name: {{order.hostName}}</div>
  <div>Delivery Address: {{order.deliveryAddress}}</div>
  Status: 
  <select id="status" disabled v-model="order.status">
    <option value="VALIDATING">Validating</option>
    <option value="WAITING_FOR_DRIVER">Waiting For Driver</option>
    <option value="REJECTED_BY_DRIVER">Rejected</option>
    <option value="COMPLETED">Completed</option>
    <option value="GO_TO_SHOP">Go To Shop</option>
    <option value="DELIVERING">Delivering</option>
  </select>
  <div v-for="(item, index) in order.orderItems" :key="index">
    Food: {{item.name}}
    Quantity: {{item.quantity}}
    Price: {{item.price}}
    </div>
  </div>
</template>

<script>
import { getOrder } from '../assets/js/services/backend'
export default {
  data() {
      return { order: null } 
  },
  created: async function () {
    this.order = await getOrder(this.$route.params.id);
  }
}
</script>

<style>

</style>