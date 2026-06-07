use("financeiro");

// USER
db.users.updateOne(
  {
    email: "admin@local.com"
  },
  {
    $set: {
      name: "Fulano de Tal admin",
      active: true,
      updatedAt: new Date()
    },
    $setOnInsert: {
      passwordHash: "123admin",
      createdAt: new Date()
    }
  },
  {
    upsert: true
  }
);

const user = db.users.findOne({
  email: "admin@local.com"
});

if (!user) {
  throw new Error(
    "Usuário admin@local.com não encontrado. Execute primeiro o seed de usuários."
  );
}

const userId = user._id;

// CONTAS

[
{
  institution: "Itau",
  name: "Conta Corrente",
  currentBalance: 405.66,
  isPrimary: true
},
{
  institution: "Nubank",
  name: "Conta Principal",
  currentBalance: 0.91,
  isPrimary: false
},
{
  institution: "Caixa",
  name: "Conta Caixa",
  currentBalance: 0,
  isPrimary: false
}
].forEach(account => {

  db.accounts.updateOne(
    {
      userId,
      institution: account.institution,
      name: account.name
    },
    {
      $set: {
        ...account,
        updatedAt: new Date()
      },
      $setOnInsert: {
        createdAt: new Date()
      }
    },
    {
      upsert: true
    }
  );

});

// EMPRÉSTIMOS

[
{
  institution: "Nubank",
  name: "Empréstimo 1",
  remainingBalance: 6028.23
},
{
  institution: "Nubank",
  name: "Empréstimo 2",
  remainingBalance: 18673.71
},
{
  institution: "Itau",
  name: "Consignado",
  monthlyInstallment: 1925.94,
  totalInstallments: 59,
  currentInstallment: 8,
  remainingInstallments: 52
},
{
  institution: "Itau",
  name: "Renegociação 1",
  remainingBalance: 10747.11,
  monthlyInstallment: 1097.32,
  totalInstallments: 14,
  currentInstallment: 8,
  remainingInstallments: 6
},
{
  institution: "Itau",
  name: "Renegociação 2",
  remainingBalance: 28170.32,
  monthlyInstallment: 2291.61,
  totalInstallments: 14,
  currentInstallment: 8,
  remainingInstallments: 6
}
].forEach(loan => {

  db.loans.updateOne(
    {
      userId,
      institution: loan.institution,
      name: loan.name
    },
    {
      $set: {
        ...loan,
        status: "active",
        updatedAt: new Date()
      },
      $setOnInsert: {
        createdAt: new Date()
      }
    },
    {
      upsert: true
    }
  );

});

print("Dados do usuário carregados.");