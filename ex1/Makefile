CC = gcc
CFLAGS = -g -Wall -Wextra -Wpedantic -Wconversion
INCLUDES = src/*.h
COMMON_DEPS = $(INCLUDES) Makefile

all: bin/ordered_array bin/ordered_array_tests

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

