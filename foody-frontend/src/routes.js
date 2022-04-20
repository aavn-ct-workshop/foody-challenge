import Merchant from './components/Merchant.vue'
import Order from './components/Order.vue'
import Orders from './components/Orders.vue'
import Recommendation from './components/Recommendation.vue'
import DriverPendingOrder from './components/DriverPendingOrder.vue'
import MerchantPendingOrder from './components/MerchantPendingOrder.vue'
// import Merchant from './components/Merchant.vue'

import { createRouter, createWebHashHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'recommendation', 
    component: Recommendation,
  },
  {
    path: '/merchant/:id',
    name: 'merchant', 
    component: Merchant,
    props: true
  },
  {
    path: '/driver/orders',
    name: 'driver-pending-order', 
    component: DriverPendingOrder,
  },
  {
    path: '/merchant/orders',
    name: 'merchant-pending-order', 
    component: MerchantPendingOrder,
  },
  {
    path: '/consumer/orders/:id',
    name: 'order', 
    component: Order,
    props: true
  },
  {
    path: '/consumer/orders',
    name: 'order-list', 
    component: Orders,
    props: true
  },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
});



export default router

/*
router.beforeEach(checkForLogin())

function checkForLogin() {
  return (to, from, next) => {

    // redirect to login page if not logged in and trying to access a restricted page
    const publicPages = ['/login'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
      return next({
        path: '/login',
        query: { returnUrl: to.path }
      });
    }

    next();
  };
}
*/
