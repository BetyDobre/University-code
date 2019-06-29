#include <stdio.h>
#include "utile.h"

void decriptare1(img b_criptat, img *b_inter, char *path, unsigned int *R)
{
    int k;
    unsigned int SV, SEED;

    b_inter->H = b_criptat.H;
    b_inter->W = b_criptat.W;

    memcpy(b_inter->header, b_criptat.header, sizeof(b_criptat.header));

    b_inter->L = ( unsigned char* ) malloc ( 3 * b_criptat.W * b_criptat.H * sizeof( unsigned char ));

    FILE *f= fopen (path, "r");
    fscanf(f, "%u", &SEED);
    fscanf(f, "%u", &SV);
    fclose(f);

    R[b_criptat.H * b_criptat.W] = SV ^ R[b_criptat.H * b_criptat.W];

    unsigned char s[4];

    memcpy(s, &R[b_criptat.H * b_criptat.W], 4);
    b_inter->L[0] = s[2] ^ b_criptat.L[0];
    b_inter->L[1] = s[1] ^ b_criptat.L[1];
    b_inter->L[2] = s[0] ^ b_criptat.L[2];

    int i=1;

    for (k = 3; k < 3 * b_criptat.W * b_criptat.H ; k+=3)
    {
        unsigned char c[4];
        memcpy(c, &R[b_criptat.W * b_criptat.H + i], 4);
        b_inter->L[k] = b_criptat.L[k - 3] ^ b_criptat.L[k] ^ c[2];
        b_inter->L[k + 1] = b_criptat.L[k - 2] ^ b_criptat.L[k + 1] ^ c[1];
        b_inter->L[k + 2] = b_criptat.L[k - 1] ^ b_criptat.L[k + 2] ^ c[0];
        i++;
    }
}

