use("financeiro");

const admin = db.users.findOne({
  email: "admin@local.com"
});

print("Limpando ambiente...");

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
db.categories.deleteMany({});

db.users.deleteMany({
  email: {
    $ne: "admin@local.com"
  }
});

print("Ambiente resetado.");