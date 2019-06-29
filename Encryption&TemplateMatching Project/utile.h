#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    unsigned int W, H;
    char header[54];
    unsigned char *L;

} img;

typedef struct {
    int H, W, **M;
    char header[54];
} img2;

typedef struct {
    int x, y, digit;
    float corr;
} point;

