-module(main).
-export([start/0, loop/2, service/0]).

service() ->
    receive
        Pid ->
            Pid ! pong,
            service()
    end.

setup(Count, List) when Count == 0 -> List;
setup(Count, List) ->
    setup(Count - 1, List ++ [spawn(main, service, [])]).

start() ->
    Before = erlang:now(), 
    io:format("Starting...~n"),
    Pids = setup(100, []),
    %erlang:display(length(Pids)),
    loop(1000000, Pids),
    After = erlang:now(), 
    io:format("Total Duration: ~f~n", [timer:now_diff(After, Before) / 1000]),
    exit(ok).

loop(Count, Pids) when Count == 0 -> true;
loop(Count, Pids) ->
    Pid = lists:nth(random:uniform(100), Pids),
    Pid ! self(),
    receive
        pong -> true
    end,
    loop(Count - 1, Pids).
