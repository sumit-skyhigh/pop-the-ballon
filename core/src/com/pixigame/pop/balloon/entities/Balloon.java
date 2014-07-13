package com.pixigame.pop.balloon.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pixigame.pop.balloon.utils.GameUtil;

/**
 * Created by nemo on 7/12/2014.
 */
public class Balloon extends Group implements GameEntity{
    Body body;
    Fixture fixture;
    static boolean axisCache = true;
    public static final int contactGroup = 2;

    @Override
    public int getContactGroup() {
        return contactGroup;
    }

    static CircleShape circleShape = new CircleShape();
    public Balloon(){
        float radius = GameUtil.minBalloonRadius + (float)Math.random() * GameUtil.minBalloonRadius;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(radius + (axisCache ? 1 : -1) * (float)Math.random() * GameUtil.balloonInitPositionX, GameUtil.screenHeight / -2);
        axisCache = !axisCache;

        circleShape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.restitution = .5f;
        fixtureDef.density = 1;
        fixtureDef.friction = 0f;
        fixtureDef.shape = circleShape;

        body = GameUtil.getWorld().createBody(bodyDef);
        body.setUserData(this);
        fixture = body.createFixture(fixtureDef);
    }

    @Override
    public void disposeGameEntry() {
        body.destroyFixture(fixture);
        body.setUserData(null);
        GameUtil.getWorld().destroyBody(body);

    }

    @Override
    public void render() {

    }
}
