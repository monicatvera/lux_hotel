const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");

const app = express();

var corsOptions = {
    origin: "*"
};

app.use(cors(corsOptions));

// parse requests of content-type - application/json
app.use(bodyParser.json());

// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

app.use(express.static("public"));

const db = require("./app/models");

//explotation time.
db.sequelize.sync();

//development time only, Drop and re-sync db.
//db.sequelize.sync({ force: true }).then(() => {
//   console.log("Drop and re-sync db.");
//});


// simple route
app.get("/", (req, res) => {
    res.json({ message: "Welcome to Lux Hotel." });
});


require("./app/routes/user.routes")(app);
require("./app/routes/room.routes")(app);
require("./app/routes/booking.routes")(app);
// set port, listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}.`);
});