use("financeiro");

const now = new Date();
const currentDate = new Date();
const currentMonth = currentDate.getMonth() + 1;
const currentYear = currentDate.getFullYear();

// SEED CREDIT CARDS
const creditCards = [
  { name: "Nubank Black", brand: "Mastercard", limitAmount: 15000.00, closingDay: 15, dueDay: 22 },
  { name: "Itaú Personnalité", brand: "Visa", limitAmount: 25000.00, closingDay: 10, dueDay: 20 },
  { name: "Bradesco Infinite", brand: "American Express", limitAmount: 12000.00, closingDay: 5, dueDay: 15 }
];

creditCards.forEach(card => {
  db.credit_cards.updateOne(
    { name: card.name },
    {
      $setOnInsert: {
        userId: "default-user",
        name: card.name,
        brand: card.brand,
        limitAmount: card.limitAmount,
        availableLimit: card.limitAmount * 0.7, // 70% disponível
        closingDay: card.closingDay,
        dueDay: card.dueDay,
        active: true,
        createdAt: now,
        updatedAt: now
      },
      $set: {
        updatedAt: now
      }
    },
    { upsert: true }
  );
});

// SEED BUDGETS
db.budgets.updateOne(
  { userId: "default-user", month: currentMonth, year: currentYear },
  {
    $setOnInsert: {
      userId: "default-user",
      month: currentMonth,
      year: currentYear,
      plannedIncome: 5500.00,
      plannedExpense: 4200.00,
      createdAt: now,
      updatedAt: now
    },
    $set: {
      updatedAt: now
    }
  },
  { upsert: true }
);

print("Credit Cards and Budgets seeded successfully.");
