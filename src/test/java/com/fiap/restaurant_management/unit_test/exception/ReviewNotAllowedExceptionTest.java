package com.fiap.restaurant_management.aplication.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReviewNotAllowedExceptionTest {

    @Test
    public void testReviewNotAllowedExceptionWithCode() {
        Long customerCode = 1L;
        ReviewNotAllowedException exception = assertThrows(ReviewNotAllowedException.class, () -> {
            throw new ReviewNotAllowedException(customerCode);
        });
        assertEquals("customer " + customerCode + " cannot review the restaurant without the status of the FINISHED reservation", exception.getMessage());
    }
}