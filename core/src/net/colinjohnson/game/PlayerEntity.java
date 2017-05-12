package net.colinjohnson.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import utils.Constants;

public class PlayerEntity extends Entity {
	private boolean alive = true;
	private boolean hasArmor = false;
	private boolean isRunning = true;
	private int health = 100;
	private int maxHealth = 100;
	private int armor = 100;
	private String playerName = "";
	private Weapon weapon;
	private int money = 0;
	private Vector2 mousePos = new Vector2(0, 0);
	
	public PlayerEntity(String playerName, Map map) {
		super(map, 0, 0, "Player");
		this.setPlayerName(playerName);
		setV(5);
		setSize(32);
		defineBody();
	} // Player Constructor

	@Override
	void defineBody() {
		Body playerBody;
		BodyDef def = new BodyDef();
		PolygonShape shape = new PolygonShape();
	
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(getMapRef().getSpawn().x / Constants.PPM, getMapRef().getSpawn().y / Constants.PPM);
		def.fixedRotation = true;		
		shape.setAsBox(32 / 2 / Constants.PPM, 32 / 2 / Constants.PPM); // NOTE: width & height measured from center		
		playerBody = getMapRef().getWorld().createBody(def);	
		playerBody.createFixture(shape, 1.0f);
		playerBody.setUserData(this);
		setBody(playerBody);
		shape.dispose();
	} // defineBody

	void update(float delta, float mouseX, float mouseY) {
		
		// check if player is dead, if so remove
		if (health < 0) {
			getMapRef().getPlayers().remove(this);
		}
		
		// update entity position
		setX(getBody().getPosition().x * Constants.PPM);
		setY(getBody().getPosition().y * Constants.PPM);
		
		// update mouse position
		mousePos.set(mouseX, mouseY);
		
		// update player rotation
		rotateToPosition(mouseX, mouseY);	
		
		// update player position
		float xDistance = mouseX - getX();
		float yDistance = mouseY - getY();
		float distance = (float) Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
		float speedScale = (distance/400 > 1)? 1:distance/400;	
		float horizontalForce = 0;
		float verticalForce = 0;
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			if (distance > 0) {
				/* disabled sketchy movement */
				horizontalForce += getV() * (xDistance/distance) * speedScale;
				verticalForce += getV() * (yDistance/distance) * speedScale;
				//verticalForce += getV();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (distance > 0) {				
				/* disabled sketchy movement */
				horizontalForce -= getV() * (xDistance/distance) * speedScale;
				verticalForce -= getV() * (yDistance/distance) * speedScale;
				//verticalForce -= getV();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (distance > 0) {
				//horizontalForce -= getV();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			if (distance > 0) {
				//horizontalForce += getV();
			}
		}	
		getBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);	
		
		// update weapon spread
		weapon.updateSpread();
		
		// try to shoot if left mouse button pressed
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			weapon.shoot(this);
		}
	} // update
	
	/**
	 * Rotates this player to face the given world point.
	 * @param targetX The x-coordinate in the world to face.
	 * @param targetY The y-coordinate in the world to face.
	 */
	public void rotateToPosition(float targetX, float targetY){
		setRotation(-(float) Math.toDegrees(Math.atan2(getX() - targetX, getY() - targetY)));
	} // rotateToPosition
	
	/**
	 * Damages this player.
	 * @param damageAmount The amount of damage to inflict.
	 * @param source The source of the damage.
	 */
	public void damage(int damageAmount, Weapon source) {

		// damage armor with a percentage of the source's power
		if (hasArmor) {
			int armorDamage = (int) (source.getPower() - source.getPower() * source.getPenetration());
			if (armor - armorDamage < 0) {
				armor = 0;
				health -= (armor - armorDamage);
			} else {
				armor -= armorDamage;
			} // else

			health -= (source.getPower() * source.getPenetration());
		} else {
			health -= source.getPower();
		} // else

		if (health < 0) {
			setAlive(false);
		} // if
	} // damage

	public String getPlayerName() {
		return playerName;
	} // getPlayerName

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	} // setPlayerName

	public boolean isAlive() {
		return alive;
	} // isAlive

	public void setAlive(boolean alive) {
		this.alive = alive;
	} // setAlive

	public Weapon getWeapon() {
		return weapon;
	} // getWeapon

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	} // setWeapon

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Vector2 getMousePos() {
		return mousePos;
	}

	public void setMousePos(Vector2 mousePos) {
		this.mousePos = mousePos;
	}

	public boolean isHasArmor() {
		return hasArmor;
	}

	public void setHasArmor(boolean hasArmor) {
		this.hasArmor = hasArmor;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
} // Player
