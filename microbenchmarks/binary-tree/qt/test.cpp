
#include "stdio.h"
#include "stdlib.h"
#include <QString>
#include <QTime>

class Node
{
public:
    Node (QString value, Node * left, Node * right);
    virtual ~Node ();

    bool souldDelete = true;

    QString value;
    Node * left;
    Node * right;
};

Node::Node (QString value, Node * left, Node * right)
{
    this->value = value;
    this->left = left;
    this->right = right;
}

Node::~Node()
{
    if(souldDelete)
    {
        delete this->left;
        delete this->right;
    }
}






int main (int argc, char const *argv[])
{
    QTime t;
    int sum = 0;





    printf("\nStarting C++\n");
    t.start();

    for(long l = 0; l < 100000000; l++) {
        Node n3("3", NULL, NULL);
        Node n2("2", NULL, NULL);
        Node n1("1", NULL, &n3);
        Node n("0", &n1, &n2);

        n.souldDelete = false;
        n1.souldDelete = false;
        n2.souldDelete = false;
        n3.souldDelete = false;
    }
    
    printf("C++ Time: %d\n", t.elapsed());


    return 0;
}