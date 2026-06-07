use("financeiro");

try {
  db.users.createIndex(
    { email: 1 },
    { unique: true }
  );
} catch (e) {
  print(e.message);
}

try {
  db.accounts.createIndex(
    { userId: 1, institution: 1, name: 1 },
    { unique: true }
  );
} catch (e) {
  print(e.message);
}

try {
  db.credit_cards.createIndex(
    { userId: 1, institution: 1, cardName: 1 },
    { unique: true }
  );
} catch (e) {
  print(e.message);
}

try {
  db.categories.createIndex(
    { name: 1 },
    { unique: true }
  );
} catch (e) {
  print(e.message);
}

try {
  db.loans.createIndex(
    { userId: 1, institution: 1, name: 1 },
    { unique: true }
  );
} catch (e) {
  print(e.message);
}

try {
  db.transactions.createIndex({
    userId: 1,
    purchaseDate: -1
  });
} catch (e) {
  print(e.message);
}

try {
  db.transaction_installments.createIndex({
    dueDate: 1
  });
} catch (e) {
  print(e.message);
}

try {
  db.loan_installments.createIndex({
    dueDate: 1
  });
} catch (e) {
  print(e.message);
}

print("Índices criados.");
