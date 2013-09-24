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
package playground.droeder.buildingEnergy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.matsim.analysis.LegHistogram;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.core.api.experimental.events.AgentArrivalEvent;
import org.matsim.core.api.experimental.events.AgentDepartureEvent;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.OutputDirectoryLogging;
import org.matsim.core.population.LegImpl;
import org.matsim.core.population.MatsimPopulationReader;
import org.matsim.core.population.PopulationImpl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.population.algorithms.PersonAlgorithm;

/**
 * @author droeder
 *
 */
public abstract class Plans2LegHistogram {

	private static final Logger log = Logger
			.getLogger(Plans2LegHistogram.class);
	
	public static void main(String[] args) {
		args = new String[]{
				"E:\\VSP\\svn\\studies\\countries\\de\\berlin\\plans\\baseplan_900s.xml.gz",
				"C:\\Users\\Daniel\\Desktop\\buildingEnergy\\compareData\\"
		};
		String plansfile = args[0];
		String outputpath = new File(args[1]).getAbsolutePath() + System.getProperty("file.separator");
		try {
			OutputDirectoryLogging.initLoggingWithOutputDirectory(outputpath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		OutputDirectoryLogging.catchLogEntries();
		log.info("plansfile: " + plansfile);
		log.info("outputpath: " + outputpath);
		
		final LegHistogram histo = new LegHistogram(300);
		Scenario sc = ScenarioUtils.createScenario(ConfigUtils.createConfig());
		((PopulationImpl) sc.getPopulation()).setIsStreaming(true);
		((PopulationImpl) sc.getPopulation()).addAlgorithm(new PersonAlgorithm() {
			
			@Override
			public void run(Person person) {
				List<PlanElement> pe = person.getSelectedPlan().getPlanElements();
				for(int i = 1; i < pe.size(); i += 2){
					LegImpl l = (LegImpl) pe.get(i);
					histo.handleEvent(new AgentDepartureEvent(l.getDepartureTime(), null, null, l.getMode()));
					double arrivaltime = (l.getArrivalTime() == Double.NaN) ? l.getDepartureTime() + l.getTravelTime() : l.getArrivalTime();
					histo.handleEvent(new AgentArrivalEvent(arrivaltime, null, null, l.getMode()));
				}
			}
		});
		
		new MatsimPopulationReader(sc).readFile(plansfile);
		
		histo.writeGraphic(outputpath + "legHistogram_all.png");
		log.info(outputpath + "legHistogram_all.png written.");
		for(String mode : histo.getLegModes()){
			histo.writeGraphic(outputpath + "legHistogram_" + mode + ".png", mode);
			log.info(outputpath + "legHistogram_" + mode + ".png written.");
		}
		OutputDirectoryLogging.closeOutputDirLogging();
	}
}

