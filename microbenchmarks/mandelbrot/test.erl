
-module(test).
-export([start/0]).

inner(Im, Re, Zr, Zi, N) ->
    Zr2 = Zr * Zr,
    Zi2 = Zi * Zi,
    if
        N =< 16 ->
            if
                Zr2 + Zi2 > 4 ->
                    N;
                true ->
                    inner(Im, Re, Zr2-Zi2+Re, 2*Zr*Zi+Im, N + 1)
            end;
        true ->
            16
    end.

middle(Im, Re, Output) ->
    Chars = " .,:;=|iI+hHOE#$-",
    if
        Re =< 1 ->
            N = inner(Im, Re, Re, Im, 0),
            middle(Im, Re + 0.03, string:concat(Output, string:substr(Chars, N + 1, 1)));
        true ->
            Output
    end.

outter(Im, Output) -> 
    if
        Im =< 1.2 ->
            NewOutput = middle(Im, -2, ""),
            outter(Im + 0.05, string:concat(string:concat(Output, NewOutput), "~n"));
        true ->
            Output
    end.

mandelbrot() ->
    outter(-1.2, "")
    .

loop(Count) ->
    if
        Count == 0 ->
            0;
        true ->
            mandelbrot(),
            loop(Count - 1)
    end.

start() ->
    io:format("Starting.~n"),
    Before = erlang:now(),
    loop(100000),
    After = erlang:now(), 
    io:format("Total Duration: ~f~n", [timer:now_diff(After, Before) / 1000]),
    exit(success).
    