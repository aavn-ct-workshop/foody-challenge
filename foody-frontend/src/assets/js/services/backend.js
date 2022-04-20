const axios = require('axios');

export async function getMerchants() {
  return await fetchJson('http://localhost:80/merchants')
}

export async function getMerchant(id) {
  return await fetchJson('http://localhost:80/merchants/' + id)
}

export async function getOrders() {
  return await fetchJson('http://localhost:80/orders');
}

export async function getOrder(id) {
  return await fetchJson('http://localhost:80/orders/' + id);
}

export function driverUpdate(data) {
  // Send a POST request
  axios.post('http://localhost:80/drivers', {
    orderId: data.orderId,
    driverId: data.driverId,
    status: data.status
  })
  .then((response) => {
    if (response.status == 400) {
      alert('Cannot accept more order');
    }
  }, (error) => {
    console.log(error)
    alert('Cannot accept more order');
  });
}

async function fetchJson(url) {
  const data = await fetch(url);
  return await data.json();
}