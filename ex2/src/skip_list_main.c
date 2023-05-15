#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

#include "skip_list.h"

#define ROW_LENGTH 1024

/**
 * @brief Apertura di un file e ritorna un puntatore ad esso
 * 
 * @param file_name nome del file da aprire
 * @param mode modalità di apertura del file
 * @return FILE* puntatore al file
 */
FILE *open_file(const char *file_name, char *mode);

/**
 * @brief Legge il file riga per riga ed inserisce ciascuna linea
 *        all'interno della SkipList
 * 
 * @param list la SkipList in cui inserire le righe
 * @param input_file file da cui leggere le righe
 */
void read_file_input(struct SkipList *list, FILE *input_file);

/**
 * @brief Legge un file e controlla se tutte le parole sono
 *        all'interno della SkipList. Se non sono all'interno 
 *        vengono stampate
 * 
 * @param list la SkipList in cui cercare
 * @param input_file il file in cui cercare le parole
 */
void search_from_file(struct SkipList *list, FILE *input_file);

/**
 * @brief Confronta due stringhe, ignorando maiuscole e minuscole
 * 
 * @param value1 primo valore da confrontare
 * @param value2 secondo valore da confrontare
 * @return int la differenza tra le due stringhe
 */
int compar_string(const void *value1, const void *value2);

/**
 * @brief Controlla se l'utente ha inserito il numero corretto
 *        di elementi
 * 
 * @param argc numero di argomenti passati al programma
 * @param argv argomenti
 * @return int 0 argomenti non corretti, 1 OK
 */
int check_args(int argc, char const *argv[]);

/**
 * @brief Determina la lista di parole nel testo da correggere non presenti nel dizionario 
 * 
 * @param dictfile percorso contenente le parole del dizionario
 * @param textfile percorso contenente il testo da coreggere
 * @param max_height parametro della SkipList
 */
void find_errors(const char *dictfile, const char *textfile, size_t max_height);



int main(int argc, char const *argv[]){
    if (check_args(argc, argv) == 0) {
        return EXIT_FAILURE;
    }

    const char *input_file_dictionary = (char *)argv[1];
    const char *input_file_correctme = (char *)argv[2];
    int param_max_height;
    char *p_max_height; 
    //argv[] è un puntatore a stringhe esecuzione della conversione da stringa a intero con utilizzo della funzione strtol
    long convert_param_max_height = strtol(argv[3], &p_max_height, 10);
    param_max_height = convert_param_max_height;
    find_errors(input_file_dictionary, input_file_correctme, param_max_height);
    printf("\nEsecuzione avvenuta con successo\n");

    return EXIT_SUCCESS;
}

void find_errors(const char *dictfile, const char *textfile, size_t max_height) {
    struct SkipList *list = NULL;

    //Lettura del dizionario
    printf("\nCreation and insertion in SkipList...\n");
    clock_t time_start_read_dictionary = clock();
    FILE *dictionary_file = open_file(dictfile, "r");
    new_skiplist(&list, max_height, compar_string);
    read_file_input(list, dictionary_file);
    fclose(dictionary_file);
    clock_t time_end_read_dictionary = clock();
    printf("Insertion file end. Time lapsed: %fs\n", (double)(time_end_read_dictionary - time_start_read_dictionary) / CLOCKS_PER_SEC);

    //Lettura del file da correggere
    printf("\nSearching in SkipList...\n");
    clock_t time_start_read_correctme = clock();
    FILE *correctme_file = open_file(textfile, "r");
    search_from_file(list, correctme_file);
    fclose(correctme_file);
    clock_t time_end_read_correctme = clock();
    printf("Searching file end. Time lapsed: %fs\n", (double)(time_end_read_correctme - time_start_read_correctme) / CLOCKS_PER_SEC);

    clear_skiplist(&list);
}


FILE *open_file(const char *file_name, char *mode) {
    FILE *file = fopen(file_name, mode);
    if(file == NULL) {
        printf("Errore nell'apertura del file\n");
        exit(EXIT_FAILURE);
    }
    return file;
}

void read_file_input(struct SkipList *list, FILE *input_file) {
    char *input = (char *)malloc(ROW_LENGTH);
    char *insert_list;
    while(fscanf(input_file, "%s", input) != EOF) {
        insert_list = strcpy((char *) malloc(strlen(input) + 1), input);
        insert_skiplist(list, insert_list);
    }
}

void buf_flush(char *buf, int size){
    for(int i = 0; i < size; i++) {
        buf[i]='\0';
    }
}

void search_from_file(struct SkipList *list, FILE *input_file) {
    char c;
    char *buffer = malloc(ROW_LENGTH);
    int i = 0;
    int flag = 0;
    while ((c = fgetc(input_file)) != EOF) {
        if (isalpha(c) > 0) {
            // Aggiungo il carattere al buffer
            buffer[i] = c;
            i++;
            flag = 1;
        } else if(flag){
            buffer[i] = '\0';  // Termina il buffer con il carattere di fine stringa '\0'
            if(search_skiplist(list, buffer) == NULL) {
                printf("%s\n", buffer);
            }
            i = 0;  // Reimposta l'indice del buffer a 0 
            flag = 0;
        }
    }
    free(buffer);  // Dealloca la memoria del buffer
}


int check_args(int argc, char const *argv[]) {
  if (strcmp(argv[1], "--help") == 0) {
    printf(
        "Usage: main_ex2 [dictfile] [textfile] [max_height] "
        "[type]\n\n");
    return 0;
  }
  return 1;
}

int compar_string(const void *value1, const void *value2) {
    return strcasecmp((char*)value1, (char*)value2);
}