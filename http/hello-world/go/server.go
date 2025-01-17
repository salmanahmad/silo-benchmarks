package main

import (
    "fmt"
    "net/http"
    "strconv"
)

func handler(w http.ResponseWriter, r *http.Request) {
    content := "Hello, World!"
    w.Header().Set("Content-Length", strconv.Itoa(len(content)))
    w.Header().Set("Connection", "Keep-Alive")
    fmt.Fprintf(w, content)
}

func main() {
    fmt.Printf("Go (Single Thread) - Running on port 8700\n")
    http.HandleFunc("/", handler)
    http.ListenAndServe("127.0.0.1:8700", nil)
}
