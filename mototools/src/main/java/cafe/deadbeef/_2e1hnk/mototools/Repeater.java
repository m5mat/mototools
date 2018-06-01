package cafe.deadbeef._2e1hnk.mototools;

import com.google.gson.Gson;

public class Repeater {
	public String name;
	public Double output;
	public Double input;
	public boolean addToScanList;
	
	public Repeater() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getOutput() {
		return output;
	}

	public void setOutput(Double output) {
		this.output = output;
	}

	public Double getInput() {
		return input;
	}

	public void setInput(Double input) {
		this.input = input;
	}

	public boolean isAddToScanList() {
		return addToScanList;
	}

	public void setAddToScanList(boolean addToScanList) {
		this.addToScanList = addToScanList;
	}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
