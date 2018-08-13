%{
#include<string.h>
%}
	char sname[20], smonth[10], syear[4];
	int item = 1;

alpha	[a-zA-Z" "]
name	{alpha}{0,20}
month	(?i:jan(uary)?|feb(ruary)?|mar(ch)?|apr(il)?|may|jun(e)?|jul(y)?|aug(ust)?|sept(ember)?|oct(ober)?|nov(ember)?|dec(ember)?)
year	(198[1-9]|199[0-9])
%%
{year}	if(item == 3){strcpy(syear, yytext); return 0;}
{month}	if(item == 2){strcpy(smonth, yytext); return 0;}
{name}	if(item == 1){strcpy(sname, yytext); return 0;}
%%
main() {
	printf("This is a scanner that will ask the user for the following:\n\t1. Name,\n\t2. Birth month,\n\t3. Birthyear\n");
	getName();
	item++; 
	getMonth();
	item++;	
	getYear();
	printf("\n1. Name:\t%s\n2. Birth month:\t%s\n3. Birthyear\t%s\n", sname, smonth, syear);
}

int getName(){
	printf("1. What is your name?:\n");
	yylex();
}

int getMonth(){
	printf("2. Please enter your birth month:\n");
	yylex();
}

int getYear(){
	printf("3. Please enter your birthyear (1981-1999):\n");
	yylex();
}
