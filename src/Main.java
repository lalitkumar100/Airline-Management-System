import com.crimsonlogic.arilinemanangmentsystem.menu.Menu;
import com.crimsonlogic.arilinemanangmentsystem.service.AirportandAirCraftService;
import com.crimsonlogic.arilinemanangmentsystem.service.FlightService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        AirportandAirCraftService aas = new AirportandAirCraftService();
        aas.initializeData();
        aas.displayAllAircraft();
        aas.displayAllAirports();
        FlightService flightService = new FlightService();
        flightService.initializeData(aas);
        flightService.displayAllFlights();
        Menu m = new Menu();
        m.start(aas,flightService);
    }
}