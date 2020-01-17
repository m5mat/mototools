package cafe.deadbeef._2e1hnk.mototools;

import cafe.deadbeef._2e1hnk.mototools.radioprofiles.RadioProfile;

public class MotoToolsJob {
	private Integer radioId;
	private RadioProfile radioProfile;
	private String radioHumanName;
	private Filter filter;
	
	public MotoToolsJob() {
		
	}
	
	public MotoToolsJob(Integer radioId, String radioHumanName, RadioProfile radioProfile, Filter filter) {
		this.setRadioId(radioId);
		this.setRadioHumanName(radioHumanName);
		this.setRadioProfile(radioProfile);
		this.setFilter(filter);
	}
	
	public Integer getRadioId() {
		return radioId;
	}
	public void setRadioId(Integer radioId) {
		this.radioId = radioId;
	}
	public RadioProfile getRadioProfile() {
		return radioProfile;
	}
	public void setRadioProfile(RadioProfile radioProfile) {
		this.radioProfile = radioProfile;
	}
	public String getRadioHumanName() {
		return radioHumanName;
	}

	public void setRadioHumanName(String radioHumanName) {
		this.radioHumanName = radioHumanName;
	}

	public Filter getFilter() {
		return filter;
	}
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
	
}
