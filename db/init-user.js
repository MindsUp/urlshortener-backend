db = db.getSiblingDB("urlshortener")
db.createUser(
  {
    user: "shortener",
    pwd: "not-so-short-password",
    roles: [
      {
        role: "dbOwner",
        db: "urlshortener"
      }
    ]
  }
)
