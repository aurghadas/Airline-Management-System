# ✈️ Airline Management System in Java

This is a full-featured **Airline Management System** built using Java that simulates the core functionalities of a real-world airline service. It is designed to handle operations from customer management to flight bookings—all through a user-friendly **Graphical User Interface (GUI)**.

---

## 🚀 Features

The program provides a wide range of essential functionalities that an airline company would typically offer:

- 🔐 **User Login System** – Secure access for users.
- 🏠 **Homepage Dashboard** – A central hub to access all functionalities.
- 👤 **Customer Management** – Add and manage customer information.
- 🛫 **Flight Details** – View and manage available flight information.
- 📋 **Available Flights** – Display all flights based on user input or filters.
- 🎟️ **Flight Booking** – Book flights for customers with validation.
- ❌ **Flight Cancellation** – Cancel booked flights with confirmation.
- 🎫 **Boarding Pass Generation** – Generate printable boarding passes after booking.
- 🧾 **Journey Details** – View, track, or manage customer itineraries and reservations.

Each of the functionalities is implemented using **modular Java classes**, ensuring clean architecture and scalability.

---

## 🧱 Tech Stack

- **Language:** Java  
- **GUI:** Java Swing  
- **Data Storage:** Text-based file system (no database required)  
- **OOP Principles:** Modular class-based design  

---

## 🎨 User Interface

The project includes a **Graphical User Interface (GUI)** built with Java Swing to enhance usability. The interface is clean, intuitive, and responsive—making it easy for users to navigate through different airline operations.

---

## How to run the code?


1. Navigate to the source directory:
   ```bash
   cd "Airline-Management-System/Source Packages/airlinemanagementsystem"
2. In order to compile all .java file, use this command: javac *.java
3. Now, to run the program, use this command: java Login

---

## 🗂️ Project Structure

```text
Airline-Management-System/         # Root project directory
│
├── .vscode/                       # VSCode editor configuration files
|   ├── settings.json
├── Source Packages/               # Main Java source folder containing packages, resources, and data
|   ├── airlinemanagementsystem/   # Primary package with Java source and class files, data files, and GUI assets
|       ├── Files/                 # Text files storing necessary data (customers, flights, reservations, etc.)
|           ├── cancellation.txt
|           ├── customerDetails.txt
|           ├── flightDetails.txt
|           ├── login.txt
|           ├── reservation.txt
|       ├── icons/                 # Image assets used in the GUI
|           ├── airindia.png
|           ├── bimanBangladesh.png
|           ├── cancel.jpg
|           ├── cancel.png
|           ├── details.jpg
|           ├── emp.png
|           ├── front.jpg
|           ├── front1.jpg
|       ├── Home.class
|       ├── Home.java
|       ├── Login.class
|       ├── Login.java
|       ├── addCustomer$Customer.class
|       ├── addCustomer.class
|       ├── addCustomer.java
|       ├── boardingPass.class
|       ├── boardingPass.java
|       ├── bookFlight$1.class
|       ├── bookFlight$Flight.class
|       ├── bookFlight$User.class
|       ├── bookFlight.class
|       ├── bookFlight.java
|       ├── cancelFlight$Reservations.class
|       ├── cancelFlight.class
|       ├── cancelFlight.java
|       ├── flightInfo$Flight.class
|       ├── flightInfo.class
|       ├── flightInfo.java
|       ├── journeyDetails$Reservation.class
|       ├── journeyDetails.class
|       ├── journeyDetails.java
├── lib/                       # External libraries (.jar files) and their configuration
|   ├── settings.json
|   ├── jcalendar-tz-1.3.3-4.jar
|   ├── mysql-connector-java-8.0.28.jar
|   ├── rs2xml.jar
└── README.md                  # Project documentation and overview

