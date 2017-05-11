package net.colinjohnson.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import utils.Constants;

public class Projectile {
	public static final int CAST_LENGTH = 1000;
	Map mapRef;
	PlayerEntity player;
	Weapon source;
	Body body;
	
	public Projectile(Map mapRef, PlayerEntity player, Weapon source){
		this.mapRef = mapRef;
		this.player = player;
		this.source = source;
		fire();
	}
	
	public void fire(){
		//dropShell();
		
		// ray cast
		Vector2 p1 = new Vector2(), p2 = new Vector2(), collision = new Vector2(), normal = new Vector2();
		
	}
	
	private void dropShell(){
		Body projBody;
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(player.getX() / Constants.PPM, player.getY() / Constants.PPM);
		def.fixedRotation = false;
		projBody = mapRef.getWorld().createBody(def);			
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1 / Constants.PPM, 1 / Constants.PPM); // NOTE: width & height measured from center		
		projBody.createFixture(shape, 1.0f);
		shape.dispose();
		this.body = projBody; 
	}
}
