package org.nugget.engine.scenes;

import org.nugget.engine.Camera;

public abstract class Scene {

    protected Camera camera;

    public Scene() {

    }

    public void init() {

    }

    public abstract void update(float dt);
}