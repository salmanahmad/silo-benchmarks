

console.log("Starting JS.")

var start = new Date()

function node(value, left, right) {
    var n = new Object()
    n.value = value
    n.left = left
    n.right = right
    return n
}

for(l = 0; l < 100000000; l++) {
    node("0", node("1", null, node("3", null, null)), node("2", null, null))
}


console.log("Javascript Time: " + ((new Date()).getTime() - start.getTime()))