package rentalcarprogram;
 
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 
/**
 * The RentalCarProgram class is a JavaFX application that allows customers to input their information
 * and choose a vehicle from a list of available options.
 */
public class RentalCarProgram extends Application {
 
    /**
     * The start method is the entry point of the JavaFX application.
     *
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Create a grid pane to hold the input fields and labels.
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
 
        // Create labels and text fields for customer information.
        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
 
        Label ageLabel = new Label("Age:");
        TextField ageTextField = new TextField();
 
        Label licenseLabel = new Label("License Number:");
        TextField licenseTextField = new TextField();
 
        // Add labels and text fields to the grid pane.
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(ageLabel, 0, 1);
        gridPane.add(ageTextField, 1, 1);
        gridPane.add(licenseLabel, 0, 2);
        gridPane.add(licenseTextField, 1, 2);
 
        // Create a choice box for vehicle selection.
        Label vehicleLabel = new Label("Choose a Vehicle:");
        ChoiceBox<String> vehicleChoiceBox = new ChoiceBox<>();
        vehicleChoiceBox.getItems().addAll("Sedan", "SUV", "Sports Car");
 
        // Add the vehicle label and choice box to the grid pane.
        gridPane.add(vehicleLabel, 0, 3);
        gridPane.add(vehicleChoiceBox, 1, 3);
 
        // Create a button to submit the customer information.
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            // Get the customer information from the text fields.
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            String licenseNumber = licenseTextField.getText();
 
            // Get the selected vehicle from the choice box.
            String selectedVehicle = vehicleChoiceBox.getValue();
 
            // Create a new customer object with the input information.
            Customer customer = new Customer(name, age, licenseNumber);
 
            // Create a new rental car object based on the selected vehicle.
            RentalCar rentalCar = null;
            if (selectedVehicle.equals("Sedan")) {
                rentalCar = new Sedan();
            } else if (selectedVehicle.equals("SUV")) {
                rentalCar = new SUV();
            } else if (selectedVehicle.equals("Sports Car")) {
                rentalCar = new SportsCar();
            }
 
            // Rent the selected vehicle to the customer.
            rentalCar.rentTo(customer);
 
            // Display the rental details.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rental Details");
            alert.setHeaderText("Rental Successful");
            alert.setContentText(rentalCar.getRentalDetails());
            alert.showAndWait();
        });
 
        // Add the submit button to the grid pane.
        gridPane.add(submitButton, 1, 4);
 
        // Create a scene with the grid pane and set it as the primary stage scene.
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rental Car Program");
        primaryStage.show();
    }
 
    /**
     * The main method launches the JavaFX application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
 
/**
 * The Customer class represents a customer with their name, age, and license number.
 */
class Customer {
    private String name;
    private int age;
    private String licenseNumber;
 
    /**
     * Constructs a new Customer object with the specified name, age, and license number.
     *
     * @param name           The name of the customer.
     * @param age            The age of the customer.
     * @param licenseNumber  The license number of the customer.
     */
    public Customer(String name, int age, String licenseNumber) {
        this.name = name;
        this.age = age;
        this.licenseNumber = licenseNumber;
    }
 
    /**
     * Gets the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }
 
    /**
     * Gets the age of the customer.
     *
     * @return The age of the customer.
     */
    public int getAge() {
        return age;
    }
 
    /**
     * Gets the license number of the customer.
     *
     * @return The license number of the customer.
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }
}
 
/**
 * The RentalCar interface represents a rental car.
 */
interface RentalCar {
    /**
     * Rents the rental car to the specified customer.
     *
     * @param customer The customer to whom the rental car is being rented.
     */
    void rentTo(Customer customer);
 
    /**
     * Gets the rental details of the rental car.
     *
     * @return The rental details of the rental car.
     */
    String getRentalDetails();
}
 
/**
 * The Sedan class represents a sedan rental car.
 */
class Sedan implements RentalCar {
    private Customer customer;
 
    /**
     * Rents the sedan to the specified customer.
     *
     * @param customer The customer to whom the sedan is being rented.
     */
    @Override
    public void rentTo(Customer customer) {
        this.customer = customer;
    }
 
    /**
     * Gets the rental details of the sedan.
     *
     * @return The rental details of the sedan.
     */
    @Override
    public String getRentalDetails() {
        return "Vehicle: Sedan\n" +
                "Customer: " + customer.getName() + "\n" +
                "Age: " + customer.getAge() + "\n" +
                "License Number: " + customer.getLicenseNumber();
    }
}
 
/**
 * The SUV class represents an SUV rental car.
 */
class SUV implements RentalCar {
    private Customer customer;
 
    /**
     * Rents the SUV to the specified customer.
     *
     * @param customer The customer to whom the SUV is being rented.
     */
    @Override
    public void rentTo(Customer customer) {
        this.customer = customer;
    }
 
    /**
     * Gets the rental details of the SUV.
     *
     * @return The rental details of the SUV.
     */
    @Override
    public String getRentalDetails() {
        return "Vehicle: SUV\n" +
                "Customer: " + customer.getName() + "\n" +
                "Age: " + customer.getAge() + "\n" +
                "License Number: " + customer.getLicenseNumber();
    }
}
 
/**
 * The SportsCar class represents a sports car rental car.
 */
class SportsCar implements RentalCar {
    private Customer customer;
 
    /**
     * Rents the sports car to the specified customer.
     *
     * @param customer The customer to whom the sports car is being rented.
     */
    @Override
    public void rentTo(Customer customer) {
        this.customer = customer;
    }
 
    /**
     * Gets the rental details of the sports car.
     *
     * @return The rental details of the sports car.
     */
    @Override
    public String getRentalDetails() {
        return "Vehicle: Sports Car\n" +
                "Customer: " + customer.getName() + "\n" +
                "Age: " + customer.getAge() + "\n" +
                "License Number: " + customer.getLicenseNumber();
    }
}
