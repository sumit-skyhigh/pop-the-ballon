package com.pixigame.pop.balloon.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pixigame.pop.balloon.utils.GameUtil;

/**
 * Created by nemo on 7/12/2014.
 */
public class Wall extends Group implements GameEntity{
    Body body;

    @Override
    public int getContactGroup() {
        return contactGroup;
    }

    Fixture fixture;
    public static final int contactGroup = 1;

    public Wall(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0,0);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        float halfHeight = GameUtil.screenHeight / 2 - 10;
        float halfWidth = GameUtil.screenWidth / 2 -10;
        ChainShape leftWall = new ChainShape();
        leftWall.createChain(new float[]{-1*halfWidth,-1*halfHeight, -1*halfWidth, halfHeight});

        ChainShape rightWall = new ChainShape();
        rightWall.createChain(new float[]{halfWidth,-1*halfHeight, halfWidth, halfHeight});

        ChainShape topLeftWall = new ChainShape();
        topLeftWall.createChain(new float[]{-1*halfWidth,halfHeight, -1*GameUtil.minBalloonRadius *4, halfHeight});

        ChainShape topRightWall = new ChainShape();
        topRightWall.createChain(new float[]{GameUtil.minBalloonRadius *4,halfHeight,halfWidth, halfHeight});

        FixtureDef wallFixtureDef = new FixtureDef();
        wallFixtureDef.friction = .5f;
        wallFixtureDef.restitution = .5f;
        wallFixtureDef.density = 5;
        body = GameUtil.getWorld().createBody(bodyDef);

        for(Shape shape: new Shape[]{leftWall, rightWall, topLeftWall, topRightWall}){
            wallFixtureDef.shape = shape;
            body.createFixture(wallFixtureDef);
        }
        body.setUserData(this);

    }

    @Override
    public void disposeGameEntry() {

    }

    @Override
    public void render() {

    }
}
