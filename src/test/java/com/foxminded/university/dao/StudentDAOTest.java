package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

class StudentDAOTest extends DataSourceBasedDBTestCase {
    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(
                "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:schema.sql'");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("dbUnitData.xml"));
    }

    @Test
    void addNewStudentToBase() throws DAOException {
        Student expected = new Student(2, "Loh", "Lohov");

        Student actual = studentDAO.addNewStudentToBase("Loh", "Lohov");
        assertEquals(expected, actual);
    }

    @Test
    void setStudentToGroup() {
    }

    @Test
    void setStudentToCourse() {
    }

    @Test
    void deleteStudentFromBaseById() {
    }
}