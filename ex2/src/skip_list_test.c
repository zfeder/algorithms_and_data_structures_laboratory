#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "unity.h"
#include "unity_internals.h"
#include "skip_list.h"

static int i1, i2, i3, i4;
static char c1, c2, c3, c4;
struct SkipList *list = NULL;

static int string_compar_test(const void* value1, const void* value2){
    return strcmp((char *)value1, (char *)value2);
}

void setUp(void) {

    i1 = 1;
    i2 = 2;
    i3 = 3;
    i4 = 4;

    c1 = 'a';
    c2 = 'b';
    c3 = 'c';
    c4 = 'd';
}

static void test_insert_one_value_int(void) {
    new_skiplist(&list, 10, string_compar_test);
    insert_skiplist(list, &i1);
    TEST_ASSERT_EQUAL_INT(*((int *)list->heads->next[0]->item), i1);
}

static void test_insert_one_value_char(void) {
    new_skiplist(&list, 10, string_compar_test);
    insert_skiplist(list, &c1);
    TEST_ASSERT_EQUAL_INT(*((char *)list->heads->next[0]->item), c1);
}

static void test_search_value_int(void) {
  new_skiplist(&list, 10, string_compar_test);
  int i = i1;
  insert_skiplist(list, &i);
  TEST_ASSERT_EQUAL_INT(1, *(int *)(search_skiplist(list, (void *)&i)));
  clear_skiplist(&list);
}

static void test_search_value_char(void) {
  new_skiplist(&list, 10, string_compar_test);
  char c = c1;
  insert_skiplist(list, &c);
  TEST_ASSERT_EQUAL_PTR(c1, *(char *)(search_skiplist(list, (void *)&c)));
  clear_skiplist(&list);
}

static void test_search_skip_list_not_found(void) {
  new_skiplist(&list, 10, string_compar_test);
  int i = i1;
  insert_skiplist(list, (void *)&i);
  int ii = i2;
  TEST_ASSERT_NULL(search_skiplist(list, (void *)&ii));
  clear_skiplist(&list);
}

int main(int argc, char const *argv[]) {
    UNITY_BEGIN();

    RUN_TEST(test_insert_one_value_int);
    RUN_TEST(test_insert_one_value_char);
    RUN_TEST(test_search_value_int);
    RUN_TEST(test_search_value_char);
    RUN_TEST(test_search_skip_list_not_found);

    return UNITY_END();
}