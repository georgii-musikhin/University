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
    course_description VARCHAR(150),
    CONSTRAINT courses_pkey PRIMARY KEY (course_id)
);

INSERT INTO students (first_name, last_name)  VALUES
('Mikhail', 'Bobrov'),
('Gera', 'Bodrov');

INSERT INTO groups (group_name)  VALUES
('AS-49'),
('IT-15');

INSERT INTO courses (course_name, course_description)  VALUES
('FirstCourse', 'This is first course'),
('SecondCourse', 'This is second course');

DROP TABLE IF EXISTS STUDENTS_COURSES CASCADE;
CREATE TABLE students_courses (
    student_id INTEGER REFERENCES students (student_id) ON DELETE CASCADE,
    course_id  INTEGER REFERENCES courses (course_id) ON DELETE CASCADE,
    PRIMARY KEY (student_id, course_id)
    );