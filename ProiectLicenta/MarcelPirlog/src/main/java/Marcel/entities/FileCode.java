package Marcel.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class FileCode {
    private SimpleStringProperty fileName;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private SimpleLongProperty size;

    public FileCode(String fileName, LocalDateTime createDate, LocalDateTime lastUpdate, Long size) {
        this.fileName = new SimpleStringProperty(fileName);
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.size = new SimpleLongProperty(size);
    }

    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public long getSize() {
        return size.get();
    }

    public SimpleLongProperty sizeProperty() {
        return size;
    }

    public void setSize(long size) {
        this.size.set(size);
    }

    public void setSize(int size) {
        this.size.set(size);
    }

    @Override
    public String toString() {
        return "FileCode{" +
                "fileName=" + fileName +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate +
                ", size=" + size +
                '}';
    }
}
