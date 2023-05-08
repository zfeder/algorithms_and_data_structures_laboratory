#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sorted_record.h"

void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void *, const void *)) {
    // in base al parametro in input k si decide secondo quale algoritmo ordinare il vettore
    merge_sort(base, nitems, size, k, compar);
        
}

int binary_search(void *a, void *item, int low, int high, size_t size, int (*compar)(const void *, const void *)) {
    if (high <= low)
        return compar(item, a + low * size) > 0 ? low + 1 : low;
    int mid = (low + high) / 2;
    int item_compar = compar(item, a + mid * size);
    if (item_compar == 0) 
        return mid + 1;
    if (item_compar > 0)
        return binary_search(a, item, mid + 1, high, size, compar);
    return binary_search(a, item, low, mid - 1, size, compar);

}

void insertion_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *)) {
    void *selected = malloc(size);
    int i;
    for(i = 1; i < nitems; i++) {
        int j = i - 1;
        memcpy(selected, base + i * size, size);
        int index = binary_search(base, selected, 0, j, size, compar);
        while(j >= index) {
            memcpy(base + (j + 1) * size, base + j * size, size);
            j--;
        }
        memcpy(base + (j + 1) * size, selected, size);
    }
    free(selected);
}

void merge(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *)) {
    long i = 0;
    size_t mid = nitems/2;
    long j = mid;
    long k = 0;
    void *selected = calloc(nitems, size);

    while(i < mid && j < nitems){
        if((*compar)(base + (i * size), base + (j * size)) <= 0){
            memcpy(selected + (k * size), base + (i * size), size);
            i++;
        }else{
            memcpy(selected + (k * size), base + (j * size), size);
            j++;
        }
        k++;
    }

    while (i < mid) {
        memcpy(selected + (k * size), base + (i * size), size);
        i++;
        k++;
    }

    while (j < nitems) {
        memcpy(selected + (k * size), base + (j * size), size);
        j++;
        k++;
    }
    
    for(k = 0; k < nitems; k++) {
        memcpy(base + (k * size), selected + (k * size), size);
    }

    free(selected);
}

void merge_sort(void* base, size_t nitems, size_t size, size_t k, int (*compar)(const void *, const void *)) {
    if(nitems <= k) {
        insertion_sort(base, nitems, size, compar);
    }
    else if(nitems > 1) {
        long mid = nitems/2;
        merge_sort(base, mid, size, k, compar);
        merge_sort(base + ((mid) * size), nitems - mid, size, k, compar);
        merge(base, nitems, size, compar);
    }
} 