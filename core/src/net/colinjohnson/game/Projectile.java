package net.colinjohnson.game;

import java.nio.file.StandardOpenOption;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

import utils.Constants;

public class Projectile {
	Vector2 p1 = new Vector2(), p1W = new Vector2(), p2 = new Vector2(), p2W = new Vector2(), stop = new Vector2(), stopW = new Vector2();
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
		
		// ray castcallback to register impacts
		RayCastCallback rcc = new RayCastCallback() {
			
			@Override
			public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
				
				stop.set(point.cpy());
				stopW.set(stop.x * Constants.PPM, stop.y * Constants.PPM);
				
				return 0;
				/*
				Entity target = (Entity) fixture.getBody().getUserData();
				if (target instanceof ObstacleEntity) {
					stop.set(point.cpy());
					stopW.set(stop.x * Constants.PPM, stop.y * Constants.PPM);
					return 0;
				} else if (target instanceof PlayerEntity && target != player){
					PlayerEntity targetPlayer = (PlayerEntity)target;
					System.out.println("shot hit " + targetPlayer.getPlayerName());
					return 0;
				} else {
					System.out.println("shot hit " + target.getName());
					return 0;
				}		*/		
			}
		};
			
		// vertical:horizontal ratio
		float xDist = ((player.getMousePos().x - player.getX()));
		float yDist = ((player.getMousePos().y - player.getY()));
		
		//yDist = 300;
		//xDist = 300;
		
		// ray start & end pts in world coordinates
		p1W.set(player.getX(), player.getY());
		p2W.set(player.getMousePos().x, player.getMousePos().y);		
		
		// ray start & end pts in box2D coordinates
		p1.set(p1W.x / Constants.PPM, p1W.y / Constants.PPM);
		p2.set(p2W.x / Constants.PPM, p2W.y / Constants.PPM);	
		
		// default stop point to end of ray
		stop.set(p2);
		stopW.set(p2W);
		
		System.out.println("p2W:" + p2W + ", p1W:" + p1W + ", p2:" + p2 + ", p1:" + p1);
		
		// ray cast
		mapRef.getWorld().rayCast(rcc, p1, p2);
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
