const express = require('express');
const axios = require('axios');
const router = express.Router();

// 1. IMPORTA O MIDDLEWARE DE TOKEN
const { verificarToken } = require('../middlewares/auth');

// Caminho do microserviço de usuários
const USERS_URL = 'http://ms-usuarios:8080';

// 2. Rota PÚBLICA: R01 - Autocadastro de Funcionário
router.post('/funcionarios/registrar', async (req, res) => {
  try {
    // Repassa o body da requisição para o microsserviço
    const response = await axios.post(`${USERS_URL}/funcionarios/registrar`, req.body);
    res.status(response.status).json(response.data);
  } catch (error) {
    console.error("Erro no gateway (usuarios/registrar):", error.message);
    const statusCode = error.response ? error.response.status : 500;
    const details = error.response ? error.response.data : error.message;

    res.status(statusCode).json({
      message: "Erro ao acessar ms-usuarios.",
      details: details
    });
  }
});

// --- ROTAS PROTEGIDAS ABAIXO ---

// 3. Rota PROTEGIDA: R03 - Dashboard do Funcionário
// Note o "verificarToken" como middleware ANTES do (req, res)
router.get('/dashboard', verificarToken, async (req, res) => {
    try {
        // O 'req.usuario' foi adicionado pelo middleware 'verificarToken'
        // 'sub' (subject) é o email, conforme definido no JwtService do ms-auth
        const email = req.usuario.sub; 
        
        const response = await axios.get(`${USERS_URL}/funcionarios/dashboard`, {
            headers: {
                // Passa o email para o ms-usuarios identificar o usuário
                'X-User-Email': email 
            }
        });
        res.json(response.data);
    } catch (error) {
        console.error("Erro no gateway (usuarios/dashboard):", error.message);
        const statusCode = error.response ? error.response.status : 500;
        res.status(statusCode).json({
          message: "Erro ao acessar ms-usuarios (dashboard).",
          details: error.response ? error.response.data : error.message
        });
    }
});

// Removemos a rota GET / que estava aqui, pois não era um proxy funcional
// router.get('/', async (req, res) => { ... });

module.exports = router;