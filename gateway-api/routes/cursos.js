const express = require('express');
const axios = require('axios');
const router = express.Router();

// 1. IMPORTA O MIDDLEWARE DE TOKEN
const { verificarToken } = require('../middlewares/auth');

const CURSOS_URL = 'http://ms-cursos:8080';

// 2. APLICA O MIDDLEWARE EM TODAS AS ROTAS DESTE ARQUIVO
// Qualquer rota de /cursos vai exigir um token
router.use(verificarToken);

// Rota PROTEGIDA: R04 - Catálogo de Cursos
router.get('/catalogo', async (req, res) => {
  try {
    const response = await axios.get(`${CURSOS_URL}/cursos/catalogo`);
    res.json(response.data);
  } catch (error) {
    console.error("Erro no gateway (cursos/catalogo):", error.message);
    res.status(500).json({
      message: "Erro ao acessar mscursos.",
      details: error.message
    });
  }
});

// Rota PROTEGIDA: R04 - Busca
router.get('/buscar', async (req, res) => {
    try {
        // Repassa os query params (ex: ?termo=java)
        const response = await axios.get(`${CURSOS_URL}/cursos/buscar`, { params: req.query });
        res.json(response.data);
    } catch (error) {
        console.error("Erro no gateway (cursos/buscar):", error.message);
        res.status(500).json({
            message: "Erro ao acessar mscursos.",
            details: error.message
        });
    }
});

// Rota PROTEGIDA: R12 - Criar Curso (Instrutor)
// TODO: Adicionar 'verificarInstrutor' aqui no futuro
router.post('/', async (req, res) => {
    try {
        // Repassa o body
        const response = await axios.post(`${CURSOS_URL}/cursos`, req.body);
        res.status(response.status).json(response.data);
    } catch (error) {
        console.error("Erro no gateway (cursos/criar):", error.message);
        const statusCode = error.response ? error.response.status : 500;
        const details = error.response ? error.response.data : error.message;
        res.status(statusCode).json({
            message: "Erro ao acessar mscursos.",
            details: details
        });
    }
});

// Removemos a rota GET / antiga
module.exports = router;