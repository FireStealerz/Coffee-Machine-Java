package machine;
import java.util.Objects;
import java.util.Scanner;


public class CoffeeMachine {
    public static void main(String[] args) {
        Machine machine = new Machine();
        Scanner scanner = new Scanner(System.in);
        while(!Objects.equals(machine.onOROff(), "OFF")) {
            machine.machineAction(scanner.next());
        }
    }
}


