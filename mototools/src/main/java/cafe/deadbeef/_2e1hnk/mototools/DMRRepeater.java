package cafe.deadbeef._2e1hnk.mototools;

import cafe.deadbeef._2e1hnk.mototools.network.AbstractNetwork;

public class DMRRepeater extends Repeater {
	public int colourCode;
	public AbstractNetwork network;
	
	public DMRRepeater(String name, Double output, Double input, int colourCode, AbstractNetwork network, boolean addToScanList) {
		super();
		this.name = name;
		this.output = output;
		this.input = input;
		this.colourCode = colourCode;
		this.network = network;
		this.addToScanList = addToScanList;
	}

	public int getColourCode() {
		return colourCode;
	}

	public void setColourCode(int colourCode) {
		this.colourCode = colourCode;
	}

	public AbstractNetwork getNetwork() {
		return network;
	}

	public void setNetwork(AbstractNetwork network) {
		this.network = network;
	}
}
