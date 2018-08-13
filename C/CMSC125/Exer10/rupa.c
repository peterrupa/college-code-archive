/**
 	rupa.c
	Author: Peter Bernard M. Rupa
	Description: Simulates demand paging
 */

#include<stdio.h>
#include<stdlib.h>

// TLB data structure
typedef struct TLBnode{
	int pageNum;
	int pageOff;
	struct TLBnode *next;
}TLB;

//initialize an empty page table
//page table is always size 256
void initializePageTable(int pageTable[]){
	int i;
	for(i=0;i<256;i+=1){
		pageTable[i] = -1;
	}
}

int inTLB(TLB *tlb, int pnum) {
    TLB *ptr;
    
    for(ptr = tlb; ptr != NULL; ptr = ptr->next) {
        if(ptr->pageNum == pnum) {
            return pnum;
        }
    }
    
    return -1;
}

int inPageTable(int *pageTable, int pnum) {
    return pageTable[pnum];
}

//main
int main(){
	int num,i,j,temp,pnum,poff,free=0,addressesCount; //Declared here are all the variables
    TLB *tlb = NULL, *tempPtr, *ptr;
	FILE *fp, *bp, *fpp;				 //you need. You can add other variables
	char physicalMemory[256][256];		 //you think are necessary.
	int pageTable[256];
    char val[256];
    
	fp = fopen("addresses.txt","r"); //address to convert
	bp = fopen("BACKING_STORE.bin","rb"); //backing store

	initializePageTable(pageTable);

	if(fp!=NULL && bp!=NULL){
		fpp = fopen("answers.txt","w");
        
        // get number of addresses
        fscanf(fp, "%i\n", &addressesCount);

        for(i = 0; i < addressesCount; i++) {
            fscanf(fp, "%i\n", &temp);
            
            // get page number
            pnum = temp >> 8;
            
            // get page offset
            poff = temp & 255;
            
            // check if in TLB
            if(inTLB(tlb, pnum) > -1) {
                for(ptr = tlb; ptr != NULL; ptr = ptr->next) {
                    if(ptr->pageNum == pnum) {
                        fprintf(fpp, "Virtual address: %d, PageNum: %d, PageOff: %d, Physical address: %i, Value: %i\n", temp, pnum, poff, (pageTable[ptr->pageNum] * 256 + poff), physicalMemory[pageTable[ptr->pageNum]][poff]);
                        break;
                    }
                }
            }
            else {
                if(inPageTable(pageTable, pnum) > -1) {
                    fprintf(fpp, "Virtual address: %d, PageNum: %d, PageOff: %d, Physical address: %i, Value: %i\n", temp, pnum, poff, (pageTable[pnum] * 256 + poff), physicalMemory[pageTable[pnum]][poff]);
                }
                else {
                    // get data from banking store
                    fseek(bp, 256 * pnum, SEEK_SET);
                    fread(val, sizeof(char), 256, bp);
                    
                    // put to physical memory[free]
                    for(j = 0; j < 256; j++) {
                        physicalMemory[free][j] = val[j];
                    }
                    
                    fprintf(fpp, "Virtual address: %d, PageNum: %d, PageOff: %d, Physical address: %i, Value: %i\n", temp, pnum, poff, (free * 256 + poff), val[poff]);
                    
                    // update page table
                    pageTable[pnum] = free;
                    
                    free++;
                    
                    // update TLB
                    tempPtr = (TLB*)malloc(sizeof(TLB));
                    tempPtr->next = NULL;
                    tempPtr->pageNum = pnum;
                    tempPtr->pageOff = poff;
                    
                    if(ptr != NULL) {
                        for(ptr = tlb; ptr->next != NULL; ptr = ptr->next){}
                    
                        ptr->next = tempPtr;
                    }
                    else {
                        tlb = tempPtr;
                    }
                }
            }
        }

		fclose(fp);
		fclose(bp);
		fclose(fpp);
	}
}


/**
 	rupa.c
	Author: Peter Bernard M. Rupa
	Description: Simulates demand paging
 */
