-module(main).
-export([start/0, first/1, service/3]).

first(Home) -> 
    receive
        Next ->
            service(Next, Home, 0)
    end.

service(Next, Home, Tag) ->
    receive
        I ->
            if
                I < 1000000 ->
                    Next ! I + 1;
                true ->
                    Home ! done
            end,
            service(Next, Home, Tag)
    end.

setup(Count, Previous) when Count == 0 -> spawn(main, service, [Previous, self(), Count]);
setup(Count, Previous) ->
    setup(Count - 1, spawn(main, service, [Previous, self(), Count])).

start() ->
    Before = erlang:now(), 
    io:format("Starting.~n"),
    First = spawn(main, first, [self()]),
    Last = setup(1000, First),
    First ! Last,
    First ! 0,
    receive
        done -> done
    end,
    After = erlang:now(), 
    io:format("Total Duration: ~f~n", [timer:now_diff(After, Before) / 1000]),
    exit(ok).
