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
    struct SkipList *new_skip_list = (struct SkipList *) malloc(sizeof(struct SkipList));
    *list = new_skip_list;
    new_skip_list->max_level = 0;
    new_skip_list->heads = malloc(sizeof(struct Node));
    new_skip_list->compare = compar;
    new_skip_list->max_height = max_height;
    new_skip_list->heads->next = calloc(sizeof(struct Node), max_height);
}

void clear_skiplist(struct SkipList **list) {
    if(list == NULL || *list == NULL) 
        return;
    if((*list)->heads != NULL) {
        if(((*list)->heads->item) != NULL) {
            free((*list)->heads->item);
        }
    }
    free(*list);
    *list = NULL;
}

void *createNode(void *item, int level) {
    struct Node *new_node = malloc(sizeof(struct Node));
    new_node->item = malloc(sizeof(char *));
    memcpy(new_node->item, item, sizeof(char *));
    new_node->next = calloc(sizeof(struct Node), level);
    new_node->size = level;
    return (void *)new_node;
}

void insert_skiplist(struct SkipList *list, void *item) {
    struct Node *new = createNode(item, random_level(list->max_height));
    if(new->size > list->max_level) {
        list->max_level = new->size;
    }
    struct Node *x = list->heads;
    for(int k = list->max_level - 1; k >= 0; k--) {
        if(x->next[k] == NULL || list->compare(item, x->next[k]->item) < 0) {
            if(k < new->size) {
                new->next[k] = x->next[k];
                x->next[k] = new;
            }
        }
        else {
            x = x->next[k];
            k++;
        }
    }
}

const void* search_skiplist(struct SkipList *list, void *item) {
    struct Node *x = list->heads;
    for(int i = list->max_level; i >= 0; i--) {
        while(x->next[i] != NULL && list->compare(x->next[i]->item, item) < 0) {
            // elemento più grande avanzo nella lista
            x = x->next[i];
        }
    }
    if(x->next[0] != NULL) {
        x = x->next[0];
        if(list->compare(x->item, item) == 0) {
            return x->item;
        }
    }
    return NULL;
}
