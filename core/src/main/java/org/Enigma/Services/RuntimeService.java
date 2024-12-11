package org.Enigma.Services;

import org.Enigma.Instance;
import org.Enigma.ScriptSignal;

import java.util.HashMap;
import java.util.Map;

public class RuntimeService extends Instance {
    @Override
    public void Tick(double DeltaTime) {
        Map<String,Object> params = new HashMap<>();
        params.put("deltaTime", DeltaTime);
        OnTick.Fire(params);
    }

    @Override
    public void PreTick(double DeltaTime) {
        Map<String,Object> params = new HashMap<>();
        params.put("deltaTime", DeltaTime);
        PreTick.Fire(params);
    }

    @Override
    public void Render(double DeltaTime) {

    }

    @Override
    public void PreRender(double DeltaTime) {

    }

    public ScriptSignal OnTick = new ScriptSignal();
    public ScriptSignal PreTick = new ScriptSignal();
    public ScriptSignal Render = new ScriptSignal();
    public ScriptSignal PreRender = new ScriptSignal();
}
