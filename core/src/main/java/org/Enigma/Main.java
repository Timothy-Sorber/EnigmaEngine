package org.Enigma;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;
import java.util.Map;

import org.Enigma.Services.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");

        Universe.RegisterService("RuntimeService", new RuntimeService());

        System.out.println("Creating test ScriptSignal and creating a connection to it...");
        ScriptSignal testSignal = new ScriptSignal();
        ScriptSignalConnection testConnection = testSignal.Connect((params) -> {
            System.out.println(params.get("message"));
        });

        System.out.println("Firing test ScriptSignal with parameters...");
        Map<String, Object> params = new HashMap<>();
        params.put("message", "Hello, World!");
        testSignal.Fire(params);

        System.out.println("Disconnecting test ScriptSignal connection and firing ScriptSignal again...");

        testConnection.Disconnect();

        params.put("message", "If you see this, It didn't work. :(");
        testSignal.Fire(params);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
