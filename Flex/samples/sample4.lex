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
{year}	{strcpy(syear, yytext); return 0;}
{month}	{strcpy(smonth, yytext); printf("3. Please enter your birthyear (1981-1999):\n");}
{name}	{strcpy(sname, yytext); printf("2. Please enter your birth month:\n");}
%%
main() {
	printf("This is a scanner that will ask the user for the following:\n\t1. Name,\n\t2. Birth month,\n\t3. Birthyear\n");
	printf("1. What is your name?:\n");
	yylex();
	printf("\n1. Name:\t%s\n2. Birth month:\t%s\n3. Birthyear\t%s\n", sname, smonth, syear);	
}
