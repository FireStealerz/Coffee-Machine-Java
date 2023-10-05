package machine;

public class Recipes {

    private int water, milk, coffeeBeans, price ;

    public Recipes(int water, int milk, int coffeeBeans, int price) {
        setWater(water);
        setMilk(milk);
        setCoffeeBeans(coffeeBeans);
        setPrice(price);
    }

    private void setWater(int water) {
        this.water = water;
    }

    public int getWater() {
        return water;
    }

    private void setMilk(int milk) {
        this.milk = milk;
    }

    public int getMilk() {
        return milk;
    }

    private void setCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans = coffeeBeans;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    private void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}
