package com.csv;

import com.spira.testcase.folder.TestCaseFolder;

import java.util.List;

public interface CSV {

    public TestCaseFolder getRootFolder() throws Exception;

    public void addRow(List<String> row);

    public List<List<String>> getRows();

    public String getFilePath();
}
