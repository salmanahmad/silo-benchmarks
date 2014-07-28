
-module(test).
-export([start/0]).

loop(Count) ->
    Begin = fun(F, Index) ->
        _ = list_to_integer(integer_to_list(Index)),
        if
            Index == 0 ->
                0;
            true ->
                F(F, Index - 1)
        end
    end,
    Begin(Begin, 1000),

    if
        Count == 0 ->
            0;
        true ->
            loop(Count - 1)
    end.

start() ->
    io:format("Starting.~n"),
    Before = erlang:now(),
    loop(100000),
    After = erlang:now(), 
    io:format("Total Duration: ~f~n", [timer:now_diff(After, Before) / 1000]),
    exit(success).
    