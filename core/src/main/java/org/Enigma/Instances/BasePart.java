package org.Enigma.Instances;

import com.badlogic.gdx.math.Vector2;
import org.Enigma.CFrame;
import org.Enigma.Instance;

public class BasePart extends Instance {

    // Overrides
    @Override
    public void Tick(double DeltaTime) {

    }

    @Override
    public void PreTick(double DeltaTime) {

    }

    @Override
    public void Render(double DeltaTime) {

    }

    @Override
    public void PreRender(double DeltaTime) {

    }

    // Variables
    public CFrame CFrame = new CFrame();
    public Vector2 LinearVelocity = new Vector2();
    public Vector2 AngularVelocity = new Vector2();
    public Vector2 Size = new Vector2(1, 1);
    public boolean Anchored = false;
    public boolean CanCollide = true;
    public boolean CanTouch = true;
}
