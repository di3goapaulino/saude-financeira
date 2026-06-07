use("financeiro");

if (!db.getCollectionNames().includes("users")) {
  db.createCollection("users");
}

print("Coleção users criada.");
