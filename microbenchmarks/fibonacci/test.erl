
-module(test).
-export([start/0]).

fib(N) ->
    if
        N =< 2 ->
            1;
        true ->
            fib(N - 1) + fib(N - 2)
    end.

loop(Count) ->
    if
        Count == 0 ->
            0;
        true ->
            fib(20),
            io:format("~B~n", [Count]),
            loop(Count - 1)
    end.

start() ->
    io:format("Starting.~n"),
    Before = erlang:now(),
    loop(100000),
    After = erlang:now(), 
    io:format("Total Duration: ~f~n", [timer:now_diff(After, Before) / 1000]),
    exit(success).
    