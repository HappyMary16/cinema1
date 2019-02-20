package ua.com.cinema1.service;

import ua.com.cinema1.dao.DaoFactory;
import ua.com.cinema1.dao.UserDao;
import ua.com.cinema1.model.Role;
import ua.com.cinema1.model.User;

import java.util.List;

public class UserService  implements IServise<Integer, User> {

    private static UserService userService = new UserService();
    private UserDao userDao;

    private UserService() {
        userDao = (UserDao) DaoFactory.getInstance().getUserDao();
    }

    public static UserService getInstance() {
        return userService;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userDao.getAll();
        for (int i = 0; i < users.size(); i++) {
            User e = users.get(i);
            if (e.getRole() != Role.USER) {
                users.remove(e);
                i--;
            }
        }
        return users;
    }

    @Override
    public Integer create(User value) {
        return userDao.insert(value);
    }

    @Override
    public User read(Integer key) {
        User user = userDao.getById(key);
        return user.getRole() == Role.USER ? user : null;
    }

    @Override
    public void update(User value) {
        userDao.update(value);
    }

    @Override
    public void delete(Integer key) {
        userDao.delete(key);

    }
}
