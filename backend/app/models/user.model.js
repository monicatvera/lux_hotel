module.exports = (sequelize, Sequelize) => {
  const User = sequelize.define("user", {
    user: {
      type: Sequelize.STRING
    },
    password: {
      type: Sequelize.STRING
    }
  }, { timestamps: false });

  return User;
};