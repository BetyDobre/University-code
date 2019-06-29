#include <stdio.h>
#include <stdlib.h>
#include "utile.h"

int* perm_aleatoare(img *bmp, unsigned int *R)
{
    unsigned int r, k, aux;
    int *p, i;
    p = (int *) malloc( bmp->H * bmp->W * sizeof(int) );
    for (k = 0; k < bmp->H * bmp->W; k++)
        p[k] = k;
    for (k = bmp->H * bmp->W - 1, i = 1; k >= 1; k--, i++)
    {
        r = R[i] % (k + 1);
        aux = p[r];
        p[r] = p[k];
        p[k] = aux;

    }
    return p;
    //algoritmul lui Durstenfeld
}
