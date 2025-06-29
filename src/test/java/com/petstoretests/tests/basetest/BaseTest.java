package com.petstoretests.tests.basetest;

import com.utility.logger.ProjectLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    @BeforeEach
    public void setUp() {
        ProjectLogger.info("Начало теста");
    }

    @AfterEach
    public void tearDown() {
        ProjectLogger.info("Завершение теста\n");
    }
}
