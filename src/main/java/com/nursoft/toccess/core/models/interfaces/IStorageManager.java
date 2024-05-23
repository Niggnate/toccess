package com.nursoft.toccess.core.models.interfaces;

import java.io.IOException;

public interface IStorageManager {
    String FILE_PATH = "src/main/resources/com/nursoft/toccess/agenda.txt";
    String SPLITTER = "::::::::::";

    void storeRecords() throws IOException;
    void collectRecords() throws IOException;
}
