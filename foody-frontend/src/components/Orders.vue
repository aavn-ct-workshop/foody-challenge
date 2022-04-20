<template>
  <h2>All Orders</h2>
  <div>
    <div v-for="order in orders" :key="order.id" class="order">
      <div>
        <div>Customer Name: {{order.hostName}}</div>
        <div>Delivery Address: {{order.deliveryAddress}}</div>
        <div v-if="order.driverId">Driver: {{order.driverId}}</div>
        Status: 
        <select id="status" disabled v-model="order.status">
          <option value="VALIDATING">Validating</option>
          <option value="WAITING_FOR_DRIVER">Waiting For Driver</option>
          <option value="REJECTED_BY_DRIVER">Rejected</option>
          <option value="COMPLETED">Completed</option>
          <option value="GO_TO_SHOP">Delivering</option>
          <option value="DELIVERING">Delivering</option>
        </select>
          <div v-for="(item, index) in order.orderItems" :key="index">
          Food: {{item.name}}
          Quantity: {{item.quantity}}
          Price: {{item.price}}
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getOrders } from '../assets/js/services/backend'
export default {
  name: 'Orders',
  data() {
    return { orders: [] }
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