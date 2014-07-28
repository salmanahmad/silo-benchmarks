
-module(test).
-export([start/0]).
-record(node, {value, left, right}).

loop(Count) ->
    if
        Count == 0 ->
            0;
        true ->
            #node{
                value="0", 
                left=#node{
                    value=1,
                    left=undefined,
                    right=#node{
                        value="3",
                        left=undefined,
                        right=undefined
                    }
                },
                right=#node{
                    value="2",
                    left=undefined,
                    right=undefined
                }
            },
            loop(Count - 1)
    end.

start() ->
    io:format("Starting.~n"),
    Before = erlang:now(),
    loop(100000000),
    After = erlang:now(), 
    io:format("Total Duration: ~f~n", [timer:now_diff(After, Before) / 1000]),
    exit(success).
    


