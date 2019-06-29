#include <stdio.h>
#include <stdlib.h>
#include "utile.h"

unsigned char*** citire_completa ( char* path, unsigned int H, unsigned int W )
{
    int padding;
    int i, j;
    FILE *f = fopen ( path, "rb" );

    unsigned char ***v;
    v = ( unsigned char***) malloc ( H * sizeof(unsigned char**) );
    for (i = 0; i < H; i++)
    {
        v[i] = (unsigned char**) malloc ( W * sizeof(unsigned char*) );
        for (j = 0; j < W; j++)
            v[i][j] = (unsigned char*) malloc(3 * sizeof(unsigned char));
    }

    if ( W  % 4 != 0)
        padding = 4 - ( 3 * W ) % 4;
    else padding = 0;

    fseek( f, 54, SEEK_SET );
    for (i = H - 1; i >= 0; i--)
    {
        for (j = 0; j < W; j++)
            fread ( v[i][j], 3, 1, f);
        fseek( f, padding, SEEK_CUR);
    }
    fclose(f);
    //construieste un tablou tridimensional ce contine matricea cu pixelii unei iamgini avand acces la fiecare octet al acestora
    return v;
}

