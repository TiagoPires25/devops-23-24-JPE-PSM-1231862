package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void testEmployee(){
        //arrange
        String firstName = "Joaquim";
        String lastName = "Faria";
        String description = "Consultor";
        String email = "joaquimfaria@gmail.com";
        int jobYears = 30;

        //act
        Employee employee = new Employee(firstName, lastName, description, jobYears, email);

        //assert
        assertNotNull(employee);
    }
    @Test
    void testEmployee_InvalidYears() {
        //arrange
        String firstName = "Joaquim";
        String lastName = "Faria";
        String description = "Consultor";
        int jobYears = -30;
        String email = "joaquimfaria@gmail.com";
        String expected = "Invalid employee data";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears, email);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testEmployee_nullFirstName() {
        //arrange
        String firstName = null;
        String lastName = "Faria";
        String description = "Consultor";
        int jobYears = 30;
        String email = "joaquimfaria@gmail.com";
        String expected = "Invalid employee data";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears, email);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testEmployee_emptyFirstName() {
        //arrange
        String firstName = " ";
        String lastName = "Faria";
        String description = "Consultor";
        int jobYears = 30;
        String email = "joaquimfaria@gmail.com";
        String expected = "Invalid employee data";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears, email);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testEmployee_nullLastName() {
        //arrange
        String firstName = "Joaquim";
        String lastName = null;
        String description = "Consultor";
        int jobYears = 30;
        String email = "joaquimfaria@gmail.com";
        String expected = "Invalid employee data";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears, email);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testEmployee_emptyLastName() {
        //arrange
        String firstName = "Joaquim";
        String lastName = " ";
        String description = "Consultor";
        int jobYears = 30;
        String email = "joaquimfaria@gmail.com";
        String expected = "Invalid employee data";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears, email);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testEmployee_nullDescription() {
        //arrange
        String firstName = "Joaquim";
        String lastName = "Faria";
        String description = null;
        String email = "joaquimfaria@gmail.com";
        int jobYears = 30;
        String expected = "Invalid employee data";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears, email);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testEmployee_emptyDescription() {
        //arrange
        String firstName = "Joaquim";
        String lastName = "Faria";
        String description = " ";
        int jobYears = 30;
        String email = "joaquimfaria@gmail.com";
        String expected = "Invalid employee data";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears, email);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testEmployee_nullEmail() {
        //arrange
        String firstName = "Joaquim";
        String lastName = "Faria";
        String description = "Consultor";
        int jobYears = 30;
        String email = null;
        String expected = "Invalid employee data";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears, email);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testEmployee_emptyEmail() {
        //arrange
        String firstName = "Joaquim";
        String lastName = "Faria";
        String description = "Consultor";
        int jobYears = 30;
        String email = " ";
        String expected = "Invalid employee data";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears, email);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testEmployee_EmailDoesNotContainAdressSign() {
        //arrange
        String firstName = "Joaquim";
        String lastName = "Faria";
        String description = "Consultor";
        int jobYears = 30;
        String email = "joaquimfaria.com";
        String expected = "Invalid employee data";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears, email);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }
}