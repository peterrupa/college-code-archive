#include<stdio.h>
#include<math.h>
#include<pthread.h>
#include<semaphore.h>
#include<unistd.h>
#include<stdlib.h>

#define MINSLEEPTIME 1
#define MAXSLEEPTIME 15
#define NUMBEROFCHAIRS 3

// mutex for TA
pthread_mutex_t taLock = PTHREAD_MUTEX_INITIALIZER;
int taState;

// semaphore for chairs
sem_t chairs;
int chairState;

void *StudentFunction(void * nameId){
    int studentId = (int)nameId;
    
    srand(time(NULL));
    
    while(1) {
        int timeToProgram = rand() % MAXSLEEPTIME + MINSLEEPTIME;
        int didWait = 0;
        
        // program
        printf("Student %i is programming for %i seconds....\n", studentId, timeToProgram);
        sleep(timeToProgram);
        printf("Student %i is done programming.\n", studentId);
        
        if(taState == 1) {
            if(chairState < 3) {
                printf("Student %i wanted to consult but TA is occupied. He waits in a chair.\n", studentId);
                sem_wait(&chairs);
                chairState++;
                didWait = 1;
            }
            else {
                printf("Student %i wanted to consult but TA is occupied. All chairs are occupied. He continues programming.\n", studentId);
                continue;
            }
        }
        
        // consult        
        pthread_mutex_lock(&taLock);
        
        if(didWait == 1) {
            sem_post(&chairs);
            chairState--;
            printf("Student %i went off from chair.\n", studentId);
        }
        
        int timeToHelp = rand() % MAXSLEEPTIME + MINSLEEPTIME;
        
        printf("Student %i consults TA for %i seconds.\n", studentId, timeToHelp);
        taState = 1;
        
        sleep(timeToHelp);
        
        printf("Student %i is done consulting.\n", studentId);
        taState = 0;
        
        pthread_mutex_unlock(&taLock);
    }
}

void *TAFunction(void * args){
    int prevState = 1;
    
    while(1) {
        if(taState == 1 && prevState == 0) {
            prevState = 1;
            printf("TA wakes up.\n");
        }
        else if(taState == 0 && prevState == 1 && chairState == 0) {
            prevState = 0;
            printf("TA sleeps.\n");
        }
    }
}

int main(int argc, char *argv[]) {
    int numberOfStudents = atoi(argv[1]);
    int i;
    
    pthread_t students[numberOfStudents];
    pthread_t ta;
    
    // Initialize chairs
	for(i = 0; i < NUMBEROFCHAIRS; i++){
		sem_init(&chairs, 0, 3);
	}
    
    taState = 0;
    chairState = 0;
    
    // Create processes for students
    for(i = 0; i < numberOfStudents; i++){
		pthread_create(&students[i], NULL, StudentFunction, (void *)i);
	}
    
    // Create process for TA
    pthread_create(&ta, NULL, TAFunction, NULL);
    
    // Join processes for students
    for(i = 0; i < numberOfStudents; i++){
		pthread_join(students[i], NULL);
	}
    
    pthread_join(ta, NULL);
    
    return 0;
}