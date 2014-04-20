-module(main).
-export([start/0, traverse/2, service/1]).

serviceLoop(Count, Path, Ret) when Count == 0 -> Ret;
serviceLoop(Count, Path, Ret) -> serviceLoop(Count - 1, Path, Ret ++ [Path ++ [Count]]).

service(Count) ->
    H = 20,
    K = 2,

    %erlang:display(Count),

    receive
        {Pid, Path} ->
            if
                length(Path) == H ->
                    Pid ! [];
                true ->
                    Ret = serviceLoop(K, Path, []),
                    %erlang:display(Path),
                    %erlang:display(Ret),
                    Pid ! Ret
            end,
            service(Count + 1)
    end.

traverse(Pid, Path) ->
    Pid ! {self(), Path},
    receive
        Children ->
            if
                length(Children) == 0 ->
                    1;
                true ->
                    1 + lists:foldl(fun(X, Sum) -> traverse(Pid, X) + Sum end, 0, Children)
            end
    end.

start() ->
    Before = erlang:now(), 
    io:format("Starting.~n"),
    Pid = spawn(main, service, [0]),
    erlang:display(traverse(Pid, [])),
    After = erlang:now(), 
    io:format("Total Duration: ~f~n", [timer:now_diff(After, Before) / 1000]),
    exit(ok).
