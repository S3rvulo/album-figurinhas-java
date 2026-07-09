# 🏆 Digital Sticker Album in Java

This is a **Java** command-line (**Terminal**) application developed to manage and track a digital sticker album (such as a World Cup or football championship album). The system allows users to manage their collection, automating the tracking of duplicates, missing items, and cross-referencing data for trading.

## 🚀 Key Features

*   **💾 Dynamic Loading (.txt):** Reads the album structure from a matrix-formatted text file (dynamically defining the number of teams and stickers per team).
*   **➕ Register Stickers:** Updates the ownership status of any sticker in real time.
*   **📊 Progress Statistics:** Displays the album completion percentage and overall collection metrics.
*   **🔍 Smart Filters:** Automatically separates which stickers are **missing** and which are **duplicates**.
*   **🔄 Album Comparator:** Imports a second user's file to cross-reference data and pinpoint trading opportunities.
*   **💾 Auto-Save:** Writes updated progress back to the text file to prevent data loss.

## 📋 Data File Template (`album.txt`)

The system interprets the configuration file using the following layout:
```text
4 5                   <-- [Number of Teams] [Stickers per Team]
Brasil                <-- Team 1 Name
Argentina             <-- Team 2 Name
França                <-- Team 3 Name
Alemanha              <-- Team 4 Name
1 1 3 1 0             <-- Sticker count for Team 1 (0 = missing, 1 = owned, 2+ = duplicate)
0 1 1 0 2             <-- Sticker count for Team 2
1 1 0 0 0             <-- Sticker count for Team 3
2 0 1 2 1             <-- Sticker count for Team 4
```

## 🛠️ Prerequisites and Technologies

*   **Language:** Java 21 (Developed using Microsoft OpenJDK 21.0.11)
*   **Recommended IDE:** IntelliJ IDEA (Language Level: SDK Default)
*   **Interface:** Command Line Interface (Terminal / Console)

## 📦 How to Run the Project

To run this project directly from your operating system's terminal, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com
   ```
2. **Navigate to the project folder:**
   ```bash
   cd YOUR_REPOSITORY_NAME
   ```
3. **Compile the Java files:**
   ```bash
   javac *.java
   ```
4. **Run the program:**
   ```bash
   java Main
   ```
   *(Replace `Main` with the name of your class containing the `public static void main` method, if different).*
