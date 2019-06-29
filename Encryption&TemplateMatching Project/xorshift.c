#include <stdio.h>
#include <stdlib.h>
#include "utile.h"

unsigned int* xorshift(img *bmp, unsigned int SEED)
{
    unsigned int k;
    unsigned int *R;
    FILE *f = fopen("secret_key.txt", "r");
    R = ( unsigned int* ) malloc ((2 * bmp->W * bmp->H) * sizeof( unsigned int ));
    fscanf(f, "%u", &R[1]);
    fclose(f);
    for(k = 1; k < 2 * bmp->W * bmp->H; k++)
    {
        R[k] = R[k] ^ R[k] << 13;
        R[k] = R[k] ^ R[k] >> 17;
        R[k] = R[k] ^ R[k] << 5;
        if( k < 2 * bmp->W * bmp->H - 1)
            R[k+1] = R[k];
    }
    return R;
}
