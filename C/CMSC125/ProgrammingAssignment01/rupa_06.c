#include <stdio.h>
#include <time.h>		// time()
#include <math.h>		// srand() and ran()
#include <pthread.h>	// PTHREADS
#include <semaphore.h>	// SEMAPHORES
#include <unistd.h>
#define N 5
#define MAXTHINKINGTIME 3
#define MAXEATINGTIME 3

// This method will be performed by each philosopher
void * Philosophize(void * id);

// Chopsticks: Shared resources
sem_t chopstick[N];
// IF YOU DO NOT WANT TO USE SEMAPHORES, YOU CAN USE MUTEXES

int main()
{
	int i;

	// Create 5 PHILOSOPHER
	pthread_t philo[N];

	// Initialize Semaphores
	for(i=0; i<N; i++){
		sem_init(&chopstick[i], 0, 1);
	}

	// CREATE PHILOSOPHER threads
	for(i=0; i<N; i++){
		pthread_create(&philo[i], NULL, Philosophize, (void *)i);
	}

	// JOIN PHILOSOPHER threads
	for(i=0; i<N; i++){
		pthread_join(philo[i], NULL);
	}

	return 0;
}

void * Philosophize(void * id){
	int ID = (int) id;

    srand(time(NULL));

	while(1){
		// 1. THINK for a while
        int timeToThink = rand() % MAXTHINKINGTIME + 1;
        printf("%i started thinking for %i seconds.\n", ID, timeToThink);
        sleep(timeToThink);
        printf("%i finished thinking.\n", ID);

		// 2. PICK-UP left chopstick
        int leftChopStickID = ID;
	    sem_wait(&chopstick[leftChopStickID]);
        printf("%i picks up left chopstick #%i.\n", ID, leftChopStickID);

		// 3. PICK-UP right chopstick
        int rightChopStickID = (ID + 1) % 5;

	    sem_wait(&chopstick[rightChopStickID]);
        printf("%i picks up right chopstick #%i.\n", ID, rightChopStickID);

		// 4. EAT for a while
        int timeToEat = rand() % MAXEATINGTIME + 1;
        printf("%i started eating for %i seconds.\n", ID, timeToEat);
        sleep(timeToEat);
        printf("%i finished eating.\n", ID);

		// 5. RELEASE left chopstick
	    sem_post(&chopstick[leftChopStickID]);
        printf("%i released left chopstick #%i.\n", ID, leftChopStickID);

		// 6. RELEASE right choptick
	    sem_post(&chopstick[rightChopStickID]);
        printf("%i released right chopstick #%i.\n", ID, rightChopStickID);
	}

}
