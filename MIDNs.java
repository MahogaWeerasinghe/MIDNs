
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * MIDNs - a robot by (your name here)
 */
public class MIDNs extends AlphaBot
{
	int dist = 50;
	boolean peek; // Don't turn if there's a robot there
	double moveAmount;
	//int trigger;
	/**
	 * run: MIDNs's default behavior
	 */
	public void run() {
			setBodyColor(Color.blue);
			setGunColor(Color.blue);
			setRadarColor(Color.black);
			setScanColor(Color.yellow);
			
		//	trigger = 80;
			
		/*	addCustomEvent(new Condition("triggerhit") {
			public boolean test() {
				return (getEnergy() <= trigger);
			}
		});*/
			moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
			peek = false;
			turnLeft(getHeading() % 90);
		    ahead(moveAmount);
			peek = true;
			turnGunRight(90);
			turnRight(90);
		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
			
			peek = true;
			ahead(moveAmount);
			peek = false;
			turnRight(90);
			
		  
				
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		//fire(1);
			if (e.getDistance() < 50 && getEnergy() > 50) {
			fire(3);
		} // otherwise, fire 1.
		else {
			fire(1);
		}
		// Call scan again, before we turn the gun
		fire(2);
		if (peek) {
			scan();
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		//back(10);
		//turnLeft(90 - e.getBearing());
		turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

		ahead(dist);
		dist *= -1;
		scan();
	}
	public void onHitRobot(HitRobotEvent e) {
		/*double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

		turnGunRight(turnGunAmt);
		fire(1);*/
		
	/*	if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(3);
		}
		if (e.isMyFault()) {
			turnRight(10);
		}*/
		
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(100);
		}
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);

		
	
	}	
	
	/*public void onCustomEvent(CustomEvent e) {
		// If our custom event "triggerhit" went off,
		if (e.getCondition().getName().equals("triggerhit")) {
			// Adjust the trigger value, or
			// else the event will fire again and again and again...
			trigger -= 20;
			out.println("Ouch, down to " + (int) (getEnergy() + .5) + " energy.");
			// move around a bit.
			turnLeft(65);
			ahead(100);
		}
	}*/
}
