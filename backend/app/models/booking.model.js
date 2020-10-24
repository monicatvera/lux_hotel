module.exports = (sequelize, Sequelize) => {
    const Booking = sequelize.define("booking", {
        user: {
            type: Sequelize.INTEGER, references: {
                model: 'user',
                key: 'id'
            }},
            room: {
                type: Sequelize.INTEGER, references: {
                    model: 'room',
                    key: 'id'
            }},
            date: {
                type: Sequelize.DATE
            },
            time: {
                type: Sequelize.TIME
            }
        }, { timestamps: false });

    Booking.associate = function (models) {
        Booking.hasOne(models.User, { foreignKey: 'user', as: 'id', onDelete: 'CASCADE' });
    };

    Booking.associate = function (models) {
        Booking.hasOne(models.Room, { foreignKey: 'room', as: 'id', onDelete: 'CASCADE' });
    };
    return Booking;
};