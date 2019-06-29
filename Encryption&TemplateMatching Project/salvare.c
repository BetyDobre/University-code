#include <stdio.h>
#include <stdlib.h>
#include "utile.h"

void salvare(char * path, img2 I, unsigned char ***v)
{
    FILE *f = fopen(path, "wb+");

    int i, j, padding;
    if ( I.W  % 4 != 0)
        padding = 4 - ( 3 * I.W ) % 4;
    else padding = 0;

    fwrite (I.header , 54, sizeof(char), f);

    for (i = I.H - 1; i >= 0; i--)
    {
        for (j = 0; j < I.W; j++)
            fwrite ( v[i][j], 3, 1, f);
        unsigned char zero = 0;
        fwrite( &zero, sizeof(unsigned char), padding, f );
    }
    fclose(f);
}

