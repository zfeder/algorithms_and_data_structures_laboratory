#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdbool.h>

#include "skip_list.h"

#define ROW_LENGTH 1024

FILE *open_file(char *file_name, char *mode);
void read_file_input(struct SkipList *list, FILE *input_file);
int compar_string(const void *value1, const void *value2);
void search_from_file(struct SkipList *list, FILE *input_file);



int main(){
    struct SkipList *list = NULL;;

    //char *input_dictionary = "dictionary.txt";

    FILE *dictionary_file = fopen("dictionary.txt", "r+");

    new_skiplist(&list,10,compar_string);

    printf("Start insert in skiplist\n");
    read_file_input(list, dictionary_file);
    fclose(dictionary_file);
    printf("Finish insert in skiplist\n");

    FILE *correctme_file = fopen("correctme.txt", "r");
    printf("Search SkipList\n");
    search_from_file(list, correctme_file);
    fclose(correctme_file);




}

FILE *open_file(char *file_name, char *mode) {
    FILE *file = fopen(file_name, mode);
    if(file == NULL) {
        printf("Errore nell'apertura del file\n");
        exit(EXIT_FAILURE);
    }
    return file;
}



/**
 * @brief Confronta due stringhe senza Case Sensitivity
 * 
 * @param value1 Primo valore
 * @param value2 Secondo valore
 * @return int 
 */
int compar_string(const void *value1, const void *value2) {
    return strcasecmp((char*)value1, (char*)value2);
}