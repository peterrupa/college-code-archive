//Exercise 10 - Implement AVL deletion with fixup

#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
#define N 10
#define BALANCED 0
#define LEFT_LEANING 1
#define RIGHT_LEANING 2

typedef struct node_tag{
	int x, height;
	struct node_tag *parent;
	struct node_tag *left;
	struct node_tag *right;
}avl_node;

int max(int a, int b){
	return(a>b?a:b);
}

void updateheight(avl_node *temp){
	if(temp!=NULL)
		temp->height = max(temp->left==NULL?-1:temp->left->height,temp->right==NULL?-1:temp->right->height)+1;
}

void left_rotate(avl_node **rootptr){
	avl_node *temp1;

	if(rootptr!=NULL && *rootptr!=NULL && (*rootptr)->right!=NULL){
		temp1 = (*rootptr)->right;
		
		(*rootptr)->right = temp1->left;
		if(temp1->left!=NULL)
			temp1->left->parent = (*rootptr);

		temp1->parent = (*rootptr)->parent;

		(*rootptr)->parent = temp1;
		temp1->left = (*rootptr);

		if(temp1->parent != NULL){
			if(temp1->parent->right == *rootptr)
				temp1->parent->right = temp1;
			else
				temp1->parent->left = temp1;
		}

		*rootptr = temp1;

		updateheight(temp1->left);
		updateheight(temp1);

	}

}

void right_rotate(avl_node **rootptr){
	avl_node *temp1;

	if(rootptr!=NULL && *rootptr!=NULL && (*rootptr)->left!=NULL){
		temp1 = (*rootptr)->left;

		(*rootptr)->left = temp1->right;
		if(temp1->right!=NULL)
			temp1->right->parent = (*rootptr);

		temp1->parent = (*rootptr)->parent;

		(*rootptr)->parent = temp1;
		temp1->right = (*rootptr);

		if(temp1->parent != NULL){
			if(temp1->parent->left == *rootptr)
				temp1->parent->left = temp1;
			else
				temp1->parent->right = temp1;
		}

		*rootptr = temp1;

		updateheight(temp1->right);
		updateheight(temp1);

	}

}

void insert_fixup(avl_node **rootptr, avl_node *temp){
	int current = BALANCED, previous, lh, rh;

	while(temp!=NULL){
		lh= (temp->left==NULL?-1:temp->left->height);
		rh= (temp->right==NULL?-1:temp->right->height);

		previous=current;
		current = (lh==rh?BALANCED:(lh>rh?LEFT_LEANING:RIGHT_LEANING));

        if(lh - rh < -1) {
            if(temp->right->x > temp->x) {
                right_rotate(&(temp->right));
                left_rotate(&temp);
            }
            else {
                left_rotate(&temp);
            }
        }
        else if(lh - rh > 1) {
            if(temp->left->x > temp->x) {
                right_rotate(&temp);
            }
            else {
                left_rotate(&(temp->left));
                right_rotate(&temp);
            }
        }

		updateheight(temp);

		if(temp->parent==NULL)
			*rootptr = temp;

		temp =temp->parent;
	}
}

void insert_node(avl_node **rootptr, avl_node *temp){
	if(*rootptr==NULL) *rootptr = temp;
	else{
		temp->parent = *rootptr;

		if((*rootptr)->x < temp->x)
			insert_node(&((*rootptr)->right),temp);
		else
			insert_node(&((*rootptr)->left),temp);
	}
}

void insert_value(avl_node **rootptr, int x){
	avl_node *temp;
	temp = (avl_node *)malloc(sizeof(avl_node));
	temp->x = x;
	temp->height = 0;
	temp->parent = temp ->left = temp->right = NULL;
	insert_node(rootptr, temp);
	insert_fixup(rootptr, temp);
}

void view(avl_node *root, int tabs){
	int i;
	if(root != NULL){
		view(root->right, tabs +1);
		for(i=0; i<tabs; i++) printf("\t");
		printf("%2i\n", root->x);
		view(root->left, tabs +1);
	}
    else if(root == NULL && tabs == 0) {
        printf("Empty tree\n");
    }
}

void swap(int *a, int *b){
	int temp;
	temp = *a; *a= *b; *b =temp;
}

avl_node* find(avl_node **rootptr,int x){
	if(*rootptr == NULL){
		return NULL;
	}
	if(x == (*rootptr)-> x){
		return *rootptr;
	}
	if(x < (*rootptr)-> x){
		return find(&((*rootptr)->left),x);
	}
	else{
		return find(&((*rootptr)->right),x);
	}
}

avl_node* find_min(avl_node **rootptr){
	if(*rootptr == NULL){ //empty tree case
		return NULL;
	}
	if((*rootptr)->left == NULL){ //leaf node
		return *rootptr;
	}
	else{
		find_min(&((*rootptr)->left));
	}
}

void delete_value(avl_node **rootptr, int num){
	avl_node *temp = find(rootptr,num);
	avl_node *child = NULL,*parent = NULL;
    

	if(temp != NULL){
		//leaf node case
		if(temp->left == NULL && temp->right == NULL){
			parent = temp->parent;

			//empty tree case
			if(temp->parent == NULL){
				*rootptr = NULL;
			}            

			//set parent's connection NULL
			if(parent != NULL){
				if(parent->left == temp){
					parent->left = NULL;
				}
				else if(parent->right == temp){
					parent->right = NULL;
				}
			}

			//free
			free(temp);
		}
		//one child case
		else if((temp->left == NULL && temp->right != NULL) || (temp->left != NULL && temp->right == NULL)){
			if(temp->left != NULL){
				child = temp->left;
			}
			else if(temp->right != NULL){
				child = temp->right;
			}

			parent = temp->parent;

			child->parent = temp->parent; //set child's parent pointer to temp's parent

			//set parent's child pointer to child
			if(parent != NULL){
				if(parent->left == temp){
					parent->left = child;
				}
				else if(parent->right == temp){
					parent->right = child;
				}
			}
			else{
				*rootptr = child;
			}

			free(temp);
		}
		//two children case
		else{
			avl_node *successor = find_min(&(temp->right));
			temp->x = successor->x;
			delete_value(&successor,successor->x);
		}

		insert_fixup(rootptr,temp->parent);
	}
	else{
		printf("No value %i found.\n",num);
	}
}

int main(){
	avl_node *root = NULL;
	int i,n=10;

	for(i=0; i<n; i++){
		insert_value(&root,i+1);
		view(root,0);
		printf("\n----------------------------------------\n");
	}
	insert_value(&root,0);	
	view(root,0);
		printf("\n========================================\n");

    // start delete

    for(i = 0; i < n; i++) {
        printf("Delete %i\n", i);
        delete_value(&root,i);	    
        view(root,0);
        printf("\n----------------------------------------\n");
    }
    printf("Delete %i\n", 10);
    delete_value(&root,10);	    
    view(root,0);
	
}
