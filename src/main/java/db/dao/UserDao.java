package db.dao;

import beans.*;
import db.DBManager;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class UserDao {


    public void addUser(String email, long idn, String password) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.ADD_USER);
            pstmt.setString(1, email);
            pstmt.setLong(2, idn);
            pstmt.setString(3, password);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void addUserDetails(int userId, String name, String surname, String patronymic, String city, String region, String school_name, int certificate_point, String name_ua, String surname_ua, String patronymic_ua, String city_ua, String region_ua, String school_name_ua) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.ADD_USER_DETAILS);
            pstmt.setInt(1, userId);
            pstmt.setString(2, name);
            pstmt.setString(3, surname);
            pstmt.setString(4, patronymic);
            pstmt.setString(5, city);
            pstmt.setString(6, region);
            pstmt.setString(7, school_name);
            pstmt.setInt(8, certificate_point);
            pstmt.setString(9, name_ua);
            pstmt.setString(10, surname_ua);
            pstmt.setString(11, patronymic_ua);
            pstmt.setString(12, city_ua);
            pstmt.setString(13, region_ua);
            pstmt.setString(14, school_name_ua);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public UserDetails findUserDetails(User user, String locale) {

        String request;
        String name;
        String surname;
        String patronymic;
        String city;
        String region;
        String school_name;
        if(locale.equals("en")){
            request = DaoUserRequest.GET_USER_DETAILS_BY_ID;
            name="name";
            surname="surname";
            patronymic = "patronymic";
            city = "city";
            region="region";
            school_name = "school_name";
        }else{
            request = DaoUserRequest.GET_USER_DETAILS_BY_ID_UA;
            name="name_ua";
            surname="surname_ua";
            patronymic = "patronymic_ua";
            city = "city_ua";
            region="region_ua";
            school_name = "school_name_ua";
        }

        UserDetails details = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(request);
            pstmt.setInt(1, user.getId());
            rs = pstmt.executeQuery();
            if (rs.next())
                details = new UserDetails();
                details.setUserId(rs.getInt("user_id"));
                details.setName(rs.getString(name));
                details.setSurname(rs.getString(surname));
                details.setPatronymic(rs.getString(patronymic));
                details.setCity(rs.getString(city));
                details.setRegion(rs.getString(region));
                details.setSchool_name(rs.getString(school_name));
                details.setAverage_certificate(rs.getInt("average_certificate_point"));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return details;
    }


    public Map<String, Date> findUserAdmissions(User user, String locale){
        String fieldName="";
        String request= "";
        if(locale.equals("en")){
            request = DaoUserRequest.GET_ALL_USER_ADMISSION;
            fieldName=DBFields.FACULTY__NAME;
        }else{
            request = DaoUserRequest.GET_ALL_USER_ADMISSION_UA;
            fieldName=DBFields.FACULTY__NAME_UA;

        }

        Map<String, Date> mapAdmissionList = new HashMap< String, Date>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            //UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(request);
            pstmt.setString(1, user.getEmail());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate(DBFields.ADMISSION__DATE);
                String facultyName = rs.getString(fieldName);
                mapAdmissionList.put(facultyName, date);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return mapAdmissionList;
    }


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

    public User findUser(String email) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(DaoUserRequest.GET_USER_BY_EMAIL);
            pstmt.setString(1, email);
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

    public User findUserByIdn(String idn) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(DaoUserRequest.GET_USER_BY_IDN);
            pstmt.setLong(1, Long.parseLong(idn));
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


    public User findUser(String email, String password) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(DaoUserRequest.GET_USER_BY_EMAIL_AND_PASSWORD);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
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


    public Set<Integer> getUserSubjects(String email){
        Set<Integer> userSubjects = new TreeSet<Integer>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.GET_ALL_USER_SUBJECTS_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int idSubject = rs.getInt("subject_exam_id");
                userSubjects.add(idSubject);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return userSubjects;
    }


    //find all user results
    public List<UserResult> findUserResult(String email, String locale) {

        List<UserResult> resultList = new ArrayList<UserResult>();
        UserResult result = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserResultMapper mapper = new UserResultMapper();
            pstmt = con.prepareStatement(DaoUserRequest.GET_ALL_USER_MARKS_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result = mapper.mapRow(rs, locale);
                resultList.add(result);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return resultList;
    }

    public SubjectExam findSubjectExam(int id, String locale) {
        //todo
        String fieldDescription="";
        String fieldName="";
        String request= "";
        if(locale.equals("en")){
            request = DaoUserRequest.GET_SUBJECT_EXAM_BY_ID;
            fieldName="name";
            fieldDescription="description";
        }else{
            request = DaoUserRequest.GET_SUBJECT_EXAM_BY_ID;
            fieldName="name_ua";
            fieldDescription="description_ua";
        }
        SubjectExam subjectExam = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(request);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                subjectExam = new SubjectExam();
                subjectExam.setId(rs.getInt("id"));
                subjectExam.setName(rs.getString(fieldName));
                subjectExam.setDescription(rs.getString(fieldDescription));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return subjectExam;
    }


    public void addUserMark(int userId, int idSubjectExam, int result) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.INSERT_NEW_USER_MARK);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, idSubjectExam);
            pstmt.setInt(3, result);
            pstmt.setDate(4, new Date(System.currentTimeMillis()));
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }


    public void addUserAdmissionToFaculty(int userId, int faculty_id) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoFacultyRequests.ADD_USER_ADMISSION);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, faculty_id);
            pstmt.setDate(3, new Date(System.currentTimeMillis()));
            pstmt.setBoolean(4, false);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public List<Admission> getUserAdmissionForFaculty(int user_id, int faculty_id){
        List<Admission> userAdmission = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            AdmissionMapper admissionMapper = new AdmissionMapper();
            pstmt = con.prepareStatement(DaoUserRequest.SELECT_USER_ADMISSIONS_FOR_FACULTY);
            pstmt.setInt(1, user_id);
            pstmt.setInt(2, faculty_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
               Admission admission = admissionMapper.mapRow(rs);
               userAdmission.add(admission);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return userAdmission;
    }

    public void removeUserResults(int userId, int idSubjectExam) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.DELETE_ALL_USER_MARKS);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, idSubjectExam);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void removeUserAdmissions(int userId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.DELETE_ALL_USER_ADMISSION);
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }


    public void updateUser(String email, long idn, String password, int idUser) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.UPDATE_USER_BY_ID);
            pstmt.setString(1, email);
            pstmt.setLong(2, idn);
            pstmt.setString(3, password);
            pstmt.setInt(4, idUser);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void updateDetails(String name, String surname, String patronymic, String city, String region, String school_name, int average_certificate_point, String name_ua, String surname_ua, String patronymic_ua, String city_ua, String region_ua, String school_name_ua, int idUser) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.UPDATE_USER_DETAILS_BY_ID);
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, patronymic);
            pstmt.setString(4, city);
            pstmt.setString(5, region);
            pstmt.setString(6, school_name);
            pstmt.setInt(7, average_certificate_point);
            pstmt.setString(8, name_ua);
            pstmt.setString(9, surname_ua);
            pstmt.setString(10, patronymic_ua);
            pstmt.setString(11, city_ua);
            pstmt.setString(12, region_ua);
            pstmt.setString(13, school_name_ua);
            pstmt.setInt(14, idUser);


            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void deleteUserAdmission(int userId, String faculty_name, String locale) {
        String fieldName="";
        String request= "";
        if(locale.equals("en")){
            request = DaoUserRequest.DELETE_USER_ADMISSION;
            fieldName="name";
        }else{
            request = DaoUserRequest.DELETE_USER_ADMISSION_UA;
            fieldName="name_ua";
        }

        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(request);
            pstmt.setInt(1, userId);
            pstmt.setString(2, faculty_name);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    //find all user results
    public List<User> findAllUsersWithLimit(int startValue, int amountOnPage) {
        List<User> userList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(DaoUserRequest.GET_ALL_USERS_WITH_LIMIT);
            pstmt.setInt(1, startValue);
            pstmt.setInt(2, amountOnPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user=mapper.mapRow(rs);
                userList.add(user);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return userList;
    }


    public int getTotalCountOfUsers(){
        int result=0;
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(DaoUserRequest.GET_TOTAL_COUNT_OF_USERS);
            if (rs.next()) {
                result = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return result;
    }


    public void blockUserById(int userId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.BLOCK_USER_BY_ID);
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void unblockUserById(int userId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.UNBLOCK_USER_BY_ID);
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }



    public void makeUserAdmin(int userId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.SET_TO_USER_ADMIN_ROLE_BY_ID);
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void makeAdminUser(int userId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.SET_TO_USER_USER_ROLE_BY_ID);
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void deleteAllResultsBySubjectExamId(int subjectExamId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoUserRequest.DELETE_ALL_USER_RESULS_BY_SUBJECT_EXAM_ID);
            pstmt.setInt(1, subjectExamId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public List<Admission> getAllUsersAdmissionsForFacultyWithDate(int faculty_id, Date date){
        List<Admission> userAdmission = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            AdmissionMapper admissionMapper = new AdmissionMapper();
            pstmt = con.prepareStatement(DaoUserRequest.SELECT_ALL_USERS_ADMISSIONS_FOR_FACULTY);
            pstmt.setInt(1, faculty_id);

            pstmt.setDate(2,date);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Admission admission = admissionMapper.mapRow(rs);
                userAdmission.add(admission);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return userAdmission;
    }

    public UserFinalStatementResult getFinalStatementResultForFaculty(int userId, int facultyId, String locale) {
        UserFinalStatementResult userFinalStatementResults=null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String request;

        if(locale.equals("uk")){
            request = DaoUserRequest.GET_USER_ADMISSION_RESULTS_BY_ID_FACULTY_ID_UA;
        }else{
            request = DaoUserRequest.GET_USER_ADMISSION_RESULTS_BY_ID_FACULTY_ID;
        }
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(request);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, userId);
            pstmt.setInt(4, userId);
            pstmt.setInt(5, facultyId);
            pstmt.setInt(6, userId);
            pstmt.setInt(7, facultyId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                userFinalStatementResults = new UserFinalStatementResult();
                userFinalStatementResults.setFullname(rs.getString("fullName"));
                userFinalStatementResults.setIdn(rs.getLong("idn"));
                userFinalStatementResults.setTotalExamResult(rs.getInt("totalResult"));
                userFinalStatementResults.setCertificatePoint(rs.getInt("certificate_point"));
                userFinalStatementResults.setIs_approved(rs.getBoolean("is_approved"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return userFinalStatementResults;
    }




    private static class UserMapper {

        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getInt(DBFields.ENTITY__ID));
                user.setEmail(rs.getString(DBFields.USER__EMAIL));
                user.setIdn(rs.getLong(DBFields.USER__IDN));
                user.setBlocked(rs.getBoolean(DBFields.USER__BLOCK));
                user.setUser_role_id(rs.getInt(DBFields.USER__ROLE));
                user.setPassword(rs.getString(DBFields.USER__PASSWORD));
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private static class UserResultMapper {

        public UserResult mapRow(ResultSet rs, String locale) {
            try {
                UserResult result = new UserResult();
                result.setUserId(rs.getInt("user_id"));
                UserDao userDao = new UserDao();
                result.setSubject_exam(userDao.findSubjectExam(rs.getInt("subject_exam_id"), locale));
                result.setResult(rs.getInt("result"));
                result.setDateOfExam(rs.getDate("date_of_exam"));
                return result;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private static class AdmissionMapper {

        public Admission mapRow(ResultSet rs) {
            try {
                Admission admission = new Admission();
                admission.setId(rs.getInt("id"));
                admission.setUser_id(rs.getInt("user_id"));
                admission.setFaculty_id(rs.getInt("faculty_id"));
                admission.setDate(rs.getDate("date"));
                admission.setIs_approved(rs.getBoolean("is_approved"));
                return admission;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
