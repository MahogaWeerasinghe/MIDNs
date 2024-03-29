
import robocode.HitRobotEvent;
//import robocode.Robot;
import robocode.ScannedRobotEvent;
//import robocode.BravoBot;
import robocode.*;

import java.awt.*;


/**
 * Walls - a sample robot by Mathew Nelson, and maintained by Flemming N. Larsen
 * <p>
 * Moves around the outer edge with the gun facing in.
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */
public class MIDNs extends CharlieBot {

	boolean peek; // Don't turn if there's a robot there
	double moveAmount; // How much to move

	/**
	 * run: Move around the walls
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.black);
		setGunColor(Color.red);
		setRadarColor(Color.orange);
		setBulletColor(Color.red);
		setScanColor(Color.cyan);
		
		// Loop forever
	

		// Initialize moveAmount to the maximum possible for this battlefield.
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		// Initialize peek to false
		peek = false;

		// turnLeft to face a wall.
		// getHeading() % 90 means the remainder of
		// getHeading() divided by 90.
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		// Turn the gun to turn right 90 degrees.
		peek = true;
		turnGunRight(180);
		turnRight(90);
		turnGunRight(180);
		while (true) {
			// Look before we turn when ahead() completes.
			peek = true;
			// Move up the wall
			ahead(moveAmount);
			// Don't look now
			peek = false;
			// Turn to the next wall
			turnRight(90);
			turnGunRight(180);
			fire(10);
		}
	}

	/**
	 * onHitRobot:  Move away a bit.
	 */
	public void onHitRobot(HitRobotEvent e) {
		// If he's in front of us, set back up a bit.
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
			
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(100);
		}
		
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(10);
		}
		if (e.isMyFault()) {
			turnRight(10);
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onRobotDeteted(ScannedRobotEvent e) {
		
		fire(10);
		// Note that scan is called automatically when the robot is moving.
		// By calling it manually here, we make sure we generate another scan event if there's a robot on the next
		// wall, so that we do not start moving up it until it's gone.
		if (peek) {
			scan();
			
			
		}
		//turnGunRight(180);
	}
	
	
	
	public void onWin(WinEvent e) {
        // Victory dance
        turnRight(360000000);
    }
}
