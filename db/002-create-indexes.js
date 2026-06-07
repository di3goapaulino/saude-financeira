use("financeiro");

// Indexes for transactions
db.transactions.createIndex({ userId: 1 });
db.transactions.createIndex({ userId: 1, purchaseDate: -1 });
db.transactions.createIndex({ accountId: 1 });

// Indexes for categories
db.categories.createIndex({ userId: 1 });
db.categories.createIndex({ userId: 1, name: 1 });

// Indexes for users
db.users.createIndex({ email: 1 }, { unique: true });

// Indexes for bank accounts
db.bank_accounts.createIndex({ userId: 1 });
db.bank_accounts.createIndex({ userId: 1, isPrimary: 1 });

// Indexes for credit cards
db.credit_cards.createIndex({ userId: 1 });
db.credit_cards.createIndex({ userId: 1, active: 1 });

// Indexes for loans
db.loans.createIndex({ userId: 1 });
db.loans.createIndex({ userId: 1, status: 1 });

// Indexes for subscriptions
db.subscriptions.createIndex({ userId: 1 });
db.subscriptions.createIndex({ userId: 1, active: 1 });

// Indexes for budgets
db.budgets.createIndex({ userId: 1 });
db.budgets.createIndex({ userId: 1, month: 1, year: 1 });

print("All indexes created successfully.");
