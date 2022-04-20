<template>
  <h2>Driver's Pending orders</h2>
  <div>
    <div v-for="order in orders" :key="order.id" class="order">
      <div v-if="order.status == 'WAITING_FOR_DRIVER'">
        <div>Customer Name: {{order.hostName}}</div>
        <div>Address: {{order.deliveryAddress}}</div>
        Status: 
        <select id="status" disabled v-model="order.status">
          <option value="WAITING_FOR_DRIVER">Waiting For Driver</option>
        </select>
          <div v-for="(item, index) in order.orderItems" :key="index">
          Food: {{item.name}}
          Quantity: {{item.quantity}}
          Price: {{item.price}}
        </div>
            <button @click="updateStatus(order, 'APPROVED')">Accept</button> 
            <button @click="updateStatus(order, 'REJECTED')">Reject</button>
      </div>
      <div v-if="order.status == 'GO_TO_SHOP'">
        <div>Customer Name: {{order.hostName}}</div>
        <div>Address: {{order.deliveryAddress}}</div>
        Status: 
        <select id="status" disabled v-model="order.status">
          <option value="GO_TO_SHOP">Delivering</option>
        </select>
          <div v-for="(item, index) in order.orderItems" :key="index">
          Food: {{item.name}}
          Quantity: {{item.quantity}}
          Price: {{item.price}}
        </div>
          <button @click="updateStatus(order, 'COMPLETED')">Finish order</button> 
      </div>
    </div>
  </div>
</template>

<script>
import { getOrders, driverUpdate } from '../assets/js/services/backend'
export default {
  name: 'DriverPendingOrder',
  data() {
    return { orders: [] }
  },
  created() {
    getOrders().then(data => this.orders = data)
  },
  methods: {
    updateStatus(order, state) {
      const data = { 
        status: state,
        orderId: order.id,
        //to be updated
        driverId: 'vu.nguyen'
      }
      driverUpdate(data);
    }
  }
}
</script>

<style>
.order {
  border: 1px solid;
}
</style>