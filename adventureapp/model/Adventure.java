package com.example.adventureapp.model;

public class Adventure {
    int id;
    String name;
    String description;
    String cost;

   // public Adventure(){}

    public Adventure(int _id, String _name, String _description, String _cost)
    {
        id =_id;
        name = _name;
        description = _description;
        cost = _cost;
    }

    public Adventure( String _name, String _description, String _cost)
    {
        name = _name;
        description = _description;
        cost = _cost;
    }

    public Adventure() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
