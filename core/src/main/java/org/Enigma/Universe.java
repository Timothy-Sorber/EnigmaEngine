package org.Enigma;

import org.Enigma.Services.RuntimeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Universe {
    protected static ArrayList<Instance> instances = new ArrayList<>();
    private static final Map<String, Instance> services = new HashMap<>();

    public static void RegisterService(String name, Instance instance) {
        if (services.containsKey(name)) {
            throw new IllegalArgumentException("Service with the same name already exists");
        }

        instance.IsService = true;
        services.put(name, instance);
    }

    public static Instance GetService(String name) {
        return services.get(name);
    }

    private static boolean IsRunning = false;
    public static void Run(int TPS) {
        if (!IsRunning) {
            IsRunning = true;

            long tickInterval = 1000 / TPS;
            long lastTickTime = System.currentTimeMillis();
            long lastFrameTime = System.nanoTime();

            while (IsRunning) {
                long currentTime = System.currentTimeMillis();
                long currentNanoTime = System.nanoTime();

                if (currentTime - lastTickTime >= tickInterval) {
                    double deltaTime = (currentNanoTime - lastFrameTime) / 1e9; // Convert nanoseconds to seconds
                    Update(deltaTime);
                    lastTickTime = currentTime;
                    lastFrameTime = currentNanoTime;
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void Update(double deltaTime) {
        // This method will be called every tick
        for (Instance instance : instances) {
            instance.PreTick(deltaTime);
        }
        for (Instance instance : instances) {
            instance.Tick(deltaTime);
        }
    }
}
