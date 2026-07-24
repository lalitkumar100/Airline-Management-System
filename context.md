# Utility Package

Package Name:
com.projectname.utility

## InputUtil

Purpose:
A common utility class used throughout the application for accepting user input from the console using a single Scanner instance.

Responsibilities:
- Display prompts to users.
- Read and return different data types.
- Avoid creating multiple Scanner objects.
- Centralize all console input operations.
- Improve code reusability and maintainability.

Supported Data Types:
1. int
2. double
3. String
4. LocalDate
5. LocalDateTime
6. boolean

Methods:

### getInt(String message)
Displays a message and returns an integer value entered by the user.

Example:
int age = InputUtil.getInt("Enter Age : ");

### getDouble(String message)
Displays a message and returns a double value entered by the user.

Example:
double salary = InputUtil.getDouble("Enter Salary : ");

### getString(String message)
Displays a message and returns a String value entered by the user.

Example:
String name = InputUtil.getString("Enter Name : ");

### getDate(String message)
Accepts a LocalDate in yyyy-MM-dd format.

Example:
LocalDate dob = InputUtil.getDate("Enter Date Of Birth");

Input:
2004-03-24

### getDateTime(String message)
Accepts a LocalDateTime in yyyy-MM-ddTHH:mm:ss format.

Example:
LocalDateTime createdDate =
InputUtil.getDateTime("Enter Created Date");

Input:
2026-07-24T10:30:00

### getBoolean(String message)
Accepts true or false.

Example:
boolean active =
InputUtil.getBoolean("Is Active");

Input:
true

Design Decisions:
- Uses a single static Scanner instance.
- All methods are static.
- No object creation required.
- Can be called from anywhere in the application.
- Helps maintain consistent input handling.

Current Version:
V1.0

Future Enhancements:
- Input validation.
- Exception handling.
- Retry mechanism for invalid values.
- Custom DateTimeFormatter support.
- Support for Long, Float, BigDecimal.
- Enum input support.