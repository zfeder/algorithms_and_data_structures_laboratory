#ifndef SORTED_RECORD_H
#define SORTED_RECORD_H
#include <stdlib.h>


/**
 * @brief Algoritmo ibrido che combina Merge Sort e BinaryInsertion Sort
 * 
 * @param base è un puntatore al primo elemento dell'array
 * @param nitems è il numero di elemento dell'array
 * @param size è la dimensione in bytes di ogni elemento
 * @param k è un parametro dell'algoritmo
 * @param compar è il criterio secondo cui ordinare i dati
 */
void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void *, const void *));

/**
 * @brief Algoritmo di ricerca per trovare la posizione di un elemento in un array ordinato
 * 
 * @param a 
 * @param item 
 * @param min 
 * @param max 
 * @param size 
 * @param compar 
 * 
 * @return int 
 */
int binary_search(void *a, void *item, int min, int max, size_t size, int (*compar)(const void *, const void *));

/**
 * @brief Accesso ai dati puntati da *base
 * 
 * @param base 
 * @param nitems 
 * @param size 
 * @param compar 
 */
void insertion_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *));

/**
 * @brief 
 * 
 * @param base 
 * @param left 
 * @param mid 
 * @param right 
 * @param size 
 * @param compar 
 */
void merge(void *base, int left, int mid, int right, size_t size, int (*compar)(const void *, const void *));

/**
 * @brief 
 * 
 * @param base 
 * @param left 
 * @param nitems 
 * @param size 
 * @param compar 
 */
void merge_sort(void* base, int left, size_t nitems, size_t size, int (*compar)(const void *, const void *));

/**
 * @brief 
 * 
 * @param base 
 * @param left 
 * @param nitems 
 * @param size 
 * @param k 
 * @param compar 
 */
void merge_sort_insertion(void* base, int left, size_t nitems, size_t size, size_t k ,int (*compar)(const void *, const void *));




#endif


