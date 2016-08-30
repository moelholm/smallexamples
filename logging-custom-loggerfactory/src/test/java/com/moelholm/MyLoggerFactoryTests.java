package com.moelholm;

import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.assertEquals;

public class MyLoggerFactoryTests {

    @Test
    public void getLogger_whenGivenNothing_thenReturnsLoggerWithCallersClassName() {

        // Given
        // ( a little bit of magic ... )

        // When
        Logger loggerOne = MyLoggerFactory.getLogger();
        Logger loggerTwo = LoggerTester.LOG;

        // Then
        assertEquals(MyLoggerFactoryTests.class.getName(), loggerOne.getName());
        assertEquals(LoggerTester.class.getName(), loggerTwo.getName());
    }

    private static class LoggerTester {
        private static final Logger LOG = MyLoggerFactory.getLogger();
    }

}
