service(Data) ->
    receive
        {get, Sender, Key} ->
            Value = dict:fetch(Data);
            Sender ! Value;
            service(New Data);
        {put, Sender, Key, Value} ->
            NewData = dict:store(Key, Value, Data);
            Sender ! Value;
            service(NewData)
        Any ->
            % Unknown Case
    end.