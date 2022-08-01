This is the test cases for the calendar program

In the repository there are some existing customer and seller files you can use to test functions of this program without setting up everything, including create account and creating calendars.

The detailed account information is listed below. You can still go to the CustomerAccount.txt and SellerAccount.txt to look at.

## Seller:
- Username: cv
- Password:1223
- File Location(this file contains names of all stores opened by the seller): cv
- Approved appointments file location(this file contains customer appointments approved by the seller): cv1
- store: apple:
- calendar: day1,day2,day3

## Customer:
- Username: szm
- Password: 1223
- File Location(this file contains customer's appointments approved by all sellers): szm

## Test case 1 customer views appointments & makes appointments (Two stages):
Note: all inputs are surrounded by [], you do not need to type [] while you are using the program

Stage 1 look up appointments on a store's calendar:
- run the Server.java
- run the Client.java
- click [login] and click [OK]
- click [customer] and click [OK]
- type in [szm] as username and click [Ok]
- type in [1223] as password and click [Ok]
- click [view calendar] option and click [Ok]
- type in store name as [apple] and click [OK]
- click [Ok] button on Store list page
- type in [day1] and click [OK]
- The end of the program

This is the end of the stage 1 you have already seen all the appointment of day1 calendar after type in [day1] and click [OK]

Stage 2 make an appointment:
- run Client.java
- click [login] and click [OK]
- click [customer] and click [OK]
- type in [szm] as username and click [Ok]
- type in [1223] as password and click [Ok]
- click [Make/cancel appointments]
- click [Make an appointment]
- type in [day1] as the calendar name and click [OK]
- type in [shoot] as the appointment tile and click [OK]
- click [OK] button on the Make an appointment page
- click [OK] button on the timestamp page
- The end of the program

This is the end of the test case 1, and you have made the appointment
## Test case 2 seller approves appointments & views store's approved appointments (Two stages):
Note: all inputs are surrounded by [], you do not need to type [] while you are using the program

Stage 1 approve the appointment:
- run the Server.java
- run the Client.java
- click [login] and click [OK]
- click [seller] and click [OK]
- type in [cv] as username and click [Ok]
- type in [1223] as password and click [Ok]
- click [approve appointments] option and click [OK]
- type in [day1] as the calendar name and click [OK]
- type in [shoot] as the appointment title and click [OK]
- type in [szm] as the customer filename and click [OK]
- click [OK] button on the Approve appointments page
- The end of the program

This is the end of the stage 1 you have approved the appointment made by the customer in the previous test case.

Stage 2 view store's approved appointments:
- run the Server.java
- run the Client.java
- click [login] and click [OK]
- click [seller] and click [OK]
- type in [cv] as username and click [Ok]
- type in [1223] as password and click [Ok]
- click [view store's approved appointments] option and click [OK]
- type in [apple] as the store name and click [OK]
- click [OK] on view store's approved appointments page
- The end of the program

This is the end of the test case 2, and you have approved the appointment by the customer and confirmed the approval

## Test case 3 customer views its own approved appointments
- run the Server.java
- run the Client.java
- click [login] and click [OK]
- click [customer] and click [OK]
- type in [szm] as username and click [Ok]
- type in [1223] as password and click [Ok]
- click [view approved appointments] and click [OK]
- click [OK] on the view approved appointments page
- The end of the program

## Test case 4 login with non-existing username and password:
- run the Server.java
- run the Client.java
- click [login] and click [OK]
- click [customer] or [seller] and click [OK]
- type in [anything] as username and click [Ok]
- type in [anything] as password and click [Ok]
- click [OK] on incorrect credentials page
- The end of the program

## Test case 5 sign up:
- run the Server.java
- run the Client.java
- click [Signup] and click [OK]
- type in username you want
- type in the password you will remember
- The end of the program

For this test case, there is no specified username and password. since you sign up with the username for the fist time, you cannot sign up with the same username anymore. Each user will not have identical username