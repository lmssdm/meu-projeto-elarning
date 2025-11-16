const jwt = require('jsonwebtoken')

// Função principal de verificação de token (R02)
const verificarToken = (req, res, next) => {
    const authHeader = req.headers.authorization;

    if (!authHeader) {
        return res.status(401).json({message: 'Token de autenticação não fornecido.'})
    }

    const parts = authHeader.split(' ');
    if (parts.length !== 2) {
        return res.status(401).json({message: 'Formato de token invalido.'})
    }

    const [scheme, token] = parts;
    if (!/^Bearer$/i.test(scheme)) {
        return res.status(401).json({message: 'Formato de token inválido'});
    }
    if (!token) {
        return res.status(401).json({mensagem: 'Token não fornecido'});
    }
    
    try {
        // 1. Garante que a chave secreta seja lida como base64
        const keyBuffer = Buffer.from(process.env.JWT_SECRET, 'base64');
        
        // 2. Verifica o token e especifica o algoritmo
        const decoded = jwt.verify(token, keyBuffer, { algorithms: ['HS256'] });
        
        // 3. Salva o payload (que contém email e tipo) no request
        req.usuario = decoded; 
        
        return next();
    } catch (e) {
        console.error(e);
        return res.status(401).json({message: 'Token invalido ou expirado'});
    }
}

// Perfil Funcionário (R03)
const verificarFuncionario = (req, res, next) => {
    if (!req.usuario || req.usuario.tipo !== 'FUNCIONARIO') {
        return res.status(403).json({message: 'Acesso negado. Rota exclusiva para funcionários.'})
    }
    return next();
}

// Perfil Instrutor (R11)
const verificarInstrutor = (req, res, next) => {
    if (!req.usuario || req.usuario.tipo !== 'INSTRUTOR') {
        return res.status(403).json({message: 'Acesso negado. Rota exclusiva para instrutores.'})
    }
    return next();
}

// TODO: Adicionar verificarAdmin (R17)
// const verificarAdmin = (req, res, next) => { ... }

module.exports = {
    verificarToken,
    verificarFuncionario,
    verificarInstrutor
    // Removemos o verificarPaciente
};