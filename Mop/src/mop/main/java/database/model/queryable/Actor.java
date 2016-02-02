package mop.main.java.database.model.queryable;

public class Actor extends Queryable {

    private int actorId;
    private String name;

    public int getId() {

        return actorId;
    }

    public String getName() {

        return name;
    }

    public void setId(int actorId) {

        this.actorId = actorId;
    }

    public void setName(String name) {

        this.name = name;
    }
}
