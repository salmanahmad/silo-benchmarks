
function fib(n)
{
    if (n <= 2) 
        return 1;
    else
        return fib(n-1) + fib(n-2);
}

console.log("Starting JS.")

var start = new Date()

var list = {}

for(l = 0; l < 100000; l++) {
    fib(20);
}


console.log("Javascript Time: " + ((new Date()).getTime() - start.getTime()))