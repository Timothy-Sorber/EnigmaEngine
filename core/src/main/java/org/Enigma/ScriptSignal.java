package org.Enigma;

import java.util.ArrayList;
import java.util.Map;

public class ScriptSignal {
    private ArrayList<ScriptSignalConnection> connections = new ArrayList<>();

    public ScriptSignalConnection Connect(LambdaFunction function) {
        ScriptSignalConnection sc = new ScriptSignalConnection(function, this);
        connections.add(sc);
        return sc;
    }

    public void DisconnectListener(ScriptSignalConnection sc) {
        connections.remove(sc);
    }

    public void Fire(Map<String, Object> params) {
        for (ScriptSignalConnection sc : connections) {
            sc.Invoke(params);
        }
    }
}
