<template>
  <h2>Merchant's Pending orders</h2>
  <div>
    <div v-for="order in orders" :key="order.id" class="order">
        <div>Merchant: {{order.hostName}}</div>
  <div>Address: {{order.deliveryAddress}}</div>
  Status: 
  <select id="status" disabled>
    <option value="VALIDATING">Validating</option>
    <option value="COMPLETED">Completed</option>
  </select>
  <div v-for="(item, index) in order.orderItems" :key="index">
    Food: {{item.name}}
    Quantity: {{item.quantity}}
    Price: {{item.price}}
    </div>
        <button>Accept</button> <button>Reject</button>
    </div>
  </div>
</template>

<script>
import { getOrders } from '../assets/js/services/backend'
export default {
    name: 'MerchantPendingOrder',
    data() {
        return { orders: getOrders() }
    },
    created() {
      getOrders().then(data => this.orders = data)
    }
}
</script>

<style>
.order {
  border: 1px solid;
}
</style>