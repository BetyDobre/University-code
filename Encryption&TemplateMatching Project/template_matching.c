#include <stdio.h>
#include <stdlib.h>
#include "utile.h"
#include <math.h>

point* template_matching(img2 I, img2 S, float prag, int *dim)
{
    int i, j, x, y, nr = 0;
    unsigned char **a;
    float S_mediu = 0, sigma_S = 0;
    point *D;
    D = (point*) calloc(I.W * I.H, sizeof(point)); //vector de detectii

    a = (unsigned char**) calloc(I.H + 14, sizeof(unsigned char*));
    for (i = 0; i < I.H + 14; i++ )
        a[i] = (unsigned char*) calloc (I.W + 10, sizeof(unsigned char));

    for (i = 0; i < I.H; i++)
        for (j = 0; j < I.W; j++)
            a[i + 7][j + 5] = I.M[i][j]; //se creeaza o noua matrice avand ca padding pixeli negri

    for (x = 0; x < S.H; x++)
        for(y = 0 ; y < S.W; y++)
            S_mediu = S_mediu + S.M[x][y];
    S_mediu /= (S.H * S.W);

    for (x = 0; x < S.H; x++)
        for (y = 0; y < S.W; y++)
            sigma_S += (S.M[x][y] - S_mediu) * (S.M[x][y] - S_mediu);
    sigma_S = sqrt(sigma_S / (S.W * S.H - 1));
    for (i = 0; i < I.H; i++)
        for (j = 0; j < I.W; j++)
        {
            float sigma_f1 = 0, f1_mediu = 0, sum = 0, corr = 0;

            for (x = 0; x < S.H; x++)
                for (y = 0; y < S.W; y++)
                    f1_mediu = f1_mediu + a[x + i][y + j];
            f1_mediu /= (S.H * S.W);

            for (x = 0; x < S.H; x++)
                for (y = 0; y < S.W; y++)
                    sigma_f1 += (a[x + i][y + j] - f1_mediu) * (a[x + i][y + j] - f1_mediu);
            sigma_f1 = sqrt(sigma_f1 / (S.W * S.H - 1));

            for (x = 0; x < S.H; x++)
                for (y = 0; y < S.W; y++)
                    sum += (1 / (sigma_S * sigma_f1)) * ((a[x + i][y + j] - f1_mediu) * (S.M[x][y] - S_mediu));
            corr = 1 / (float)(S.H * S.W) * sum;
            if(corr > prag){
                ++nr;
                D[nr - 1].corr = corr;
                D[nr - 1].x = i;
                D[nr - 1].y = j;
            }
        }
    *dim = nr;
    for (i = 0; i < I.H + 14; i++ )
        free(a[i]);
    free(a);
    a = NULL;
    return D;
}

