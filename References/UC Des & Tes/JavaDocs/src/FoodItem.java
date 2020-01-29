/**
 * Class hold a food item with some basic nutrients. Class can be used in a weight tracker application.
 * Purpose of said class here...
 * Requirements of the class...
 * Required imports... jars...
 * (HTML Code can be included here...)
 */
public class FoodItem
{
    /**
     * Variables
     */
    private String foodName;
    private int calories;
    private int fatGrams;
    private int sugar;


    public FoodItem() {
    }

    /**
     * @param foodName
     * @param calories
     * @param fatGrams
     * @param sugar
     */
    public FoodItem(String foodName, int calories, int fatGrams, int sugar) {
        this.foodName = foodName;
        this.calories = calories;
        this.fatGrams = fatGrams;
        this.sugar = sugar;
    }


    /**
     *
     * @return
     */
    public boolean isYummy()
    {
        if(calories > 300)
            return true;
        else
            return false;
    }

    /**
     * @return
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * @param foodName
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    /**
     * @return
     */
    public int getCalories() {
        return calories;
    }

    /**
     * @param calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * @return
     */
    public int getFatGrams() {
        return fatGrams;
    }

    /**
     * @param fatGrams
     */
    public void setFatGrams(int fatGrams) {
        this.fatGrams = fatGrams;
    }

    /**
     * @return
     */
    public int getSugar() {
        return sugar;
    }

    /**
     * @param sugar
     */
    public void setSugar(int sugar) {
        this.sugar = sugar;
    }
}
