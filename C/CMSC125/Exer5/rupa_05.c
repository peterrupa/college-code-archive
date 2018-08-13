#include<stdio.h>
#include<stdlib.h> //for malloc
#include<pthread.h> //for threads

typedef struct ARG2{
	int rowIndex;
	int colIndex;
}args2;

int **A;
int **B;
int numRowA, numRowB;
int numColA, numColB;

void printMatrix(int **mat, int numRow, int numCol) {
    int i, j;

    for(i = 0; i < numRow; i++) {
        for(j = 0; j < numCol; j++) {
            printf("%i ", mat[i][j]);
        }
        printf("\n");
    }
}

void printMatrix2(int ***mat, int numRow, int numCol) {
    int i, j;

    for(i = 0; i < numRow; i++) {
        for(j = 0; j < numCol; j++) {
            printf("%i ", *mat[i][j]);
        }
        printf("\n");
    }
}

void * computeGrid(void * args) {
    args2 *arg;
    arg = (args2 *) args;

    int i;
    int products[numRowA];
    int * sum = (int *)malloc(sizeof(int));

    // multiply A's row to B's col
    for(i = 0; i < numRowA; i++) {
        products[i] = A[arg->rowIndex][i] * B[i][arg->colIndex];
    }

    *sum = 0;

    // get summation of products
    for(i = 0; i < numRowA; i++) {
        *sum += products[i];
    }

	pthread_exit(sum);
}

int main(){
    int i, j;
	FILE *fp;
	pthread_t *tid;

	fp = fopen("matrix.in", "r");
	if(fp!=NULL){
		// read matrix sizes
        fscanf(fp, "%i", &numRowA);
        fscanf(fp, "%i", &numColA);
        fscanf(fp, "%i", &numRowB);
        fscanf(fp, "%i", &numColB);

		// check if the size is invalid, i.e. colA != rowB
        if(numColA != numRowB) {
            printf("FATAL: Invalid matrices size\n");
            return -1;
        }

        // allocate array
        // allocate row
        A = (int **)malloc(sizeof(int *) * numRowA);

        // allocate col
        for(i = 0; i < numRowA; i++) {
            A[i] = (int *)malloc(sizeof(int *) * numColA);
        }

        // allocate array
        // allocate row
        B = (int **)malloc(sizeof(int *) * numRowB);

        // allocate col
        for(i = 0; i < numRowB; i++) {
            B[i] = (int *)malloc(sizeof(int *) * numColB);
        }

		// read matrix
        for(i = 0; i < numRowA; i++) {
            for(j = 0; j < numColA; j++) {
                fscanf(fp, "%i", &A[i][j]);
            }
        }

        for(i = 0; i < numRowB; i++) {
            for(j = 0; j < numColB; j++) {
                fscanf(fp, "%i", &B[i][j]);
            }
        }
        
		// print Matrix A
        printf("Matrix A:\n");
        printMatrix(A, numRowA, numColA);

		// print Matrix B
        printf("Matrix B:\n");
        printMatrix(B, numRowB, numColB);

        // instantiate matrix solution
        int ***C = (int ***)malloc(sizeof(int **) * numRowA);

        for(i = 0; i < numRowA; i++) {
            C[i] = (int **)malloc(sizeof(int *) * numColB);
        }

        args2 **arg = (args2 **)malloc(sizeof(args2 *) * numRowA);
        
        // allocate args rows
        for(i = 0; i < numRowA; i++) {
            arg[i] = (args2 *)malloc(sizeof(args2) * numColB);
        }
        
        // instantiate pid array
        tid = (pthread_t *)malloc(sizeof(pthread_t) * (numRowA * numColB));
        int tidCounter = 0;

        // compute per grid
        for(i = 0; i < numRowA; i++) {
            for(j = 0; j < numColB; j++) {
                arg[i][j].rowIndex = i;
                arg[i][j].colIndex = j;

		        // thread for computing a specific grid
        		pthread_create(&tid[tidCounter++], NULL, computeGrid, (void *) &arg[i][j]);
            }
        }

        for(i = 0; i < numRowA * numColB; i++) {
            int row = i / numRowA;
            int col = i % numRowA;
            
            // get return values of each thread and store in C array
            pthread_join(tid[i], (void  **) &C[row][col]);
        }
        
        // print solution
        printf("Result:\n");
        printMatrix2(C, numRowA, numColB);
	}else{
		printf("File not found!\n");
	}
}

