
-module(test).
-export([start/0]).

pop(Map, N) ->
    if
        N == 0 ->
            Map;
        true ->
            pop(dict:erase(N, Map), N - 1)
    end.

push(Map, N) ->
    if
        N == 0 ->
            Map;
        true ->
            push(dict:append(N, "", Map), N - 1)
    end.


loop(Count) ->
    if
        Count == 0 ->
            0;
        true ->
            pop(push(dict:new(), 1000), 1000),
            loop(Count - 1)
    end.

start() ->
    io:format("Starting.~n"),
    Before = erlang:now(),
    loop(10000),
    After = erlang:now(), 
    io:format("Total Duration: ~f~n", [timer:now_diff(After, Before) / 1000]),
    exit(success).
    


