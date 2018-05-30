package cafe.deadbeef._2e1hnk.mototools;

import cafe.deadbeef._2e1hnk.mototools.radioprofiles.RadioProfile;

public class MotoToolsJob {
	private Integer radioId;
	private RadioProfile radioProfile;
	private Filter filter;
	
	public MotoToolsJob() {
		
	}
	
	public MotoToolsJob(Integer radioId, RadioProfile radioProfile, Filter filter) {
		this.setRadioId(radioId);
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
	public Filter getFilter() {
		return filter;
	}
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
	
}
