package models;

import java.util.Objects;

public class DepNews extends GeneralNews{

    private static final String NEWSTYPE ="Department";
    private  String type;

    public DepNews(int depId, String title, String body, String writtenBy) {
        super(depId, title, body, writtenBy);
        this.type=this.NEWSTYPE;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepNews)) return false;
        if (!super.equals(o)) return false;
        DepNews depNews = (DepNews) o;
        return Objects.equals(getType(), depNews.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getType());
    }
}
