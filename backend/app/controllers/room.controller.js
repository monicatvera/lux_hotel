const db = require("../models");
const Room = db.rooms;
const User = db.users;

// Create and Save a new Room
exports.create = (req, res) => {
    // Validate request
    if (!req.body.name || !req.body.price) {
        res.status(400).send({
            message: "Content can not be empty!"
        });
        return;
    }

    // Create a Room
    const room = {
        name: req.body.name,
        description: req.body.description,
        price: req.body.price
    };

    // Save Room in the database
    Room.create(room)
        .then(data => {
            res.send(data);
        })
        .catch(err => {
            res.status(500).send({
                message:
                    err.message || "Some error occurred while creating the Tutorial."
            });
        });
};

// Retrieve all Rooms from the database.
exports.findAll = (req, res) => {
    Room.findAll()
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving users."
        });
      });
};

// Find a single Room with an id
exports.findOne = (req, res) => {
    const id = req.params.id;

    Room.findByPk(id)
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message: "Error retrieving user with id=" + id
        });
      });
};

// Update a Room by the id in the request
exports.update = (req, res) => {
    const id = req.params.id;

    Room.update(req.body, {
      where: { id: id }
    })
      .then(num => {
        if (num == 1) {
          res.send({
            message: "Room was updated successfully."
          });
        } else {
          res.send({
            message: `Cannot update Room with id=${id}. Maybe Room was not found or req.body is empty!`
          });
        }
      })
      .catch(err => {
        res.status(500).send({
          message: "Error updating Room with id=" + id
        });
      });
};

// Delete a Room with the specified id in the request
exports.delete = (req, res) => {
    const id = req.params.id;

    Room.destroy({
      where: { id: id }
    })
      .then(num => {
        if (num == 1) {
          res.send({
            message: "Room was deleted successfully!"
          });
        } else {
          res.send({
            message: `Cannot delete Room with id=${id}. Maybe Room was not found!`
          });
        }
      })
      .catch(err => {
        res.status(500).send({
          message: "Could not delete Room with id=" + id
        });
      });
};

// Delete all Room from the database.
exports.deleteAll = (req, res) => {
    Root.destroy({
        where: {},
        truncate: false
      })
        .then(nums => {
          res.send({ message: `${nums} Rooms were deleted successfully!` });
        })
        .catch(err => {
          res.status(500).send({
            message:
              err.message || "Some error occurred while removing all Rooms."
          });
        });
};
