// =============================================================================
//  FILE        : WeatherApp.java
//  PROJECT     : Weather Monitoring System
//  SUBJECT     : Object Oriented Programming With Java (RU-100-01-00012)
//  UNIVERSITY  : Rungta International Skills University, Bhilai, CG
//  STUDENT     : Devashish Mudgal  |  RU-25-10454
//  GUIDE       : Mr. Soumik Karmakar
//  SESSION     : 2025-26  (April 2026)
// -----------------------------------------------------------------------------
//  DESCRIPTION :
//      This is the MAIN DRIVER CLASS for the Weather Monitoring System.
//      It provides a menu-driven console interface that lets the user:
//        1. Add a new weather reading (temperature + humidity)
//        2. View reports for ALL stored weather records
//        3. View the report for only the LATEST stored record
//        4. Exit the application cleanly
//
//      All weather readings entered during the session are stored in an
//      ArrayList<WeatherData>, demonstrating Java Collection usage.
//
//  BONUS FEATURES IMPLEMENTED (as documented in project report):
//        [BONUS 1]  Multiple weather records stored in ArrayList
//        [BONUS 2]  Menu-driven interface with while loop + switch
//        [BONUS 3]  Input validation via try-catch (no crash on bad input)
//        [BONUS 4]  Combined alert logic in WeatherData.checkAlert()
//
//  HOW TO COMPILE AND RUN:
//        Step 1:  javac WeatherData.java WeatherApp.java
//        Step 2:  java WeatherApp
//
//  NOTE: Both WeatherData.java and WeatherApp.java must be in the same folder.
// =============================================================================

import java.util.ArrayList;  // needed for ArrayList — a resizable list collection
import java.util.List;       // the List interface that ArrayList implements
import java.util.Scanner;    // needed to read keyboard input from the console

public class WeatherApp {

    // =========================================================================
    // SECTION 1 : CLASS-LEVEL (STATIC) FIELDS
    // =========================================================================
    // 'static' means these fields belong to the CLASS itself, not to any
    // individual object. This is appropriate here because WeatherApp is never
    // instantiated as an object — it only has a static main() method.

    /**
     * records — stores ALL WeatherData objects added during this session.
     *
     * ArrayList<WeatherData> is a dynamic array that automatically grows when
     * new items are added. It is declared as List<WeatherData> (the interface)
     * on the left side — this follows good OOP practice of "coding to an
     * interface" rather than to a concrete implementation.
     *
     * [BONUS 1] : Multiple weather records stored across the session.
     */
    private static final List<WeatherData> records = new ArrayList<>();

    /**
     * scanner — a single shared Scanner object for ALL console input.
     *
     * Declared 'static final' so that the same Scanner is reused throughout
     * the program instead of creating a new one in every method.
     * It is closed in main() when the program exits.
     */
    private static final Scanner scanner = new Scanner(System.in);


    // =========================================================================
    // SECTION 2 : MAIN METHOD — Program Entry Point
    // =========================================================================
    /**
     * main(String[] args)
     *
     * This is the entry point of the entire application — the JVM calls this
     * method first when "java WeatherApp" is executed.
     *
     * FLOW:
     *   1. Print the application banner once at startup.
     *   2. Enter a while loop that keeps running until the user chooses Exit.
     *   3. Inside each loop iteration:
     *        a. Display the menu options.
     *        b. Read the user's integer choice (with input validation).
     *        c. Route to the correct method using a switch expression.
     *   4. When the loop ends, close the Scanner to release the system resource.
     *
     * [BONUS 2] : Menu-driven interface implemented here.
     *
     * @param args  Command-line arguments (not used in this project).
     */
    public static void main(String[] args) {

        printBanner(); // display the title header once at start

        // 'running' controls the main menu loop.
        // It stays true until the user selects option 4 (Exit).
        boolean running = true;

        while (running) {

            printMenu(); // show the four menu options

            // readInt() handles all invalid input — it will keep asking
            // until the user types a valid whole number.
            int choice = readInt("Enter your choice: ");

            // Switch expression (Java 14+) — routes to the correct method.
            // Each case uses the arrow (->) syntax: no 'break' needed.
            switch (choice) {
                case 1 -> addWeatherRecord();   // add a new reading
                case 2 -> viewAllRecords();     // show all stored records
                case 3 -> viewLatestRecord();   // show only the latest record
                case 4 -> {
                    // User chose to exit — set flag to false, exit the loop
                    running = false;
                    System.out.println("\nThank you for using the Weather Monitoring System.");
                    System.out.println("Goodbye!\n");
                }
                default -> {
                    // Any number other than 1-4 falls here
                    System.out.println("\n  [!] Invalid choice. Please enter a number between 1 and 4.");
                }
            }
        }

        // Close the Scanner when the program is about to end.
        // Not closing it causes a resource-leak warning.
        scanner.close();

    } // end main()


    // =========================================================================
    // SECTION 3 : MENU OPTION 1  —  addWeatherRecord()
    // =========================================================================
    /**
     * addWeatherRecord()
     *
     * Handles Menu Option 1: accepts temperature and humidity from the user,
     * creates a new WeatherData object, stores it in the records ArrayList,
     * and immediately displays the weather report for that reading.
     *
     * STEPS:
     *   1. Prompt user for temperature using readDouble() (input-safe).
     *   2. Prompt user for humidity using readDouble() (input-safe).
     *   3. Create a new WeatherData object using the two-parameter constructor.
     *   4. Add the object to the 'records' ArrayList with records.add().
     *   5. Call displayReport() on the new object to show the alert.
     *
     * OOP Note: 'new WeatherData(temp, hum)' calls the constructor defined in
     *           WeatherData.java and returns a fully initialised object.
     *
     * [BONUS 1] : The object is added to ArrayList for multi-record support.
     */
    private static void addWeatherRecord() {

        System.out.println("\n--- Add New Weather Reading ---");

        // readDouble() asks repeatedly until the user types a valid decimal number.
        // \u00b0 is the Unicode escape sequence for the degree symbol °.
        double temp = readDouble("Enter Temperature (\u00b0C): ");
        double hum  = readDouble("Enter Humidity    (%):  ");

        // Create a new WeatherData object — this calls the constructor in WeatherData.java
        WeatherData data = new WeatherData(temp, hum);

        // Add the new object to the end of the ArrayList
        records.add(data);

        System.out.println("\n  [+] Record #" + records.size() + " saved successfully.");
        System.out.println("  Generating report...");

        // Call displayReport() on the newly created object — this also internally
        // calls checkAlert() and prints the temperature, humidity, and alert.
        data.displayReport();

    } // end addWeatherRecord()


    // =========================================================================
    // SECTION 4 : MENU OPTION 2  —  viewAllRecords()
    // =========================================================================
    /**
     * viewAllRecords()
     *
     * Handles Menu Option 2: iterates through the entire 'records' ArrayList
     * and prints a numbered weather report for every stored reading.
     *
     * STEPS:
     *   1. Check if the ArrayList is empty — if yes, print a message and return.
     *   2. Use a for loop with an index counter to print each record numbered.
     *   3. Call displayReport() on each WeatherData object in the list.
     *
     * ArrayList methods used:
     *   records.isEmpty()  -->  returns true if the list has no elements
     *   records.size()     -->  returns the total number of stored records
     *   records.get(i)     -->  retrieves the object at index i (0-based)
     *
     * [BONUS 1] : Demonstrates retrieval of multiple stored WeatherData objects.
     */
    private static void viewAllRecords() {

        // Guard clause — exit early if no records have been added yet
        if (records.isEmpty()) {
            System.out.println("\n  [!] No records found.");
            System.out.println("      Please add at least one weather reading first.\n");
            return; // exits this method, returns to the menu loop in main()
        }

        System.out.println("\n--- All Weather Records (" + records.size() + " total) ---");

        // Standard for loop — iterates from index 0 to records.size()-1
        for (int i = 0; i < records.size(); i++) {

            // Print a record label starting from 1 (i+1) for user readability
            System.out.printf("%nRecord #%d%n", i + 1);

            // Retrieve the WeatherData object at index i and call its display method
            records.get(i).displayReport();
        }

    } // end viewAllRecords()


    // =========================================================================
    // SECTION 5 : MENU OPTION 3  —  viewLatestRecord()
    // =========================================================================
    /**
     * viewLatestRecord()
     *
     * Handles Menu Option 3: retrieves and displays only the most recently
     * added weather record (the last element in the ArrayList).
     *
     * STEPS:
     *   1. Check if the ArrayList is empty — if yes, print a message and return.
     *   2. Retrieve the LAST element using index (records.size() - 1).
     *   3. Call displayReport() on it.
     *
     * Why (records.size() - 1)?
     *   ArrayList indices are zero-based. If there are 3 records, they are at
     *   indices 0, 1, 2. The last index is always size()-1 = 3-1 = 2.
     */
    private static void viewLatestRecord() {

        // Guard clause — exit early if no records exist yet
        if (records.isEmpty()) {
            System.out.println("\n  [!] No records found.");
            System.out.println("      Please add at least one weather reading first.\n");
            return;
        }

        System.out.println("\n--- Latest Weather Record (Record #" + records.size() + ") ---");

        // records.size()-1 gives the index of the last element in the list
        records.get(records.size() - 1).displayReport();

    } // end viewLatestRecord()


    // =========================================================================
    // SECTION 6 : HELPER — printBanner()
    // =========================================================================
    /**
     * printBanner()
     *
     * Prints the application title banner to the console once at startup.
     * Kept in its own method to keep main() clean and readable.
     */
    private static void printBanner() {
        System.out.println("\n==========================================");
        System.out.println("      WEATHER MONITORING SYSTEM          ");
        System.out.println("   Object Oriented Programming With Java  ");
        System.out.println("   Rungta International Skills University ");
        System.out.println("==========================================\n");
    } // end printBanner()


    // =========================================================================
    // SECTION 7 : HELPER — printMenu()
    // =========================================================================
    /**
     * printMenu()
     *
     * Prints the four menu options to the console before each user input.
     * Called at the start of every iteration of the while loop in main().
     */
    private static void printMenu() {
        System.out.println("------------------------------------------");
        System.out.println("  MENU");
        System.out.println("------------------------------------------");
        System.out.println("  1.  Add New Weather Record");
        System.out.println("  2.  View All Records");
        System.out.println("  3.  View Latest Record");
        System.out.println("  4.  Exit");
        System.out.println("------------------------------------------");
    } // end printMenu()


    // =========================================================================
    // SECTION 8 : HELPER — readInt()   [BONUS 3 — Input Validation]
    // =========================================================================
    /**
     * readInt(String prompt)
     *
     * Safely reads an integer from the console with input validation.
     * Keeps asking until the user enters a valid whole number.
     *
     * HOW IT WORKS:
     *   - scanner.nextLine() reads the entire line the user typed as a String.
     *   - .trim() removes any leading/trailing whitespace or accidental spaces.
     *   - Integer.parseInt() converts the String to an int.
     *   - If the String is not a valid integer (e.g. "abc", "3.5", ""),
     *     Integer.parseInt() throws a NumberFormatException.
     *   - The catch block catches that exception, prints a friendly error
     *     message, and the while(true) loop repeats the prompt.
     *   - When a valid integer IS entered, 'return' exits the loop and method.
     *
     * [BONUS 3] : Prevents the application from crashing on bad input.
     *
     * @param prompt  The message displayed to the user before their input.
     * @return        The valid integer entered by the user.
     */
    private static int readInt(String prompt) {

        while (true) { // keep looping until a valid integer is entered
            System.out.print(prompt); // print without newline so cursor stays on same line
            try {
                // Read the line, trim whitespace, and parse to int
                return Integer.parseInt(scanner.nextLine().trim());
                // 'return' exits the while loop and the method simultaneously
            } catch (NumberFormatException e) {
                // Caught when input is not a valid integer — inform user and retry
                System.out.println("  [!] Invalid input. Please enter a whole number (e.g. 1, 2, 3).");
            }
        }

    } // end readInt()


    // =========================================================================
    // SECTION 9 : HELPER — readDouble()   [BONUS 3 — Input Validation]
    // =========================================================================
    /**
     * readDouble(String prompt)
     *
     * Safely reads a decimal number (double) from the console with validation.
     * Keeps asking until the user enters a valid number.
     *
     * HOW IT WORKS:
     *   - Same pattern as readInt() above, but uses Double.parseDouble().
     *   - Double.parseDouble() accepts integers AND decimals (e.g. "36", "36.5").
     *   - If the input is not numeric (e.g. "abc", "hot", ""),
     *     Double.parseDouble() throws a NumberFormatException.
     *   - The catch block handles it gracefully with a helpful message.
     *
     * [BONUS 3] : Ensures only valid numeric weather values are accepted.
     *
     * @param prompt  The message displayed to the user before their input.
     * @return        The valid double value entered by the user.
     */
    private static double readDouble(String prompt) {

        while (true) { // keep looping until a valid double is entered
            System.out.print(prompt);
            try {
                // Read the line, trim whitespace, and parse to double
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                // Caught when input cannot be converted to a decimal number
                System.out.println("  [!] Invalid input. Please enter a valid number (e.g. 36.5, 42, -5).");
            }
        }

    } // end readDouble()

} // end of class WeatherApp