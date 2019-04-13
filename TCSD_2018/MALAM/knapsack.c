#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define N 5
#define MAX_VOL 300

int fill_sack_aux(int vol_price[N][2], int max_vol, int index, bool taken[N]) {
    if (index == N) {
        return 0;
    }
    int res_without = fill_sack_aux(vol_price, max_vol, index + 1, taken);
    int res_with = 0;
    bool taken_with[N] = {};
    if (max_vol >= vol_price[index][0]) {
        res_with = fill_sack_aux(vol_price,
                                 max_vol - vol_price[index][0],
                                 index + 1, taken_with) + vol_price[index][1];
    }
    if (res_without >= res_with) {
        taken[index] = false;
        return res_without;
    } else {
        taken[index] = true;
        for(int i = index + 1; i < N; ++i) {
            taken[i] = taken_with[i];
        }
        return res_with;
    }
}

int fill_sack(int vol_price[N][2], int max_vol, bool taken[N]) {
    return fill_sack_aux(vol_price, max_vol, 0, taken);
}

int main()
{
    bool taken[N] = {false};
    int vol_price[N][2] = {{100, 40},{230, 173},{100, 40},{70, 40},{300, 200}};
    int res = fill_sack(vol_price, MAX_VOL, taken);
    printf("max price is %d\n", res);
    for (int i = 0; i < N; ++i) {
        if (taken[i]) printf("%d ", i);
    }
    return 0;
}
