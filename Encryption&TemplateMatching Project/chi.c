#include <stdio.h>
#include <math.h>
#include "utile.h"

void chi(char *path, img bmp)
{
   int i;
   double f = 0, chiR = 0, chiG = 0, chiB = 0;
   unsigned int *fr_R, *fr_G, *fr_B ;

   fr_R = (unsigned int*) calloc (256, sizeof(unsigned int));
   fr_G = (unsigned int*) calloc (256, sizeof(unsigned int));
   fr_B = (unsigned int*) calloc (256, sizeof(unsigned int));
   f = ( bmp.W * bmp.H )/256.;

    for (i = 0; i < 3 * bmp.H * bmp.W; i += 3)
    {
        fr_R[bmp.L[i]] ++;
        fr_G[bmp.L[i+1]] ++;
        fr_B[bmp.L[i+2]] ++;
    }

    for (i = 0; i <= 255; i++)
        chiR = chiR + ((fr_R[i] - f) * (fr_R[i] - f))/f;
    for (i = 0; i <= 255; i++)
        chiG = chiG + ((fr_G[i] - f) * (fr_G[i] - f))/f;
    for (i = 0; i <= 255; i++)
        chiB = chiB + ((fr_B[i] - f) * (fr_B[i] - f))/f;

    printf("%lf\n", chiR);
    printf("%lf\n", chiG);
    printf("%lf\n", chiB);

    free(fr_R);
    free(fr_G);
    free(fr_B);

}
