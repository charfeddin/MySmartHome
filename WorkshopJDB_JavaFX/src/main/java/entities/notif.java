package entities;

public class notif {

    private int id;

    private String description;


    public notif() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public notif(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "notif{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}