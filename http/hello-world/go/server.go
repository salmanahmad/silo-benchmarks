package main

import (
    "fmt"
    "net/http"
)

func handler(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintf(w, "Hello, World!")
}

func main() {
    fmt.Printf("Go - Running on port 8700\n")
    http.HandleFunc("/", handler)
    http.ListenAndServe("127.0.0.1:8700", nil)
}
