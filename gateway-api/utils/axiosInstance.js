const axios = require('axios');

module.exports = axios.create({
  baseURL: 'http://ms-auth:8080', // ⚠ Ajuste conforme o seu container MS_AUTH
  timeout: 5000,
});
