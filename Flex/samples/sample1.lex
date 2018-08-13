/* 
 * Sample Scanner1: 
 * Description: Replace the string "username" from standard input 
 *              with the user's login name (e.g. lgao)
 * Usage: (1) $ flex sample1.lex
 *        (2) $ gcc lex.yy.c -lfl
 *        (3) $ ./a.out
 *            stdin> username
 *	      stdin> Ctrl-D
 * Question: What will happen if we remove the indent
 *		on the comment in the Rules Section?
 */

%{
/* need this for the call to getlogin() below */
#include <unistd.h>
%}
/* Definitions Section */

%%
	/* Rules Section (indented) */
username	printf("%s\n", getlogin());

%%
/* User Code Section */
main()
{
  yylex();
}

