package cafe.deadbeef._2e1hnk.mototools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter {
	public static final boolean WHITELIST = true; // Only include entries that match this filter
	public static final boolean BLACKLIST = false; // Only include entries the DON'T match this filter (default)
	
	private String filterName;

	public boolean filterType = BLACKLIST;

	/*
	 * filter will look something like:
	 * 
	 * "region" {"SCOT","NI"}
	 * "talkgroup" {"850"}
	 * 
	 * If the filter type is BLACKLIST (default) then this would exclude Scottish
	 * and NI analogue repeaters and would also exclude TG850 from any DMR
	 * repeaters.
	 * 
	 * Valid keys are
	 *  - region
	 */

	private Map<String, List<String>> filter = new HashMap<String, List<String>>();
	
	private Map<String, Integer> attributes = new HashMap<String, Integer>();

	public Filter(String filterName) {
		this.setFilterName(filterName);
	}

	/**
	 * This method asks the question 'Should I include this item' - not necessarily
	 * 'is this item in the filter'
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean permits(String key, String value) {
		if (this.filter.get(key).contains(value)) {
			// Item is present in filter, so return the filterType
			return filterType;
		}
		return !filterType;
	}

	public boolean isFilterType() {
		return filterType;
	}

	public void setFilterType(boolean filterType) {
		this.filterType = filterType;
	}

	public Map<String, List<String>> getFilter() {
		return filter;
	}

	public void setFilter(Map<String, List<String>> filter) {
		this.filter = filter;
	}

	public void addFilter(String key, List<String> values) {
		this.filter.put(key, values);
	}

	public void addToFilterKey(String key, String value) {
		List<String> filterContents = this.filter.get(key);
		try {
			filterContents.add(value);
		} catch (NullPointerException e) {
			filterContents = new ArrayList<String>();
			filterContents.add(value);
		}
		this.filter.put(key, filterContents);
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	
	public void addAttribute(String attributeName, int attributeValue) {
		this.attributes.put(attributeName, attributeValue);
	}
	
	public int getAttribute(String attributeName) {
		return this.attributes.get(attributeName);
	}
}
