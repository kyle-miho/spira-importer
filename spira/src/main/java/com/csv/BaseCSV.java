package com.csv;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCSV implements CSV {

    public BaseCSV(String filePath) {
        _filePath = filePath;
    }

    @Override
    public void addRow(List<String> row) {
        _rows.add(row);
    }

    public String getFilePath() {
        return _filePath;
    }

    @Override
    public List<List<String>> getRows() {
        return null;
    }

    private String _filePath;

    private List<List<String>> _rows = new ArrayList<>();
}
