package models;

import java.util.Objects;

public class DepNews extends GeneralNews{

//    private static final String NEWSTYPE ="departmental";
//    private  String type;
    private int dep_id;
    public DepNews(int dep_id, String title, String body, String written_by) {
        super( title, body, written_by);
        this.dep_id =dep_id;
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }
    //    @Override
//    public String getType() {
//        return type;
//    }
//
//    @Override
//    public void setType(String type) {
//        this.type = type;
//    }

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
