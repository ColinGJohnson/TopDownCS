package net.colinjohnson.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

import utils.Constants;

public class BotEntity extends PlayerEntity{
	
	public BotEntity(String playerName, Map map) {
		super(playerName, map);
	}
	
	public void botUpdate(){
		
		// target a player	
		for (PlayerEntity target : getMapRef().getPlayers()) {
			if (target != this && target != null) {
				
				// rotate to face player							
				rotateToPosition(target.getX(), target.getY());
				
				// move towards player if has line of sight			
				RayCastCallback rcc = new RayCastCallback() {		
					boolean hasLOS = true;
					
					@Override
					public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {						
						if (fixture.getBody().getUserData() instanceof ObstacleEntity) {
							hasLOS = false;
							return 1;							
						}
						return 0;					
					}
				};				
				getMapRef().getWorld().rayCast(rcc, this.getBody().getPosition(), target.getBody().getPosition());
			}
		}		
		
		// update position variables
		setX(getBody().getPosition().x * Constants.PPM);
		setY(getBody().getPosition().y * Constants.PPM);
	}

	@Override
	void defineBody() {
		Body botBody;
		BodyDef def = new BodyDef();
		PolygonShape shape = new PolygonShape();
		
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(getMapRef().getSpawn().x / Constants.PPM, getMapRef().getSpawn().y / Constants.PPM);
		shape.setAsBox(32 / 2 / Constants.PPM, 32 / 2 / Constants.PPM); // NOTE: width & height measured from center
		botBody = getMapRef().getWorld().createBody(def);	
		botBody.createFixture(shape, 1.0f);
		botBody.setUserData(this);		
		botBody.setAngularDamping(5f);
		botBody.setLinearDamping(5f);
		setBody(botBody);
		
		shape.dispose();
	}
}
