package Logic;

import java.util.ArrayList;

public class Invoke {
    public void execute(ArrayList<Commands> actions) {
        for (Commands c: actions)
            c.execute();
    }
}
