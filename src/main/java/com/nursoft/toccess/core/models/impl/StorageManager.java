package com.nursoft.toccess.core.models.impl;

import com.nursoft.toccess.core.models.Agenda;
import com.nursoft.toccess.core.models.AgendaView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public final class StorageManager extends AbstractStorageManager {

    private static StorageManager instance = null;
    private ObservableList<Agenda> observableDataList;
    private ObservableList<AgendaView> observableDataViewList;

    private StorageManager() {
        super();
    }

    // Get class singleton instance
    public static StorageManager getInstance() {
        if (instance == null) {
            instance = new StorageManager();
        }
        return instance;
    }

    @Override
    public void storeRecords() throws IOException {
        final Path path = Paths.get(FILE_PATH);
        try (final BufferedWriter bw = Files.newBufferedWriter(path)) {
            String s = SPLITTER;
            for (Agenda agenda : observableDataList) {
                bw.write(String.format(
                        "%s%s%s%s%s%s%s%s%s",
                        agenda.getId(),
                        s,
                        agenda.getTitle(),
                        s,
                        agenda.getDescription(),
                        s,
                        agenda.getCategory(),
                        s,
                        agenda.getDeadline().format(getFormatter())
                ));
                bw.newLine();
            }
        }
    }

    @Override
    public void collectRecords() throws IOException {
        observableDataList = FXCollections.observableArrayList();
        observableDataViewList = FXCollections.observableArrayList();

        final Path path = Paths.get(FILE_PATH);
        try (final BufferedReader br = Files.newBufferedReader(path)) {
            String line;

            while ((line = br.readLine()) != null) {
                final String[] row = line.split(SPLITTER);
                final String _id = row[0];
                final String title = row[1];
                final String description = row[2];
                final String category = row[3];
                final String deadline = row[4];

                final LocalDate date = LocalDate.parse(deadline, getFormatter());
                final Agenda agenda = new Agenda(title, description, category, date);
                agenda.setId(_id);

                observableDataList.add(agenda);
                observableDataViewList.add(new AgendaView(agenda));
            }
        }
    }

    public ObservableList<Agenda> getObservableDataList() {
        return observableDataList;
    }

    public ObservableList<AgendaView> getObservableDataViewList() {
        return observableDataViewList;
    }
}
