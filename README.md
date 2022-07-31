# porject-4-Summer
This Program contains 7 classes including Account, Appointment, Calender, Customer, Seller, SharingEconomy, and Store. Those classes working together will implement a
system that can implement a marketplace calender. The calendar will allow for booking and managing appointments between sellers and customers. Since the system needs
to store the date even when the program shuts down, most class have their own class to keep their data.

James submitted Vocareum workspace
Saanika & Spencer submitted report on Brightspace

**Appointment
*Appointment Class
The appointment class can format inputs in to the an appintment object or a string object. So, the calender class will handle and write each appointment to its own .txt
file.
The appointment class's object contains following arguements:
String title, String calender, String store, int capacity, int booking, int startTime, int endTime
The same thing will appear in each appointment line in the calender file.


**Calender
*Calender Class
The calender class will read a calender file with a calender title, a descritionm, a time when last time the calender is modified, and lines of appointments, then the
calender class will create a calender object that contains all the attributes of the calender file. The calender is able to create, modify, or remove any appoiment in its file.
That allows customers to make or cancel the appintment by adding or subtracting the attendance number for the booking number of each appointment.

*Calender File format:

Title

Calender Description

Appointment 1 title,Appointment 1 calender,Appointment 1 store,Appointment 1 capacity,Appointment 1 booking,Appointment 1 startTime,Appointment 1 endTime

Appointment 2 title,Appointment 2 calender,Appointment 2 store,Appointment 2 capacity,Appointment 2 booking,Appointment 2 startTime,Appointment 2 endTime

.
.
.
.


**Store
*Store class
The store class will read store files listing calender files names. By reading those file names and put them into calender class, the store class can read all calender
files in its own file. The store class also manages all calenders under its file. The seller will be able to create, modify, or delete any calender files managed by it.

*Store file format:

calender 1 name

calender 2 name

calender 3 name

.
.
.

**Customer
*Customer class
The customer class will read its own file only containing their approved appointments and store it into the customer object. It can list all approved appointments on the terminal

*Customer file format:

Appointment 1 title,Appointment 1 calender,Appointment 1 store,Appointment 1 capacity,Appointment 1 booking,Appointment 1 startTime,Appointment 1 endTime

Appointment 2 title,Appointment 2 calender,Appointment 2 store,Appointment 2 capacity,Appointment 2 booking,Appointment 2 startTime,Appointment 2 endTime

.
.
.
.


** Seller
*Seller class
The seller classes read two files one is the store list and one contains all approved appointments. After the seller class is called, it will reads those two files
and store them in two arraylists.The seller class is in charge of approving the appintment requests by reading a calender file and picking an appointment to send to
a customer's approved appointment list and seller's owm appointment list. The seller can view the all the approved appointments of a store.Since each seller can open
multiple stores, the seller class also opens stores by creatinga new txt file for one store.

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


**Account
*Account class
Account class controls the system's log in, sign up, and account managements.When the accout class is called it will reads two files containing sellers' and customers'
account info including the username, password, and all file locations related to them. Signing up an account will let the account class write a line to their countering
file to record their username, passwordm and file locations. The only difference between the seller and customer account file is seller contains more column of approved
appointment file location

*Seller account file format:

username,password,file location,approved appointment file location

.
.
.


*Customer account fle format:

username,password,file location

.
.
.


*SharingEconomy is the main method
*************
To run this program, you need to make an account first by signing up an account. You can still open the "CustomerAccount" and "SellerAccount" files to find an existing account
to login some of them may not have their own store and their store may not have a calender. But you can use any of the account including your created one to test the functions
This program is only able to handle on operation at a time. that means if you made an appointment or logged in with wrong username and password, you ened to start over. But all
changed made to the data will be saved. And when you are asked to eneter a file path, you are expected to type in the correct path. you can find the path in the files
of "CustomerAccount" and "SellerAccount" to tell you all the information.

My suggestion is to find the "CustomerAccount" and "SellerAccount" and get all the account and filename info before you run the program
