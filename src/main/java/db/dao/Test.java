package db.dao;

import db.DBManager;
import db.User;
import db.UserDao;

public class Test {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = userDao.findUser(1);

        System.out.println(user.getEmail());
    }
}
