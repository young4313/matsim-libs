package playground.dhosse.gap.scenario.mid;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.matsim.core.utils.misc.Time;

import playground.dhosse.gap.Global;
import playground.dhosse.scenarios.generic.utils.ActivityTypes;

public class MiDTravelChain {
	
	private LinkedList<MiDTravelStage> stages = new LinkedList<>();
	private double c = 0.;
	private double maxD = 0.;
	private double weight;
	
	public MiDTravelChain(String pId,String[] legs, String[] acts, String[] times, String[] lengths, String weight){
		
		String mainAct = null;
		
		//acts.length() = legs.length + 1 (because of first activity: home / other)
		for(int i = 0; i < legs.length; i++){
			
			setMainActType(mainAct, acts[i]);
			if(isPrimaryActType(acts[i + 1])){
			if(!lengths[i].equals("NULL")){

				double d = 1000*Double.parseDouble(lengths[i].replace(",", "."));
				if(d > this.c)
				this.c = d;
				
			}
			} else{
				
				if(!lengths[i].equals("NULL")){
					
					double d = 1000*Double.parseDouble(lengths[i].replace(",", "."));
					if(d > maxD){
						maxD = d;
					}
					
				}
				
			}
			
			this.weight = Double.parseDouble(weight);
			this.stages.addLast(new MiDTravelStage(legs[i], acts[i], acts[i+1], Time.parseTime(times[i].split("-")[0]), Time.parseTime(times[i].split("-")[1]),lengths[i]));
			
		}
		
	}
	
	private String setMainActType(String mainAct, String act){
		
		if(mainAct == null){
			
			return act;
			
		} else {
			
			if(mainAct.equals(ActivityTypes.WORK) || mainAct.equals(ActivityTypes.EDUCATION)){
				
				return mainAct;
				
			} else {
				
				return "";
				
			}
			
		}
		
	}
	
	private String getMainAct(String act1, String act2){
		
		Set<String> acts = new HashSet<>();
		acts.add(ActivityTypes.LEISURE);
		acts.add(ActivityTypes.SHOPPING);
		acts.add(ActivityTypes.OTHER);
		
		return "";
		
	}
	
	private boolean isPrimaryActType(String actType){
		
		return(actType.equals(Global.ActType.home.name()) || actType.equals(Global.ActType.work.name()) || actType.equals(Global.ActType.education.name()));
		
	}
	
	public double getC(){
		return this.c;
	}
	
	public double getMaxD(){
		return this.maxD;
	}
	
	public LinkedList<MiDTravelStage> getStages(){
		return this.stages;
	}
	
	public static class MiDTravelStage{
		
		private String legMode;
		private String previousActType;
		private String nextActType;
		private double departureTime;
		private double arrivalTime;
		private double distance;
		
		public MiDTravelStage(String legMode, String previousActType, String nextActType, double departureTime, double arrivalTime, String distance){
			
			this.legMode = legMode;
			this.previousActType = previousActType;
			this.nextActType = nextActType;
			this.departureTime = departureTime;
			this.arrivalTime = arrivalTime;
			if(this.arrivalTime < this.departureTime){
				this.arrivalTime += 24 * 3600;
			}
			if(!distance.equals("NULL")){
				this.distance = Double.parseDouble(distance.replace(",", "."));
			}
			
		}

		public String getLegMode() {
			return legMode;
		}

		public void setLegMode(String legMode) {
			this.legMode = legMode;
		}

		public String getPreviousActType() {
			return previousActType;
		}

		public void setPreviousActType(String previousActType) {
			this.previousActType = previousActType;
		}

		public String getNextActType() {
			return nextActType;
		}

		public void setNextActType(String nextActType) {
			this.nextActType = nextActType;
		}

		public double getDepartureTime() {
			return departureTime;
		}

		public void setDepartureTime(double departureTime) {
			this.departureTime = departureTime;
		}

		public double getArrivalTime() {
			return arrivalTime;
		}

		public void setArrivalTime(double arrivalTime) {
			this.arrivalTime = arrivalTime;
		}

		public double getDistance() {
			return distance;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}
		
	}

}
