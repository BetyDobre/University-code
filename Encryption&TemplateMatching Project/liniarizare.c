#include <stdio.h>
#include <stdlib.h>
#include "utile.h"

void liniarizare ( char* path, img *bmp )
{
    int padding;
    int i, j;
    FILE *f = fopen ( path, "rb" );
    fseek ( f, 18, SEEK_SET );
    fread ( &bmp->W, sizeof( unsigned int ), 1, f);
    fseek ( f, 22, SEEK_SET );
    fread ( &bmp->H, sizeof( unsigned int ), 1, f);

    bmp->L = ( unsigned char* ) malloc ( 3 * bmp->W * bmp->H * sizeof( unsigned char ));

    if ( bmp->W  % 4 != 0)
        padding = 4 - ( 3 * bmp->W ) % 4;
    else padding = 0;       //calculeaza paddingul

    fseek( f, 0, SEEK_SET );
    fread( bmp->header, 54, 1, f);
    for (i = bmp->H - 1; i >= 0; i--)
    {
        for (j = 0; j < 3 * bmp->W; j += 3){
            unsigned char c[3];
            fread ( c, 3, 1, f); //citeste fiecare octet al unui pixel
            bmp->L[i * 3 * bmp->W + j] = c[2];
            bmp->L[i * 3 * bmp->W + j + 1] = c[1];
            bmp->L[i * 3 * bmp->W + j + 2] = c[0]; //adauga fiecare pixel in ordinea corecta in vector
        }
        fseek( f, padding, SEEK_CUR);
    }
    fclose(f);
}
