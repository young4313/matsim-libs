/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2012 by the members listed in the COPYING,        *
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

package playground.mmoyo.utils.counts;

import org.matsim.api.core.v01.Id;

public class TabularCountRecord {
	Id lineId;
	char direction;
	String stop;
	double count;
		 
	public TabularCountRecord (final Id lineId, final char direction, final String stop, final double count){
		this.lineId = lineId; 
		this.direction = direction; 
		this.stop = stop;
		this.count = count; 
	}

	public Id getLineId() {
		return lineId;
	}
	
	public char getDirection() {
		return direction;
	}
	
	public String getStop() {
		return stop;
	}
	
	public double getCount() {
		return count;
	}
 	
}
