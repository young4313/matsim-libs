/*
 *  *********************************************************************** *
 *  * project: org.matsim.*
 *  * ExampleWithinDayControllerTest.java
 *  *                                                                         *
 *  * *********************************************************************** *
 *  *                                                                         *
 *  * copyright       : (C) 2015 by the members listed in the COPYING, *
 *  *                   LICENSE and WARRANTY file.                            *
 *  * email           : info at matsim dot org                                *
 *  *                                                                         *
 *  * *********************************************************************** *
 *  *                                                                         *
 *  *   This program is free software; you can redistribute it and/or modify  *
 *  *   it under the terms of the GNU General Public License as published by  *
 *  *   the Free Software Foundation; either version 2 of the License, or     *
 *  *   (at your option) any later version.                                   *
 *  *   See also COPYING, LICENSE and WARRANTY file                           *
 *  *                                                                         *
 *  * ***********************************************************************
 */

package org.matsim.withinday.controller;

import org.junit.Rule;
import org.junit.Test;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.core.config.Config;
import org.matsim.core.config.groups.ControlerConfigGroup;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.testcases.MatsimTestUtils;

public class ExampleWithinDayControllerTest {

	@Rule
	public MatsimTestUtils utils = new MatsimTestUtils();

	@Test
	public void testRun() {
		Config config = utils.loadConfig("test/scenarios/equil/config.xml");
		config.controler().setLastIteration(1);
		config.controler().setRoutingAlgorithmType(ControlerConfigGroup.RoutingAlgorithmType.Dijkstra);
		Scenario scenario = ScenarioUtils.loadScenario(config);

		//plans are missing departure times, so clear all routes to re-route all legs and provide some departure times
		scenario.getPopulation()
				.getPersons()
				.values()
				.stream()
				.flatMap(p -> p.getSelectedPlan().getPlanElements().stream())
				.filter(Leg.class::isInstance)
				.forEach(planElement -> ((Leg)planElement).setRoute(null));

		final Controler controler = new Controler(scenario);
		ExampleWithinDayController.configure(controler);
		controler.run();
	}

}
