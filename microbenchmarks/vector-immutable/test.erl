
-module(test).
-export([start/0]).

pop(List, N) ->
    if
        N == 0 ->
            List;
        true ->
            pop(lists:sublist(List, length(List) - 1), N - 1)
    end.

push(List, N) ->
    if
        N == 0 ->
            List;
        true ->
            push(lists:append(List, [N]), N - 1)
    end.


loop(Count) ->
    if
        Count == 0 ->
            0;
        true ->
            pop(push([], 1000), 1000),
            loop(Count - 1)
    end.

start() ->
    io:format("Starting.~n"),
    Before = erlang:now(),
    loop(10000),
    After = erlang:now(), 
    io:format("Total Duration: ~f~n", [timer:now_diff(After, Before) / 1000]),
    exit(success).
    


