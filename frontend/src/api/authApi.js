import axios from "./axiosConfig";

const AUTH_BASE = "/api/auth";

export const login = (credentials) =>
  axios.post(`${AUTH_BASE}/login`, credentials);

export const registerUser = (credentials) =>
  axios.post(`${AUTH_BASE}/register`, credentials);
