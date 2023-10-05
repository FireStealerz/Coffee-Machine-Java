package machine;

public class Machine {

    public Machine() {
        machineMenu();
    }
    private enum MachineState {
        OFF, MAIN, BUYING, FILLING_WATER, FILLING_MILK, FILLING_BEANS, FILLING_CUPS
    }

     private MachineState state;

    private int cupsOfCoffee = 9, water = 400, milk = 540, coffeeBeans = 120, money = 550;

    private final Recipes Espresso = new Recipes(250, 0, 16, 4 );
    private final Recipes Latte = new Recipes(350, 75, 20, 7);
    private final Recipes Cappuccino = new Recipes(200, 100, 12, 6);

    private final Recipes[] recipes = {Espresso, Latte, Cappuccino};

    private int getCupsOfCoffee() {
        return cupsOfCoffee;
    }

    private void setCupsOfCoffee(int cupsOfCoffee) {
        this.cupsOfCoffee += cupsOfCoffee;
    }

    private void restCupsOfCoffee() {
        this.cupsOfCoffee -= 1;
    }

    private int getWater() {
        return water;
    }

    private void setWater(int water) {
        this.water += water;
    }

    private void restWater(int water) {
        this.water -= water;
    }

    private int getMilk() {
        return milk;
    }

    private void setMilk(int milk) {
        this.milk += milk;
    }

    private void restMilk(int milk) {
        this.milk -= milk;
    }

    private int getCoffeeBeans() {
        return coffeeBeans;
    }

    private void setCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans  += coffeeBeans;
    }

    private void restCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans  -= coffeeBeans;
    }

    private int getMoney() {
        return money;
    }

    private void setMoney(int money) {
        this.money += money;
    }

    public String onOROff() {
        return state.toString();
    }
    public void machineAction(String input) {
        switch (state) {
            case MAIN ->
                machineState(input);
            case BUYING -> {
                buy(input);
                machineMenu();
            }
            case FILLING_WATER -> {
                System.out.println("Write how many ml of water you want to add:");
                setWater(Integer.parseInt(input));
                state = MachineState.FILLING_MILK;
            }
            case FILLING_MILK -> {
                System.out.println("Write how many ml of milk you want to add:");
                setMilk(Integer.parseInt(input));
                state = MachineState.FILLING_BEANS;
            }
            case FILLING_BEANS -> {
                System.out.println("Write how many grams of coffee beans you want to add:");
                setCoffeeBeans(Integer.parseInt(input));
                state = MachineState.FILLING_CUPS;
            }
            case FILLING_CUPS -> {
                System.out.println("Write how many disposable cups you want to add:");
                setCupsOfCoffee(Integer.parseInt(input));
                machineMenu();
            }
        }
    }

    private void machineState(String action) {
        switch (action) {
            case "remaining":
                remaining();
                machineMenu();
                break;
            case "fill":
                state = MachineState.FILLING_WATER;
                break;
            case "take":
                retrieveMoney();
                machineMenu();
                break;
            case "buy":
                System.out.println("What do you want to buy? 1- espresso, 2 - latte, 3 - cappuccino:\n" +
                        "Or write back to return to the Main menu.");
                state = MachineState.BUYING;
                break;
            case "exit":
                state = MachineState.OFF;
                break;
        }
    }

    private void remaining() {
        System.out.println("The coffee machine has: \n" +
                getWater() + " ml of water\n" +
                getMilk() + " ml of milk\n" +
                getCoffeeBeans() + " g of coffee beans\n" +
                getCupsOfCoffee() + " disposable cups\n" +
                "$" + getMoney() + " of money\n");
    }

    private void buy(String input){
        int coffee;
        if (input.equals("back")) {
            machineMenu();
            return;
        }else {
            coffee = Integer.parseInt(input);
        }
        if(check(coffee) != 0) {
            coffee -= 1;
            restWater(recipes[coffee].getWater());
            restMilk(recipes[coffee].getMilk());
            restCoffeeBeans(recipes[coffee].getCoffeeBeans());
            setMoney(recipes[coffee].getPrice());
            restCupsOfCoffee();
        }
    }

    private int check(int selection) {
        selection -= 1;
        boolean checkWater = water >= recipes[selection].getWater();
        boolean checkMilk = milk >= recipes[selection].getMilk();
        boolean checkCoffee = coffeeBeans >= recipes[selection].getCoffeeBeans();
        if (checkWater && checkMilk && checkCoffee && coffeeBeans > 0) {
            System.out.println("I have enough resources, making you a coffee!");
            return 1;
        } else if (!checkWater && checkMilk && checkCoffee && cupsOfCoffee > 0) {
            System.out.println("Sorry not enough water!");
        } else if (checkWater && !checkMilk && checkCoffee && cupsOfCoffee > 0) {
            System.out.println("Sorry not enough milk!");
        } else if (checkWater && checkMilk && !checkCoffee && cupsOfCoffee > 0) {
            System.out.println("Sorry not enough coffee beans!");
        } else if (checkWater && checkMilk && checkCoffee && cupsOfCoffee <= 0) {
            System.out.println("Sorry not enough disposable cups!");
        }
        return 0;
    }

    private void retrieveMoney(){
        System.out.printf("I gave you $%d\n",getMoney());
        money = 0;
    }

    private void machineMenu() {
        state = MachineState.MAIN;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }
}
