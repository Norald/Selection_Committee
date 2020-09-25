package db;

public class DaoUserRequest {
    public static final String ADD_USER_ROLE = "INSERT INTO user_role(name) VALUES (?)";
    public static final String GET_ALL_USER_ROLES = "SELECT * FROM user_role";
    public static final String GET_USER_ROLE_BY_NAME = "SELECT * FROM user_role WHERE name = (?)";
    public static final String GET_USER_ROLE_BY_ID = "SELECT * FROM user_role WHERE id = (?)";
    public static final String DELETE_USER_ROLE_BY_NAME = "DELETE FROM user_role WHERE name = (?)";

    public static final String GET_ALL_USERS = "SELECT * FROM user";
    public static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email = (?)";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = (?)";
    public static final String GET_USER_BY_IDN = "SELECT * FROM user WHERE idn = (?)";
    public static final String ADD_USER = "INSERT INTO user (email, idn , block, user_role_id, password) VALUES (?,?, 0, 1 , ?)";
    public static final String BLOCK_USER = "UPDATE user SET block = 1 WHERE email = (?)";
}
