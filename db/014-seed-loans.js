use("financeiro");

const now = new Date();

// SEED LOANS
// Nubank 1 = 6028.23
db.loans.updateOne(
  { institution: "Nubank", name: "Empréstimo Nubank 1" },
  {
    $setOnInsert: {
      userId: "default-user",
      institution: "Nubank",
      name: "Empréstimo Nubank 1",
      remainingBalance: 6028.23,
      monthlyInstallment: 250.34,
      totalInstallments: 24,
      currentInstallment: 1,
      remainingInstallments: 24,
      status: "ACTIVE",
      createdAt: now,
      updatedAt: now
    },
    $set: {
      updatedAt: now
    }
  },
  { upsert: true }
);

// Nubank 2 = 18673.71
db.loans.updateOne(
  { institution: "Nubank", name: "Empréstimo Nubank 2" },
  {
    $setOnInsert: {
      userId: "default-user",
      institution: "Nubank",
      name: "Empréstimo Nubank 2",
      remainingBalance: 18673.71,
      monthlyInstallment: 1556.14,
      totalInstallments: 12,
      currentInstallment: 1,
      remainingInstallments: 12,
      status: "ACTIVE",
      createdAt: now,
      updatedAt: now
    },
    $set: {
      updatedAt: now
    }
  },
  { upsert: true }
);

// Consignado = 1925.94, parcela 8 de 59
db.loans.updateOne(
  { institution: "Consignado", name: "Empréstimo Consignado" },
  {
    $setOnInsert: {
      userId: "default-user",
      institution: "Consignado",
      name: "Empréstimo Consignado",
      remainingBalance: 1925.94,
      monthlyInstallment: 158.75,
      totalInstallments: 59,
      currentInstallment: 8,
      remainingInstallments: 52,
      status: "ACTIVE",
      createdAt: now,
      updatedAt: now
    },
    $set: {
      updatedAt: now
    }
  },
  { upsert: true }
);

// Renegociação 1 = 10747.11
db.loans.updateOne(
  { institution: "Banco", name: "Renegociação 1" },
  {
    $setOnInsert: {
      userId: "default-user",
      institution: "Banco",
      name: "Renegociação 1",
      remainingBalance: 10747.11,
      monthlyInstallment: 894.76,
      totalInstallments: 12,
      currentInstallment: 1,
      remainingInstallments: 12,
      status: "ACTIVE",
      createdAt: now,
      updatedAt: now
    },
    $set: {
      updatedAt: now
    }
  },
  { upsert: true }
);

// Renegociação 2 = 28170.32
db.loans.updateOne(
  { institution: "Banco", name: "Renegociação 2" },
  {
    $setOnInsert: {
      userId: "default-user",
      institution: "Banco",
      name: "Renegociação 2",
      remainingBalance: 28170.32,
      monthlyInstallment: 2347.53,
      totalInstallments: 12,
      currentInstallment: 1,
      remainingInstallments: 12,
      status: "ACTIVE",
      createdAt: now,
      updatedAt: now
    },
    $set: {
      updatedAt: now
    }
  },
  { upsert: true }
);

print("Loans seeded successfully.");
