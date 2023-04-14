CC = gcc
CFLAGS = -g -Wall -Wextra -Wpedantic -Wconversion -Wpointer-arith
INCLUDES = src/*.h
COMMON_DEPS = $(INCLUDES) Makefile

all: sorted_record sorted_record_main
	
sorted_record: src/sorted_record.c
			$(CC) $(CFLAGS) -c src/sorted_record.c -o build/sorted_record.o

sorted_record_main: src/sorted_record_main.c
			$(CC) $(CFLAGS) -c src/sorted_record_main.c build/sorted_record.o -o build/main_ex1.o



