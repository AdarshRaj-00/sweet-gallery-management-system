import axios from "./axiosConfig";

const API_BASE_URL = "/api/sweets";

export const fetchAllSweets = () => {
  return axios.get(API_BASE_URL);
};

export const purchaseSweet = (id) => {
  return axios.post(`${API_BASE_URL}/${id}/purchase`);
};

export const addSweet = (sweet) => {
  return axios.post(API_BASE_URL, sweet);
};

export const deleteSweet = (id) => {
  return axios.delete(`${API_BASE_URL}/${id}`);
};
