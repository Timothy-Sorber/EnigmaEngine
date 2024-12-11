package org.Enigma;

import java.util.Map;

public class ScriptSignalConnection {
    private LambdaFunction function;
    private ScriptSignal connection;

    public ScriptSignalConnection(LambdaFunction function, ScriptSignal connection) {
        this.function = function;
        this.connection = connection;
    }

    public void Invoke(Map<String, Object> args) {
        function.Invoke(args);
    }

    public void Disconnect() {
        connection.DisconnectListener(this);
    }
}
