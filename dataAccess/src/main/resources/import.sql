INSERT INTO CATEGORY_FACTOR(NAME) VALUES('Sport');
INSERT INTO CATEGORY_FACTOR(NAME) VALUES('Starost');
INSERT INTO CATEGORY_FACTOR(NAME) VALUES('Region');
INSERT INTO CATEGORY_FACTOR(NAME) VALUES('Tip putovanja');

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Skijanje', 1);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Planinarenje', 1);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Skok sa padobranom', 1);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Boćanje', 1);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Do 16', 2);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Od 16 do 60', 2);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Od 60', 2);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Južna Amerika', 3);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Evropa', 3);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Azija', 3);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Afrika', 3);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Australija', 3);

INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Turističko', 4);
INSERT INTO FACTOR(NAME, CATEGORY_ID) VALUES ('Poslovno',4);

INSERT INTO PRICE_LIST(FROM_DATE, TO_DATE) VALUES ('2016-6-8','2016-6-11');
INSERT INTO PRICE_LIST(FROM_DATE) VALUES ('2016-6-11');

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (1,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (2,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (3,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (4,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (5,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (6,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (7,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (8,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (9,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (10,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (11,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (12,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (13,1,0.15);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (14,1,0.15);

INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (1,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (2,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (3,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (4,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (5,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (6,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (7,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (8,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (9,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (10,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (11,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (12,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (13,2,0.25);
INSERT INTO PRICE_LIST_ITEM(FACTOR_ID,PRICE_LIST_ID,PERCENT) VALUES (14,2,0.25);


