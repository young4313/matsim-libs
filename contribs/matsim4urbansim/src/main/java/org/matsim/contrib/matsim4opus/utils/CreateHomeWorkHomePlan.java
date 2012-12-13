package org.matsim.contrib.matsim4opus.utils;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.contrib.matsim4opus.constants.InternalConstants;
import org.matsim.core.api.experimental.facilities.ActivityFacility;
import org.matsim.core.population.ActivityImpl;
import org.matsim.core.population.PlanImpl;


public class CreateHomeWorkHomePlan {
	
	/**
	 * Helper method to start a plan by inserting the home location.  This is really only useful together with "completePlanToHwh",
	 * which completes the plan, and benefits from the fact that the Strings for the "home" and the "work" act are now concentrated
	 * here.
	 *
	 * @param plan
	 * @param homeCoord
	 * @param homeLocation
	 *
	 * @author nagel
	 */
	public static void makeHomePlan( PlanImpl plan, Coord homeCoord, ActivityFacility homeLocation) {
		ActivityImpl act = plan.createAndAddActivity( InternalConstants.ACT_HOME, homeCoord) ;
		act.setFacilityId( homeLocation.getId() );	// tnicolai: added facility id to compute zone2zone trips
	}

	/**
	 * Helper method to complete a plan with *wh in a consistent way.  Assuming that the first activity is the home activity.
	 *
	 * @param plan
	 * @param workCoord
	 * @param jobLocation
	 *
	 * @author nagel
	 */
	public static void completePlanToHwh ( PlanImpl plan, Coord workCoord, ActivityFacility jobLocation ) {
		
		// complete the first activity (home) by setting end time. 
		ActivityImpl act = (ActivityImpl)plan.getFirstActivity();
		act.setEndTime( 7.*3600. ) ; // tnicolai: make configurable: see actType1.setOpeningTime(7*3600)
		// gather coordinate and facility id needed for last activity
		Coord homeCoord = act.getCoord();
		Id homeId = act.getFacilityId();
		
		// set Leg
		plan.createAndAddLeg(TransportMode.car);
		
		// set second activity (work)
		act = plan.createAndAddActivity( InternalConstants.ACT_WORK, workCoord );
		act.setFacilityId( jobLocation.getId() );
		act.setMaximumDuration( 8.*3600. ) ; // tnicolai: make configurable: actType1.setTypicalDuration(8*60*60);
		
		// set Leg
		plan.createAndAddLeg(TransportMode.car) ;
		
		// set last activity (=first activity) and complete home-work-home plan.
		plan.createAndAddActivity( InternalConstants.ACT_HOME, homeCoord );
		act = (ActivityImpl)plan.getLastActivity();
		act.setFacilityId( homeId );
	}

}
