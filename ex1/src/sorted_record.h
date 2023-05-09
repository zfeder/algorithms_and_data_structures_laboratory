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
 * @brief Algoritmo di ricerca per trovare la posizione di un elemento in un array
 * 
 * @param a puntatore al primo elemento
 * @param item elemento da ricercare
 * @param min valore minimo 
 * @param max valore massimo
 * @param size è la dimensione in bytes di ogni elemento
 * @param compar è il criterio secondo cui ordinare i dati
 * 
 * @return int 
 */
int binary_search(void *a, void *item, int min, int max, size_t size, int (*compar)(const void *, const void *));

/**
 * @brief Algoritmo di ordinamento, dove per ciascuno elemento j-esimo, l'algoritmo lo confronta con gli elementi precedenti  
 *        tramite una struttura ciclica e se trova un elemento più grande lo sposta in avanti, altrimenti se l'elemento 
 *         è minore o uguale termina il ciclo più interno. L'algoritmo termina l'esecuzione quando termina il ciclo esterno
 * 
 * @param base è un puntatore al primo elemento dell'array
 * @param nitems è il numero di elemento dell'array 
 * @param size è la dimensione in bytes di ogni elemento 
 * @param compar è il criterio secondo cui ordinare i dati
 */
void insertion_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *));

/**
 * @brief Algoritmo che si occupa di unire le due parti dell'array ordinate tra loro
 * 
 * @param base è un puntatore al primo elemento dell'array
 * @param nitems è il numero di elemento dell'array
 * @param size è la dimensione in bytes di ogni elemento
 * @param compar è il criterio secondo cui ordinare i dati
 */
void merge(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *));

/**
 * @brief Algoritmo di ordinamento basato sulla ricorsione e sull'approccio divide et impera
 * 
 * @param base è un puntatore al primo elemento dell'array
 * @param nitems è il numero di elemento dell'array
 * @param size è la dimensione in bytes di ogni elemento
 * @param k è un parametro dell'algoritmo
 * @param compar è il criterio secondo cui ordinare i dati
 */
void merge_sort(void* base, size_t nitems, size_t size, size_t k ,int (*compar)(const void *, const void *));



#endif

