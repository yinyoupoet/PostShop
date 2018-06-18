package bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Seconddirectory {
    private int id;
    private String directoryName;
    private int firstDirectoryId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "directoryName", nullable = false, length = 255)
    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    @Basic
    @Column(name = "firstDirectoryId", nullable = false)
    public int getFirstDirectoryId() {
        return firstDirectoryId;
    }

    public void setFirstDirectoryId(int firstDirectoryId) {
        this.firstDirectoryId = firstDirectoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seconddirectory that = (Seconddirectory) o;

        if (id != that.id) return false;
        if (firstDirectoryId != that.firstDirectoryId) return false;
        if (directoryName != null ? !directoryName.equals(that.directoryName) : that.directoryName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (directoryName != null ? directoryName.hashCode() : 0);
        result = 31 * result + firstDirectoryId;
        return result;
    }
}
