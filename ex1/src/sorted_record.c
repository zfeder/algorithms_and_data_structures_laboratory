#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sorted_record.h"

void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void *, const void *)) {
    // in vase al parametro in input k si decide secondo quale algoritmo ordinare il vettore
    if(nitems <= k) {
        insertion_sort(base, nitems, size, compar);
    } else {
        merge_sort_insertion(base, 0, nitems, size, k, compar);
    }
}

int binary_search(void *a, void *item, int low, int high, size_t size, int (*compar)(const void *, const void *)) {
    if (high <= low)
        return compar(item, a + low * size) > 0 ? low + 1 : low;
    int mid = (low + high) / 2;
    if (compar(item, a + mid * size) == 0) 
        return mid + 1;
    if (compar(item, a + mid * size) > 0)
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

void merge(void *base, int left, int mid, int right, size_t size, int (*compar)(const void *, const void *)) {
    int len1 = mid - left + 1;
    int len2 = right - mid;
    // Creazione di array temporanei 
    // Definire una malloc per allocare i due vettori
    void *left_array = malloc(len1 * size);
    void *right_array = malloc(len2 * size);

    //Copia dei dati in array temporanei 
    for(int i = 0; i < len1; i++){
        memcpy(left_array + i * size, base + (left + i) * size, size);
    }   
    
    for(int i = 0; i < len2; i++){
        memcpy(right_array + i * size, base + (mid + 1 + i) * size, size);
    }
         
    //Unione degli array temporanei
    int i, j, k;
    i = 0;
    j = 0;
    k = left;

    while(i < len1 && j < len2){
        if(compar(left_array + i * size, right_array + j * size) <= 0){
            memcpy(base + k * size, left_array + i * size, size);
            i++;
        }else{
            memcpy(base + k * size, right_array + j * size, size);
            j++;
        }
        k++;
    }
    while (i < len1) {
        memcpy(base + k * size, left_array + i * size, size);
        i++;
        k++;
    }
    while (j < len2) {
        memcpy(base + k * size, right_array + j * size, size);
        j++;
        k++;
    }
    free(left_array);
    free(right_array);
}

void merge_sort(void* base, int left, size_t nitems, size_t size, int (*compar)(const void *, const void *)) {
     if(left < nitems) {
        int mid = left + (nitems - left)/2;
        merge_sort(base, left, mid, size, compar);
        merge_sort(base, mid + 1, nitems, size, compar);
        merge(base, left, mid, nitems, size, compar);
    } 
}

void merge_sort_insertion(void* base, int left, size_t nitems, size_t size, size_t k, int (*compar)(const void *, const void *)) {
        int mid = left + (nitems - left)/2;
        if(nitems <= k) {
            insertion_sort(base, nitems, size, compar);
        } 
        if(left < nitems) {
            merge_sort_insertion(base, left, mid, size, k, compar);
            merge_sort_insertion(base, mid + 1, nitems, size, k, compar);
            merge(base, left, mid, nitems, size, compar);
        }         
}











