# ATAC_BUS_TRACKER_BACKEND
Backend software for ATAC bus tracking


### Install MongoDB
- pull the latest MongoDB version
    `docker pull mongodb/mongodb-community-server`
- run the image as a container
    `docker run --name mongo -d mongodb/mongodb-community-server:latest`
- connect to MongoDB container using the following command:
    `docker exec -it mongo mongo -u root -p password --authenticationDatabase admin`
- create a database named atac_db with the command:
    `use atac_db`
- create a new user with the following command:
    `db.createUser({
      user: "user",
      pwd: "password",
      roles: [{ role: "readWrite", db: "atac_db" }]
    })`
