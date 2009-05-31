/* *********************************************************************** *
 * project: org.matsim.*
 * PopulationWriterHandlerImplV4Test.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2009 by the members listed in the COPYING,        *
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

package org.matsim.core.population;

import org.matsim.api.basic.v01.TransportMode;
import org.matsim.api.basic.v01.population.BasicPopulationWriter;
import org.matsim.core.api.network.Link;
import org.matsim.core.api.population.Leg;
import org.matsim.core.api.population.Person;
import org.matsim.core.api.population.Plan;
import org.matsim.core.api.population.Population;
import org.matsim.core.api.population.PopulationBuilder;
import org.matsim.core.api.population.Route;
import org.matsim.core.basic.v01.IdImpl;
import org.matsim.core.gbl.Gbl;
import org.matsim.core.network.MatsimNetworkReader;
import org.matsim.core.network.NetworkLayer;
import org.matsim.core.population.routes.GenericRouteImpl;
import org.matsim.testcases.MatsimTestCase;

public class PopulationWriterHandlerImplV4Test extends MatsimTestCase {

	public void testWriteGenericRoute() {
		super.loadConfig(null);
		NetworkLayer network = new NetworkLayer();
		new MatsimNetworkReader(network).readFile("test/scenarios/equil/network.xml");
		Link link1 = network.getLinks().get(new IdImpl(1));
		Link link2 = network.getLinks().get(new IdImpl(2));
		Gbl.createWorld().setNetworkLayer(network);
		
		Population pop = new PopulationImpl();
		PopulationBuilder pb = pop.getPopulationBuilder();
		Person person = pb.createPerson(new IdImpl(1));
		Plan plan = pb.createPlan(person);
		plan.addActivity(pb.createActivityFromLinkId("h", link1.getId()));
		Leg leg = pb.createLeg(TransportMode.undefined);
		Route route = new GenericRouteImpl(link1, link2);
		route.setTravelTime(123);
		route.setDistance(9876.54);
		leg.setRoute(route);
		plan.addLeg(leg);
		plan.addActivity(pb.createActivityFromLinkId("h", new IdImpl(1)));
		person.addPlan(plan);
		pop.getPersons().put(person.getId(), person);
		
		String filename = getOutputDirectory() + "population.xml";
		new BasicPopulationWriter(pop).writeV4(filename);
		
		Population pop2 = new PopulationImpl();
		new MatsimPopulationReader(pop2, network).readFile(filename);
		Person person2 = pop2.getPersons().get(new IdImpl(1));
		Leg leg2 = (Leg) person2.getPlans().get(0).getPlanElements().get(1);
		Route route2 = leg2.getRoute();
		assertEquals(123, route2.getTravelTime(), EPSILON); // if this succeeds, we know that writing/reading the data works
		assertEquals(9876.54, route2.getDistance(), EPSILON);
	}
	
}
