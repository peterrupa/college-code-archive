#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <time.h>

FILE *fp;

void swap(int *a, int *b){
	int temp=*a;
	*a=*b;
	*b=temp;
}

int comp(const void *a, const void *b){
	return(*(int*)a-*(int*)b);
}

void init_asc(int a[],int n){
    int i;

    //ascending
	for(i=0;i<n;i++){
		a[i]=i+1;
	}
}

void init_desc(int a[],int n){
    int i;

	//descending
	for(i=0;i<n;i++){
		a[i]=n-i;
	}
}

void init_rand(int a[],int n){
    int i, r;

	//descending
	for(i=0;i<n;i++){
		a[i]=n-i;
	}

    //random
	for(i=0;i<n;i++){
		r = rand()%n;
		swap(&a[i],&a[r]);
	}
}

void init(int a[], int n, int d) {
    if(d == 0) init_asc(a, n);
    if(d == 1) init_rand(a, n);
    if(d == 2) init_desc(a, n);
}

void output(int a[], int n){
	int i;
	for(i=0;i<n;i++){
		if(i%10==0)
			printf("\n");
		printf("%d\t",a[i]);
	}
		
	printf("\n\n\n\n\n");
}
// Merge sort
void merge (int a[], int lower, int mid, int upper){
	int *temp,i,j,k;
	temp=(int *)malloc((upper-lower+1)*sizeof(int));
	for(i=0,j=lower,k=mid+1; j<=mid || k<=upper; i++)
		temp[i]=((j <= mid) && (k > upper || a[j] <= a[k]))?a[j++]:a[k++];
	for(i=0,j=lower;j<=upper; i++, j++)
	a[j]=temp[i];
	free(temp);
}

void msort(int a[],int lower, int upper){
	int mid;
	if((upper-lower)>0){
		mid=(upper+lower)/2;
		msort(a,lower,mid);
		msort(a,mid+1,upper);
		merge(a,lower,mid,upper);
	}
}

void bubsort(int a[],int n){
	int i,j;
	for(i=0;i<n-1;i++){
		for(j=0;j<n-i-1;j++){
			if(a[j]>a[j+1])
				swap(&a[j],&a[j+1]);
		}
	}
}

void inssort(int a[], int n){
	int i,j;
	for(i=1;i<n;i++){
		for(j=i;j>0;j--){
			if(a[j]<a[j-1])
				swap(&a[j],&a[j-1]);
			else break;
		}
	}
}
void selsort(int a[],int n){
	int i,j;
	for(i=0;i<n-1;i++){
		for(j=i+1;j<n;j++){
			if(a[i]>a[j]){
				swap(&a[i],&a[j]);
			}
		}
	}
}

int do_round(int a[], int n, int i, int input_multiplier, int d) {
    clock_t t;
	t = clock();


	fp = fopen("out.txt", "a");
    printf("\t\t%d Round \n",i+1);
	fprintf(fp, "\t\t%d Round \n",i+1);
	fclose(fp);
	
    init(a,n,d);
    //output(a,n);
    t = clock();
    qsort(a,n,sizeof(int),comp);
    t = clock()-t;
    double timeTaken = ((double)t)/CLOCKS_PER_SEC;

	fp = fopen("out.txt", "a");
    printf("\t\t\tqsort: %f\n", timeTaken);
	fprintf(fp, "\t\t\tqsort: %f\n", timeTaken);
	fclose(fp);

    init(a,n,d);
    t = clock();
    msort(a,0,n);
    t = clock()-t;
    timeTaken = ((double)t)/CLOCKS_PER_SEC;

	fp = fopen("out.txt", "a");
    printf("\t\t\tmsort: %f\n", timeTaken);
	fprintf(fp, "\t\t\tmsort: %f\n", timeTaken);
	fclose(fp);

    init(a,n,d);
    t = clock();
    bubsort(a,n);
    t = clock()-t;
    timeTaken = ((double)t)/CLOCKS_PER_SEC;

	fp = fopen("out.txt", "a");
    printf("\t\t\tbubsort: %f\n", timeTaken);
	fprintf(fp, "\t\t\tbubsort: %f\n", timeTaken);
	fclose(fp);

    init(a,n,d);
    t = clock();
    inssort(a,n);
    t = clock()-t;
    timeTaken = ((double)t)/CLOCKS_PER_SEC;

	fp = fopen("out.txt", "a");
    printf("\t\t\tinssort: %f\n", timeTaken);
	fprintf(fp, "\t\t\tinssort: %f\n", timeTaken);
	fclose(fp);

    init(a,n,d);
    t = clock();
    selsort(a,n);
    t = clock()-t;
    timeTaken = ((double)t)/CLOCKS_PER_SEC;

	fp = fopen("out.txt", "a");
    printf("\t\t\tselsort: %f\n", timeTaken);
	fprintf(fp, "\t\t\tselsort: %f\n", timeTaken);
	fclose(fp);
}

int main()
{
	int x=50000, *a,n;
	int i, j, k;
    char *names[3] = {"Ascending", "Average", "Descending"};

    for(i=0; i < 3; i++) {

		fp = fopen("out.txt", "a");
        printf("Input %s\n", names[i]);
		fprintf(fp, "Input %s\n", names[i]);
		fclose(fp);

        for(j=1;j<5;j++) {
            n = j * x;


			fp = fopen("out.txt", "a");
            printf("\t%cx\n", j + '0');
			fprintf(fp, "\t%cx\n", j + '0');
			fclose(fp);
            
            for(k=0;k<3;k++){
                a = (int*)malloc(sizeof(int)*n);
                do_round(a, n, k, j, i);
                free(a);
            }
        }
    }

	return 0;
}