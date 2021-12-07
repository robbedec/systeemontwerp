INSERT INTO certificate_data_model (certificate_id, student_id, degree_id) VALUES ('1','1','1'),('2','1','2'),('3','2','1')

INSERT INTO task_data_model (task_id, course_id, description, due_date) VALUES ('1','1','Taak 1', TO_DATE('01/12/2021','DD/MM/YYYY'))

INSERT INTO task_submission_data_model (submission_id, student_id, task_id, file, date_submited, score) VALUES ('1','1','1','taak1.zip',TO_DATE('27/11/2021','DD/MM/YYYY'),-1)
