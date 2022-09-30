package service;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    private  UserDao userDao;
    public int addData(User user) {
        return this.userDao.insertData(user);
    }

    public List<User> getAllUsersData() {
        return this.userDao.getAllUsers();
    }

    public int deleteUser(String uid) {
        return this.userDao.deleteUser(uid);
    }

    public User getUserDataById(String uid) {
        return this.userDao.getStudentDataById(uid);
    }
}
