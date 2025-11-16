const express = require('express');
const axios = require('axios');
const router = express.Router();

// Caminho do microserviço de usuários
const USERS_URL = 'http://ms-usuarios:8080'; // ⚠️ MUDANÇA AQUI

router.get('/', async (req, res) => {
  try {
    const response = await axios.get(`${USERS_URL}/`);
    res.json(response.data);
  } catch (error) {
    console.error("Erro no gateway (usuarios):", error.message);

    res.status(500).json({
      message: "Erro ao acessar msusuarios.",
      details: error.message
    });
  }
});

module.exports = router;