package com.todoist.util;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.testng.annotations.DataProvider;

import java.io.IOException;

/**
 * @author biswanath.padhi
 */
public class DataGenerator {

    private final String createProjectDataName = "project";
    private final String createTaskDataSheetName = "task";

    /**
     * DataProvider for createProjectData - for TestNG Test
     * @return the Object[][] for createProjectData
     * @throws InvalidFormatException
     * @throws IOException
     */
    @DataProvider(name="createProjectData")
    public Object[][] getCreateProjectData() throws InvalidFormatException, IOException {
        Object[][] data = com.todoist.util.ExcelReader.getTestData(createTaskDataSheetName);
        printData(data);
        return data;
    }

    /**
     * printing the data created from DataProvider methods
     * @param data
     */
    private void printData(Object[][] data) {
        for(int row = 0; row < 1; row++) {
            for (int col = 0; col < data[0].length; col++) {
                System.out.print(data[0][col]+", ");
            }
            System.out.println();
        }
    }

    /**
     * DataProvider for create Task
     * @return the Object[][] - full rows of create task data
     * @throws InvalidFormatException
     * @throws IOException
     */
    @DataProvider(name="createTask")
    public Object[][] getCreateTaskData() throws InvalidFormatException, IOException {
        Object[][] data = com.todoist.util.ExcelReader.getTestData(createTaskDataSheetName);
        printData(data);
        return data;
    }

    /**
     *
     * @return
     * @throws InvalidFormatException
     * @throws IOException
     */
    @DataProvider(name = "testproject")
    public Object[][] getProjectData() throws InvalidFormatException, IOException {
        Object[][] data = com.todoist.util.ExcelReader.getTestData("project");
        printData(data);
        return data;
    }
}


