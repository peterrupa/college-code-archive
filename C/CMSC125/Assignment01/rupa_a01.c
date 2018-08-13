/*
	Filename: rupa_07.c
	Author: Peter Bernard M. Rupa
	Program Description: Simulates Preemptive Shortest Job First scheduling algorithm.
 */

#include <stdio.h>
#include <stdlib.h>

/* Process Control Block (PCB) */
typedef struct _PCB
{
	int process_id;
	int arrival_time;
	int job_length;
	int wait_time;
	int run_time;
}PCB;

/* A singly-linked list of PCB */
typedef struct _linked_list
{
	PCB pcb;
	struct _linked_list *next;
}linked_list;

/* Global Variables */
int process_count = 0;
linked_list *ready_queue;

int arrival_time[256];
int job_length[256];

void PrintPCB(PCB pcb) {
	printf("\tProcess ID: %d, Arrival Time: %d, Job Length: %d, Waiting Time: %d, Running Time: %d\n", pcb.process_id, pcb.arrival_time, pcb.job_length, pcb.wait_time, pcb.run_time);
}

void PrintReadyQueue() {
	linked_list *ptr;
	ptr = ready_queue;
	printf("\tPrinting Ready Queue\n");
	while(ptr!=NULL)
	{
		PrintPCB(ptr->pcb);
		ptr = ptr->next;
	}
    printf("\n");
}

void Dequeue() {	
	linked_list *temp;
	
	/* Dequeue the head of the ready queue */
    temp = ready_queue;
    
    ready_queue = ready_queue->next;
    
	//print process details
    printf("\tDequeued:\n");
    PrintPCB(temp->pcb);
    printf("\n");
    
    free(temp);
}

// @TODO me
void MoveToHead(int process_id) {
    linked_list *ptr, *temp;
    
    temp = ready_queue;
    
    if(ready_queue->next == NULL) {
        return;
    }
    
    // traverse to node with process_id - 1
    
    for(ptr = ready_queue; (ptr->next->pcb).process_id == process_id; ptr = ptr->next) {
        if((ptr->next->pcb).process_id == process_id) {
            break;
        }
    }
    
    // set reference to node with process_id
    temp = ptr->next;
    
    ptr->next = temp->next;
    
    temp->next = ready_queue;
    
    ready_queue = temp;
    
    printf("\tMoving to head:\n");
    PrintPCB(ready_queue->pcb);
    printf("\n");
}

void Processing() {
    linked_list *ptr;
    
	/*This simulates a processing of a single CPU cycle
	The first process in the ready queue will increment its run time
	The other process will increment their wait time. */
    
    // check if head has shortest
    int process_id_highest = (ready_queue->pcb).process_id;
    int remaining_time_highest = (ready_queue->pcb).job_length - (ready_queue->pcb).run_time;
    
    for(ptr = ready_queue->next; ptr != NULL; ptr = ptr->next) {
        if((ptr->pcb).job_length - (ptr->pcb).run_time < remaining_time_highest) {
            process_id_highest = (ptr->pcb).process_id;
            remaining_time_highest = (ptr->pcb).job_length - (ptr->pcb).run_time;
        }
    }
    
    if(process_id_highest != (ready_queue->pcb).process_id) {
        MoveToHead(process_id_highest);
    }
    
    // head
    (ready_queue->pcb).run_time++;
    printf("\tExecuting:\n");
    PrintPCB(ready_queue->pcb);
    printf("\n");
    
    // others
    for(ptr = ready_queue->next; ptr != NULL; ptr = ptr->next) {
        (ptr->pcb).wait_time++;
    }
    
	/*if run_time == job length, then dequeue the job */
	if((ready_queue->pcb).run_time == (ready_queue->pcb).job_length) {
        Dequeue();
    }
}

void EnqueueProcess(int arrival, int job_length) {
	//enqueue process here
	linked_list *ptr, *temp;

	// if queue still empty
    if(ready_queue == NULL) {
        // allocate pcb
        ready_queue = (linked_list *)malloc(sizeof(linked_list));
        (ready_queue->pcb).process_id = 1;
        (ready_queue->pcb).arrival_time = arrival;
        (ready_queue->pcb).job_length = job_length;
        (ready_queue->pcb).wait_time = 0;
        (ready_queue->pcb).run_time = 0;
        ready_queue->next = NULL;
        
        PrintPCB(ready_queue->pcb);
    }
    else {
        // traverse to tail
        for(ptr = ready_queue; ptr->next != NULL; ptr = ptr->next) {}
        
        // allocate pcb
        temp = (linked_list *)malloc(sizeof(linked_list));
        (temp->pcb).process_id = process_count++ + 2;
        (temp->pcb).arrival_time = arrival;
        (temp->pcb).job_length = job_length;
        (temp->pcb).wait_time = 0;
        (temp->pcb).run_time = 0;
        temp->next = NULL;
        
        // push to queue
        ptr->next = temp;
        
        PrintPCB(ptr->next->pcb);
    }
}

void RunSimulation() {
	int index = 0;
	int cpu_cycles = 1;

	while(1) {
		printf("CPU Cycle: %d\n", cpu_cycles);

		//check if a new process arrived
        if(arrival_time[index] == cpu_cycles) {
            // enqueue process
            printf("\tEnqueued:\n");
            EnqueueProcess(arrival_time[index], job_length[index]);
            printf("\n");
            
            index++;
        }

		//execute item on ready queue
		
		//		
		Processing();
        
        PrintReadyQueue();
        
		cpu_cycles++;
		if(ready_queue == NULL) break;
	}
}

void ReadFile() {
	int arrival, length, i = 0;
	
	/* Read File process.txt */
	FILE *fp;
	fp = fopen("process.txt", "rw");
	
	/* <process.txt>
	   The format of the process.txt file is :
	   <arrival_time> <job_length>
	
	   The arrival time is already arranged in ascending order.
	   Increment the process ID for each new process.
	   The last entry of the process.txt file is "-1 -1" 
	*/
    
	// get processes
	while(1) {
		fscanf(fp, "%i %i", &arrival, &length);

		if(arrival == -1 && length == -1) {
			break;
		}

		arrival_time[i] = arrival;
		job_length[i++] = length;
	}
}

int main() {	
	ready_queue = NULL;
	process_count = 0;
	ReadFile();
	RunSimulation();
}
