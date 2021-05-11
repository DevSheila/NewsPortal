package models;

import java.util.Objects;

public class GeneralNews {
    private  int id;

    private String title;
    private String body;
    private String written_by;
    private String type;
    private static final String NEWSTYPE="general";
    private int dep_id =0;

    public GeneralNews( String title, String body, String written_by) {
        this.title = title;
        this.body = body;
        this.written_by = written_by;
        this.type=this.NEWSTYPE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getWrittenBy() {
        return written_by;
    }

    public void setWrittenBy(String writtenBy) {
        this.written_by = writtenBy;
    }
    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneralNews)) return false;
        GeneralNews that = (GeneralNews) o;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getBody(), that.getBody()) && Objects.equals(getWrittenBy(), that.getWrittenBy()) && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getBody(), getWrittenBy(), getType());
    }
}
