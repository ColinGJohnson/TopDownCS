package net.colinjohnson.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import utils.Constants;

public class ObstacleEntity extends Entity{
	private Map mapRef;
	private float width;
	private float height;

	public ObstacleEntity(float x, float y, float width, float height, Map map) {
		super(x, y, "Obstacle");
		mapRef = map;
		this.width = width;
		this.height = height;
		defineBody();
	}

	@Override
	void defineBody() {
		Body obstBody;
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.StaticBody;
		def.position.set(getX() / Constants.PPM, getY() / Constants.PPM);
		def.fixedRotation = true;
		obstBody = mapRef.getWorld().createBody(def);			
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2 / Constants.PPM, height / 2 / Constants.PPM); // NOTE: width & height measured from center		
		obstBody.createFixture(shape, 1.0f);
		shape.dispose();
		setBody(obstBody);
	}
	
	@Override
	public String toString(){
		return ("(" + getBody().getPosition().x * Constants.PPM + ", " + getBody().getPosition().y * Constants.PPM + ")");
	}
}
