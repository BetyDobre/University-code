#include <stdio.h>
#include "utile.h"
extern void permuta(img b_in, img *b_out, unsigned int *R);

void criptare ( img b_out, img *b_criptat, char* path, unsigned int *R)
{
    int k;
    unsigned int SV, SEED;

    b_criptat->H = b_out.H;
    b_criptat->W = b_out.W;

    memcpy(b_criptat->header, b_out.header, sizeof(b_out.header));

    b_criptat->L = ( unsigned char* ) malloc ( 3 * b_out.W * b_out.H * sizeof( unsigned char ));

    FILE *f= fopen (path, "r");
    fscanf(f, "%u", &SEED);
    fscanf(f, "%u", &SV);
    fclose(f);

    R[b_out.H * b_out.W] = SV ^ R[b_out.H * b_out.W];

    unsigned char s[4];

    memcpy(s, &R[b_out.H * b_out.W], 4);
    b_criptat->L[0] = s[2] ^ b_out.L[0];
    b_criptat->L[1] = s[1] ^ b_out.L[1];
    b_criptat->L[2] = s[0] ^ b_out.L[2];

    int i=1;

    for (k = 3; k < 3 * b_out.W * b_out.H ; k+=3)
    {
        unsigned char c[4];
        memcpy(c, &R[b_out.W * b_out.H + i], 4);
        b_criptat->L[k] = b_criptat->L[k - 3] ^ b_out.L[k] ^ c[2];
        b_criptat->L[k + 1] = b_criptat->L[k - 2] ^ b_out.L[k + 1] ^ c[1];
        b_criptat->L[k + 2] = b_criptat->L[k - 1] ^ b_out.L[k + 2] ^ c[0];
        i++;
    }

}

