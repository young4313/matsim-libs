/* *********************************************************************** *
 * project: org.matsim.*
 * Daganzo2012Vis
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
package playground.dgrether.daganzo2012;

import org.matsim.run.OTFVis;


/**
 * @author dgrether
 *
 */
public class Daganzo2012Vis {

	public static void main(String[] args) {
		String file = "/media/data/work/repos/runs-svn/run1150/ITERS/it.20/1150.20.otfvis.mvi";
		OTFVis.playMVI(file);
	}

}
