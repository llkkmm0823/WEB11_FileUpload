create table bookproduct(
	code number(5) primary key,
	name varchar2(100),
	price number(8),
	pictureurl varchar2(50),
	description varchar2(1000) 
	);

create sequence bookproduct_seq start with 1 increment by 1;

insert into bookproduct
values(bookproduct_seq.nextVal,'JQuery and JQuery mobile : 이해하기 쉽게 풀어 쓴',25000,
'jquery.jpg','소스하나로 데스크랍과 모바일까지 HTML5와 함께 사용....');

insert into bookproduct
values(bookproduct_seq.nextVal,'자바의 신',30000,
'java.gif','자바프로그래밍의 정석, 기초부터 실무 예제까지....');

insert into bookproduct
values(bookproduct_seq.nextVal,'오라클 데이터 베이스',25000,
null,'오라클 데이터 베이스 활용의 정석, 기초부터 실무 예제까지....');


select*from bookproduct;