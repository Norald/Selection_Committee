package beans;

/**
 * Enum that describes user role.
 * Can be USER or ADMIN.
 * @author Vladislav Prokopenko.
 */
public enum UserRole {
    USER, ADMIN;


    public static UserRole getRole(User user) {
        int roleId = user.getUser_role_id()-1;
        return UserRole.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
