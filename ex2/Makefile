CC = gcc
CFLAGS =  -Wall 
INCLUDES = src/*.h
COMMON_DEPS = $(INCLUDES) Makefile
dictfile = ./dictionary.txt
textfile = ./correctme.txt
max_height = 10


all: skip_list skip_list_main
tester: skip_list skip_list_test
	
skip_list: src/skip_list.c
			$(CC) $(CFLAGS) -c src/skip_list.c -o bin/skip_list.o

skip_list_main: src/skip_list_main.c
			$(CC) $(CFLAGS) src/skip_list_main.c bin/skip_list.o -o build/main_ex2

skip_list_test: src/skip_list_test.c
			$(CC) $(CFLAGS) src/skip_list.c src/skip_list_test.c src/unity.c -o build/main_ex2_test			
		
test:
	make tester
	build/main_ex2_test

run:
	./build/main_ex2 $(dictfile) $(textfile) $(max_height)


clear:
		rm ./build/*





