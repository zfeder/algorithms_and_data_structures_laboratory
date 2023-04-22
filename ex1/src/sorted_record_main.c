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
FILE *open_file(const char *file_name, char *mode);

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
int compar_value_char(const void *value1, const void *value2);

/**
 * @brief Confronta il campo int di due record
 * 
 * @param value1 Primo valore da confrontare
 * @param value2 Secondo valore da confrontare
 * @return int 
 */
int compar_value_int(const void *value1, const void *value2);

/**
 * @brief Confronta il campo float di due record
 * 
 * @param value1 Primo valore da confrontare
 * @param value2 Secondo valore da confrontare
 * @return int 
 */
int compar_value_float(const void *value1, const void *value2);

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
 * @brief Libera la memoria allocata per l'array records
 * 
 * @param records Array records
 * @param rows Numero di righe di records all'interno del file 
 */
void free_memory(Record *records, int rows);

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

/**
 * @brief Scrive i records nel file
 * 
 * @param output_file File su cui scrivere
 * @param records Array di records da scrivere
 * @param rows Numero di righe nel file
 */
int *write_output_file(FILE *output_file, Record *records, int rows);


int main(int argc, char const *argv[]) {
    if (check_args(argc, argv) == 0) {
        return EXIT_FAILURE;
    }

    const char *input_file_name = (char *)argv[1];
    const char *output_file_name = (char *)argv[2];
    int param_k;
    char *p_k;
    /* 
        argv[] è un puntatore a stringhe esecuzione della conversione da stringa a intero
        con utilizzo della funzione strtol
    */
    long convert_param_k = strtol(argv[3], &p_k, 10);
    param_k = convert_param_k;

    int param_field;
    char *p_field;
    long convert_param_field = strtol(argv[4], &p_field, 10);
    param_field = convert_param_field;
    sort_records(input_file_name, output_file_name, param_k, param_field);
    printf("\nEsecuzione avvenuta con successo\n");
    
    return EXIT_SUCCESS;

}

void sort_records(const char *infile, const char *outfile, size_t k, size_t field) {
    //Lettura del file in input
    printf("\nReading file...\n");
    clock_t time_start_read_file = clock();
    FILE *input_file = open_file(infile, "r");
    int rows = 0;
    Record *records = read_file_input(input_file, &rows);
    fclose(input_file);
    clock_t time_end_read_file = clock();
    printf("Read file end. Time lapsed: %fs\n", (double)(time_end_read_file - time_start_read_file) / CLOCKS_PER_SEC);

    //Algoritmo di ordinamento 
    printf("\nSorting file...\n");
    clock_t time_start = clock();
    if(field == 1){
        merge_binary_insertion_sort((void *) records, rows - 1, sizeof(Record), k, compar_value_char);
    } else if(field == 2) {
        merge_binary_insertion_sort((void *) records, rows - 1, sizeof(Record), k, compar_value_int);
    } else if (field == 3) {
        merge_binary_insertion_sort((void *) records, rows - 1, sizeof(Record), k, compar_value_float);
    }
    clock_t time_end = clock();
    printf("Sorting end. Time lapsed: %fs\n", (double)(time_end - time_start) / CLOCKS_PER_SEC);

    // Scrittura del file in output
    printf("\nWriting file...\n");
    clock_t time_start_write_file = clock();
    FILE *output_file = open_file(outfile, "w");
    write_output_file(output_file, records, rows);
    fclose(output_file);
    clock_t time_end_write_file = clock();
    printf("Write file end. Time lapsed: %fs\n", (double)(time_end_write_file - time_start_write_file) / CLOCKS_PER_SEC);

    free_memory(records, rows);
 }

FILE *open_file(const char *file_name, char *mode) {
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
        records[*rows].value_float = strtof(split, NULL);
        *rows += 1;
    }
    free(buffer);
    return records;
}

int *write_output_file(FILE *output_file, Record *records, int rows) {
    for (int i = 0; i < rows; i++) {
        fprintf(output_file, "%d,%s,%d,%lf\n", records[i].id,
            records[i].value_char, records[i].value_int,
            records[i].value_float);
  }
  return 0;

}

int check_args(int argc, char const *argv[]) {
  if (strcmp(argv[1], "--help") == 0) {
    printf(
        "Usage: main_ex1 [input_file] [output_file] [k] [field] "
        "[type]\n\n");
    return 0;
  }
  return 1;
}

void free_memory(Record *records, int rows) {
    for(int i = 0; i < rows; i++) {
        free(records[i].value_char);
    }
    free(records);
}

int compar_value_char(const void *value1, const void *value2) {
    int res;
    res = strcmp(((Record *)value1)->value_char, ((Record *)value2)->value_char);
    if(res != 0) {
        return res;
    }
    return ((Record *)value1)->id - ((Record *)value2)->id;
}

int compar_value_int(const void *value1, const void *value2) {
    if(((Record *)value1)->value_int < ((Record *)value2)->value_int)
        return -1;
    else if(((Record *)value1)->value_int > ((Record *)value2)->value_int)
        return 1;
    else    
        return 0;
}

int compar_value_float(const void *value1, const void *value2) {
    if(((Record *)value1)->value_float < ((Record *)value2)->value_float)
        return -1;
    else if(((Record *)value1)->value_float > ((Record *)value2)->value_float)
        return 1;
    else    
        return 0;
}

