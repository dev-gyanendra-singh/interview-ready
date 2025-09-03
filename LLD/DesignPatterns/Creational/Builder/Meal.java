package LLD.DesignPatterns.Creational.Builder;

public class Meal {
    // Required
    private final String mainItem;

    // Optional
    private final String drink;
    private final String side;
    private final String dessert;
    private final boolean toyIncluded;

    private Meal(MealBuilder builder) {
        this.mainItem = builder.mainItem;
        this.drink = builder.drink;
        this.side = builder.side;
        this.dessert = builder.dessert;
        this.toyIncluded = builder.toyIncluded;
    }

    @Override
    public String toString() {
        return "Meal: " + mainItem +
                ", Drink: " + (drink != null ? drink : "None") +
                ", Side: " + (side != null ? side : "None") +
                ", Dessert: " + (dessert != null ? dessert : "None") +
                ", Toy: " + (toyIncluded ? "Included" : "Not Included");
    }

    // Builder class
    public static class MealBuilder {
        private final String mainItem; // required

        private String drink;
        private String side;
        private String dessert;
        private boolean toyIncluded;

        public MealBuilder(String mainItem) {
            this.mainItem = mainItem;
        }

        public MealBuilder addDrink(String drink) {
            this.drink = drink;
            return this;
        }

        public MealBuilder addSide(String side) {
            this.side = side;
            return this;
        }

        public MealBuilder addDessert(String dessert) {
            this.dessert = dessert;
            return this;
        }

        public MealBuilder includeToy(boolean includeToy) {
            this.toyIncluded = includeToy;
            return this;
        }

        public Meal build() {
            return new Meal(this);
        }
    }
}
