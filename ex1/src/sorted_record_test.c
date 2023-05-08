#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "unity.h"
#include "unity_internals.h"
#include "sorted_record.h"

static int i1, i2, i3, i4, i5, i6, i7, i8, i9, i10;
static float f1, f2, f3, f4, f5, f6, f7, f8, f9, f10;
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

    f1 = 1.0;
    f2 = 2.0;
    f3 = 3.0;
    f4 = 4.0;
    f5 = 5.0;
    f6 = 6.0;
    f7 = 7.0;
    f8 = 8.0;
    f9 = 9.0;
    f10 = 10.0;

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

int compar_value_float_test(const void *p1, const void *p2) {
    if ((*(float *)p1) < (*(float *)p2))
        return -1;
    else if ((*(float *)p1) > (*(float *)p2))
        return 1;
    else
        return 0;
}

int compar_value_char_test(const void *p1, const void *p2) {
    return strcmp(((char *)p1), ((char *)p2));
}

static void test_one_value_int_sort(void) {
    int *input[] = {&i1};
    int *expected[] = {&i1};
    merge_binary_insertion_sort((void *) input, 1, sizeof(int), 1, compar_value_int_test);
    TEST_ASSERT_EQUAL_INT_ARRAY(expected, input, 1);
}

static void test_ten_value_int_sort(void) {
    int input[10] = {i9, i7, i4, i8, i2, i6, i1, i3, i5, i10};
    int expected[10] = {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10};
    merge_binary_insertion_sort((void *)(&input[0]), 10, sizeof(int), 0, compar_value_int_test);
    TEST_ASSERT_EQUAL_INT_ARRAY(expected, input, 10);
}

static void test_ten_value_int_sort_k(void) {
    int input[10] = {i9, i7, i4, i8, i2, i6, i1, i3, i5, i10};
    int expected[10] = {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10};
    merge_binary_insertion_sort((void *)(&input[0]), 10, sizeof(int), 10, compar_value_int_test);
    TEST_ASSERT_EQUAL_INT_ARRAY(expected, input, 10);
}

static void test_one_value_char_sort(void) {
    char *input[] = {&c1};
    char *expected[] = {&c1};
    merge_binary_insertion_sort((void *) input, 1, sizeof(char), 1, compar_value_char_test);
    TEST_ASSERT_EQUAL_INT_ARRAY(expected, input, 1);
}

static void test_ten_value_char_sort(void) {
    char input[10] = {c9, c7, c4, c8, c2, c6, c1, c3, c5, c10};
    char expected[10] = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
    merge_binary_insertion_sort((void *)(&input[0]), 10, sizeof(char), 0, compar_value_char_test);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 10);
}


static void test_ten_value_char_sort_k(void) {
    char input[10] = {c9, c7, c4, c8, c2, c6, c1, c3, c5, c10};
    char expected[10] = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
    merge_binary_insertion_sort((void *)(&input[0]), 10, sizeof(char), 10, compar_value_char_test);
    TEST_ASSERT_EQUAL_INT_ARRAY(expected, input, 10);
}

static void test_one_value_float_sort(void) {
    float *input[] = {&f1};
    float *expected[] = {&f1};
    merge_binary_insertion_sort((void *) input, 1, sizeof(float), 1, compar_value_float_test);
    TEST_ASSERT_EQUAL_INT_ARRAY(expected, input, 1);
}

static void test_ten_value_float_sort(void) {
    float input[10] = {f9, f7, f4, f8, f2, f6, f1, f3, f5, f10};
    float expected[10] = {f1, f2, f3, f4, f5, f6, f7, f8, f9, f10};
    merge_binary_insertion_sort((void *)(&input[0]), 10, sizeof(float), 0, compar_value_float_test);
    TEST_ASSERT_EQUAL_INT_ARRAY(expected, input, 10);
}

static void test_ten_value_float_sort_k(void) {
    float input[10] = {f9, f7, f4, f8, f2, f6, f1, f3, f5, f10};
    float expected[10] = {f1, f2, f3, f4, f5, f6, f7, f8, f9, f10};
    merge_binary_insertion_sort((void *)(&input[0]), 10, sizeof(float), 10, compar_value_float_test);
    TEST_ASSERT_EQUAL_INT_ARRAY(expected, input, 10);
}


int main(int argc, char const *argv[]) {
    UNITY_BEGIN();

    RUN_TEST(test_one_value_int_sort);
    RUN_TEST(test_ten_value_int_sort);
    RUN_TEST(test_ten_value_int_sort_k);
    RUN_TEST(test_one_value_char_sort);
    RUN_TEST(test_ten_value_char_sort);
    //RUN_TEST(test_ten_value_char_sort_k);
    RUN_TEST(test_one_value_float_sort);
    RUN_TEST(test_ten_value_float_sort);
    RUN_TEST(test_ten_value_float_sort_k);

    return UNITY_END();
}