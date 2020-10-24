module.exports = (sequelize, Sequelize) => {
    const Room = sequelize.define("room", {
      name: {
        type: Sequelize.STRING
      },
      description: {
        type: Sequelize.STRING
      },
      price: {
        type: Sequelize.DECIMAL
      }
    }, { timestamps: false });
    return Room;
  };