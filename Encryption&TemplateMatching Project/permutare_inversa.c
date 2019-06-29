#include <stdio.h>
#include <stdlib.h>
#include "utile.h"

int* permutare_inversa(img *bmp, unsigned int *R)
{
    unsigned int r, k, aux;
    int *p, i, *inv;

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

    inv = (int *) malloc( bmp->H * bmp->W * sizeof(int) );

    for (k = 0; k < bmp->H * bmp->W; k++)
       inv[p[k]] = k;

    return inv;
}
