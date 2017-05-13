package net.colinjohnson.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Weapon extends Entity{
	private boolean isHeld = true;
	private String owner = ""; // player who bought it
	
	private double power = 0; // max damage	
	private double penetration = 0; // % power to surpass armor 
	private double fallOffRate = 0; // decrease in power per tick
	private double reloadTime = 0; // time to reload	
	private double velocity = 0; // bullet velocity
	private float movementSpeed = 0; // % of max movement speed while holding
	private int fireRate = 0; // firing delay (ms)
	private long lastFired = 0; // time when gun was last fired (ms)	
	private float currentSpread = 0; // increases as gun is fired for longer
	private int spread = 0; // max spread (degrees)
	private int spreadR = 0; // max spread (degrees) when running
	private int spreadW = 0; // max spread (degrees) when walking
	private int capacity = 0; // bullets per magazine
	private int ammo = 0; // bullets remaining aside
	private int cost = 0; // buy cost
	private int killAward = 0; // money awarded for kill
	private int bulletsRemaining = 0; // bullets left in current magazine
	private float recoveryTime = 150; // time to reset accuracy (ms)
	private float spreadRate = 0.5f; // rate at which to change spread
	

	public static enum Gun {
		
		// pistols
		glock {int cost = 10;},
		usps {int cost = 10;},
		p2000 {int cost = 10;},
		p250 {int cost = 10;},
		tec9 {int cost = 10;},
		cz75 {int cost = 10;},
		fiveseven {int cost = 10;},
		dualberettas {int cost = 10;},
		deserteagle {int cost = 10;},
		
		// smgs
		mp9 {int cost = 10;},
		mac10 {int cost = 10;},
		mp7 {int cost = 10;},
		ppbizon {int cost = 10;},
		ump45 {int cost = 10;},
		
		// rifles
		ak47 {int cost = 2700;},
		m4a4 {int cost = 10;},
		m4a1s {int cost = 10;},
		galil {int cost = 10;},
		famas {int cost = 10;},
		awp {int cost = 10;},
		sg553 {int cost = 10;},
		ssg08 {int cost = 10;},
		scar20 {int cost = 10;},
		g3sg1 {int cost = 10;},
		
		// heavy
		m249 {int cost = 10;},
		negev {int cost = 10;},
		nova {int cost = 10;},
		xm1014 {int cost = 10;},
		mag7 {int cost = 10;},
		
		// misc
		zeus {int cost = 10;}	
	}
	
	/**
	 * Fires a shot by adding an appropriate projectile to the map.
	 */
	public void shoot(PlayerEntity player) {
		
		// shoot only of enough time has elapsed
		if (lastFired + fireRate < System.currentTimeMillis()) {
			
			// remove bullet
			if(bulletsRemaining > 0){
				bulletsRemaining--;
			} else if (bulletsRemaining <= 0) {
				bulletsRemaining = capacity;
				ammo -= capacity;
				if (ammo <= 0) {
					bulletsRemaining += ammo;
					ammo = 0;
				}
				if (bulletsRemaining <= 0) {
					ammo = 0;
					bulletsRemaining = 0;
				}
			}
		
			// create projectile if there are bullets available
			if (bulletsRemaining > 0) {
				getMapRef().getProjectiles().add(new Projectile(getMapRef(), player, this, (player.getRotation() - 90 + MathUtils.random(-currentSpread/2, currentSpread/2))));
				lastFired = System.currentTimeMillis();
			}
		}
	} // shoot
	
	/**
	 * Increases or decreases spread depending on if this weapon has been fired recently. 
	 * Spread decreases at twice the rate it increases (<code>spreadRate</code>).
	 */
	public void updateSpread(){
		if (System.currentTimeMillis() - lastFired < recoveryTime) {
			currentSpread = (currentSpread < spread)? currentSpread + spreadRate: spread; 
		} else {
			currentSpread = (currentSpread > 0)? currentSpread - spreadRate * 2: 0; 
		}	
	}
	
	public Weapon(Map map, float x, float y, Gun gunType){
		super(map, x, y, "Gun");
		switch (gunType) {
		
		// pistols
		case glock:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case p2000:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case p250:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case tec9:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case cz75:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case dualberettas:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;		
		case fiveseven:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case deserteagle:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
			
		// smgs
		case mp9:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case mac10:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case mp7:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case ppbizon:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case ump45:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
			
		// rifles
		case ak47:
			power = 0; // max damage
			power = 36; // max damage
			penetration = 0.775; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 2500; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 100; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 15; // max spread (degrees)
			capacity = 30; // bullets per magazine
			ammo = 90; // bullets remaining aside
			cost = 2700; // buy cost
			killAward = 300; // money awarded for kill
			bulletsRemaining = 30; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/AK47.png")));
			break;
		case m4a4:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case m4a1s:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case galil:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case famas:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case awp:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case sg553:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case ssg08:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case scar20:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
		case g3sg1:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
			
		// misc
		case zeus:
			power = 0; // max damage
			penetration = 0; // % power to surpass armor 
			fallOffRate = 0; // decrease in power per tick
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 0; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
			setSprite(new Sprite(new Texture("sprites/Missing.png")));
			break;
			
		default:
			break;
		} // switch
		getSprite().setScale(2f);
	} // Weapon
	
	@Override
	void defineBody() {
		// TODO Auto-generated method stub
	} // defineBody

	public boolean isHeld() {
		return isHeld;
	} // isHeld

	public void setHeld(boolean isHeld) {
		this.isHeld = isHeld;
	} // setHeld

	public String getOwner() {
		return owner;
	} // getOwner

	public void setOwner(String owner) {
		this.owner = owner;
	} // setOwner

	public double getPower() {
		return power;
	} // getPower

	public void setPower(double power) {
		this.power = power;
	} // setPower

	public double getPenetration() {
		return penetration;
	} // getPenetration

	public void setPenetration(double penetration) {
		this.penetration = penetration;
	} // setPenetration

	public double getVelocity() {
		return velocity;
	} // getVelocity

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	} // setVelocity

	public double getFallOffRate() {
		return fallOffRate;
	} // getFallOffRate

	public void setFallOffRate(double fallOffRate) {
		this.fallOffRate = fallOffRate;
	} // setFallOffRate

	public double getReloadTime() {
		return reloadTime;
	} // getReloadTime

	public void setReloadTime(double reloadTime) {
		this.reloadTime = reloadTime;
	} // setReloadTime

	public int getFireRate() {
		return fireRate;
	} // getFireRate

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	} // setFireRate

	public int getCapacity() {
		return capacity;
	} // getCapacity

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	} // setCapacity

	public int getAmmo() {
		return ammo;
	} // getAmmo

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	} // setAmmo

	public int getCost() {
		return cost;
	} // getCost

	public void setCost(int cost) {
		this.cost = cost;
	} // setCost

	public int getKillAward() {
		return killAward;
	} // getKillAward

	public void setKillAward(int killAward) {
		this.killAward = killAward;
	} // setKillAward

	public long getLastFired() {
		return lastFired;
	} // getLastFired

	public void setLastFired(long lastFired) {
		this.lastFired = lastFired;
	} // setLastFired

	public int getBulletsRemaining() {
		return bulletsRemaining;
	} // getBulletsRemaining

	public void setBulletsRemaining(int bulletsRemaining) {
		this.bulletsRemaining = bulletsRemaining;
	} // setBulletsRemaining

	public int getSpread() {
		return spread;
	} // getSpread

	public void setSpread(int spread) {
		this.spread = spread;
	} // getSpread
} // Gun
