#include <stdio.h>
#include <string.h>
#include "utile.h"

void citire (char* path1, img2 *bmp)
{
    FILE *f = fopen ( path1, "rb" );
    fseek ( f, 18, SEEK_SET );
    fread ( &bmp->W, sizeof(int), 1, f);
    fseek ( f, 22, SEEK_SET );
    fread ( &bmp->H, sizeof(int), 1, f);

    fseek(f, 54, SEEK_SET);
    int  padding;

    bmp->M = (int**) malloc ( bmp->H * sizeof(int*) );
    for (int i = 0; i < bmp->H; i++ )
        bmp->M[i] = (int*) malloc ( bmp->W* sizeof(int));

    if ( bmp->W  % 4 != 0)
        padding = 4 - ( 3 * bmp->W ) % 4;
    else padding = 0;

    fseek( f, 0, SEEK_SET );
    fread(bmp->header, 54, 1, f);

    int i, j;
    for (i = bmp->H - 1; i >= 0; i--){
        for ( j = 0; j < 3 * bmp->W; j+=3)
        {
            unsigned char a[3];
            fread(&a, 3, 1, f);
            bmp->M[i][j / 3] = a[0];
        }
        fseek( f, padding, SEEK_CUR);
    }
    fclose(f);

}

