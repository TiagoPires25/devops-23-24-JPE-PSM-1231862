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
        int jobYears = 30;

        //act
        Employee employee = new Employee(firstName, lastName, description, jobYears);

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
        String expected = "Invalid employee data";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears);
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
        String expected = "Invalid employee data";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears);
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
        String expected = "Invalid employee data";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears);
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
        String expected = "Invalid employee data";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears);
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
        String expected = "Invalid employee data";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears);
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
        int jobYears = 30;
        String expected = "Invalid employee data";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears);
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
        String expected = "Invalid employee data";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }


}