
function test(s) {
    return parseInt(s)
}

function clone(obj) {
    if (null == obj || "object" != typeof obj) return obj;
    var copy = obj.constructor();
    for (var attr in obj) {
        if (obj.hasOwnProperty(attr)) copy[attr] = clone(obj[attr]);
    }
    return copy;
}

console.log("Starting JS.")

var start = new Date()

var list = {}

for(l = 0; l < 1000; l++) {
    for(i = 0; i < 1000; i++) {
        list = clone(list)
        list[i] = ""
    }

    for(i = 0; i < 1000; i++) {
        list = clone(list)
        delete list[i]
    }
}


console.log("Javascript Time: " + ((new Date()).getTime() - start.getTime()))