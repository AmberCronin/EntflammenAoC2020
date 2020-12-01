#include <stdio.h>

main() {
    FILE *f;

    f = fopen("/home/entflammen/git/EntflammenAoC2020/day1/day1.dat", "r");
    
    fclose(f);
}