package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User findUser(int id) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(DaoUserRequest.GET_USER_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }


    private static class UserMapper {

        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getInt(DBFields.ENTITY__ID));
                user.setEmail(rs.getString(DBFields.USER__EMAIL));
                user.setIdn(rs.getInt(DBFields.USER__IDN));
                user.setBlocked(rs.getBoolean(DBFields.USER__BLOCK));
                user.setUser_role_id(rs.getInt(DBFields.USER__ROLE));
                user.setPassword(rs.getString(DBFields.USER__PASSWORD));
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
