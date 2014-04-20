
import java.util.*;

import silo.lang.Runtime;
import silo.lang.*;
import silo.lang.compiler.*;
import silo.lang.compiler.Compiler;
import silo.lang.compiler.grammar.*;

import java.util.*;

import com.github.krukow.clj_lang.IPersistentVector;

import java.lang.ClassNotFoundException;

@Function.Definition
public class pong extends Function {

    @Function.Body
    public static Object invoke(ExecutionContext context) {
        Actor actor = context.fiber.actor;

        while(true) {
            Object o = silo.core.actor.read.invoke(context);
            if(!context.yielding) {
                silo.core.actor.sendRaw.invoke(context, (Actor)o, "Pong");
            } else {
                return null;
            }
        }
    }
}