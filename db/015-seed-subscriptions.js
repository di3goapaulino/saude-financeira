use("financeiro");

const now = new Date();

// SEED SUBSCRIPTIONS
const subscriptions = [
  { name: "Netflix", amount: 49.90, billingDay: 15 },
  { name: "Spotify", amount: 19.90, billingDay: 10 },
  { name: "Prime Video", amount: 14.90, billingDay: 1 },
  { name: "ChatGPT Plus", amount: 29.90, billingDay: 20 }
];

subscriptions.forEach(sub => {
  db.subscriptions.updateOne(
    { name: sub.name },
    {
      $setOnInsert: {
        userId: "default-user",
        name: sub.name,
        amount: sub.amount,
        billingDay: sub.billingDay,
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

print("Subscriptions seeded successfully.");
