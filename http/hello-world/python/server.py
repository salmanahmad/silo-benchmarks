
import timeit

def foo():
    while True:
        yield

def test():
    i = 0
    f = foo()
    while i < 1000000:
        i = i + 1
        f.next()
        

if __name__ == "__main__":
    print(timeit.timeit("test()", setup="from __main__ import test", number=1))
    
