package com.fiap.restaurant_management.aplication.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerNotFoundExceptionTest {

    @Test
    public void testCustomerNotFoundExceptionWithCode() {
        Long customerCode = 1L;
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            throw new CustomerNotFoundException(customerCode);
        });
        assertEquals("Customer with code " + customerCode + " was not found", exception.getMessage());
    }
}