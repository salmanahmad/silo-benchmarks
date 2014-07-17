
#include "stdio.h"
#include "stdlib.h"
#include <QString>
#include <QTime>

int test(QString s)
{
    return s.toInt();
}

int test2(char * s)
{
    int i;
    sscanf(s, "%d", &i);
    return i;
}


int main (int argc, char const *argv[])
{
    QTime t;
    int sum = 0;
    int output = 0;

    printf("\nStarting C++\n");

    t.start();

    for(long l = 0; l < 100000; l++) {
        for(int i = 0; i < 1000; i++) {
            QString value = QString::number(i);
            output = test(value);
        }
    }

    printf("C++ Time: %d\n", t.elapsed());
    
    
    
    printf("\nStarting C++\n");

    char value[60];

    t.start();

    for(long l = 0; l < 100000; l++) {
        for(int i = 0; i < 1000; i++) {
            sprintf(value, "%d", i);
            output = test2(value);
        }
    }

    printf("C++ Time: %d\n", t.elapsed());

    return 0;
}