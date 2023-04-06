#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "sorted_record.h"

#define ROW_LENGHT 1024


/**
 * @brief Elementi usati da ordinare
 */
typedef struct _Record {
    int id;
    char *value_char;
    int value_int;
    float value_float;
} Record;

/**
 * @brief Apre un file e ritorna un puntatore ad esso
 * 
 * @param file_name Nome del file da aprire
 * @param mode Modalità di apertura r, w
 * @return FILE* Puntatore al file
 */
FILE *open_file_input(char *file_name, char *mode);

/**
 * @brief Legge in input un file CSV e ritorna un puntatore
 *        a un array di strutture Record
 * 
 * @param input_file File da leggere in input
 * @param rows Numero di righe del file
 * @return Record* Array di record
 */
Record *read_file_input(FILE *input_file, int *rows);

/**
 * @brief Confronta il campo char di due record
 * 
 * @param value1 Primo valore da confrontare
 * @param value2 Secondo valore da confrontare
 * @return int 
 */
int compar_value_char(void *value1, void *value2);

/**
 * @brief Confronta il campo int di due record
 * 
 * @param value1 Primo valore da confrontare
 * @param value2 Secondo valore da confrontare
 * @return int 
 */
int compar_value_int(void *value1, void *value2);

/**
 * @brief Confronta il campo float di due record
 * 
 * @param value1 Primo valore da confrontare
 * @param value2 Secondo valore da confrontare
 * @return int 
 */
int compar_value_float(void *value1, void *value2);

/**
 * @brief Controlla se l'utente ha inserito il 
 *        numero corretto di argomenti
 * 
 * @param argc Numero di argomenti passati al programma
 * @param argv Argomenti
 * @return int 0 argomenti non corretti, 1 OK
 */
int check_args(int argc, char const *argv[]);

/**
 * @brief Ordina gli elementi contenuti nel file CSV 
 *        secondo un criterio 
 * 
 * @param infile Percorso del file CSV
 * @param outfile Percorso nel quale salvare i record ordinati
 * @param k Paramentro dell'algoritmo
 * @param field Secondo quale dei 3 campi ordinare i record
 */
void sort_records(const char *infile, const char *outfile, size_t k, size_t field);


int main(int argc, char const *argv[]) {
    if(argc < 0) {
       return EXIT(EXIT_FAILURE);
    }
    char *input_file_name = (char *)argv[1];
    FILE *input_file = open_file_input(input_file_name, "r");
    printf("Reading file...\n");

    int rows = 0;
    Record *records = read_file_input(input_file, &rows);
    fclose(input_file);

    /* Algoritmo di ordinamento */

    printf("Write file...\n");

}

void sort_records(const char *infile, const char *outfile, size_t k, size_t field) {

}

FILE *open_file_input(char *file_name, char *mode) {
    FILE *file = fopen(file_name, mode);
    if(file == NULL) {
        printf("File non esiste\n");
        exit(EXIT_FAILURE);
    }
    return file;
}

Record *read_file_input(FILE *input_file, int *rows) {
    char *buffer = malloc(ROW_LENGHT);
    Record *records = malloc(sizeof(Record));

    while(fgets(buffer, ROW_LENGHT, input_file)) {
        records = realloc(records, sizeof(Record) * (*rows + 1));
        char *split = strtok(buffer, ",");
        records[*rows].id = atoi(split);

        split = strtok(NULL, ",");
        records[*rows].value_char = malloc(sizeof(split));
        strcpy(records[*rows].value_char, split);

        split = strtok(NULL, ",");
        records[*rows].value_int = atoi(split);

        split = strtok(NULL, ",");
        records[*rows].value_float = strtod(split, NULL);
        *rows += 1;
    }
    free(buffer);

    return records;
}

int compar_value_char(void *value1, void *value2) {
    int res;
    res = strcmp(((Record *)value1)->value_char, ((Record *)value2)->value_char);
    if(res != 0) {
        return res;
    }
    return ((Record *)value1)->id - ((Record *)value2)->id;
}

int compar_value_int(void *value1, void *value2) {
    if(((Record *)value1)->value_int < ((Record *)value2)->value_int)
        return -1;
    else if(((Record *)value1)->value_int > ((Record *)value2)->value_int)
        return 1;
    else    
        return 0;
}

int compar_value_float(void *value1, void *value2) {
    if(((Record *)value1)->value_float < ((Record *)value2)->value_float)
        return -1;
    else if(((Record *)value1)->value_float > ((Record *)value2)->value_float)
        return 1;
    else    
        return 0;
}

