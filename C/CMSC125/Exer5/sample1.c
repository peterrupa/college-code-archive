/**
 sample1.c
 KBPPelaez

 Sample program showing the usage of the basic pthread functions:
 	pthread_create()
	pthread_join()
	pthread_exit()
 **/

#include<stdio.h>
#include<pthread.h>

//structure that will be passed to the thread
typedef struct node{
	int num;
	char letter;
}arg;

//function for the thread that takes two or more parameters
void * multiParam(void *args){
	arg * temp;

	temp = (arg *) args;

	printf("Hello from thread[%d] with letter %c.\n",temp->num, temp->letter);

	pthread_exit(NULL);

}

//function for the thread that takes one parameter
void * oneParameter(void *args){
	int *num;
	num = (int *) args;

	printf("Hello from thread[%d]\n", *num);

	pthread_exit(NULL);
}

//function for the thread that takes processes 0 arguments
void *noParameter(void *args){
	printf("Hello\n");

	pthread_exit(NULL);
}

int main(){
	pthread_t tid[5]; //the pthread data type for the thread ids
	int i, threadnum[5];
	arg arguments[5];

	for(i=0;i<5;i+=1){
		// 1
		//pthread_create(&tid[i],NULL,noParameter,NULL);

		// 2
		//threadnum[i] = i;
		//pthread_create(&tid[i],NULL,oneParameter,(void *) &threadnum[i] );

		// 3
		arguments[i].num = i;
		arguments[i].letter = i+65;
		pthread_create(&tid[i],NULL,multiParam, (void *) &arguments[i]);
	}

	for(i=0;i<5;i+=1){
		//joins the threads so that the kernel thread will wait for the others
		pthread_join(tid[i],NULL);
	}

	return 0;

}//end of main
