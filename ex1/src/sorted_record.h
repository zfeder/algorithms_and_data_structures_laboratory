#ifndef SORTED_RECORD_H
#define SORTED_RECORD_H
#include <stdlib.h>

void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void *, const void *));

int binary_search(void *a, void *item, int min, int max, int size, int (*compar)(const void *, const void *));

void merge_sort(void *base, int left, int mid, int right, size_t size, int (*compar)(const void *, const void *));

void insertion_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *));




#endif