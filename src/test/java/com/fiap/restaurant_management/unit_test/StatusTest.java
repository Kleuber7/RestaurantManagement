package com.fiap.restaurant_management.domain.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusTest {

    @Test
    public void testStatusValues() {
        assertEquals(1, Status.CONFIRMED.getValue());
        assertEquals(2, Status.PENDING.getValue());
        assertEquals(3, Status.DENIED.getValue());
        assertEquals(4, Status.FINISHED.getValue());
    }

    @Test
    public void testStatusEnum() {
        assertEquals(Status.CONFIRMED, Status.valueOf("CONFIRMED"));
        assertEquals(Status.PENDING, Status.valueOf("PENDING"));
        assertEquals(Status.DENIED, Status.valueOf("DENIED"));
        assertEquals(Status.FINISHED, Status.valueOf("FINISHED"));
    }
}