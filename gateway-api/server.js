const express = require('express');
const cors = require('cors');

// 1. NÃO PRECISAMOS MAIS DO TOKEN AQUI
// const { verificarToken } = require('./middlewares/auth'); 

const app = express();

app.use(cors());
app.use(express.json());

// --- TODAS AS ROTAS SÃO CARREGADAS AQUI ---
// A proteção será decidida DENTRO de cada arquivo.

// Rota de autenticação (Pública)
app.use('/auth', require('./routes/auth'));

// Rota de usuários (Contém rotas públicas E privadas)
app.use('/usuarios', require('./routes/usuarios'));

// Rota de cursos (A maioria das rotas aqui é privada)
app.use('/cursos', require('./routes/cursos'));

app.get('/', (req, res) => {
  res.json({ message: "Gateway ativo e funcionando." });
});

app.listen(8080, () => {
  console.log("Gateway rodando na porta 8080");
});