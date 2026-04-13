🌦️ Weather Monitoring System

A console-based Java application that records and analyzes weather data (temperature and humidity) using Object-Oriented Programming principles and a menu-driven interface.

📌 About the Project

This project was developed as part of the Object Oriented Programming with Java course at Rungta International Skills University, Bhilai.

It simulates a real-world weather monitoring system where multiple weather readings can be stored, analyzed, and displayed efficiently using clean OOP design and modular structure.

🏗️ Project Structure
WeatherApp.java
 ├── WeatherApp Class        → Menu-driven main driver program
 ├── ArrayList<WeatherData>  → Stores multiple weather records
 └── Helper Methods          → Input handling & display utilities

WeatherData.java
 ├── WeatherData Class       → Stores temperature & humidity
 ├── checkAlert()            → Evaluates weather conditions
 └── displayReport()         → Displays formatted output
🧠 OOP Concepts Used
Concept	Implementation
Encapsulation	Private fields with controlled access in WeatherData
Class Design	Separate classes for data and logic
Object Interaction	WeatherApp creates and uses WeatherData objects
Collections	ArrayList used for storing multiple records
Exception Handling	try-catch used for input validation
Modular Design	Helper methods improve readability and structure
✨ Features
➤ Add multiple weather records
➤ View all stored records
➤ View latest weather record
➤ Input validation (prevents crashes)
➤ Weather alert system
➤ Menu-driven console interface
⚙️ Working Logic
User inputs temperature and humidity
A WeatherData object is created
Object is stored in an ArrayList
Alert is generated using checkAlert()
Report is displayed using displayReport()
🖥️ Sample Output
==========================================
      WEATHER MONITORING SYSTEM          
==========================================

------------------------------------------
  MENU
------------------------------------------
  1.  Add New Weather Record
  2.  View All Records
  3.  View Latest Record
  4.  Exit
------------------------------------------

Enter your choice: 1

--- Add New Weather Reading ---
Enter Temperature (°C): 36.5
Enter Humidity (%): 70

[+] Record #1 saved successfully.
Generating report...

Temperature : 36.5 °C
Humidity    : 70 %
Alert       : Hot & Humid Conditions
▶️ How to Run
Step 1: Compile
javac WeatherData.java WeatherApp.java
Step 2: Run
java WeatherApp

📌 Note:

Both files must be in the same directory
Requires Java JDK 8 or above
👨‍💻 Author

Devashish Mudgal
B.Tech CSE (Core)
Rungta International Skills University, Bhilai
Student ID: RU-25-10454

Guide: Mr. Soumik Karmakar
Session: 2025–26
