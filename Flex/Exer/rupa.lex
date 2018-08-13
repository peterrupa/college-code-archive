%{
    #include<string.h>
    
    int d_count          = 0,
        i_count          = 0,
        e_count          = 0,
        sentence_count   = 0,
        avg_words        = 0;

    int sentences[200];
    
    int getInput();
    int average(int *input, int length);
    int num_words(char *input);
    void new_sentence(char *input);
    void trim();
%}

DECLARATIVE [^\.\?!]+\.\n?
INTERROGATIVE [^\.\?!]+\?\n?
EXCLAMATORY [^\.\?!]+!\n?

%%

{DECLARATIVE} ++d_count; new_sentence(yytext); if(yytext[strlen(yytext) - 1] == '\n') return 0;
{INTERROGATIVE} ++i_count; new_sentence(yytext); if(yytext[strlen(yytext) - 1] == '\n') return 0;
{EXCLAMATORY} ++e_count; new_sentence(yytext); if(yytext[strlen(yytext) - 1] == '\n') return 0;

%%
int main() {
    printf("Enter paragraph:\n");
    getInput();
    printf("Declarative: %i\n", d_count);
    printf("Interrogative: %i\n", i_count);
    printf("Exclamatory: %i\n", e_count);
    printf("Average number of words per sentence: %i\n", average(sentences, sentence_count));
    
    return 0;
}

int getInput() {
    yylex();
}

int average(int *input, int length) {
    int accumulator = 0, i;
    
    for(i = 0; i < length; i++) {
        accumulator += input[i];
    }
    
    return accumulator / length;
}

int num_words(char *input) {
    int i, num_words = 0;
    
    for(i = 0; i < strlen(input); i++) {
        if(input[i] == ' ') {
            num_words++;
        } 
    }
    
    return num_words + 1;
}

void new_sentence(char *input) {
    trim(&input);
    
    // get number of words
    int number_of_words = num_words(input);
    
    // push to array
    sentences[sentence_count++] = number_of_words;
}

void trim(char **input) {
    if(*input[0] == ' ') (*input)++;
}