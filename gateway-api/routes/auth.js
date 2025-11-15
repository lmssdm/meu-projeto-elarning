const express = require('express');
const axios = require('axios');
const router = express.Router();

// Caminho do microserviço de autenticação
const AUTH_URL = 'http://ms-auth:3001';

router.post('/login', async (req, res) => {
  try {
    const response = await axios.post(`${AUTH_URL}/login`, req.body);
    res.json(response.data);
  } catch (error) {
    console.error("Erro no gateway (auth/login):", error.message);

    res.status(500).json({
      message: "Erro ao acessar o serviço de autenticação.",
      details: error.message
    });
  }
});

module.exports = router;
