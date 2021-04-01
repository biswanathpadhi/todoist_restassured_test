package com.todoist.util;

import org.testng.annotations.Test;

/**
 *
 */
public class DataGeneratorTest {

    @Test(dataProvider = "testproject", dataProviderClass = com.todoist.util.DataGenerator.class)
    public void testDataReadingFromExcelUsingDataProvider(String projectName){
        System.out.println("projectName = " + projectName);
    }
}
