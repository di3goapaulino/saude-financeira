use("financeiro");

const categories = [
  "Casa",
  "Transporte",
  "Saúde",
  "Mercado",
  "Lazer",
  "Assinaturas",
  "Compras Online",
  "Receita",
  "Dívidas",
  "Impostos"
];

categories.forEach(name => {
  db.categories.updateOne(
    { name },
    {
      $set: {
        active: true,
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

print("Categorias carregadas.");
