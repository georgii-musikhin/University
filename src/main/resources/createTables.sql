DROP TABLE IF EXISTS groups CASCADE;
CREATE TABLE groups (
    group_id SERIAL NOT NULL,
    group_name VARCHAR(30),
    CONSTRAINT groups_pkey PRIMARY KEY (group_id)
);

DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students(
    student_id SERIAL NOT NULL,
    group_id INTEGER DEFAULT NULL,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    CONSTRAINT students_pkey PRIMARY KEY (student_id),
    CONSTRAINT students_group_id_fkey FOREIGN KEY (group_id)
            REFERENCES groups (group_id) ON DELETE SET DEFAULT
);

DROP TABLE IF EXISTS courses CASCADE;
CREATE TABLE courses(
    course_id SERIAL NOT NULL,
    course_name VARCHAR(30),
    course_description VARCHAR(50),
    CONSTRAINT courses_pkey PRIMARY KEY (course_id)
);