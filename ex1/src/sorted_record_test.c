#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "unity.h"
#include "unity_internals.h"
#include "sorted_record.h"

static int i1, i2, i3, i4, i5, i6, i7, i8, i9, i10;
static char c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;

void setUp(void) {
    i1 = 1;
    i2 = 2;
    i3 = 3;
    i4 = 4;
    i5 = 5;
    i6 = 6;
    i7 = 7;
    i8 = 8;
    i9 = 9;
    i10 = 10;

    c1 = 'a';
    c2 = 'b';
    c3 = 'c';
    c4 = 'd';
    c5 = 'e';
    c6 = 'f';
    c7 = 'g';
    c8 = 'h';
    c9 = 'i';
    c10 = 'l';
}

int compar_value_int_test(const void *p1, const void *p2) {
    if ((*(int *)p1) < (*(int *)p2))
        return -1;
    else if ((*(int *)p1) > (*(int *)p2))
        return 1;
    else
        return 0;
}

int compar_value_char_test(const void *p1, const void *p2) {
    return strcmp(((char *)p1), ((char *)p2));
}

// insertion_sort

static void test_one_value_int_insertion_sort() {
    int *input[] = {&i1};
    int *expected[] = {&i1};
    insertion_sort((void *)input, 1, sizeof(int), compar_value_int_test);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
}

static void test_ten_value_int_insertion_sort() {
    int input[10] = {i9, i7, i4, i8, i2, i6, i1, i3, i5, i10};
    int expected[10] = {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10};
    insertion_sort((void *)(&input[0]), 9, sizeof(int), compar_value_int_test);
    TEST_ASSERT_EQUAL_INT_ARRAY(expected, input, 10);
}

static void test_one_value_char_insertion_sort() {
    char *input[] = {&c1};
    char *expected[] = {&c1};
    insertion_sort((void *)input, 1, sizeof(char), compar_value_char_test);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
}

static void test_ten_value_char_insertion_sort() {
    char input[10] = {c9, c7, c4, c8, c2, c6, c1, c3, c5, c10};
    char expected[10] = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
    insertion_sort((void *)(&input[0]), 9, sizeof(char), compar_value_char_test);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 10);
}

// merge_sort

static void test_one_value_int_merge_sort() {
    int *input[] = {&i1};
    int *expected[] = {&i1};
    merge_sort((void *)input, 0, 0, sizeof(int), compar_value_int_test);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
}

static void test_ten_value_int_merge_sort() {
    int input[10] = {i9, i7, i4, i8, i2, i6, i1, i3, i5, i10};
    int expected[10] = {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10};
    merge_sort((void *)input, 0, 9, sizeof(int), compar_value_int_test);
    TEST_ASSERT_EQUAL_INT_ARRAY(expected, input, 10);
}

static void test_one_value_char_merge_sort() {
    char *input[] = {&c1};
    char *expected[] = {&c1};
    merge_sort((void *)input, 0, 0, sizeof(char), compar_value_char_test);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
}

static void test_ten_value_char_merge_sort() {
    char input[10] = {c9, c7, c4, c8, c2, c6, c1, c3, c5, c10};
    char expected[10] = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
    merge_sort((void *)(&input[0]), 0, 9, sizeof(char), compar_value_char_test);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 10);
}

// merge_binary_insertion_sort

static void test_one_value_int_merge_binary_insertion_sort() {
    int *input[] = {&i1};
    int *expected[] = {&i1};
    merge_binary_insertion_sort((void *) input, 1, sizeof(int), 1, compar_value_int_test);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
}

static void test_ten_value_int_merge_binary_insertion_sort() {
    int input[10] = {i9, i7, i4, i8, i2, i6, i1, i3, i5, i10};
    int expected[10] = {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10};
    merge_binary_insertion_sort((void *)(&input[0]), 9, sizeof(int), 1, compar_value_int_test);
    TEST_ASSERT_EQUAL_INT_ARRAY(expected, input, 10);
}

static void test_one_value_char_merge_binary_insertion_sort() {
    char *input[] = {&c1};
    char *expected[] = {&c1};
    merge_binary_insertion_sort((void *) input, 1, sizeof(char), 1, compar_value_char_test);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
}

static void test_ten_value_char_merge_binary_insertion_sort() {
    char input[10] = {c9, c7, c4, c8, c2, c6, c1, c3, c5, c10};
    char expected[10] = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
    merge_binary_insertion_sort((void *)(&input[0]), 9, sizeof(char), 1, compar_value_char_test);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 10);
}


int main(int argc, char const *argv[]) {
    UNITY_BEGIN();

    printf("\n--- Test Insertion Sort ---\n");
    RUN_TEST(test_one_value_int_insertion_sort);
    RUN_TEST(test_ten_value_int_insertion_sort);
    RUN_TEST(test_one_value_char_insertion_sort);
    RUN_TEST(test_ten_value_char_insertion_sort);
    printf("\n");
    printf("--- Test Merge Sort ---\n");
    RUN_TEST(test_one_value_int_merge_sort);
    RUN_TEST(test_ten_value_int_merge_sort);
    RUN_TEST(test_one_value_char_merge_sort);
    RUN_TEST(test_ten_value_char_merge_sort);
    printf("\n");
    printf("--- Test Binary Insertion Sort ---\n");
    RUN_TEST(test_one_value_int_merge_binary_insertion_sort);
    RUN_TEST(test_ten_value_int_merge_binary_insertion_sort);
    RUN_TEST(test_one_value_char_merge_binary_insertion_sort);
    RUN_TEST(test_ten_value_char_merge_binary_insertion_sort);

    return UNITY_END();
}