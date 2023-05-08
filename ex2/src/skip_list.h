#include <stdio.h>
#ifndef _SKIP_LIST_H
#define _SKIP_LIST_H

#define MAX_HEIGHT 21


struct SkipList;
struct Node;

/**
 * @brief  La SkipList è un tipo di lista concatenata che memorizza una lista ordinata di elementi
 * 
 * @param head è il primo nodo della SkipList
 * @param max_level è il numero massimo di puntatori che al momento ci sono in un singolo nodo della SkipList
 * @param max_height è una costante che definisce il massimo numero di puntatori che possono esserci in un singolo nodo della SkipList
 * @param compare è il criterio secondo cui ordinare i dati
 */
struct SkipList {
  struct Node *head;
  size_t max_level;
  size_t max_height;
  int (*compare)(const void*, const void*);
};

/**
 * @brief Ogni Node contiene un puntatore all'elemento successivo nella lista
 * 
 * @param next è l'array di puntatori in un dato nodo della SkipList
 * @param size è il mumero di puntatori in un dato nodo della SkipList 
 * @param item è il dato memorizzato in un dato nodo della SkipList
 */
struct Node {
  struct Node **next;
  size_t size;
  void *item;
};

/**
 * @brief 
 * 
 * @param list 
 * @param max_height 
 * @param compar 
 */
void new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void *, const void*));

/**
 * @brief 
 * 
 * @param list 
 */
void clear_skiplist(struct SkipList **list);

/**
 * @brief 
 * 
 * @param list 
 * @param item 
 */
void insert_skiplist(struct SkipList *list, void *item);

/**
 * @brief 
 * 
 * @param list 
 * @param item 
 * @return const void* 
 */
const void* search_skiplist(struct SkipList *list, void *item);


#endif