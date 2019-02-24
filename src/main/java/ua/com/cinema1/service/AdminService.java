package ua.com.cinema1.service;

import ua.com.cinema1.dao.DaoFactory;
import ua.com.cinema1.dao.UserDao;
import ua.com.cinema1.model.Role;
import ua.com.cinema1.model.User;

import java.util.List;

public class AdminService implements IServise<Integer, User> {

    private static AdminService adminService = new AdminService();
    private UserDao userDao;

    private AdminService() {
        userDao = (UserDao) DaoFactory.getInstance().getUserDao();
    }

    public static AdminService getInstance() {
        return adminService;
    }

    @Override
    public List<User> getAll() {
        List<User> admins = userDao.getAll();
        for (int i = 0; i < admins.size(); i++) {
            User e = admins.get(i);
            if (e.getRole() != Role.ADMIN) {
                admins.remove(e);
                i--;
            }
        }
        return admins;
    }

    @Override
    public Integer create(User value) {
        return userDao.insert(value);
    }

    @Override
    public User getById(Integer key) {
        User admin = userDao.getById(key);
        return admin.getRole() == Role.ADMIN ? admin : null;
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
