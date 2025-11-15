const express = require('express');
const cors = require('cors');

const app = express();

app.use(cors());
app.use(express.json());

app.use('/auth', require('./routes/auth'));
app.use('/usuarios', require('./routes/usuarios'));
app.use('/cursos', require('./routes/cursos'));

app.get('/', (req, res) => {
  res.json({ message: "Gateway ativo e funcionando." });
});

app.listen(8080, () => {
  console.log("Gateway rodando na porta 8080");
});
