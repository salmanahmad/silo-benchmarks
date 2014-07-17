
#include "stdio.h"
#include <QTime>

int fib(int n)
{
    if (n <= 2) 
        return 1;
    else
        return fib(n-1) + fib(n-2);
}

int main (int argc, char const *argv[])
{
    QTime t;
    int sum = 0;
    int output = 0;

    printf("\nStarting C++\n");

    t.start();

    for(long l = 0; l < 100000; l++) {
        output = fib(20);
        sum = sum + output + qrand();
    }

    printf("Output: %d\n", sum);
    printf("C++ Time: %d\n", t.elapsed());

    return 0;
}