CREATE TABLE customer
   (
       customerid number,
       customername VARCHAR2(15),
       address varchar2(30),
       mobileno number,
       emailid varchar2(20),
       dateofbirth DATE,
      CONSTRAINT customer_custoid_pk PRIMARY KEY(customerid)   
   );   
CREATE TABLE account
   (
      customerid  number,
      acnumber number,
      openingbalance number(15,2),
      acopendate DATE,
      actype VARCHAR(10),
      acstatus VARCHAR(10),
      CONSTRAINT ac_number_pk PRIMARY KEY(acnumber),
      CONSTRAINT ac_custoid_fk FOREIGN KEY(customerid) REFERENCES customer(customerid)
    );

