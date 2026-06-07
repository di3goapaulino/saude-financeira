use("financeiro");

print("=== RESETANDO AMBIENTE ===");

[
  "transaction_installments",
  "loan_installments",
  "transactions",
  "subscriptions",
  "loans",
  "credit_cards",
  "accounts",
  "budgets",
  "monthly_snapshots",
  "audit_logs"
].forEach(collection => {

  if (db.getCollectionNames().includes(collection)) {

    db.getCollection(collection).deleteMany({});

    print(`✓ ${collection}`);

  }

});

print("=== CONCLUÍDO ===");