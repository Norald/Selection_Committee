package beans;

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
