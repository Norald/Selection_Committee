INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (1,"Math exam","External Independent Testing", "Екзамен з Математики", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (2,"Ukrainian language and literature exam","External Independent Testing", "Екзамен з Української мови та літератури", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (3,"Physics exam","External Independent Testing" , "Екзамен з Фізики", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (4,"Spanish language exam","External Independent Testing" , "Екзамен з Іспанської мови", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (5,"German language exam","External Independent Testing", "Екзамен з Німецької мови", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (6,"French language exam","External Independent Testing", "Екзамен з Французької мови", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (7,"English language exam","External Independent Testing", "Екзамен з Англійської мови", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (8,"History of Ukraine exam","External Independent Testing" , "Екзамен з Історії України", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (9,"Biology exam","External Independent Testing", "Екзамен з Біології", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (10,"Geography exam","External Independent Testing", "Екзамен з Географії", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (11,"Chemistry exam","External Independent Testing", "Екзамен з Хімії", "Екзамен Зовнішнього Незалежного Оцінювання");


INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (1, "Faculty of Information Technology", 25 , 50 , "Faculty of Information Technology","Факультет Інформаційних Технологій","Факультет Інформаційних Технологій");
INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (2, "Faculty of Physics", 15 , 30 , "Faculty of Physics","Факультет Фізики","Факультет Фізики");
INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (3, "Faculty of Chemistry", 15 , 30 , "Faculty of Chemistry","Факультет Хімії","Факультет Хімії");
INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (4, "Faculty of History", 40 , 100 , "Faculty of History","Факультет Історії","Факультет Історії");
INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (5, "Faculty of Construction", 25 , 50 , "Faculty of Construction","Факультет Будівництва","Факультет Будівництва");
INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (6, "Faculty of Finance and Economics", 25 , 50 , "Faculty of Finance and Economics","Факультет Економіки та фінансів","Факультет Економіки та фінансів");


INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (1 , 1);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 1);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (7 , 1);

INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (1 , 2);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 2);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (3 , 2);

INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (1 , 3);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 3);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (11 , 3);

INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (8 , 4);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 4);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (7 , 4);

INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (1 , 5);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 5);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (10 , 5);

INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (1 , 6);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 6);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (7 , 6);


INSERT INTO user_role (id , name) VALUES (1 , "user");
INSERT INTO user_role (id , name) VALUES (2 , "admin");


INSERT INTO user (id , email, idn, block, user_role_id, password) VALUES (1 , "test@gmail.com", 111111, 0, 2, 1111);


INSERT INTO user_details(user_id, name, surname, patronymic, city, region, school_name, average_certificate_point, name_ua, surname_ua, patronymic_ua, city_ua, region_ua, school_name_ua) VALUES (1, "test", "test","test","test","test","test",200, "test", "test","test","test","test","test");


INSERT INTO admission (id, user_id, faculty_id, date, is_approved) VALUES (1, 1, 1, "2020-09-23", 0);


INSERT INTO users_results(user_id, subject_exam_id, result, date_of_exam) VALUES (1, 1, 200,"2020-09-23");

INSERT INTO users_results (user_id, subject_exam_id, result, date_of_exam) VALUES (1, 2, 200,"2020-09-23");

INSERT INTO users_results (user_id, subject_exam_id, result, date_of_exam) VALUES (1, 7, 200,"2020-09-23");