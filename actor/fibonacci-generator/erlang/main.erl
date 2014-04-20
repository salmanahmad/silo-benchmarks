-module(main).
-export([start/0, loop/2, service/2]).

service(A, B) ->
    receive
        {Pid, message} ->
            Pid ! {value, A},
            Temp = B,
            service(A + Temp, A)
    end.

start() ->
    Before = erlang:now(), 
    %erlang:display("Starting!"),
    Pid = spawn(main, service, [0, 1]),
    loop(1000000, Pid),
    After = erlang:now(), 
    io:format("Total Duration: ~f~n", [timer:now_diff(After, Before) / 1000]),
    exit(ok).

loop(Count, Pid) when Count == 0 -> true;
loop(Count, Pid) -> 
    Pid ! {self(), message},
    receive
        {value, F} ->
            F
    end,
    loop(Count - 1, Pid).
