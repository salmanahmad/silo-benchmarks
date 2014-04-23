service(AService, BService, CService) ->
    receive
        {request, Sender, Request} ->
            Response = buildResponse(AA, BB, CC)
            Sender ! Response
    end.