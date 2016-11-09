# billingsystem
Q. How to build project?
A. Prerequisite- maven should be installed
   From command line execute: mvn clean install

Q. How to explore project?
A. You can deploy war file generated from above command, on any application server. I deployed it on tomcat
   After deployment hit url:
   http://localhost:8080/billing-system/
  
Q. How to test application:
A. On Right hand upper corner side you can enter product code to add into bill.
   Once you add code in given text box tab out, you will see bill is getting generated. You can add same code multiple times, in this case 
   Quantity will get increased. 
   Valid Product codes are:
  1000
  2000
  3000
  4000
  5000
  6000
  7000
  8000
  9000
  1001
  1100
  1200

Q. How can I clear/reset page?
A. Press Ctrl + F5

Q. Where can I find DB schema?
A. schema.sql and test-data.sql

Q. What technologies used?
A. Spring, Hibernate, HSQL
