# ATAC_BUS_TRACKER_BACKEND
Backend software for ATAC bus tracking


### Install MongoDB
- launch the command
    `docker-compose up -d`
- create a database named atac_db with the command:
    `use atac_db`
- create a new user with the following command:
    `db.createUser({
      user: "user",
      pwd: "password",
      roles: [{ role: "readWrite", db: "atac_db" }]
    })`
