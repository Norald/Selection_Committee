package db;

public class DaoFacultyRequests {
    public static final String GET_ALL_FACULTIES = "SELECT * FROM faculty";
    public static final String GET_FACULTY_BY_NAME = " SELECT * FROM faculty WHERE name = (?)";
    public static final String GET_FACULTY_BY_ID = " SELECT * FROM faculty WHERE id = (?)";
    public static final String GET_FACULTY_EXAM_DEMENDS_BY_ID = " SELECT se.id, se.name FROM subject_exam se INNER JOIN faculty_exam_demends fed ON se.id = fed.subject_exam_id INNER JOIN faculty f ON f.id = fed.faculty_id WHERE f.id = (?)";
    public static final String GET_FACULTY_EXAM_DEMENDS_BY_NAME = " SELECT se.id, se.name FROM subject_exam se INNER JOIN faculty_exam_demends fed ON se.id = fed.subject_exam_id INNER JOIN faculty f ON f.id = fed.faculty_id WHERE f.name = (?)";
    public static final String GET_ALL_FACULTY_ADMISSIONS_BY_FACULTY_NAME = "SELECT a.id, a.user_id, a.date, a.is_approved FROM admission a INNER JOIN faculty f ON a.faculty_id = f.id WHERE f.name = (?)";
    public static final String GET_ALL_FACULTY_ADMISSIONS_BY_ID = "SELECT a.id, a.user_id, a.date, a.is_approved FROM admission a INNER JOIN faculty f ON a.faculty_id = f.id WHERE f.id = (?)";
    public static final String ADD_FACULTY = "INSERT INTO faculty (name, budget_amount, total_amount, description, logo_url) VALUES(?,?,?,?,?)";
    public static final String UPDATE_FACULTY_BY_NAME = "UPDATE faculty SET name = (?) , budget_amount = (?), total_amount = (?), description = (?), logo_url = (?) WHERE name = (?)";
    public static final String DELETE_FACULTY_BY_NAME = "DELETE FROM faculty WHERE name = (?)";
    public static final String ADD_EXAM_DEMENDS_BY_FACULTY_ID = "INSERT INTO faculty_exam_demends (subject_exam_id) VALUES(?) WHERE faculty_id = (?)";
    public static final String UPDATE_EXAM_DEMENDS = "UPDATE faculty_exam_demends SET subject_exam_id = (?), faculty_id = (?)";
    public static final String DELETE_EXAM_DEMENDS = "DELETE FROM faculty_exam_demends WHERE subject_exam_id = (?) AND faculty_id = (?)";
    public static final String GET_ALL_SUBJECTS = "SELECT * FROM subject_exam";
    public static final String ADD_SUBJECT = "INSERT INTO subject_exam(name,description) VALUES (?, ?)";
    public static final String UPDATE_SUBJECT_BY_ID = "UPDATE subject_exam SET name = (?), description = (?) WHERE id = (?)";
    public static final String DELETE_SUBJECT_BY_ID = "DELETE FROM subject_exam WHERE id = (?)";
    public static final String DELETE_SUBJECT_BY_NAME = "DELETE FROM subject_exam WHERE name = (?)";
}
