insert into users(fullname, username, password, role) values('Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ADMIN');
insert into users(fullname, username, password, role) values('User', 'user', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'USER');

insert into bidlist(account, type, bid_Quantity) values('un', 'un', '200');
insert into bidlist(account, type, bid_Quantity) values('deux', 'deux', '300');
insert into bidlist(account, type, bid_Quantity) values('trois', 'trois', '400');

insert into curvepoint(Curve_Id, term, value) values('1', '10.0', '20.0');
insert into curvepoint(Curve_Id, term, value) values('2', '30.0', '40.0');
insert into curvepoint(Curve_Id, term, value) values('3', '50.0', '60.0');

insert into rating(moodys_Rating, sandPRating, fitch_Rating, order_Number) values('un', 'un','un', '1');
insert into rating(moodys_Rating, sandPRating, fitch_Rating, order_Number) values('deux', 'deux','deux', '2');
insert into rating(moodys_Rating, sandPRating, fitch_Rating, order_Number) values('trois', 'trois','trois', '3');

insert into rulename(name, description,json, template, sql_Str, sql_Part) values('un', 'un', 'un', 'un', 'un', 'un');
insert into rulename(name, description,json, template, sql_Str, sql_Part) values('deux', 'deux', 'deux', 'deux', 'deux', 'deux');
insert into rulename(name, description,json, template, sql_Str, sql_Part) values('trois', 'trois', 'trois', 'trois', 'trois', 'trois');