#include <stdio.h>
#ifndef _SKIP_LIST_H
#define _SKIP_LIST_H

#define MAX_HEIGHT 20

struct SkipList {
  struct Node *head;
  size_t max_level;
  size_t max_height;
  int (*compare)(const void*, const void*);
};

struct Node {
  struct Node **next;
  size_t size;
  void *item;
};












#endif