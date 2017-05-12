package net.colinjohnson.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon extends Entity{
	public static final int COST_GLOCK = 200;

	private boolean isHeld = true;
	private String owner = ""; // player who bought it
	
	private double power = 0; // max damage	
	private double penetration = 0; // % power to surpass armor 
	private double fallOffRate = 0; // decrease in power per tick
	private double reloadTime = 0; // time to reload	
	private double velocity = 0; // bullet velocity
	private float movementSpeed = 0; // % of max movement speed
	private int fireRate = 0; // firing delay (ms)
	private int lastFired = 0; // time when gun was last fired (ms)	
	private double spread = 0; // max spread (degrees)
	private int capacity = 0; // bullets per magazine
	private int ammo = 0; // bullets remaining aside
	private int cost = 0; // buy cost
	private int killAward = 0; // money awarded for kill
	private int bulletsRemaining = 0; // bullets left in current magazine
	

	public static enum Gun {
		
		// pistols
		glock,
		usps,
		p2000,
		p250,
		tec9,
		cz,
		fiveseven,
		
		// smgs
		mp9,
		mac10,
		mp7,
		ppbizon,
		ump45,
		
		// rifles
		ak47,
		m4a4,
		m4a1s,
		galil,
		famas,
		awp,
		sg553,
		ssg08,
		scar20,
		g3sg1,
		
		// misc
		zeus
		
	}
	
	public Weapon(float x, float y, Gun gunType){
		super(x, y, "Gun");
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
		case cz:
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
			reloadTime = 0; // time to reload	
			velocity = 0; // bullet velocity
			movementSpeed = 0; // % of max movement speed
			fireRate = 100; // firing delay (ms)
			lastFired = 0; // time when gun was last fired (ms)	
			spread = 0; // max spread (degrees)
			capacity = 0; // bullets per magazine
			ammo = 0; // bullets remaining aside
			cost = 0; // buy cost
			killAward = 0; // money awarded for kill
			bulletsRemaining = 0; // bullets left in current magazine
			
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

	public int getLastFired() {
		return lastFired;
	} // getLastFired

	public void setLastFired(int lastFired) {
		this.lastFired = lastFired;
	} // setLastFired

	public int getBulletsRemaining() {
		return bulletsRemaining;
	} // getBulletsRemaining

	public void setBulletsRemaining(int bulletsRemaining) {
		this.bulletsRemaining = bulletsRemaining;
	} // setBulletsRemaining

	public double getSpread() {
		return spread;
	} // getSpread

	public void setSpread(double spread) {
		this.spread = spread;
	} // getSpread
} // Gun
