# Lux Hotel 🏨

This is an application for hotel management of a hotel.

🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨🏨
![alt text](https://github.com/monitv98/lux_hotel/blob/master/captura.png)

### Prerequisites 📌

To install this project you need:
* A working node environment.
* A working Android environment.
* A Mysql Server.

### Backend 🔁
On the server we have a database
It contains 3 tables, which one relates the other two.
The server implements images in the / public / img directory.
Has a total of 3 full CRUDs

### Postmand 📄
[Postman](https://documenter.getpostman.com/view/12915900/TVYAgLa4)

### Frontend 📱
The application can view images from the server. currently makes 2 full CRUDS,Only need to implement the reservations section. It has a floating menu with which to change sections.
![alt text](https://github.com/monitv98/lux_hotel/blob/master/capturaMenu.png)


### Installing ▶️

Open a command line console and clone this project.

```
git clone https://github.com/monitv98/lux_hotel
```

Go to the new created directory. After that go to the backend directory and install all dependencies:

```
cd backend
npm install
```

After that start your MySQL Server and import the database lux_hotel.sql included in the /backend directory of this project.
[lux_hotel.sql](https://github.com/monitv98/lux_hotel/blob/master/backend/lux_hotel.sql)

Start the backend project

```
node server.js
```

And finally start the frontend Android App with Android Studio.

remember to change the ip of the singletons and some adapters for your server ip.

## Built With 🛠️

* [Android Studio](https://developer.android.com/studio?hl=es) - Android Studio IDE.
* [Git](https://git-scm.com) - You can install it from https://git-scm.com/downloads.
* [MySQL](https://www.mysql.com) - You can install it from https://www.mysql.com/downloads/.
* [Node.js](https://nodejs.org) - Install node.js from https://nodejs.org/es/download/. It's advisable to install the LTS version.

## Acknowledgments 👏

* https://developer.android.com/training/volley/requestqueue?hl=es - How to configure a Singleton.
* https://developer.android.com/training/volley?hl=es - How to send data with Volley from Kotlin App.
* https://www.varvet.com/blog/kotlin-with-volley/ - Kotlin with Volley
