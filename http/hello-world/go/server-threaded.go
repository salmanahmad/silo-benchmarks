
package main

import (
    "fmt"
    "net/http"
    "strconv"
    "runtime"
)

func handler(w http.ResponseWriter, r *http.Request) {
    content := "Hello, World!"
    w.Header().Set("Content-Length", strconv.Itoa(len(content)))
    w.Header().Set("Connection", "Keep-Alive")
    fmt.Fprintf(w, content)
}

func main() {
    runtime.GOMAXPROCS(runtime.NumCPU())
    fmt.Printf("Go (Multithreaded) - Running on port 8701\n")
    http.HandleFunc("/", handler)
    http.ListenAndServe("127.0.0.1:8701", nil)
}
