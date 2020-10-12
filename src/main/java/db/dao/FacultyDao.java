package db.dao;

import beans.Faculty;
import beans.SubjectExam;
import beans.User;
import db.DBManager;

import java.sql.*;
import java.util.*;

public class FacultyDao {


    public List<SubjectExam> getSubjectExamsWithLimit(int startValue, int amountOnPage, String locale){
        String request;
        String fieldName;
        String fieldDescription;
        if(locale.equals("uk")){
            request=DaoFacultyRequests.GET_ALL_SUBJECT_EXAMS_WITH_LIMIT_UA;
            fieldName="name_ua";
            fieldDescription = "description_ua";
        }else{
            request=DaoFacultyRequests.GET_ALL_SUBJECT_EXAMS_WITH_LIMIT;
            fieldName="name";
            fieldDescription = "description";
        }
        ArrayList<SubjectExam> examsList = new ArrayList<SubjectExam>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            pstmt = con.prepareStatement(request);
            pstmt.setInt(1,startValue);
            pstmt.setInt(2,amountOnPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SubjectExam subjectExam = new SubjectExam();
                subjectExam.setId(rs.getInt("id"));
                subjectExam.setName(rs.getString(fieldName));
                subjectExam.setDescription(rs.getString(fieldDescription));
                examsList.add(subjectExam);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return examsList;
    }



    public int getTotalCountOfSubjectExams(){
        int result=0;
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(DaoFacultyRequests.GET_TOTAL_COUNT_OF_SUBJECT_EXAMS);
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

    public int getTotalCountOfFaculty(){
        int result=0;
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(DaoFacultyRequests.GET_TOTAL_COUNT_OF_FACULTIES);
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

    public List<Faculty> getFacultiesWithLimitOrderBugdet(int startValue, int amountOnPage, String locale){
        ArrayList<Faculty> facultiesList = new ArrayList<Faculty>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            pstmt = con.prepareStatement(DaoFacultyRequests.GET_ALL_FACULTIES_LIMIT_ORDER_BY_BUDGET_AMOUNT);
            pstmt.setInt(1, startValue);
            pstmt.setInt(2, amountOnPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Faculty faculty;
                faculty = facultyMapper.mapRow(rs, locale);
                facultiesList.add(faculty);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return facultiesList;
    }

    public List<Faculty> getFacultiesWithLimitOrderTotal(int startValue, int amountOnPage, String locale){
        ArrayList<Faculty> facultiesList = new ArrayList<Faculty>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            pstmt = con.prepareStatement(DaoFacultyRequests.GET_ALL_FACULTIES_LIMIT_ORDER_BY_TOTAL_AMOUNT);
            pstmt.setInt(1, startValue);
            pstmt.setInt(2, amountOnPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Faculty faculty;
                faculty = facultyMapper.mapRow(rs, locale);
                facultiesList.add(faculty);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return facultiesList;
    }

    public List<Faculty> getFacultiesWithLimitOrderAZ(int startValue, int amountOnPage, String locale){
        String request;
        if(locale.equals("uk")){
            request=DaoFacultyRequests.GET_ALL_FACULTIES_LIMIT_ORDER_BY_AZ_UA;
        }else{
            request=DaoFacultyRequests.GET_ALL_FACULTIES_LIMIT_ORDER_BY_ZA;
        }
        ArrayList<Faculty> facultiesList = new ArrayList<Faculty>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            pstmt = con.prepareStatement(request);
            pstmt.setInt(1, startValue);
            pstmt.setInt(2, amountOnPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Faculty faculty;
                faculty = facultyMapper.mapRow(rs, locale);
                facultiesList.add(faculty);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return facultiesList;
    }

    public List<Faculty> getFacultiesWithLimitOrderZA(int startValue, int amountOnPage, String locale){
        String request;
        if(locale.equals("uk")){
            request=DaoFacultyRequests.GET_ALL_FACULTIES_LIMIT_ORDER_BY_ZA_UA;
        }else{
            request=DaoFacultyRequests.GET_ALL_FACULTIES_LIMIT_ORDER_BY_ZA;
        }
        ArrayList<Faculty> facultiesList = new ArrayList<Faculty>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            pstmt = con.prepareStatement(request);
            pstmt.setInt(1, startValue);
            pstmt.setInt(2, amountOnPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Faculty faculty;
                faculty = facultyMapper.mapRow(rs, locale);
                facultiesList.add(faculty);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return facultiesList;
    }

    public List<Faculty> getAllFacultiesNoOffset(String locale){
        ArrayList<Faculty> facultiesList = new ArrayList<Faculty>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(DaoFacultyRequests.GET_ALL_FACULTIES);
            while (rs.next()) {
                Faculty faculty = facultyMapper.mapRow(rs, locale);
                facultiesList.add(faculty);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        System.out.println(Arrays.asList(facultiesList));
        return facultiesList;
    }

    public Faculty findFacultyById(String id, String locale) {
        Faculty faculty = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            pstmt = con.prepareStatement(DaoFacultyRequests.GET_FACULTY_BY_ID);
            pstmt.setInt(1, Integer.parseInt(id));
            rs = pstmt.executeQuery();
            if (rs.next())
                faculty = facultyMapper.mapRow(rs, locale);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return faculty;
    }

    public Set<Integer> getFacultyDemends(String id){
        Set<Integer> demendsList = new TreeSet<Integer>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            pstmt = con.prepareStatement(DaoFacultyRequests.GET_FACULTY_EXAM_DEMENDS_BY_ID);
            pstmt.setInt(1, Integer.parseInt(id));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int idDemend = rs.getInt("id");
                demendsList.add(idDemend);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return demendsList;
    }

    public Set<Integer> getFacultiesByDemends(String idSubjectExam){
        Set<Integer> demendsList = new TreeSet<Integer>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            pstmt = con.prepareStatement(DaoFacultyRequests.GET_FACULTIES_BY_SUBJECT_EXAM_DEMENDS);
            pstmt.setInt(1, Integer.parseInt(idSubjectExam));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int idDemend = rs.getInt("faculty_id");
                demendsList.add(idDemend);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return demendsList;
    }

    public List<SubjectExam> getFacultyDemendsWithName(String id, String locale){
        List <SubjectExam> examList = new ArrayList<>();
        String request;

        if(locale.equals("en")){
            request=DaoFacultyRequests.GET_FACULTY_EXAM_DEMENDS_WITH_FACULTY_NAME_BY_ID;
        }else{
            request=DaoFacultyRequests.GET_FACULTY_EXAM_DEMENDS_WITH_FACULTY_NAME_BY_ID_UA;
        }
        Set<Integer> demendsList = new TreeSet<Integer>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            pstmt = con.prepareStatement(request);
            pstmt.setInt(1, Integer.parseInt(id));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SubjectExam subjectExam = new SubjectExam();
                subjectExam.setId(rs.getInt(1));
                subjectExam.setName(rs.getString(2));
                subjectExam.setDescription(rs.getString(3));
                examList.add(subjectExam);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return examList;
    }

    public void deleteExamDemendForFaculty(int examId, int facultyId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoFacultyRequests.DELETE_EXAM_DEMENDS);
            pstmt.setInt(1, examId);
            pstmt.setInt(2, facultyId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void deleteAllFacultyAdmissions(int facultyId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoFacultyRequests.DELETE_ALL_FACULTY_ADMISSION_BY_ID);
            pstmt.setInt(1, facultyId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }


    public List<SubjectExam> getAllSubjectExams(String locale){
        //todo
        String fieldName="";
        String fieldDescription="";
        String request= "";
        if(locale.equals("en")){
            request = DaoFacultyRequests.GET_ALL_SUBJECTS;
            fieldName="name";
            fieldDescription="description";
        }else{
            request = DaoFacultyRequests.GET_ALL_SUBJECTS;
            fieldName="name_ua";
            fieldDescription="description_ua";
        }
        List<SubjectExam> subjectsList = new ArrayList<SubjectExam>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            FacultyMapper facultyMapper = new FacultyMapper();
            pstmt = con.prepareStatement(DaoFacultyRequests.GET_ALL_SUBJECTS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SubjectExam subjectExam = new SubjectExam();
                subjectExam.setId(rs.getInt("id"));
                subjectExam.setName(rs.getString(fieldName));
                subjectExam.setDescription(rs.getString(fieldDescription));
                subjectsList.add(subjectExam);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return subjectsList;
    }


    public void deleteFacultyById(int facultyId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoFacultyRequests.DELETE_FACULTY_BY_ID);
            pstmt.setInt(1, facultyId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void deleteExamDemndsFacultyFacultyById(int facultyId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoFacultyRequests.DELETE_FACULTY_EXAM_DEMENDS_BY_FACULTY_ID);
            pstmt.setInt(1, facultyId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void deleteSubjectExamById(int subjectExamId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoFacultyRequests.DELETE_SUBJECT_BY_ID);
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

    public void addExamDemendForFaculty(int idExam, int idFaculty) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoFacultyRequests.ADD_EXAM_DEMENDS_BY_FACULTY_ID);
            pstmt.setInt(1, idExam);
            pstmt.setInt(2, idFaculty);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void addFaculty(String name, int budget_amount, int total_amount, String description, String name_ua, String description_ua) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoFacultyRequests.ADD_FACULTY);
            pstmt.setString(1, name);
            pstmt.setInt(2, budget_amount);
            pstmt.setInt(3, total_amount);
            pstmt.setString(4, description);
            pstmt.setString(5, name_ua);
            pstmt.setString(6, description_ua);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public void addSubjectExam(String name, String description, String name_ua, String description_ua) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(DaoFacultyRequests.ADD_SUBJECT);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, name_ua);
            pstmt.setString(4, description_ua);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }


    private static class FacultyMapper {

        public Faculty mapRow(ResultSet rs, String locale) {
            try {
                String fieldName="";
                String fieldDescription= "";
                if(locale.equals("en")){
                    fieldName=DBFields.FACULTY__NAME;
                    fieldDescription="description";
                }else{
                    fieldName=DBFields.FACULTY__NAME_UA;
                    fieldDescription="description_ua";
                }
                Faculty faculty = new Faculty();
                faculty.setId(rs.getInt(DBFields.ENTITY__ID));
                faculty.setName(rs.getString(fieldName));
                faculty.setBudgetAmount(rs.getInt(DBFields.BUDGET__AMOUNT));
                faculty.setTotalAmount(rs.getInt(DBFields.TOTAL__AMOUNT));
                faculty.setDescription(rs.getString(fieldDescription));
                faculty.setLogoUrl(rs.getString(DBFields.LOGO__URL));
                return faculty;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
