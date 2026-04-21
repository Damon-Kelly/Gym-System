# Gym Management System

A Java app for handling gym members, memberships, trainers, classes, and bookings.

## Overview

- Java Swing GUI for member and trainer stuff
- SQLite database (`gym.db`)
- JDBC with prepared statements
- Member sign-up, login, booking classes, and managing memberships
- Trainer class management and schedule views

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- SQLite

## Required files

- `gym.db` — The SQLite database
- `sqlite/sqlite-jdbc-3.51.1.0.jar` — JDBC driver
- `GymSystem.jar` — The compiled app JAR

## Project files

- `code/` — All the Java source files
- `gym.db` — The database
- `sqlite/` — JDBC driver
- `.vscode/` — VS Code settings for running and building
- `UML-Diagrams/` — ER diagram and database design files

## Notes

- Errors pop up in dialog boxes in the GUI.
- Input checking is done in `Validator.java`.
- The main class to run is `GymGui`.

## Database Overview

The database holds gym users, schedules, and bookings in SQLite.

### Tables

- `Member`
  - Keeps member account info like `MemberID`, `Name`, `Email`, `PhoneNumber`, `dateOfBirth`, `Password`, and `DeletedFlag`.
  - I use `DeletedFlag` for soft-deleting records.

- `Trainer`
  - Stores trainer accounts with `TrainerID`, `Name`, `Email`, `Password`, and `DeletedFlag`.

- `Class`
  - Holds gym class details like `ClassID`, `Title`, `Schedule`, `Capacity`, `TrainerID`, and `DeletedFlag`.
  - `TrainerID` connects the class to a trainer.

- `Membership`
  - Tracks subscriptions for members, with `MembershipID`, `PlanType`, `StartDate`, `EndDate`, `IsActive`, and `MemberID`.

- `Booking`
  - Records member class bookings, including `BookingID`, `BookingDate`, `Status`, `MemberID`, `ClassID`, and `DeletedFlag`.

## ER Diagram

You can open the ER diagram file here:

[UML-Diagrams/er_diagram.png](/UML-Diagrams/er_diagram.png)

### How it works

- Members log in and can book classes that have spots left.
- Trainers log in and handle their classes.
- I used `DeletedFlag` for soft deletes to keep old booking data even if someone cancels, which helps with records and history.
- The GUI pulls data from the database via `QueryHelpers` and saves changes with classes like `InsertBooking`, `InsertMember`, and `UpdateClass`.

