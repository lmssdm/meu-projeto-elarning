const express = require('express');
const axios = require('axios');
const router = express.Router();

// Caminho do microserviço de autenticação (PORTA CORRETA)
const AUTH_URL = 'http://ms-auth:8080';

// Função auxiliar com lógica de re-tentativa (retry) para erros de rede
const callAuthService = async (url, body, retries = 5, delay = 1000) => {
    for (let i = 0; i < retries; i++) {
        try {
            return await axios.post(url, body);
        } catch (error) {
            // Verifica se é um erro de rede (DNS issue: EAI_AGAIN)
            if (i < retries - 1 && error.code === 'EAI_AGAIN') {
                console.warn(`[Retry] DNS error (EAI_AGAIN) for ${url}. Retrying in ${delay / 1000}s... (Attempt ${i + 1}/${retries})`);
                await new Promise(resolve => setTimeout(resolve, delay));
            } else {
                // Se for a última tentativa ou outro tipo de erro (401, 404, etc.), lança o erro.
                throw error;
            }
        }
    }
};

router.post('/login', async (req, res) => {
  try {
    const response = await callAuthService(`${AUTH_URL}/login`, req.body); // Chama a função com retry
    res.json(response.data);
  } catch (error) {
    // Trata o erro de forma mais informativa (para 401, 404, etc.)
    const statusCode = error.response ? error.response.status : 500;
    const details = error.response ? (error.response.data.erro || error.response.data.message || error.response.data) : error.message;

    console.error("Erro no gateway (auth/login):", error.message);

    res.status(statusCode).json({
      message: "Erro ao acessar o serviço de autenticação.",
      details: details
    });
  }
});

module.exports = router;