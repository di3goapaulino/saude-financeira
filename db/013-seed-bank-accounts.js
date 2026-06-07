use("financeiro");

const now = new Date();

// SEED BANK ACCOUNTS
db.bank_accounts.updateMany(
  {},
  [
    {
      $set: {
        updatedAt: now
      }
    }
  ]
);

// Itaú
db.bank_accounts.updateOne(
  { bank: "Itau", name: "Itaú Principal" },
  {
    $setOnInsert: {
      userId: "default-user",
      bank: "Itau",
      name: "Itaú Principal",
      currentBalance: 5000.00,
      isPrimary: true,
      createdAt: now,
      updatedAt: now
    },
    $set: {
      updatedAt: now
    }
  },
  { upsert: true }
);

// Nubank
db.bank_accounts.updateOne(
  { bank: "Nubank", name: "Nubank Conta" },
  {
    $setOnInsert: {
      userId: "default-user",
      bank: "Nubank",
      name: "Nubank Conta",
      currentBalance: 3200.50,
      isPrimary: false,
      createdAt: now,
      updatedAt: now
    },
    $set: {
      updatedAt: now
    }
  },
  { upsert: true }
);

// Caixa
db.bank_accounts.updateOne(
  { bank: "Caixa", name: "Caixa Economia" },
  {
    $setOnInsert: {
      userId: "default-user",
      bank: "Caixa",
      name: "Caixa Economia",
      currentBalance: 8500.00,
      isPrimary: false,
      createdAt: now,
      updatedAt: now
    },
    $set: {
      updatedAt: now
    }
  },
  { upsert: true }
);

print("Bank Accounts seeded successfully.");
