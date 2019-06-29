#include <string.h>
#include "utile.h"
extern int* perm_aleatoare(img *bmp, unsigned int *R);

void permuta(img b_in, img *b_out, unsigned int *R)
{
    int *permutare = perm_aleatoare(&b_in, R);
    int i;

    b_out->H = b_in.H;
    b_out->W = b_in.W;

    memcpy(b_out->header, b_in.header, sizeof(b_in.header));

    b_out->L = ( unsigned char* ) malloc ( 3 * b_in.W * b_in.H * sizeof( unsigned char ));

    for(i = 0; i < b_in.W * b_in.H; i++)
        memcpy(&b_out->L[3 * permutare[i]], &b_in.L[3 * i], 3);
    free(permutare);
    //permuta pixelii
}
