Weather Monitoring System

A console-based Java application that records and analyzes weather data (temperature and humidity) using Object-Oriented Programming principles and a menu-driven interface.

About the Project

This project was built as part of the Object Oriented Programming with Java course at Rungta International Skills University, Bhilai.

It simulates a real-world weather monitoring system where multiple weather readings can be stored, analyzed, and displayed. The program is designed using clean OOP concepts with proper separation of data handling and application logic.

Project Structure
WeatherApp.java
 ├── class WeatherApp        → Main driver class with menu system
 ├── ArrayList<WeatherData>  → Stores multiple weather records
 └── Helper Methods          → Input handling & display functions

WeatherData.java
 ├── class WeatherData       → Stores temperature & humidity
 ├── checkAlert()            → Determines weather condition
 └── displayReport()         → Displays formatted weather report
OOP Concepts Used
Concept	How it's applied
Encapsulation	Private data in WeatherData with controlled access
Class Design	Separate classes for data and logic
Object Interaction	WeatherApp creates and uses WeatherData objects
Collections	ArrayList used to store multiple records
Exception Handling	try-catch for safe user input
Modular Design	Helper methods for clean structure
Features
Add multiple weather records
View all stored records
View latest weather record
Input validation (no crash on wrong input)
Alert system based on temperature & humidity
Menu-driven console interface
Working Logic
User enters temperature and humidity
A WeatherData object is created
Object is stored in an ArrayList
Alert is generated using checkAlert()
Reports are displayed using displayReport()
Sample Output
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
How to Run

Step 1 — Compile

javac WeatherData.java WeatherApp.java

Step 2 — Run

java WeatherApp

Note: Both files must be in the same folder. Java JDK 8 or above is required.

Author

Devashish Mudgal
B.Tech CSE (Core) — Rungta International Skills University
Student ID: RU-25-10454
Guide: Mr. Soumik Karmakar
Session: 2025–26
