package LLD.DesignPatterns.Creational.Builder;

public class Restaurant {
    public static void main(String[] args) {
        Meal adultMeal = new Meal.MealBuilder("Cheeseburger")
                .addDrink("Coke")
                .addSide("Fries")
                .build();

        Meal kidsMeal = new Meal.MealBuilder("Chicken Nuggets")
                .addDrink("Apple Juice")
                .addDessert("Ice Cream")
                .includeToy(true)
                .build();

        System.out.println(adultMeal);
        System.out.println(kidsMeal);
    }
}



