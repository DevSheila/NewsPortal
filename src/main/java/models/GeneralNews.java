package models;

import java.util.Objects;

public class GeneralNews {
    private  int id;
    private int depId;
    private String title;
    private String body;
    private String writtenBy;
    private String type;
    private static final String newsType="general";

    public GeneralNews(int depId, String title, String body, String writtenBy) {
        this.depId = depId;
        this.title = title;
        this.body = body;
        this.writtenBy = writtenBy;
        this.type=this.newsType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
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
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
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
        return getDepId() == that.getDepId() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getBody(), that.getBody()) && Objects.equals(getWrittenBy(), that.getWrittenBy()) && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepId(), getTitle(), getBody(), getWrittenBy(), getType());
    }
}
