package service;

import model.User;
import repository.IUserRepository;
import repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService {
    private IUserRepository iUserRepository = new UserRepository();

    @Override
    public void insertUser(User user) throws SQLException {
        iUserRepository.insertUser(user);
    }

    @Override
    public User selectUser(int id) {
        return iUserRepository.selectUser(id);
    }

    @Override
    public List<User> selectAllUsers() {
//        return iUserRepository.selectAllUsers();
        return iUserRepository.selectAllUsersStore();
    }


    @Override
    public boolean deleteUser(int id) throws SQLException {
//        return iUserRepository.deleteUser(id);
        return iUserRepository.deleteUserStore(id);
    }


    @Override
    public boolean updateUser(User user) throws SQLException {
//        return iUserRepository.updateUser(user);
        return iUserRepository.updateUserStore(user);
    }

    @Override
    public void orderByName() {
        iUserRepository.orderByName();
    }

    @Override
    public List<User> selectUsersWhereCountry(String country) {
        return iUserRepository.selectUsersWhereCountry("%" + country + "%");
    }

    @Override
    public User getUserById(int id) {
        return iUserRepository.getUserById(id);
    }

    @Override
    public void insertUserStore(User user) throws SQLException {
        iUserRepository.insertUserStore(user);
    }
}
