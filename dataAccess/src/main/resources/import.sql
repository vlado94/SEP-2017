INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Sport',100);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Starost',100);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Region',100);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Tip putovanja',100);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Pokrice', 1000);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Povrsina stana', 1000);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Starost stana', 1000);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Vrednost stana', 1000);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Rizici', 1000);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Slepovanje', 600);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Popravka', 600);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Smestaj', 500);
INSERT INTO CATEGORY_FACTOR(NAME,BASE_PRICE) VALUES('Alternativni prevoz', 500);


INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Skijanje', 1);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Planinarenje', 1);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Skok sa padobranom', 1);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Boćanje', 1);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('0-16', 2);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('16-60', 2);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('60-80', 2);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Južna Amerika', 3);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Evropa', 3);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Azija', 3);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Afrika', 3);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Australija', 3);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Turističko', 4);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Poslovno',4);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('10000e',5);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('30000e',5);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('60000e',5);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Do 50 m2',6);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Izmedju 50m2 i 100m2',6);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Preko 100m2',6);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Do 5 godina',7);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Izmedju 5 i 15 godina',7);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Preko 15 godina',7);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Do 100.000,00 eura',8);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Izmedju 100.000,00 i 200.00,00 eura',8);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Preko 200.00,00 eura',8);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Krađe',9);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Požara',9);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Poplave',9);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Šlepovanje do 500km',10);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Šlepovanje do 1000km',10);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Šlepovanje preko 1000km',10);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Popravka do 300e',11);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Popravka do 800e',11);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Popravka do 1500e',11);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Smeštaj u hotelu 2 dana',12);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Smeštaj u hotelu 4 dana',12);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Smeštaj u hotelu 6 dana',12);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Prevoz automobilom',13);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Prevoz kombijem',13);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Prevoz avionom',13);

INSERT INTO PRICE_LIST(FROM_DATE) VALUES ('2016-6-11');

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (1,1,170); #sport
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (2,1,150);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (3,1,240);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (4,1,120);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (5,1,120); #starost
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (6,1,160);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (7,1,180);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (8,1,170); #region
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (9,1,140);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (10,1,150);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (11,1,180);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (12,1,155);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (13,1,150); #tip_putovanja
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (14,1,130);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (15,1,2); #pokrice
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (16,1,3);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (17,1,4);


INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (18,1,10); #povrsine_stana
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (19,1,20);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (20,1,30);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (21,1,6); #starost_stana
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (22,1,7);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (23,1,8);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (24,1,50); #vrednost_stana
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (25,1,60);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (26,1,70);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (27,1,2); #rizici
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (28,1,3);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (29,1,4);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (30,1,70); #slep
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (31,1,80);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (32,1,90);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (33,1,5); #popravka
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (34,1,6);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (35,1,7);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (36,1,20);  #smjestaj
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (37,1,30);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (38,1,40);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (39,1,40); #prevoz
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (40,1,50); 
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (41,1,60); 

INSERT INTO BANK(NAME,CODE) VALUES ('UNI-CREDIT',111);
INSERT INTO BANK(NAME,CODE) VALUES ('INTESA',222);

INSERT INTO BANK_MEMBER(NAME,CARD_NUMBER,BILL_NUMBER,AMOUNT,BANK_ID,VALID) VALUES ('Pera Peric',132134,111234,100000,1,true);
INSERT INTO BANK_MEMBER(NAME,CARD_NUMBER,BILL_NUMBER,AMOUNT,BANK_ID,VALID) VALUES ('Mika Mikic',132135,111235,350000,1,true);
INSERT INTO BANK_MEMBER(NAME,CARD_NUMBER,BILL_NUMBER,AMOUNT,BANK_ID,VALID) VALUES ('Radovan 3',132136,111236,400000,1,true);
INSERT INTO BANK_MEMBER(NAME,CARD_NUMBER,BILL_NUMBER,AMOUNT,BANK_ID,VALID) VALUES ('Mihajlo Maric',132137,222234,100000,2,true);
INSERT INTO BANK_MEMBER(NAME,CARD_NUMBER,BILL_NUMBER,AMOUNT,BANK_ID,VALID) VALUES ('Ivan Aleksic',132138,222235,200000,2,true);
INSERT INTO BANK_MEMBER(NAME,CARD_NUMBER,BILL_NUMBER,AMOUNT,BANK_ID,VALID) VALUES ('Janko Jankovic',132139,222236,300000,2,true);


