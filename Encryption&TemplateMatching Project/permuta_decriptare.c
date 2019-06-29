#include <stdio.h>
#include <stdlib.h>
#include "utile.h"

extern int* permutare_inversa(img *bmp, unsigned int *R);

void permuta_decriptare(img b_inter, img *b_decriptat, unsigned int *R)
{
    int *permutare = permutare_inversa(&b_inter, R);
    int i;

    b_decriptat->H = b_inter.H;
    b_decriptat->W = b_inter.W;

    memcpy(b_decriptat->header, b_inter.header, sizeof(b_inter.header));

    b_decriptat->L = ( unsigned char* ) malloc ( 3 * b_inter.W * b_inter.H * sizeof( unsigned char ));

    for(i = 0; i < b_inter.W * b_inter.H; i++)
        memcpy(&b_decriptat->L[3 * permutare[i]], &b_inter.L[3 * i], 3);
    free(permutare);
}
