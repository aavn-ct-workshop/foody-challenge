import Vuex from 'vuex';
import products from "./modules/products"
import cart from "./modules/cart"
import { alert } from "./modules/alert";
import { authentication } from "./modules/authentication";

export default new Vuex.Store({
  modules: {
    products,
    cart,
    authentication,
    alert,
  }
});
