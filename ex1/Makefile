CC = gcc
CFLAGS = -g 
INCLUDES = src/*.h
COMMON_DEPS = $(INCLUDES) Makefile

all: sorted_record sorted_record_main
tester: sorted_record sorted_record_test 
	
sorted_record: src/sorted_record.c
			$(CC) $(CFLAGS) -c src/sorted_record.c -o build/sorted_record.o

sorted_record_main: src/sorted_record_main.c
			$(CC) $(CFLAGS) src/sorted_record_main.c build/sorted_record.o -o build/main_ex1

sorted_record_test: src/sorted_record_test.c 
			$(CC) $(CFLAGS) src/sorted_record.c src/sorted_record_test.c src/unity.c -o build/sort_unity_test

test:
	make tester
	build/sort_unity_test

run:
	make all
	build/main_ex1

clear:
		rm build/*.o
		rm build/main_ex1





