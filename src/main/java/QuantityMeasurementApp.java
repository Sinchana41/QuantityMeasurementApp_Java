import com.bl.quantitymeasurement.controller.QuantityMeasurementController;
import com.bl.quantitymeasurement.dto.QuantityDTO;
import com.bl.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.bl.quantitymeasurement.repository.RepositoryFactory;
import com.bl.quantitymeasurement.service.IQuantityMeasurementService;
import com.bl.quantitymeasurement.impl.QuantityMeasurementServiceImpl;
import org.h2.tools.Server;

public class QuantityMeasurementApp {

    public IQuantityMeasurementRepository repository;
    public QuantityMeasurementController controller;

    private static QuantityMeasurementApp instance;

    private QuantityMeasurementApp() {

        this.repository = RepositoryFactory.getRepository();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(this.repository);
        this.controller = new QuantityMeasurementController(service);
    }

    public static synchronized QuantityMeasurementApp getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementApp();
        }
        return instance;
    }

    public static void main(String[] args) {
        QuantityMeasurementApp app = QuantityMeasurementApp.getInstance();

        // Demonstration of Operations via Controller API
        QuantityDTO feet = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO inches = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);

        // 1. Perform Comparison
        boolean areEqual = app.controller.performComparison(feet, inches);
        System.out.println("1.0 FEET equals 12.0 INCHES? " + areEqual);

        // 2. Perform Addition
        QuantityDTO additionResult = app.controller.performAddition(feet, inches);
        System.out.println("1.0 FEET + 12.0 INCHES = " + additionResult);

        try {
            // Starts the H2 Web Console server on port 8082
            Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("H2 Web Console started at: " + webServer.getURL());
        } catch (Exception e) {
            System.err.println("Failed to start H2 Console: " + e.getMessage());
        }
    }
}
