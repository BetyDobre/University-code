#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utile.h"

extern unsigned int* xorshift (img* bmp, unsigned int seed);
extern unsigned char* liniarizare ( char* path, img *bmp );
extern void salv_ext (char * path, img bmp);
extern void permuta(img b1, img *b2, unsigned int *R);
extern void criptare ( img b_out, img *b_criptat, char* path, unsigned int *R);
extern void chi(char *path, img bmp);
extern void decriptare1(img b_criptat, img *b_inter, char *path, unsigned int *R);
extern void permuta_decriptare(img b_inter, img *b_decriptat, unsigned int *R);
extern void grayscale_image(char* nume_fisier_sursa, char* nume_fisier_destinatie);
extern point* template_matching(img2 I, img2 S, float prag, int *dim);
extern int cmpdescrescator (const void* a, const void *b);
extern void colorare ( unsigned char ****v, unsigned int W, unsigned int H, point f1, unsigned char c[3] );
extern char* citire (char* path1, img2 *bmp);
extern unsigned char*** citire_completa ( char* path, unsigned int H, unsigned int W );
extern void salvare(char * path, img2 I, unsigned char ***v);

float suprapunere(point a, point b){
    int topA = a.x - 7;
    int bottomA = a.x + 7;
    int leftA = a.y - 5;
    int rightA = a.y + 5;

    int topB = b.x - 7;
    int bottomB = b.x + 7;
    int leftB = b.y - 5;
    int rightB = b.y + 5;

    int top = (topA > topB) ? topA : topB;
    int bottom = (bottomA < bottomB) ? bottomA : bottomB;
    int left = (leftA > leftB) ? leftA : leftB;
    int right = (rightA < rightB) ? rightA : rightB;

    if(bottomA < topB || bottomB < topA)
        return 0;
    if(leftA > rightB || rightA < leftB)
        return 0;
    int ariaA = (rightA - leftA + 1) * (bottomA - topA + 1);
    int ariaB = (rightB - leftB + 1) * (bottomB - topB + 1);
    int aria = (right - left + 1) * (bottom - top + 1);
    return (float)aria / (ariaA + ariaB - aria);
}


void criptare_img()
{
    img bmp, bmp_permutat, bmp_criptat, bmp_decriptat, bmp_inter;
    char c[6][40];
    int i;

    unsigned int *R;

    FILE *f = fopen("nume_fisiere_criptare.txt", "r");

    for(i = 0; i < 6; i++)
        fscanf(f, "%s", c[i]);

    liniarizare(c[0], &bmp);

    R = xorshift(&bmp, 1000);

    permuta(bmp, &bmp_permutat, R);

    criptare(bmp_permutat, &bmp_criptat, c[1], R);

    salv_ext(c[2], bmp_permutat);

    salv_ext(c[3], bmp_criptat);

    printf("Valorile testului chi patrat pentru imaginea in clar sunt:\n");
    chi(c[0], bmp);

    printf("\n");

    printf("Valorile testului chi patrat pentru imaginea criptata sunt:\n");

    chi(c[3], bmp_criptat);

    printf("\n");

    free(R);
    R = NULL;

    R = xorshift(&bmp, 1000);


    decriptare1(bmp_criptat, &bmp_inter, c[1], R);


    salv_ext(c[4], bmp_inter);

    permuta_decriptare(bmp_inter, &bmp_decriptat, R);


    salv_ext(c[5], bmp_decriptat);
    free(R);
    R = NULL;

    fclose(f);
}

 void recunoastere_pattern()
 {
    FILE *f = fopen("nume_fisiere_template.txt", "r");
    char c[13][40];
    int nr;
    for (nr = 0; nr < 13; nr++)
        fscanf(f, "%s", c[nr]);

    grayscale_image(c[0], "cifra0g.bmp");

	grayscale_image(c[1], "cifra1g.bmp");

	grayscale_image(c[2], "cifra2g.bmp");

	grayscale_image(c[3], "cifra3g.bmp");

	grayscale_image(c[4], "cifra4g.bmp");

	grayscale_image(c[5], "cifra5g.bmp");

	grayscale_image(c[6], "cifra6g.bmp");

	grayscale_image(c[7], "cifra7g.bmp");

	grayscale_image(c[8], "cifra8g.bmp");

	grayscale_image(c[9], "cifra9g.bmp");

	grayscale_image(c[10], "test_grayscale.bmp");
	img2 S[10], I, I2;


    citire(c[11], &I);

    citire(c[10], &I2);

    citire("cifra0g.bmp", &S[0]);
    citire("cifra1g.bmp", &S[1]);
    citire("cifra2g.bmp", &S[2]);
    citire("cifra3g.bmp", &S[3]);
    citire("cifra4g.bmp", &S[4]);
    citire("cifra5g.bmp", &S[5]);
    citire("cifra6g.bmp", &S[6]);
    citire("cifra7g.bmp", &S[7]);
    citire("cifra8g.bmp", &S[8]);
    citire("cifra9g.bmp", &S[9]);

    unsigned char colors[10][3] = {{255, 0, 0}, {255, 255, 0}, {0, 255, 0}, {0, 255, 255}, {255, 0, 255}, {0, 0, 255}, {192, 192, 192}, {255, 140, 0}, {128, 0, 128}, {128, 0, 0}};
    unsigned char ***Img;
    int i, j, dim[10], sum = 0;

    for(i = 0; i <= 9; i++)
    {
        unsigned char aux = colors[i][2];
        colors[i][2] = colors[i][0];
        colors[i][0] = aux;
    } //inversarea ordinii pixelilor pentru a fi RGB

    Img = citire_completa("test.bmp", I2.H, I2.W);
    point *p[10];

    for(i = 0; i <= 9; i++)
    {
        p[i] = template_matching(I, S[i], 0.5, &dim[i]);
        for(j = 0; j < dim[i]; j++)
            p[i][j].digit = i; //digit retine cifra pentru a stii culoarea cu care trebuie desenata
        sum += dim[i];
    }

    point *D = calloc(sum, sizeof(point));
    int *ok = calloc(sum, sizeof(int));
    sum = 0;

    for(i = 0; i <= 9; i++){
        memcpy(D + sum, p[i], dim[i] * sizeof(point));
        sum += dim[i];
    }
    for(i = 0; i < sum; i++)
        ok[i] = 1;

    qsort(D, sum, sizeof(point), cmpdescrescator);

    for(int i = 0; i < sum; i++)
        if(ok[i])
            for(j = i + 1; j < sum; j++)
                if(ok[j])
                    if(suprapunere(D[i], D[j]) > 0.2)
                        ok[j] = 0;
    sum = 0;
    for(i = 0; i <= 9; i++){
        for(j = 0; j < dim[i]; j++)
            if(ok[sum + j])
                colorare(&Img, I2.W, I2.H, D[sum + j], colors[D[sum + j].digit]);
        sum += dim[i];
    }


    salvare(c[12], I2, Img);

    free(D);
    free(ok);
    fclose(f);
 }

 int main()
 {   criptare_img();
     recunoastere_pattern();
     return 0;
}
