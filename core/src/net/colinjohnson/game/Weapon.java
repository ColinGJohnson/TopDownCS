package net.colinjohnson.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon extends Entity{
	public static final int COST_GLOCK = 200;

	private boolean isHeld = true;
	private String owner = ""; // player who bought it
	private double power = 0; // max damage
	private double penetration = 0; // % power to surpass armor 
	private double velocity = 0; // bullet velocity
	private double fallOffRate = 0; // decrease in power per tick
	private double reloadTime = 0; // time to reload
	private double spread = 0; // max spread (degrees)
	private int fireRate = 0; // firing delay (ms)
	private int capacity = 0; // bullets per magazine
	private int ammo = 0; // bullets remaining aside
	private int cost = 0; // buy cost
	private int killAward = 0; // money awarded for kill
	private int lastFired = 0; // time when gun was last fired (ms)
	private int bulletsRemaining = 0; // bullets left in current magazine

	public static enum Gun {
		glock,
		ak47
	}
	
	public Weapon(float x, float y, Gun gunType){
		super(x, y, "Gun");
		switch (gunType) {
		case glock:
			penetration = 47.5;
			power = 28;
			break;
			
		case ak47:
			penetration = 77.5;
			power = 36;
			setSprite(new Sprite(new Texture("AK47.png")));
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
