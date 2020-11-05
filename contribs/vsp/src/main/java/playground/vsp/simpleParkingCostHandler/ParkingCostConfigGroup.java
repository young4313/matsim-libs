/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2019 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package playground.vsp.simpleParkingCostHandler;

import org.matsim.core.config.ReflectiveConfigGroup;

/**
 * 
 * @author ikaddoura
 */

public class ParkingCostConfigGroup extends ReflectiveConfigGroup {
	public static final String GROUP_NAME = "parkingCost" ;

	private static final String MODE = "mode";
	private static final String DAILY_PARKING_COST_LINK_ATTRIBUTE_NAME = "dailyParkingCostLinkAttributeName";
	private static final String FIRST_HOUR_PARKING_COST_LINK_ATTRIBUTE_NAME = "firstHourParkingCostLinkAttributeName";
	private static final String EXTRA_HOUR_PARKING_COST_LINK_ATTRIBUTE_NAME = "extraHourParkingCostLinkAttributeName";
	private static final String MAX_DAILY_PARKING_COST_LINK_ATTRIBUTE_NAME = "maxDailyParkingCostLinkAttributeName";
	private static final String HOME_ACTIVITY_PREFIX_TO_BE_EXCLUDED_FROM_PARKING_COSTS = "homeActivityPrefixToBeExcludedFromParkingCost";
	private static final String MAX_PARKING_DURATION_ATTRIBUTE_NAME = "maxParkingDurationAttributeName";
	private static final String PARKING_PENALTY_ATTRIBUTE_NAME = "parkingPenaltyAttributeName";
	private static final String RESIDENTIAL_PARKING_FEE_PER_DAY = "residentialParkingFeePerDay";
	
	public ParkingCostConfigGroup() {
		super(GROUP_NAME);
	}
	
	private String mode = "car";
	private String dailyParkingCostLinkAttributeName = "dailyPCost";
	private String firstHourParkingCostLinkAttributeName = "oneHourPCost";
	private String extraHourParkingCostLinkAttributeName = "extraHourPCost";
	private String maxDailyParkingCostLinkAttributeName = "maxDailyPCost";
	private String homeActivityPrefixToBeExcludedFromParkingCost = "home";
	private String maxParkingDurationAttributeName = "maxPDuration";
	private String parkingPenaltyAttributeName = "penalty";
	private String residentialParkingFeePerDay = "residentialPFee";

	@StringGetter( MODE )
	public String getMode() {
		return mode;
	}
	
	@StringSetter( MODE )
	public void setMode(String mode) {
		this.mode = mode;
	}

	@StringGetter( DAILY_PARKING_COST_LINK_ATTRIBUTE_NAME )
	public String getDailyParkingCostLinkAttributeName() {
		return dailyParkingCostLinkAttributeName;
	}

	@StringSetter( DAILY_PARKING_COST_LINK_ATTRIBUTE_NAME )
	public void setDailyParkingCostLinkAttributeName(String dailyParkingCostLinkAttributeName) {
		this.dailyParkingCostLinkAttributeName = dailyParkingCostLinkAttributeName;
	}

	@StringGetter( FIRST_HOUR_PARKING_COST_LINK_ATTRIBUTE_NAME )
	public String getFirstHourParkingCostLinkAttributeName() {
		return firstHourParkingCostLinkAttributeName;
	}

	@StringSetter( FIRST_HOUR_PARKING_COST_LINK_ATTRIBUTE_NAME )
	public void setFirstHourParkingCostLinkAttributeName(String firstHourParkingCostLinkAttributeName) {
		this.firstHourParkingCostLinkAttributeName = firstHourParkingCostLinkAttributeName;
	}

	@StringGetter( EXTRA_HOUR_PARKING_COST_LINK_ATTRIBUTE_NAME )
	public String getExtraHourParkingCostLinkAttributeName() {
		return extraHourParkingCostLinkAttributeName;
	}

	@StringSetter( EXTRA_HOUR_PARKING_COST_LINK_ATTRIBUTE_NAME )
	public void setExtraHourParkingCostLinkAttributeName(String extraHourParkingCostLinkAttributeName) {
		this.extraHourParkingCostLinkAttributeName = extraHourParkingCostLinkAttributeName;
	}

	@StringGetter( MAX_DAILY_PARKING_COST_LINK_ATTRIBUTE_NAME )
	public String getMaxDailyParkingCostLinkAttributeName() {
		return maxDailyParkingCostLinkAttributeName;
	}

	@StringSetter( MAX_DAILY_PARKING_COST_LINK_ATTRIBUTE_NAME )
	public void setMaxDailyParkingCostLinkAttributeName(String maxDailyParkingCostLinkAttributeName) {
		this.maxDailyParkingCostLinkAttributeName = maxDailyParkingCostLinkAttributeName;
	}

	@StringGetter( HOME_ACTIVITY_PREFIX_TO_BE_EXCLUDED_FROM_PARKING_COSTS )
	public String getHomeActivityPrefixToBeExcludedFromParkingCost() {
		return homeActivityPrefixToBeExcludedFromParkingCost;
	}

	@StringSetter( HOME_ACTIVITY_PREFIX_TO_BE_EXCLUDED_FROM_PARKING_COSTS )
	public void setHomeActivityPrefixToBeExcludedFromParkingCost(String homeActivityPrefixToBeExcludedFromParkingCost) {
		this.homeActivityPrefixToBeExcludedFromParkingCost = homeActivityPrefixToBeExcludedFromParkingCost;
	}

	@StringGetter( MAX_PARKING_DURATION_ATTRIBUTE_NAME )
    public String getMaxParkingDurationAttributeName() {
		return maxParkingDurationAttributeName;
    }

	@StringSetter( MAX_PARKING_DURATION_ATTRIBUTE_NAME )
	public void setMaxParkingDurationAttributeName(String maxParkingDurationAttributeName) {
		this.maxParkingDurationAttributeName = maxParkingDurationAttributeName;
	}

	@StringGetter( PARKING_PENALTY_ATTRIBUTE_NAME )
	public String getParkingPenaltyAttributeName() {
		return parkingPenaltyAttributeName;
	}

	@StringSetter( PARKING_PENALTY_ATTRIBUTE_NAME )
	public void setParkingPenaltyAttributeName(String parkingPenaltyAttributeName) {
		this.parkingPenaltyAttributeName = parkingPenaltyAttributeName;
	}

	@StringGetter( RESIDENTIAL_PARKING_FEE_PER_DAY )
	public String getResidentialParkingFeeAttributeName() {
		return residentialParkingFeePerDay;
	}

	@StringSetter( RESIDENTIAL_PARKING_FEE_PER_DAY )
	public void setResidentialParkingFeeAttributeName(String residentialParkingFeePerDay) {
		this.residentialParkingFeePerDay = residentialParkingFeePerDay;
	}

}

