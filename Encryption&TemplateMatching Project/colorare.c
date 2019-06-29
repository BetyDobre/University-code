#include <stdlib.h>
#include <stdio.h>
#include "utile.h"

void colorare ( unsigned char ****v, unsigned int W, unsigned int H, point f1, unsigned char c[3] )
{
    int i, j;
    if(f1.x - 7 >= 0 && f1.x + 7 < H && f1.y - 5 >= 0 && f1.y + 5 < W)
        for (i = f1.x - 7; i <= f1.x + 7; i++)
            for(j = 0; j < 3; j++)
            {
                (*v)[i][f1.y - 5][j] = c[j];
                (*v)[i][f1.y + 5][j] = c[j];
            }

    if(f1.x - 7 >= 0 && f1.x + 7 < H && f1.y - 5 >= 0 && f1.y + 5 < W)
        for (i = f1.y - 5; i <= f1.y + 5; i++)
            for(j = 0; j < 3; j++)
            {
                (*v)[f1.x - 7][i][j] = c[j];
                (*v)[f1.x + 7][i][j] = c[j];
            }
}

