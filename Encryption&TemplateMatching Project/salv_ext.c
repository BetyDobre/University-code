#include <stdio.h>
#include <stdlib.h>
#include "utile.h"

void salv_ext(char * path, img bmp)
{
    FILE *f = fopen(path, "wb");
    fwrite( bmp.header, 54, 1, f );

    int i, j, padding;
    if ( bmp.W  % 4 != 0)
        padding = 4 - ( 3 * bmp.W ) % 4;
    else padding = 0;

    for (i = bmp.H - 1; i >= 0; i--)
    {
        for (j = 0; j < 3 * bmp.W; j += 3){
            unsigned char c[3];
            c[2] = bmp.L[i * 3 * bmp.W + j];
            c[1] = bmp.L[i * 3 * bmp.W + j + 1];
            c[0] = bmp.L[i * 3 * bmp.W + j + 2];
            fwrite ( c, 3, 1, f);
        }
        unsigned char zero = 0;
        fwrite( &zero, sizeof(unsigned char), padding, f );
    }
    fclose(f);
}
