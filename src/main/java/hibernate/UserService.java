package hibernate;

import model.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User findUser(int id) {
        return userDAO.findUserById(id);
    }

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    public void saveAllUsers(List<User> users) {
        userDAO.saveAllUsers(users);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

}
