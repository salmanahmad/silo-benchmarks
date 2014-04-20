-module(main).
-export([start/0, loop/1, service/0]).

service() ->
    receive
        message ->
            true
    end.

start() ->
    Before = erlang:now(), 
    %erlang:display("Starting!"),
    loop(1000000),
    After = erlang:now(), 
    erlang:display(timer:now_diff(After, Before) / 1000),
    exit(ok).

loop(Count) when Count == 0 -> true;
loop(Count) -> 
    Pid = spawn(main, service, []),
    Pid ! message,
    loop(Count - 1).
