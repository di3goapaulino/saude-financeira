# Saúde Financeira

## Como iniciar o projeto

### 1) Iniciar o MongoDB local

Certifique-se de que o MongoDB está rodando em `localhost:27017`.

- Se estiver usando serviço local: execute `mongod` ou inicie o serviço MongoDB.
- Se estiver usando Docker:
  ```powershell
  docker run -d --name mongo-finance -p 27017:27017 -v mongo-data:/data/db mongo:latest
  ```

### 2) Configurar o banco de dados

O backend usa variáveis de ambiente para manter segredos fora do código.

- `MONGODB_URI` para a conexão com o MongoDB.
- `DATABASE_URL` é suportado como fallback Heroku se fornecido pelo addon.
- `JWT_SECRET` para proteção de tokens.

> O arquivo de exemplo está em `backend/.env.example` e a configuração de runtime está em `backend/src/main/resources/application.properties`.

### 3) Preparar os scripts do Mongo (opcional)

No diretório `db/` estão os scripts de criação e seed de dados. Se quiser rodar manualmente:

```powershell
cd db
# execute no shell do MongoDB ou no seu runner preferido
mongo localhost:27017/financeiro 001-create-users.js
mongo localhost:27017/financeiro 002-create-accounts.js
mongo localhost:27017/financeiro 003-create-categories.js
mongo localhost:27017/financeiro 004-create-credit-cards.js
mongo localhost:27017/financeiro 005-create-loans.js
mongo localhost:27017/financeiro 006-create-transactions.js
mongo localhost:27017/financeiro 007-create-installments.js
mongo localhost:27017/financeiro 008-create-subscriptions.js
mongo localhost:27017/financeiro 009-create-budgets.js
mongo localhost:27017/financeiro 010-create-audit-log.js
mongo localhost:27017/financeiro 011-create-indexes.js
mongo localhost:27017/financeiro 012-seed-categories.js
```

### 4) Iniciar o backend

No diretório `backend/`:

- Windows:
  ```powershell
  cd backend
  .\mvnw.cmd spring-boot:run
  ```

- Linux / macOS:
  ```bash
  cd backend
  ./mvnw spring-boot:run
  ```

A aplicação deve subir em `http://localhost:8081`.

### 5) Deploy no Heroku

Para deploy no Heroku, use o diretório `backend` como raiz do app.

1. No diretório do backend, crie o app Heroku:
   ```powershell
   cd backend
   heroku create your-app-name
   ```
2. Configure as variáveis de ambiente:
   ```powershell
   heroku config:set MONGODB_URI="<sua-mongodb-uri>"
   heroku config:set JWT_SECRET="<seu-jwt-secret>"
   ```
3. Faça o deploy:
   ```powershell
   git push heroku main
   ```

O `backend/Procfile` já está configurado para usar `PORT` do Heroku e o arquivo `backend/system.properties` define o Java 24.

### 6) Usar o Thunder Client

Importe o arquivo `thunder-saude-financeira-collection.json` na raiz do projeto.

Defina a variável de ambiente:
- `baseUrl = http://localhost:8081`

### 6) Endpoints disponíveis

| Recurso | Método | URL | Observação |
|---|---|---|---|
| Registrar usuário | POST | `/auth/register` | Retorna token JWT |
| Login | POST | `/auth/login` | Retorna token JWT |
| Criar categoria | POST | `/categories` | Requer header `Authorization: Bearer {{jwtToken}}` |
| Listar categorias | GET | `/categories` | Requer token |
| Criar transação | POST | `/transactions` | Requer token |
| Listar transações | GET | `/transactions` | Requer token |

### 7) Como testar no Postman

1. Execute o request `Auth / Register` para criar um usuário.
2. Execute `Auth / Login` para receber `token`.
3. O script de teste em `Auth / Login` salva o token automaticamente em `jwtToken`.
4. Execute os requests de categoria e transação com o token salvo.

### Observação

O backend atual exporta apenas os endpoints de criação e listagem para `categories` e `transactions`. Atualizações e exclusões ainda não estão implementadas.
