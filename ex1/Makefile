CC = gcc
CFLAGS =  -Wall 
INCLUDES = src/*.h
COMMON_DEPS = $(INCLUDES) Makefile
infile = ./records.csv
outfile = ./sort_record.csv
k = 32
field = 1

all: sorted_record sorted_record_main sorted_record_test
tester: sorted_record sorted_record_test 
	
sorted_record: src/sorted_record.c
			$(CC) $(CFLAGS) -c src/sorted_record.c -o bin/sorted_record.o

sorted_record_main: src/sorted_record_main.c
			$(CC) $(CFLAGS) src/sorted_record_main.c bin/sorted_record.o -o build/main_ex1

sorted_record_test: src/sorted_record_test.c 
			$(CC) $(CFLAGS) src/sorted_record.c src/sorted_record_test.c src/unity.c -o build/main_ex1_test
			
test:
	make tester
	build/main_ex1_test

run:
	./build/main_ex1 $(infile) $(outfile) $(k) $(field)

clear:
		rm ./build/*





