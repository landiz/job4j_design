package ru.job4j.question;

public class Info {

    private int added;
    private int changed;
    private int deleted;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    /* Геттеры, сеттеры, equals() & hashCode() */

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Info)) {
            return false;
        }

        Info info = (Info) o;

        if (added != info.added) {
            return false;
        }
        if (changed != info.changed) {
            return false;
        }
        return deleted == info.deleted;
    }

    @Override
    public int hashCode() {
        int result = added;
        result = 31 * result + changed;
        result = 31 * result + deleted;
        return result;
    }
}