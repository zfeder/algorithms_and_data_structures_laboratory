#include <stdlib.h>
#include <string.h>

#include "skip_list.h"
 

 int random_level() {
  int level = 1;
  double temp = (double)rand() / (double)RAND_MAX;
  while (temp < 0.5 && level < MAX_HEIGHT) {
    level += 1;
    temp = (double)rand() / (double)RAND_MAX;
  }
  return level;
}

void new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void *, const void*)) {
    struct SkipList *new_skip_list = (struct SkipList *) malloc(sizeof(struct SkipList));
    *list = new_skip_list;
    new_skip_list->max_level = 0;
    new_skip_list->head = malloc(sizeof(struct Node));
    new_skip_list->compare = compar;
    new_skip_list->max_height = max_height;
    new_skip_list->head->next = calloc(sizeof(struct Node), max_height);
    printf("Successo: SkipList creata\n");
}

void clear_skiplist(struct SkipList **list) {
    if(list == NULL || *list == NULL) 
        return;
    if((*list)->head != NULL) {
        if(((*list)->head->item) != NULL) {
            free((*list)->head->item);
        }
    }
    free(*list);
    *list = NULL;
}



struct Node *createNode(void *item, int level) {
    struct Node *new_node = malloc(sizeof(struct Node));
    new_node->item = malloc(sizeof(char *));
    memcpy(new_node->item, item, sizeof(char *));
    new_node->next = calloc(sizeof(struct Node), level);
    new_node->size = level;
    return new_node;
}



