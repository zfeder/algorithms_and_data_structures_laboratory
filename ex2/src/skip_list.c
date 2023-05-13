#include <stdlib.h>
#include <string.h>

#include "skip_list.h"
 
int random_level(size_t max_height) {
    int level = 1;
    double temp = (double)rand() / (double)RAND_MAX;
    while (temp < 0.5 && level < max_height) { 
        level += 1;
        temp = (double)rand() / (double)RAND_MAX;
    }
    return level;
}

void new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void *, const void*)) {
    /*
    struct SkipList *new_skip_list = (struct SkipList *) malloc(sizeof(struct SkipList));
    *list = new_skip_list;
    new_skip_list->max_level = 0;
    new_skip_list->heads = malloc(sizeof(struct Node **));
    new_skip_list->heads = (struct Node **)calloc(max_height, sizeof(struct Node *));
    new_skip_list->compare = compar;
    new_skip_list->max_height = max_height;
    printf("skiplist creata\n");
    */
    //new_skip_list->heads[0]->next = calloc(sizeof(struct Node), max_height);
    *list = (struct SkipList *)calloc(1, sizeof(struct SkipList));
    (*list)->heads = (struct Node **)calloc(max_height, sizeof(struct Node *));
    (*list)->max_height = max_height;
    (*list)->compare = compar;
    (*list)->max_level = 0;
}

void clear_skiplist(struct SkipList **list) {
    if(list == NULL || *list == NULL) 
        return;
    if((*list)->heads != NULL) {
        if(((*list)->heads[0]->item) != NULL) {
            free((*list)->heads[0]->item);
        }
    }
    free(*list);
    *list = NULL;
}

void *createNode(void *item, int level) {
    struct Node *new = (struct Node *)calloc(1, sizeof(struct Node));
    new->item = malloc(sizeof(char *));
    memcpy(new->item, item, sizeof(char *));
    new->next = (struct Node **)calloc(level, sizeof(struct Node *));
    new->size = level;
    return (void *)new;
}

void insert_skiplist(struct SkipList *list, void *item) {
    struct Node *new = createNode(item, random_level(list->max_height));
    if(new->size > list->max_level) {
        list->max_level = new->size;
    }
    struct Node **x = list->heads;
    for(size_t k = list->max_level; k > 0; k--) {
        if(x[k - 1] == NULL || list->compare(item, x[k - 1]->item) < 0) {
            if(k - 1 < new->size) {
                new->next[k - 1] = x[k - 1];
                x[k - 1] = new;
            }
        }
        else {
            x = x[k - 1]->next;
            k++;
        }
    }
}

const void* search_skiplist(struct SkipList *list, void *item) {
    struct Node **x = list->heads;
    for(size_t i = list->max_level; i > 0; i--) {
        while(x[i-1] != NULL && list->compare(x[i-1]->item, item) < 0) {
            // elemento più grande avanzo nella lista
            x = x[i-1]->next;
        }
    }
    if(x[0] != NULL && list->compare(x[0]->item, item) == 0) {
        return x[0]->item;
    }
    return NULL;
}
