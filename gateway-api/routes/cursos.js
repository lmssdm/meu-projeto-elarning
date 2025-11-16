const express = require('express');
const axios = require('axios');
const router = express.Router();

// Caminho do microserviço de cursos
const CURSOS_URL = 'http://ms-cursos:8080'; // ⚠️ MUDANÇA AQUI

router.get('/', async (req, res) => {
  try {
    const response = await axios.get(`${CURSOS_URL}/`);
    res.json(response.data);
  } catch (error) {
    console.error("Erro no gateway (cursos):", error.message);

    res.status(500).json({
      message: "Erro ao acessar mscursos.",
      details: error.message
    });
  }
});

module.exports = router;