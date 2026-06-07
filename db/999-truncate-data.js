use("financeiro");

print("Limpando dados...");

db.transaction_installments.deleteMany({});
db.loan_installments.deleteMany({});
db.transactions.deleteMany({});
db.subscriptions.deleteMany({});
db.loans.deleteMany({});
db.credit_cards.deleteMany({});
db.accounts.deleteMany({});
db.budgets.deleteMany({});
db.monthly_snapshots.deleteMany({});
db.audit_logs.deleteMany({});

// Opcional:
// mantém categorias
// mantém usuários

print("Dados removidos com sucesso.");