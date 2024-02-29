package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void testEmployee() {
        //arrange
        String firstName = "Bilbo";
        String lastName = "Baggins";
        String description = "burglar";
        int jobYears = -30;

        //assert
        assertThrows(IllegalArgumentException.class, () -> {
            //act
            new Employee(firstName, lastName, description, jobYears);
        });
    }
}