package com.pixigame.pop.balloon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.pixigame.pop.balloon.entities.Balloon;
import com.pixigame.pop.balloon.entities.GameEntity;
import com.pixigame.pop.balloon.entities.Wall;
import com.pixigame.pop.balloon.utils.GameUtil;

/**
 * Created by nemo on 7/12/2014.
 */
public class GameScreen implements Screen, ContactFilter, ContactListener{
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
        return ((GameEntity)fixtureA.getBody().getUserData()).getContactGroup()==((GameEntity)fixtureA.getBody().getUserData()).getContactGroup();
    }

    World world;
    Camera camera;
    Box2DDebugRenderer debugRenderer;
    Array<Body> bodies;
    float timePassed = 60;
    float xMinGravity = 0;
    Array<GameEntity> removableBodies;
    @Override
    public void show() {
        camera = new OrthographicCamera(GameUtil.screenWidth, GameUtil.screenHeight);
        world = GameUtil.getWorld();
        world.setContactFilter(this);
        world.setContactListener(this);
        debugRenderer = new Box2DDebugRenderer();
        Wall wall = new Wall();
        Gdx.gl.glClearColor(1,1,1,1);
        removableBodies = new Array<GameEntity>();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float accelY = Gdx.input.getAccelerometerY();
        float accelX = Gdx.input.getAccelerometerX();
        Gdx.app.log("X : ",String.valueOf(accelX));
        Gdx.app.log("Y : ", String.valueOf(accelY));
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            xMinGravity --;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            xMinGravity ++;
        }
        world.setGravity(new Vector2(-10 * accelX, 9.81f + accelY));
        world.step(delta, 8, 3);
        if(removableBodies.size > 0){
            for(GameEntity ge : removableBodies){
                ge.disposeGameEntry();
            }
            removableBodies.clear();
        }

        debugRenderer.render(world, camera.combined);

        timePassed+=delta;
        if(timePassed > 1){
            new Balloon();
            timePassed = 0;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {
        GameEntity gameEntityA, gameEntityB;
        gameEntityA=((GameEntity)(contact.getFixtureA().getBody().getUserData()));
        gameEntityB=((GameEntity)(contact.getFixtureB().getBody().getUserData()));
        if(gameEntityA.getContactGroup() != gameEntityB.getContactGroup()){
            if(gameEntityA.getContactGroup()!= Balloon.contactGroup){
                removableBodies.add(gameEntityA);
            }
            gameEntityB=((GameEntity)(contact.getFixtureB().getBody().getUserData()));
            if(gameEntityB.getContactGroup()== Balloon.contactGroup){
                removableBodies.add(gameEntityB);
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
