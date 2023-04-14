#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sorted_record.h"


void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void *, const void *)) {
    // in vase al parametro in input k si decide secondo quale algoritmo ordinare il vettore
    /*if(nitems <= k) {
        insertion_sort(base, nitems, size, compar);
    }*/
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
    for(int i = 1; i < nitems; i++) {
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

void merge_sort(void *base, int left, int mid, int right, size_t size, int (*compar)(const void *, const void *)) {
    int len1 = mid - left + 1;
    int len2 = right - mid;

    char leftArr[len1 * size];
    char rightArr[len2 * size];
    char *arr = (char *)base;

    for(int i = 0; i < len1; i++){
        memcpy(leftArr + i * size, arr + (left + i) * size, size);
    }   
    for(int i = 0; i < len2; i++){
        memcpy(rightArr + i * size, arr + (mid + 1 + i) * size, size);
    }   
    
    int i, j, k;
    i = 0;
    j = 0;
    k = left;

    while(i < len1 && j < len2){
        if(compar(leftArr + i * size, rightArr + j * size) <= 0){
            memcpy(arr + k * size, leftArr + i * size, size);
            i++;
        }else{
            memcpy(arr + k * size, rightArr + j * size, size);
            j++;
        }
        k++;
    }

    while (i < len1) {
        memcpy(arr + k * size, leftArr + i * size, size);
        i++;
        k++;
    }

    while (j < len2) {
        memcpy(arr + k * size, rightArr + j * size, size);
        j++;
        k++;
    }

}





