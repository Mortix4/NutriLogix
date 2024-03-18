package com.example.nutrilogix.Classes;

public class Meal {
    private Food[] foods;
    private int image;
    private String name;

    // C'tor
    public Meal(String name, int size, int image) {
        this.foods = new Food[size];
    }

    // Getters
    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Food[] getFoods() {
        return foods;
    }

    // Setters
    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addFood(Food food) {
        for (int i = 0; i < foods.length; i++) {
            if (foods[i] == null) {
                foods[i] = food;
                return;
            }
        }
    }


}
