package com.example.nutrilogix.Classes;

public class Food {
    private float calories;
    private float protein;
    private float fat;
    private float carb;
    public Food(float calories, float protein, float fat, float carb) {
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
    }

    //Getters
    public float getCalories() {
        return calories;
    }
    public float getProtein() {
        return protein;
    }
    public float getFat() {
        return fat;
    }
    public float getCarb() {
        return carb;
    }

    //Setters
    public void setCalories(float calories) {
        this.calories = calories;
    }
    public void setProtein(float protein) {
        this.protein = protein;
    }
    public void setFat(float fat) {
        this.fat = fat;
    }
    public void setCarb(float carb) {
        this.carb = carb;
    }


}
