package org.Enigma;

import com.badlogic.gdx.math.Vector2;

public class CFrame {
    public Vector2 position = new Vector2();
    public Vector2 orientation = new Vector2(0,1);

    public CFrame(float posX, float posY) {
        position.set(posX, posY);
    }

    public CFrame(Vector2 pos) {
        position.set(pos);
    }

    public CFrame() {}

    public static CFrame fromRotation(Vector2 rotation) {
        CFrame n = new CFrame(0, 0);
        n.orientation = rotation;
        return n;
    }

    public CFrame translate(Vector2 vector) {
        position.add(vector);
        return this;
    }

    public CFrame rotateDeg(float angle) {
        orientation.rotateDeg(angle);
        return this;
    }

    public CFrame rotateRadians(float angle) {
        orientation.rotateRad(angle);
        return this;
    }

    public Vector2 lookVector() {
        return new Vector2(orientation).nor();
    }
}
