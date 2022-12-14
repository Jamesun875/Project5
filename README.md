# Project 5 Calendar Program
This Program contains 9 classes: Account.java, Appointment.java, Calender.java, Client.java, Client1.java, Customer.java, Seller.java, Server.java, and Store.java. Those classes working together will implement a system that can implement a marketplace calender. The calendar will allow for booking and managing appointments between sellers and customers. Since the system needs to store the date even when the program shuts down, most class have their own class to keep their data.

James submitted Vocareum workspace.

Spencer submitted the Report on Brightspace.

Saanika submitted the Presentation on Brightspace.


## Appointment.java
This class will create appointments and format each of them with the title, calendar name, store, max capacity, number of attendance, starting time, and ending time.
- Appointment Class
- Format inputs into the appointment object or a string object
- Handles and writes each appointment to its own calendar.txt file after the Calender.java receives an appointment object
- Contains String title, String calendar, String store, int capacity, int booking, int startTime, int endTime
- The equivalent will appear in each appointment line in the Calender.java file.


## Calender.java
- Calendar Class
- Reads a calendar file with a calendar title, a description, when the calendar was last modified, and the lines of appointments.
- Creates a calendar object that contains all the attributes of the calendar file.
- The calendar is able to create, modify, or remove any appointment in its file.
- Allows customers to make or cancel the appointment by adding or subtracting the attendance number for the booking number of each appointment.
- A calendar title and description will be listed at the top of the page.
- Appointment windows will be listed below the title.
- All created text content will display a timestamp tracking the most recent modification

*Calender File format:

Title

last time modification: ddMMyyyyHHmm

Calender Description

Appointment 1 title,Appointment 1 calender,Appointment 1 store,Appointment 1 capacity,Appointment 1 booking,Appointment 1 startTime,Appointment 1 endTime

Appointment 2 title,Appointment 2 calender,Appointment 2 store,Appointment 2 capacity,Appointment 2 booking,Appointment 2 startTime,Appointment 2 endTime

.
.
.
.


## Store.java
- Reads store files listing calendar files names, then stores them into Calender.java
- Reads all calendar files in its own file
- Any number of appointment calendars may be added to a store.
- Manages all calenders under its file
- Allows seller to create, modify, or delete any calendar files managed by it.

*Store file format:

calender 1 name

calender 2 name

calender 3 name

.
.
.

## Customer.java
- Read its own file containing approved appointments
- Customers can view any of the created calendars for a store.
- Customers can make or cancel appointment requests.
- Customers can view a list of their currently approved appointments.
- Store approved appointments by adding appointments into the Customer object and its file
- Lists all approved appointments

*Customer file format:

Appointment 1 title,Appointment 1 calender,Appointment 1 store,Appointment 1 capacity,Appointment 1 booking,Appointment 1 startTime,Appointment 1 endTime

Appointment 2 title,Appointment 2 calender,Appointment 2 store,Appointment 2 capacity,Appointment 2 booking,Appointment 2 startTime,Appointment 2 endTime

.
.
.
.


## Seller.java
- Reads two files; the store list and one contains all approved appointments
- Sellers can create, edit, and delete calendars with individual appointment windows.
- Sellers can either create am empty calendar or import a calendar from a csv file.
- Sellers can approve or decline customer appointment requests by adding or not adding them to the seller and customer's approved appointment list.
- Sellers can view a list of currently approved appointments by store.
- After the seller class is called, it will read those two files and stores them in two arraylists.
- Allows for approving appointment requests by reading a calendar file and picking an appointment to send to a customer's approved appointment list and seller's own appointment list.
- The seller can view all the approved appointments of a store.
- Since each seller can open multiple stores, the seller class also opens stores by creating a new txt file for one store.

*Seller file format:

store 1 name

store 2 name

store 3 name

.
.
.

*Seller's approved appointments list format:

Appointment 1 title,Appointment 1 calender,Appointment 1 store,Appointment 1 capacity,Appointment 1 booking,Appointment 1 startTime,Appointment 1 endTime

Appointment 2 title,Appointment 2 calender,Appointment 2 store,Appointment 2 capacity,Appointment 2 booking,Appointment 2 startTime,Appointment 2 endTime

.
.
.
.


## Account.java
- Controls the system's log in, sign up, and account managements.
- Users can create, edit, and delete accounts for themselves.
- When the account class is called, it will read two files containing the account info of both sellers and customers.
- Account info includes the username, password, and all file locations related to them.
- Signing up for an account will let the account class write a line to their countering file to record their username, password and file locations.
- The difference between the seller and customer account file is that the seller account file contains more columns of approved appointment file locations while the custotmer account file doesn't.

*Seller account file format:

username,password,file location,approved appointment file location

.
.
.


*Customer account file format:

username,password,file location

.
.
.


## Server.java
- This will be the class equivalent to the main class as in project 4.
- It accesses all classes and use them with the info received from the Client.java
- It receives user info from Client.java.
- It sends result for each action of the user to the Client.java.




## Client.java
- Allows customer and seller to interact with the system using GUI
- It will send info typed by user to the server.
- It will receive the result from the server and pop up different GUI depending on the result
- In order to run this program, you need to make an account first by signing up an account.
- You can still open the "CustomerAccount" and "SellerAccount" files to find an existing account to login some of them may not have their own store and their store may not have a calender.
- You can use any of the accounts, including your created account to test the functions of the program.
- This program is only able to handle one operation at a time, which means that if you made an appointment or logged in with wrong username and password, you will need to start over.
- All changes made to the data will be saved.
- When the user is prompted to enter a file path, the user is expected to type in the correct path, which can be found in the files
  in the "CustomerAccount" and "SellerAccount" files.
- RECOMMENDED: Find the "CustomerAccount" and "SellerAccount" and get all the account and filename info before you run the program.


## Client1.java
- Does the same thing as Client.java. This class will work with Client.java to simulate concurrent state on the server.
