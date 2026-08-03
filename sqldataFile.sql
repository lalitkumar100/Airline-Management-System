INSERT INTO airports (airport_code, airport_name, city) VALUES
('DEL', 'Indira Gandhi International Airport', 'Delhi'),
('BOM', 'Chhatrapati Shivaji Maharaj Airport', 'Mumbai'),
('BLR', 'Kempegowda International Airport', 'Bengaluru'),
('MAA', 'Chennai International Airport', 'Chennai'),
('HYD', 'Rajiv Gandhi International Airport', 'Hyderabad');

INSERT INTO aircrafts (aircraft_id, model, capacity) VALUES
('AC001', 'Airbus A320', 5),
('AC002', 'Boeing 737', 189),
('AC003', 'Airbus A321', 220),
('AC004', 'Boeing 787 Dreamliner', 290),
('AC005', 'Airbus A350', 315);

INSERT INTO loyalty_accounts (loyalty_id, points, tier) VALUES
(1,150,'Silver'),
(2,420,'Gold'),
(3,80,'Silver'),
(4,900,'Diamond'),
(5,0,'Silver'),
(6,250,'Silver'),
(7,650,'Gold'),
(8,1200,'Diamond'),
(9,0,'Silver'),
(10,350,'Silver');

INSERT INTO passengers
(passenger_id,name,email,phone,date_of_birth,bank_name,account_number,password,loyalty_id)
VALUES
('PAS1001','Rahul Sharma','rahul@gmail.com','9876543210','1998-05-14','State Bank of India','SBI1234567890','rahul123',1),

('PAS1002','Priya Patel','priya@gmail.com','9876543211','1996-09-22','HDFC Bank','HDFC9876543210','priya123',2),

('PAS1003','Amit Kumar','amit@gmail.com','9876543212','1999-02-10','ICICI Bank','ICICI4567891230','amit123',3),

('PAS1004','Sneha Reddy','sneha@gmail.com','9876543213','1995-11-05','Axis Bank','AXIS1239874560','sneha123',4),

('PAS1005','Arjun Singh','arjun@gmail.com','9876543214','2000-07-18','Punjab National Bank','PNB7894561230','arjun123',5),

('PAS1006','Neha Verma','neha@gmail.com','9876543215','1998-12-01','Bank of Baroda','BOB6543217890','neha123',6),

('PAS1007','Rohan Mehta','rohan@gmail.com','9876543216','1997-04-25','Canara Bank','CAN1112223334','rohan123',7),

('PAS1008','Kavya Nair','kavya@gmail.com','9876543217','1994-08-30','Kotak Mahindra Bank','KOT9871236540','kavya123',8),

('PAS1009','Vikram Joshi','vikram@gmail.com','9876543218','1999-01-15','Union Bank of India','UBI1122334455','vikram123',9),

('PAS1010','Ananya Das','ananya@gmail.com','9876543219','1998-06-09','Indian Bank','IND9988776655','ananya123',10);


INSERT INTO routes (source_airport_code,destination_airport_code) VALUES
('DEL','BOM'),
('BLR','HYD'),
('BOM','DEL'),
('MAA','BLR'),
('HYD','MAA'),
('DEL','HYD'),
('BOM','MAA'),
('BLR','DEL'),
('HYD','BOM'),
('MAA','DEL');


INSERT INTO flights
(flight_id,source_airport_code,destination_airport_code,
departure_date_time,arrival_date_time,
aircraft_id,base_fare,status,
booked_a_seats,booked_b_seats,booked_c_seats)
VALUES

('FL001','DEL','BOM',
'2026-07-30 10:00:00',
'2026-07-30 12:10:00',
'AC001',2000.00,'Scheduled',1,1,1),

('FL002','BLR','HYD',
'2026-07-30 14:00:00',
'2026-07-30 15:30:00',
'AC002',3000.00,'Scheduled',1,1,1),

('FL003','BOM','DEL',
'2026-07-31 09:15:00',
'2026-07-31 11:25:00',
'AC003',2500.00,'Scheduled',1,1,1),

('FL004','MAA','BLR',
'2026-07-31 13:00:00',
'2026-07-31 14:05:00',
'AC004',1800.00,'Scheduled',1,1,1),

('FL005','HYD','MAA',
'2026-07-31 16:30:00',
'2026-07-31 17:45:00',
'AC005',2200.00,'Scheduled',1,1,1),

('FL006','DEL','HYD',
'2026-08-01 08:00:00',
'2026-08-01 10:20:00',
'AC001',3200.00,'Scheduled',1,1,1),

('FL007','BOM','MAA',
'2026-08-01 11:30:00',
'2026-08-01 13:20:00',
'AC002',2800.00,'Scheduled',1,1,1),

('FL008','BLR','DEL',
'2026-08-01 15:00:00',
'2026-08-01 17:45:00',
'AC003',3500.00,'Scheduled',1,1,1),

('FL009','HYD','BOM',
'2026-08-02 09:45:00',
'2026-08-02 11:35:00',
'AC004',2700.00,'Scheduled',1,1,1),

('FL010','MAA','DEL',
'2026-08-02 18:00:00',
'2026-08-02 20:40:00',
'AC005',3600.00,'Scheduled',1,1,1);


INSERT INTO payments
(payment_id, amount, is_paid, payment_method)
VALUES
('PAY1001',2000.00,TRUE,'UPI'),
('PAY1002',2000.00,TRUE,'Credit card'),
('PAY1003',2000.00,TRUE,'PayPal'),

('PAY1004',3000.00,TRUE,'UPI'),
('PAY1005',3000.00,TRUE,'Credit card'),
('PAY1006',3000.00,TRUE,'PayPal'),

('PAY1007',2500.00,TRUE,'UPI'),
('PAY1008',2500.00,TRUE,'Credit card'),
('PAY1009',2500.00,TRUE,'PayPal'),

('PAY1010',1800.00,TRUE,'UPI'),
('PAY1011',1800.00,TRUE,'Credit card'),
('PAY1012',1800.00,TRUE,'PayPal'),

('PAY1013',2200.00,TRUE,'UPI'),
('PAY1014',2200.00,TRUE,'Credit card'),
('PAY1015',2200.00,TRUE,'PayPal'),

('PAY1016',3200.00,TRUE,'UPI'),
('PAY1017',3200.00,TRUE,'Credit card'),
('PAY1018',3200.00,TRUE,'PayPal'),

('PAY1019',2800.00,TRUE,'UPI'),
('PAY1020',2800.00,TRUE,'Credit card'),
('PAY1021',2800.00,TRUE,'PayPal'),

('PAY1022',3500.00,TRUE,'UPI'),
('PAY1023',3500.00,TRUE,'Credit card'),
('PAY1024',3500.00,TRUE,'PayPal'),

('PAY1025',2700.00,TRUE,'UPI'),
('PAY1026',2700.00,TRUE,'Credit card'),
('PAY1027',2700.00,TRUE,'PayPal'),

('PAY1028',3600.00,TRUE,'UPI'),
('PAY1029',3600.00,TRUE,'Credit card'),
('PAY1030',3600.00,TRUE,'PayPal');

INSERT INTO bookings
(booking_id,passenger_id,flight_id,seat_type,seat_no,amount,booking_status,passenger_check_in,payment_id)
VALUES

('BK1001','PAS1001','FL001','A',NULL,2000,'Confirmed',FALSE,'PAY1001'),
('BK1002','PAS1002','FL001','B',NULL,2000,'Confirmed',FALSE,'PAY1002'),
('BK1003','PAS1003','FL001','C',NULL,2000,'Confirmed',FALSE,'PAY1003'),

('BK1004','PAS1004','FL002','A',NULL,3000,'Confirmed',FALSE,'PAY1004'),
('BK1005','PAS1005','FL002','B',NULL,3000,'Confirmed',FALSE,'PAY1005'),
('BK1006','PAS1006','FL002','C',NULL,3000,'Confirmed',FALSE,'PAY1006'),

('BK1007','PAS1007','FL003','A',NULL,2500,'Confirmed',FALSE,'PAY1007'),
('BK1008','PAS1008','FL003','B',NULL,2500,'Confirmed',FALSE,'PAY1008'),
('BK1009','PAS1009','FL003','C',NULL,2500,'Confirmed',FALSE,'PAY1009'),

('BK1010','PAS1010','FL004','A',NULL,1800,'Confirmed',FALSE,'PAY1010'),
('BK1011','PAS1001','FL004','B',NULL,1800,'Confirmed',FALSE,'PAY1011'),
('BK1012','PAS1002','FL004','C',NULL,1800,'Confirmed',FALSE,'PAY1012'),

('BK1013','PAS1003','FL005','A',NULL,2200,'Confirmed',FALSE,'PAY1013'),
('BK1014','PAS1004','FL005','B',NULL,2200,'Confirmed',FALSE,'PAY1014'),
('BK1015','PAS1005','FL005','C',NULL,2200,'Confirmed',FALSE,'PAY1015'),

('BK1016','PAS1006','FL006','A',NULL,3200,'Confirmed',FALSE,'PAY1016'),
('BK1017','PAS1007','FL006','B',NULL,3200,'Confirmed',FALSE,'PAY1017'),
('BK1018','PAS1008','FL006','C',NULL,3200,'Confirmed',FALSE,'PAY1018'),

('BK1019','PAS1009','FL007','A',NULL,2800,'Confirmed',FALSE,'PAY1019'),
('BK1020','PAS1010','FL007','B',NULL,2800,'Confirmed',FALSE,'PAY1020'),
('BK1021','PAS1001','FL007','C',NULL,2800,'Confirmed',FALSE,'PAY1021'),

('BK1022','PAS1002','FL008','A',NULL,3500,'Confirmed',FALSE,'PAY1022'),
('BK1023','PAS1003','FL008','B',NULL,3500,'Confirmed',FALSE,'PAY1023'),
('BK1024','PAS1004','FL008','C',NULL,3500,'Confirmed',FALSE,'PAY1024'),

('BK1025','PAS1005','FL009','A',NULL,2700,'Confirmed',FALSE,'PAY1025'),
('BK1026','PAS1006','FL009','B',NULL,2700,'Confirmed',FALSE,'PAY1026'),
('BK1027','PAS1007','FL009','C',NULL,2700,'Confirmed',FALSE,'PAY1027'),

('BK1028','PAS1008','FL010','A',NULL,3600,'Confirmed',FALSE,'PAY1028'),
('BK1029','PAS1009','FL010','B',NULL,3600,'Confirmed',FALSE,'PAY1029'),
('BK1030','PAS1010','FL010','C',NULL,3600,'Confirmed',FALSE,'PAY1030');