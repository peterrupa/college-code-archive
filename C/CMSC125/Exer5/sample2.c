/**
 sample2.c
 KBPPelaez

 Sample program for handling the return value of a thread. 
**/


#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>

typedef struct node{
	int num;
	char letter;
}arg;

void * doThis(void *args){
	arg * temp;
	int * temp2 = (int *) malloc(sizeof(int));

	temp = (arg *) args;
	*temp2 = 45 + temp->num;

	printf("Hello from thread[%d] with letter %c.\n",temp->num, temp->letter);

	pthread_exit(temp2);
	//return a value from a thread

}

//main function
int main(){
	pthread_t tid[5];
	int i, threadnum[5], *temp[5];
	arg arguments[5];

	//create the threads
	for(i=0;i<5;i+=1){
		arguments[i].num = i;
		arguments[i].letter = i+65;
		//we pass to each thread their own arguments
		pthread_create(&tid[i], NULL, doThis, (void *) &arguments[i]);
	}

	//join the threads
	for(i=0;i<5;i+=1){
		pthread_join(tid[i], (void  **) &temp[i]);
	}

	//accessing the return values
	for(i=0;i<5;i+=1){
		printf("%d: %i\n", i, *((int *)temp[i]));
	}


}//end of main
