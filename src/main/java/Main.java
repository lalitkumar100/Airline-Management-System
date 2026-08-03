import com.crimsonlogic.arilinemanangmentsystem.utility.Menu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
abstract public class Main {

    public static void main(String[] args) {

        com.crimsonlogic.arilinemanangmentsystem.utility.IdGenerator.initializeCounters();
        Menu menu = new Menu();
        menu.start();
    }
}