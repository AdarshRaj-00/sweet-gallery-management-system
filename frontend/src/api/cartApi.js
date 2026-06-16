import axios from "./axiosConfig";

const CART_BASE_URL = "/api/cart";

export const fetchCart = () => {
  return axios.get(CART_BASE_URL);
};

export const addToCart = (sweetId, quantity = 1) => {
  return axios.post(`${CART_BASE_URL}/add`, { sweetId, quantity });
};

export const updateCartQuantity = (sweetId, quantity) => {
  return axios.put(`${CART_BASE_URL}/update`, { sweetId, quantity });
};

export const removeFromCart = (sweetId) => {
  return axios.delete(`${CART_BASE_URL}/remove/${sweetId}`);
};

export const clearCart = () => {
  return axios.delete(`${CART_BASE_URL}/clear`);
};
