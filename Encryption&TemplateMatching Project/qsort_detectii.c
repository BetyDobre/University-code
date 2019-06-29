#include <stdlib.h>
#include <stdio.h>
#include "utile.h"

int cmpdescrescator (const void* a, const void *b)
{
    point va, vb;
    va = *(point*)a;
    vb = *(point*)b;
    if (va.corr > vb.corr) return -1;
    if (va.corr < vb.corr) return 1;
    return 0;
}

