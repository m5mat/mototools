package cafe.deadbeef._2e1hnk.mototools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import cafe.deadbeef._2e1hnk.mototools.exceptions.ChannelListFullException;
import cafe.deadbeef._2e1hnk.mototools.exceptions.ContactListFullException;
import cafe.deadbeef._2e1hnk.mototools.exceptions.RadioIdOutOfRangeException;
import cafe.deadbeef._2e1hnk.mototools.exceptions.RoamListNotFoundException;
import cafe.deadbeef._2e1hnk.mototools.exceptions.ScanListNotFoundException;
import cafe.deadbeef._2e1hnk.mototools.exceptions.ZoneNotFoundException;
import cafe.deadbeef._2e1hnk.mototools.network.AbstractNetwork;
import cafe.deadbeef._2e1hnk.mototools.network.NetworkInterface;
import cafe.deadbeef._2e1hnk.mototools.network.Salop;
import cafe.deadbeef._2e1hnk.mototools.network.Talkgroup;
import cafe.deadbeef._2e1hnk.mototools.radioprofiles.RadioProfile;
import cafe.deadbeef._2e1hnk.mototools.xml.*;

public class Codeplug {

	public static LTDCODEPLUG codeplug;

	public RadioProfile radioProfile;

	public Codeplug() {
		codeplug = new LTDCODEPLUG();
	}

	public Codeplug(String baseFileName, RadioProfile radioProfile) {
		codeplug = this.loadXml(baseFileName);
		this.setRadioProfile(radioProfile);
	}

	public Codeplug(File baseXmlFile, RadioProfile radioProfile) {
		codeplug = this.loadXml(baseXmlFile);
		this.setRadioProfile(radioProfile);
	}

	public RadioProfile getRadioProfile() {
		return radioProfile;
	}

	public void setRadioProfile(RadioProfile radioProfile) {
		this.radioProfile = radioProfile;
	}

	public void setRadioId(int radioId) throws RadioIdOutOfRangeException {
		if (radioId >= 1 && radioId <= radioProfile.MAX_RADIO_ID) {
			codeplug.getAPPPARTITION().getRADCFGCMPTYPEGRP().getRADCFGCMPTYPE().getRCSUBSCRIBERID()
					.setValue(BigInteger.valueOf(radioId));
		} else {
			throw new RadioIdOutOfRangeException();
		}
	}

	public int getRadioId() {
		return codeplug.getAPPPARTITION().getRADCFGCMPTYPEGRP().getRADCFGCMPTYPE().getRCSUBSCRIBERID().getValue()
				.intValue();
	}

	public int addContact(int radioId, String contactName, boolean privateCall)
			throws RadioIdOutOfRangeException, ContactListFullException {
		if (radioId > radioProfile.MAX_RADIO_ID) {
			throw new RadioIdOutOfRangeException();
		}

		if (contactName.length() > radioProfile.MAX_CONTACT_NAME_LENGTH) {
			contactName = contactName.substring(0, radioProfile.MAX_CONTACT_NAME_LENGTH);
		}

		// find the next ID number
		int entryNumber = 0;

		for (DIGITALUCLDLLTYPE contact : codeplug.getAPPPARTITION().getDIGITALUCLDLHTYPEGRP().getDIGITALUCLDLTTYPE()
				.getDIGITALUCLDLLTYPE()) {
			if (contact.getListLetID().intValue() >= entryNumber) {
				entryNumber = contact.getListLetID().intValue() + 1;
			}
		}

		if (entryNumber >= (radioProfile.MAX_DIGITAL_CONTACTS - 1)) {
			throw new ContactListFullException();
		}

		String callType = "GRPCALL";
		if (privateCall) {
			callType = "PRIVCALL";
		}

		String routeType = "REGULAR";
		String txtMsgAlertType = "MOMENTARY";

		DIGITALUCLDLLTYPE contact = new DIGITALUCLDLLTYPE();
		contact.setListID(BigInteger.valueOf(0));
		contact.setApplicable("Enabled");
		contact.setListLetID(BigInteger.valueOf(entryNumber));

		// Unknown
		DIGITALUCLDLLTYPENTLLISHTYPE content1 = new DIGITALUCLDLLTYPENTLLISHTYPE();
		content1.setListID(BigInteger.valueOf(0));
		content1.setApplicable("Enabled");
		content1.setListLetID(BigInteger.valueOf(entryNumber));
		content1.setValue("NULL");
		contact.setDIGITALUCLDLLTYPENTLLISHTYPE(content1);

		// Unknown
		DIGITALUCLDLLTYPENTLLISHID content2 = new DIGITALUCLDLLTYPENTLLISHID();
		content2.setListID(BigInteger.valueOf(0));
		content2.setApplicable("Enabled");
		content2.setListLetID(BigInteger.valueOf(entryNumber));
		content2.setValue(BigInteger.valueOf(0));
		contact.setDIGITALUCLDLLTYPENTLLISHID(content2);

		// Unknown
		DULLEQ lleq = new DULLEQ();
		lleq.setListID(BigInteger.valueOf(0));
		lleq.setApplicable("Enabled");
		lleq.setListLetID(BigInteger.valueOf(entryNumber));
		lleq.setValue(BigInteger.valueOf(1));
		contact.setDULLEQ(lleq);

		// Call type (Group/Private)
		DUCALLTYPE calltype = new DUCALLTYPE();
		calltype.setListID(BigInteger.valueOf(0));
		calltype.setApplicable("Enabled");
		calltype.setListLetID(BigInteger.valueOf(entryNumber));
		calltype.setValue(callType);
		contact.setDUCALLTYPE(calltype);

		// Unknown
		DUUKPOTCFLG ukPotCflg = new DUUKPOTCFLG();
		ukPotCflg.setListID(BigInteger.valueOf(0));
		ukPotCflg.setApplicable("Enabled");
		ukPotCflg.setListLetID(BigInteger.valueOf(entryNumber));
		ukPotCflg.setValue(BigInteger.valueOf(0));
		contact.setDUUKPOTCFLG(ukPotCflg);

		// Unknown
		DUCALLPRCDTNEN callPrcdTnen = new DUCALLPRCDTNEN();
		callPrcdTnen.setListID(BigInteger.valueOf(0));
		callPrcdTnen.setApplicable("Enabled");
		callPrcdTnen.setListLetID(BigInteger.valueOf(entryNumber));
		callPrcdTnen.setValue(BigInteger.valueOf(1));
		contact.setDUCALLPRCDTNEN(callPrcdTnen);

		// Route Type
		DUROUTETYPE routetype = new DUROUTETYPE();
		routetype.setListID(BigInteger.valueOf(0));
		routetype.setApplicable("Enabled");
		routetype.setListLetID(BigInteger.valueOf(entryNumber));
		routetype.setValue(routeType);
		contact.setDUROUTETYPE(routetype);

		// Text Message Alert Type
		DUTXTMSGALTTNTP txtmsgalerttype = new DUTXTMSGALTTNTP();
		txtmsgalerttype.setListID(BigInteger.valueOf(0));
		txtmsgalerttype.setApplicable("Enabled");
		txtmsgalerttype.setListLetID(BigInteger.valueOf(entryNumber));
		txtmsgalerttype.setValue(txtMsgAlertType);
		contact.setDUTXTMSGALTTNTP(txtmsgalerttype);

		//
		DURVRTPERS variable = new DURVRTPERS();
		variable.setListID(BigInteger.valueOf(0));
		variable.setApplicable("Enabled");
		variable.setListLetID(BigInteger.valueOf(entryNumber));
		variable.setValue(BigInteger.valueOf(0));
		contact.setDURVRTPERS(variable);

		//
		DURVRTPERSTYPE rvrtPersType = new DURVRTPERSTYPE();
		rvrtPersType.setListID(BigInteger.valueOf(0));
		rvrtPersType.setApplicable("Enabled");
		rvrtPersType.setListLetID(BigInteger.valueOf(entryNumber));
		rvrtPersType.setValue("SELECTED");
		contact.setDURVRTPERSTYPE(rvrtPersType);

		//
		DURVRTPERSID rvrtPersId = new DURVRTPERSID();
		rvrtPersId.setListID(BigInteger.valueOf(0));
		rvrtPersId.setApplicable("Enabled");
		rvrtPersId.setListLetID(BigInteger.valueOf(entryNumber));
		rvrtPersId.setValue(BigInteger.valueOf(0));
		contact.setDURVRTPERSID(rvrtPersId);

		// Contact Radio ID
		DUCALLLSTID contactRadioId = new DUCALLLSTID();
		contactRadioId.setListID(BigInteger.valueOf(0));
		contactRadioId.setApplicable("Enabled");
		contactRadioId.setListLetID(BigInteger.valueOf(entryNumber));
		contactRadioId.setValue(BigInteger.valueOf(radioId));
		contact.setDUCALLLSTID(contactRadioId);

		// Contact Name
		DUCALLALIAS contactAlias = new DUCALLALIAS();
		contactAlias.setListID(BigInteger.valueOf(0));
		contactAlias.setApplicable("Enabled");
		contactAlias.setListLetID(BigInteger.valueOf(entryNumber));
		contactAlias.setContent(contactName);
		contact.setDUCALLALIAS(contactAlias);

		// Ring Type
		DURINGTYPE ringType = new DURINGTYPE();
		ringType.setListID(BigInteger.valueOf(0));
		ringType.setApplicable("Enabled");
		ringType.setListLetID(BigInteger.valueOf(entryNumber));
		ringType.setValue("NOSTYLE");
		contact.setDURINGTYPE(ringType);

		// Connection Type
		DUCONNECTTYPE connectionType = new DUCONNECTTYPE();
		connectionType.setListID(BigInteger.valueOf(0));
		connectionType.setApplicable("Enabled");
		connectionType.setListLetID(BigInteger.valueOf(entryNumber));
		connectionType.setValue("USB");
		contact.setDUCONNECTTYPE(connectionType);

		// Reserved
		DULRESERVED reserved = new DULRESERVED();
		reserved.setListID(BigInteger.valueOf(0));
		reserved.setApplicable("Enabled");
		reserved.setListLetID(BigInteger.valueOf(entryNumber));
		reserved.setValue(BigInteger.valueOf(0));
		contact.setDULRESERVED(reserved);

		codeplug.getAPPPARTITION().getDIGITALUCLDLHTYPEGRP().getDIGITALUCLDLTTYPE().getDIGITALUCLDLLTYPE().add(contact);

		return entryNumber;
	}

	public void addTalkgroup(int id, String name) throws Exception {
		// TODO: Validate inputs

		// Create contact
		// TODO: Make this a group call
		this.addContact(id, name, false);

		// Create RX List
		// find the next ID number
		int entryNumber = 0;

		for (RXTGLISTDLTTYPE rxList : codeplug.getAPPPARTITION().getRXTGLISTDLHTYPEGRP().getRXTGLISTDLTTYPE()) {
			if (rxList.getListID().intValue() >= entryNumber) {
				entryNumber = rxList.getListID().intValue() + 1;
			}
		}

		ObjectFactory objectFactory = new ObjectFactory();

		RXTGLISTDLTTYPE rxList = objectFactory.createRXTGLISTDLTTYPE();
		RXTGLISTDLHTYPE rxListHeader = objectFactory.createRXTGLISTDLHTYPE();
		RXTGLISTDLLTYPE rxListEntry = objectFactory.createRXTGLISTDLLTYPE();
	}

	public void addDigitalRepeater(String name, double rxFreq, double txFreq, int colourCode, AbstractNetwork network,
			boolean addScanList) throws JAXBException {

		if (rxFreq < radioProfile.getMinFrequencyMhz() || txFreq < radioProfile.getMinFrequencyMhz()
				|| rxFreq > radioProfile.getMaxFrequencyMhz() || txFreq > radioProfile.getMaxFrequencyMhz()) {
			MotoTools.logger.info("Not adding digital repeater " + name + " - frequency out of range");
			return;
		}

		// Trim name, if necessary
		if (name.length() > radioProfile.getMaxZoneNameLength()) {
			name = name.substring(0, (radioProfile.getMaxZoneNameLength() - 1));
		}

		MotoTools.logger.info("Adding digital repeater " + name + " which is on the "
				+ network.getClass().getSimpleName() + " network");
		MotoTools.logger.debug("Talkgroups: " + network.getTalkgroupList());

		// find the next ID number
		int entryNumber = 0;

		for (ZNPERASGNDLTTYPE zone : codeplug.getAPPPARTITION().getZNPERASGNDLHTYPEGRP().getZNPERASGNDLTTYPE()) {
			if (zone.getListID().intValue() >= entryNumber) {
				entryNumber = zone.getListID().intValue() + 1;
			}
		}

		SCANPERDLTTYPE scanList = null;

		if (addScanList) {
			scanList = this.findOrCreateScanList(name);
		}

		ZNPERASGNDLTTYPE zone = new ZNPERASGNDLTTYPE();
		zone.setListID(BigInteger.valueOf(entryNumber));
		zone.setApplicable("Enabled");
		zone.setSortType("Position");

		ZNPERASGNDLHTYPE zoneHeader = new ZNPERASGNDLHTYPE();
		zoneHeader.setListID(BigInteger.valueOf(entryNumber));
		zoneHeader.setApplicable("Enabled");

		// Unknown
		ZPLSTCTG zplstctg = new ZPLSTCTG();
		zplstctg.setListID(BigInteger.valueOf(entryNumber));
		zplstctg.setApplicable("Enabled");
		zplstctg.setValue(BigInteger.valueOf(1));
		zoneHeader.setZPLSTCTG(zplstctg);

		// Unknown
		ZPLEDSZ zpledsz = new ZPLEDSZ();
		zpledsz.setListID(BigInteger.valueOf(entryNumber));
		zpledsz.setApplicable("Enabled");
		zpledsz.setValue(BigInteger.valueOf(6));
		zoneHeader.setZPLEDSZ(zpledsz);

		// Unknown
		ZPLEQMAX zpleqmax = new ZPLEQMAX();
		zpleqmax.setListID(BigInteger.valueOf(entryNumber));
		zpleqmax.setApplicable("Enabled");
		zpleqmax.setValue(BigInteger.valueOf(160));
		zoneHeader.setZPLEQMAX(zpleqmax);

		// Unknown
		ZPLLEQMAX zplleqmax = new ZPLLEQMAX();
		zplleqmax.setListID(BigInteger.valueOf(entryNumber));
		zplleqmax.setApplicable("Enabled");
		zplleqmax.setValue(BigInteger.valueOf(1));
		zoneHeader.setZPLLEQMAX(zplleqmax);

		// Unknown
		ZNPERASGNDLHTYPEFTLLISHTYPE znperasgndlhtypeftllishtype = new ZNPERASGNDLHTYPEFTLLISHTYPE();
		znperasgndlhtypeftllishtype.setListID(BigInteger.valueOf(entryNumber));
		znperasgndlhtypeftllishtype.setApplicable("Enabled");
		znperasgndlhtypeftllishtype.setValue("ZN_PER_ASGN_DLL_TYPE");
		zoneHeader.setZNPERASGNDLHTYPEFTLLISHTYPE(znperasgndlhtypeftllishtype);

		// Unknown
		ZNPERASGNDLHTYPEFTLLISHID znperasgndlhtypeftllishid = new ZNPERASGNDLHTYPEFTLLISHID();
		znperasgndlhtypeftllishid.setListID(BigInteger.valueOf(entryNumber));
		znperasgndlhtypeftllishid.setApplicable("Enabled");
		znperasgndlhtypeftllishid.setValue(BigInteger.valueOf(entryNumber));
		zoneHeader.setZNPERASGNDLHTYPEFTLLISHID(znperasgndlhtypeftllishid);

		// Zone name
		ZPZONEALIAS zoneName = new ZPZONEALIAS();
		zoneName.setListID(BigInteger.valueOf(entryNumber));
		zoneName.setApplicable("Enabled");
		zoneName.setContent(name);
		zoneHeader.setZPZONEALIAS(zoneName);

		// Unknown
		ZPZONETYPE zoneType = new ZPZONETYPE();
		zoneType.setListID(BigInteger.valueOf(entryNumber));
		zoneType.setApplicable("Enabled");
		zoneType.setValue(BigInteger.valueOf(1));
		zoneHeader.setZPZONETYPE(zoneType);

		// Unknown
		ZPCBZONE cbZone = new ZPCBZONE();
		cbZone.setListID(BigInteger.valueOf(entryNumber));
		cbZone.setApplicable("Enabled");
		cbZone.setValue(BigInteger.valueOf(0));
		zoneHeader.setZPCBZONE(cbZone);

		// Unknown
		ZPZI zpzi = new ZPZI();
		zpzi.setListID(BigInteger.valueOf(entryNumber));
		zpzi.setApplicable("Enabled");
		zpzi.setValue(BigInteger.valueOf(1));
		zoneHeader.setZPZI(zpzi);

		// Unknown
		ZPZVFNLITEM zpzvflnitem = new ZPZVFNLITEM();
		zpzvflnitem.setListID(BigInteger.valueOf(entryNumber));
		zpzvflnitem.setApplicable("Disabled");
		zpzvflnitem.setTypeID("NONE");
		zpzvflnitem.setValue(BigInteger.valueOf(65535));
		zoneHeader.setZPZVFNLITEM(zpzvflnitem);

		// Unknown
		ZPZVFNLITEMTYPE zpzvflnitemtype = new ZPZVFNLITEMTYPE();
		zpzvflnitemtype.setListID(BigInteger.valueOf(entryNumber));
		zpzvflnitemtype.setApplicable("Disabled");
		zpzvflnitemtype.setValue("NONE");
		zoneHeader.setZPZVFNLITEMTYPE(zpzvflnitemtype);

		// Unknown
		ZPZVFNLITEMID zpzvflnitemid = new ZPZVFNLITEMID();
		zpzvflnitemid.setListID(BigInteger.valueOf(entryNumber));
		zpzvflnitemid.setApplicable("Disabled");
		zpzvflnitemid.setValue(BigInteger.valueOf(65535));
		zoneHeader.setZPZVFNLITEMID(zpzvflnitemid);

		// Set the zone header
		zone.setZNPERASGNDLHTYPE(zoneHeader);

		// Add talkgroups to zone
		int channelListLetID = 0;
		for (Talkgroup talkgroup : network.talkgroups) {

			MotoTools.logger.info("Adding " + name + " " + talkgroup.getTalkGroupName());

			try {
				channelListLetID = this.addDigitalChannel(talkgroup.getSlot(), colourCode, talkgroup.getTalkGroupName(),
						txFreq, rxFreq, talkgroup, scanList);

				MotoTools.logger
						.debug("Adding Talkgroup " + talkgroup.getTalkGroupName() + " as ID " + channelListLetID);

				ZNPERASGNDLLTYPE channel = new ZNPERASGNDLLTYPE();
				channel.setListID(BigInteger.valueOf(entryNumber));
				channel.setApplicable("Enabled");
				channel.setListLetID(BigInteger.valueOf(channelListLetID));

				// Unknown
				ZNPERASGNDLLTYPENTLLISHTYPE znperasgndlltypeftllishtype = new ZNPERASGNDLLTYPENTLLISHTYPE();
				znperasgndlltypeftllishtype.setListID(BigInteger.valueOf(entryNumber));
				znperasgndlltypeftllishtype.setApplicable("Enabled");
				znperasgndlltypeftllishtype.setValue("ZN_PER_ASGN_DLL_TYPE");
				channel.setZNPERASGNDLLTYPENTLLISHTYPE(znperasgndlltypeftllishtype);

				// Unknown
				ZNPERASGNDLLTYPENTLLISHID znperasgndlltypeftllishid = new ZNPERASGNDLLTYPENTLLISHID();
				znperasgndlltypeftllishid.setListID(BigInteger.valueOf(entryNumber));
				znperasgndlltypeftllishid.setApplicable("Enabled");
				znperasgndlltypeftllishid.setValue(BigInteger.valueOf(0));
				channel.setZNPERASGNDLLTYPENTLLISHID(znperasgndlltypeftllishid);

				// Unknown
				ZPLLEQ zplleq = new ZPLLEQ();
				zplleq.setListID(BigInteger.valueOf(entryNumber));
				zplleq.setListLetID(BigInteger.valueOf(channelListLetID));
				zplleq.setApplicable("Enabled");
				zplleq.setValue(BigInteger.valueOf(1));
				channel.setZPLLEQ(zplleq);

				// Unknown
				ZPPERSIT zppersit = new ZPPERSIT();
				zppersit.setListID(BigInteger.valueOf(entryNumber));
				zppersit.setListLetID(BigInteger.valueOf(channelListLetID));
				zppersit.setApplicable("Enabled");
				zppersit.setTypeID("CNV_PER_CMP_TYPE");
				zppersit.setValue(BigInteger.valueOf(channelListLetID));
				channel.setZPPERSIT(zppersit);

				// Unknown
				ZPPERSITTYPE zppersittype = new ZPPERSITTYPE();
				zppersittype.setListID(BigInteger.valueOf(entryNumber));
				zppersittype.setListLetID(BigInteger.valueOf(channelListLetID));
				zppersittype.setApplicable("Enabled");
				zppersittype.setValue("CNV_PER_CMP_TYPE");
				channel.setZPPERSITTYPE(zppersittype);

				// Unknown
				ZPPERSITID zppersitid = new ZPPERSITID();
				zppersitid.setListID(BigInteger.valueOf(entryNumber));
				zppersitid.setListLetID(BigInteger.valueOf(channelListLetID));
				zppersitid.setApplicable("Enabled");
				zppersitid.setValue(BigInteger.valueOf(channelListLetID));
				channel.setZPPERSITID(zppersitid);

				// Unknown
				ZPLRESERVED zplreserved = new ZPLRESERVED();
				zplreserved.setListID(BigInteger.valueOf(entryNumber));
				zplreserved.setListLetID(BigInteger.valueOf(channelListLetID));
				zplreserved.setApplicable("Enabled");
				zplreserved.setValue(BigInteger.valueOf(0));
				channel.setZPLRESERVED(zplreserved);

				zone.getZNPERASGNDLLTYPE().add(channel);

				// If this is a roaming talkgroup, then find (or create) the roaming zone and
				// add this talkgroup to it as a channel. TODO: need to set this channel's
				// roaming group and create the roam list too!

				channelListLetID++;
			} catch (ChannelListFullException e) {
				MotoTools.logger.error("Channel List Full", e);
			}
		}

		// Add the zone to the codeplug
		codeplug.getAPPPARTITION().getZNPERASGNDLHTYPEGRP().getZNPERASGNDLTTYPE().add(zone);

		// Add roaming channels. This nees to be done after the main zone is written to
		// the codeplug to avoid a race condition

		for (Talkgroup talkgroup : network.talkgroups) {
			// Check if the talkgroup is a roaming talkgroup
			if (talkgroup.isRoamingTalkgroup()) {
				// Check if the repeater is in the roaming TG list (or list = All)
				if (Arrays.asList(network.roamRepeaters.get(talkgroup.getTalkGroupId()))
						.contains(name.substring(0, name.indexOf(" ")))
						|| Arrays.asList(network.roamRepeaters.get(talkgroup.getTalkGroupId())).contains("All")) {
					try {
						ZNPERASGNDLTTYPE roamingZone = this
								.findOrCreateZone(network.roamName.get(talkgroup.getTalkGroupId()));
	
						int roamingChannel = this.addDigitalChannel(talkgroup.getSlot(), colourCode, name, txFreq, rxFreq,
								talkgroup, scanList);
	
						this.addChannelToZone(roamingChannel, roamingZone);
	
						this.addChannelToRoamList(network.roamName.get(talkgroup.getTalkGroupId()), roamingChannel);
					} catch ( ChannelListFullException e ) {
						MotoTools.logger.error("Channel List Full", e);
					}

				}
			}
		}
	}

	// Add an analogue Repeater
	// TODO: Check how many repeaters (channels) are already in the zone (max 160)
	public void addAnalogueRepeater(String zoneName, String name, double rxFreq, double txFreq, Tone tone,
			boolean scanList) {

		if (rxFreq < radioProfile.getMinFrequencyMhz() || txFreq < radioProfile.getMinFrequencyMhz()
				|| rxFreq > radioProfile.getMaxFrequencyMhz() || txFreq > radioProfile.getMaxFrequencyMhz()) {
			MotoTools.logger.info("Not adding analogue repeater " + name + " - frequency out of range");
			return;
		}

		// Trim name, if necessary
		if (name.length() > radioProfile.getMaxZoneNameLength()) {
			name = name.substring(0, (radioProfile.getMaxZoneNameLength() - 1));
		}

		MotoTools.logger.info("Adding analogue repeater " + name + " to zone " + zoneName);

		// find the next ID number
		ZNPERASGNDLTTYPE zone = this.findOrCreateZone(zoneName);
		int entryNumber = zone.getListID().intValue();

		// TODO: Add one ZN_PER_ASGN_DLL_TYPE per channel to the zone here

		// Add talkgroups to zone
		int channelListLetID;
		try {
			if (scanList) {
				channelListLetID = this.addAnalogueChannel(name, txFreq, rxFreq, tone, zoneName);
			} else {
				channelListLetID = this.addAnalogueChannel(name, txFreq, rxFreq, tone);
			}

			MotoTools.logger.debug("Adding channel for " + name + " as ID " + channelListLetID);

			ZNPERASGNDLLTYPE channel = new ZNPERASGNDLLTYPE();
			channel.setListID(BigInteger.valueOf(entryNumber));
			channel.setApplicable("Enabled");
			channel.setListLetID(BigInteger.valueOf(channelListLetID));

			// Unknown
			ZNPERASGNDLLTYPENTLLISHTYPE znperasgndlltypeftllishtype = new ZNPERASGNDLLTYPENTLLISHTYPE();
			znperasgndlltypeftllishtype.setListID(BigInteger.valueOf(entryNumber));
			znperasgndlltypeftllishtype.setApplicable("Enabled");
			znperasgndlltypeftllishtype.setValue("ZN_PER_ASGN_DLL_TYPE");
			channel.setZNPERASGNDLLTYPENTLLISHTYPE(znperasgndlltypeftllishtype);

			// Unknown
			ZNPERASGNDLLTYPENTLLISHID znperasgndlltypeftllishid = new ZNPERASGNDLLTYPENTLLISHID();
			znperasgndlltypeftllishid.setListID(BigInteger.valueOf(entryNumber));
			znperasgndlltypeftllishid.setApplicable("Enabled");
			znperasgndlltypeftllishid.setValue(BigInteger.valueOf(0));
			channel.setZNPERASGNDLLTYPENTLLISHID(znperasgndlltypeftllishid);

			// Unknown
			ZPLLEQ zplleq = new ZPLLEQ();
			zplleq.setListID(BigInteger.valueOf(entryNumber));
			zplleq.setListLetID(BigInteger.valueOf(channelListLetID));
			zplleq.setApplicable("Enabled");
			zplleq.setValue(BigInteger.valueOf(1));
			channel.setZPLLEQ(zplleq);

			// Unknown
			ZPPERSIT zppersit = new ZPPERSIT();
			zppersit.setListID(BigInteger.valueOf(entryNumber));
			zppersit.setListLetID(BigInteger.valueOf(channelListLetID));
			zppersit.setApplicable("Enabled");
			zppersit.setTypeID("CNV_PER_CMP_TYPE");
			zppersit.setValue(BigInteger.valueOf(channelListLetID));
			channel.setZPPERSIT(zppersit);

			// Unknown
			ZPPERSITTYPE zppersittype = new ZPPERSITTYPE();
			zppersittype.setListID(BigInteger.valueOf(entryNumber));
			zppersittype.setListLetID(BigInteger.valueOf(channelListLetID));
			zppersittype.setApplicable("Enabled");
			zppersittype.setValue("CNV_PER_CMP_TYPE");
			channel.setZPPERSITTYPE(zppersittype);

			// Unknown
			ZPPERSITID zppersitid = new ZPPERSITID();
			zppersitid.setListID(BigInteger.valueOf(entryNumber));
			zppersitid.setListLetID(BigInteger.valueOf(channelListLetID));
			zppersitid.setApplicable("Enabled");
			zppersitid.setValue(BigInteger.valueOf(channelListLetID));
			channel.setZPPERSITID(zppersitid);

			// Unknown
			ZPLRESERVED zplreserved = new ZPLRESERVED();
			zplreserved.setListID(BigInteger.valueOf(entryNumber));
			zplreserved.setListLetID(BigInteger.valueOf(channelListLetID));
			zplreserved.setApplicable("Enabled");
			zplreserved.setValue(BigInteger.valueOf(0));
			channel.setZPLRESERVED(zplreserved);

			zone.getZNPERASGNDLLTYPE().add(channel);
			channelListLetID++;

			// Add the zone to the codeplug
			// codeplug.getAPPPARTITION().getZNPERASGNDLHTYPEGRP().getZNPERASGNDLTTYPE().add(zone);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( ChannelListFullException e ) {
			MotoTools.logger.error("Channel List Full", e);
		}
	}

	public int addDigitalChannel(int slot, int colourcode, String name, double txFreq, double rxFreq,
			Talkgroup talkgroup, SCANPERDLTTYPE scanList) throws JAXBException, ChannelListFullException {
		// TODO: Verify inputs

		// find the talkgroup ID number
		int tgId = 0, rxListId = 0;
		try {
			tgId = this.findTalkgroupId(talkgroup);
			rxListId = this.findTalkgroupRxListId(talkgroup);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// find the next ID number
		int entryNumber = 0;

		for (CNVPERCMPTYPE channel : codeplug.getAPPPARTITION().getCNVPERCMPTYPEGRP().getCNVPERCMPTYPE()) {
			if (channel.getListID().intValue() >= entryNumber) {
				entryNumber = channel.getListID().intValue() + 1;
			}
		}

		if (entryNumber >= radioProfile.getMaxChannels()) {
			throw new ChannelListFullException();
		}

		File channelTemplate = new File(
				getClass().getClassLoader().getResource("digital-channel-template.xml").getFile());
		JAXBContext jaxbContext = JAXBContext.newInstance(CNVPERCMPTYPE.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		CNVPERCMPTYPE channel = (CNVPERCMPTYPE) jaxbUnmarshaller.unmarshal(channelTemplate);
		channel.setListID(BigInteger.valueOf(entryNumber));

		for (Object child : channel.getCPPERSTYPEOrCPSELECT5CHAN()) {

			try {
				child.getClass().getMethod("setListID", BigInteger.class).invoke(child,
						BigInteger.valueOf(entryNumber));
			} catch (NoSuchMethodException e) {
				// Nothing to do
				MotoTools.logger.debug("setListID method not found");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			if (child instanceof CPCNVPERSALIAS) {
				((CPCNVPERSALIAS) child).setContent(name);
			}

			if (child instanceof CPSLTASSGMNT) {
				if (slot == 1) {
					((CPSLTASSGMNT) child).setValue("SLOT1");
				} else {
					((CPSLTASSGMNT) child).setValue("SLOT2");
				}
			}

			if (child instanceof CPTXFREQ) {
				((CPTXFREQ) child).setValue(BigDecimal.valueOf(txFreq));
			}

			if (child instanceof CPRXFREQ) {
				((CPRXFREQ) child).setValue(BigDecimal.valueOf(rxFreq));
			}

			if (child instanceof CPCOLORCODE) {
				((CPCOLORCODE) child).setValue(BigInteger.valueOf(colourcode));
			}

			if (child instanceof CPTGLISTIT) {
				((CPTGLISTIT) child).setAlias(talkgroup.talkGroupName);
				((CPTGLISTIT) child).setValue(BigInteger.valueOf(rxListId));
			}

			if (child instanceof CPTGLISTITID) {
				((CPTGLISTITID) child).setValue(BigInteger.valueOf(rxListId));
			}

			if (child instanceof CPUKPPERS) {
				MotoTools.logger
						.debug("Setting Contact Name to " + talkgroup.getTalkGroupName() + " (ID " + tgId + ")");
				((CPUKPPERS) child).setAlias(talkgroup.talkGroupName);
				((CPUKPPERS) child).setValue(BigInteger.valueOf(tgId));
			}

			if (child instanceof CPUKPPERSID) {
				((CPUKPPERSID) child).setValue(BigInteger.valueOf(tgId));
			}

		}

		for (Object child : channel.getCP625EDMENOrCP625EDMPRFLDOrCPADVRFAGC()) {

			try {
				child.getClass().getMethod("setListID", BigInteger.class).invoke(child,
						BigInteger.valueOf(entryNumber));
			} catch (NoSuchMethodException e) {
				// Nothing to do
				MotoTools.logger.debug("setListID method not found");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			if (child instanceof CPCNVPERSALIAS) {
				((CPCNVPERSALIAS) child).setContent(name);
			}

			if (child instanceof CPSLTASSGMNT) {
				if (slot == 1) {
					((CPSLTASSGMNT) child).setValue("SLOT1");
				} else {
					((CPSLTASSGMNT) child).setValue("SLOT2");
				}
			}

			if (child instanceof CPTXFREQ) {
				((CPTXFREQ) child).setValue(BigDecimal.valueOf(txFreq));
			}

			if (child instanceof CPRXFREQ) {
				((CPRXFREQ) child).setValue(BigDecimal.valueOf(rxFreq));
			}

			if (child instanceof CPCOLORCODE) {
				((CPCOLORCODE) child).setValue(BigInteger.valueOf(colourcode));
			}

			if (child instanceof CPTGLISTIT) {
				((CPTGLISTIT) child).setAlias(talkgroup.talkGroupName);
				((CPTGLISTIT) child).setValue(BigInteger.valueOf(rxListId));
			}

			if (child instanceof CPTGLISTITID) {
				((CPTGLISTITID) child).setValue(BigInteger.valueOf(rxListId));
			}

			if (child instanceof CPUKPPERS) {
				MotoTools.logger
						.debug("Setting Contact Name to " + talkgroup.getTalkGroupName() + " (ID " + tgId + ")");
				((CPUKPPERS) child).setAlias(talkgroup.talkGroupName);
				((CPUKPPERS) child).setValue(BigInteger.valueOf(tgId));
			}

			if (child instanceof CPUKPPERSID) {
				((CPUKPPERSID) child).setValue(BigInteger.valueOf(tgId));
			}

		}

		codeplug.getAPPPARTITION().getCNVPERCMPTYPEGRP().getCNVPERCMPTYPE().add(channel);

		if (talkgroup.addToScanList) {
			this.addChannelToScanList(scanList, entryNumber);
		}

		return entryNumber;

	}

	private int addAnalogueChannel(String name, double txFreq, double rxFreq, Tone tone, String scanListName)
			throws JAXBException, ChannelListFullException {
			int channelId = this.addAnalogueChannel(name, txFreq, rxFreq, tone);

			this.addChannelToScanList(scanListName, channelId);

			return channelId;
		
	}

	private int addAnalogueChannel(String name, double txFreq, double rxFreq, Tone tone)
			throws JAXBException, ChannelListFullException {
		// TODO: Verify inputs

		// find the next ID number
		int entryNumber = 0;

		for (CNVPERCMPTYPE channel : codeplug.getAPPPARTITION().getCNVPERCMPTYPEGRP().getCNVPERCMPTYPE()) {
			if (channel.getListID().intValue() >= entryNumber) {
				entryNumber = channel.getListID().intValue() + 1;
			}
		}

		if (entryNumber >= radioProfile.getMaxChannels()) {
			throw new ChannelListFullException();
		}

		File channelTemplate = new File(
				getClass().getClassLoader().getResource("analogue-channel-template.xml").getFile());
		JAXBContext jaxbContext = JAXBContext.newInstance(CNVPERCMPTYPE.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		CNVPERCMPTYPE channel = (CNVPERCMPTYPE) jaxbUnmarshaller.unmarshal(channelTemplate);
		channel.setListID(BigInteger.valueOf(entryNumber));

		for (Object child : channel.getCPPERSTYPEOrCPSELECT5CHAN()) {

			try {
				child.getClass().getMethod("setListID", BigInteger.class).invoke(child,
						BigInteger.valueOf(entryNumber));
			} catch (NoSuchMethodException e) {
				// Nothing to do
				MotoTools.logger.debug("setListID method not found");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			if (child instanceof CPCNVPERSALIAS) {
				((CPCNVPERSALIAS) child).setContent(name);
			}

			if (child instanceof CPTXFREQ) {
				((CPTXFREQ) child).setValue(BigDecimal.valueOf(txFreq));
			}

			if (child instanceof CPRXFREQ) {
				((CPRXFREQ) child).setValue(BigDecimal.valueOf(rxFreq));
			}

			if (child instanceof CPTXTPLCODE) {
				((CPTXTPLCODE) child).setValue(tone.getCode());
			}

			if (child instanceof CPTXTTPLFREQ) {
				((CPTXTTPLFREQ) child).setValue(BigDecimal.valueOf(tone.getFreq()));
			}

		}

		for (Object child : channel.getCP625EDMENOrCP625EDMPRFLDOrCPADVRFAGC()) {

			try {
				child.getClass().getMethod("setListID", BigInteger.class).invoke(child,
						BigInteger.valueOf(entryNumber));
			} catch (NoSuchMethodException e) {
				// Nothing to do
				MotoTools.logger.debug("setListID method not found");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			if (child instanceof CPCNVPERSALIAS) {
				((CPCNVPERSALIAS) child).setContent(name);
			}

			if (child instanceof CPTXFREQ) {
				((CPTXFREQ) child).setValue(BigDecimal.valueOf(txFreq));
			}

			if (child instanceof CPRXFREQ) {
				((CPRXFREQ) child).setValue(BigDecimal.valueOf(rxFreq));
			}

			if (child instanceof CPTXTPLCODE) {
				((CPTXTPLCODE) child).setValue(tone.getCode());
			}

			if (child instanceof CPTXTTPLFREQ) {
				((CPTXTTPLFREQ) child).setValue(BigDecimal.valueOf(tone.getFreq()));
			}

		}

		codeplug.getAPPPARTITION().getCNVPERCMPTYPEGRP().getCNVPERCMPTYPE().add(channel);

		return entryNumber;

	}

	private int findTalkgroupId(Talkgroup talkgroup) throws Exception {
		for (DIGITALUCLDLLTYPE contact : codeplug.getAPPPARTITION().getDIGITALUCLDLHTYPEGRP().getDIGITALUCLDLTTYPE()
				.getDIGITALUCLDLLTYPE()) {
			// if (contact.getDUCALLLSTID().getValue().intValue() ==
			// talkgroup.getTalkGroupId()
			// && contact.getDUCALLALIAS().getValue().equals(talkgroup.getTalkGroupName()))
			// {
			if (contact.getDUCALLLSTID().getValue().intValue() == talkgroup.getTalkGroupId()) {
				int tgId = contact.getListLetID().intValue();
				return tgId;
			} else {
				MotoTools.logger.debug("TG NOT FOUND (looking for " + talkgroup.getTalkGroupId() + " but found "
						+ contact.getDUCALLLSTID().getValue().intValue() + ")");
			}
		}

		int tgId = this.addContact(talkgroup.getTalkGroupId(), "TG" + talkgroup.getTalkGroupId(), false);

		return tgId;
	}

	private int addRxList(int tgId, String talkGroupName) throws JAXBException {
		// find the next ID number
		int entryNumber = 0;

		for (RXTGLISTDLTTYPE rxList : codeplug.getAPPPARTITION().getRXTGLISTDLHTYPEGRP().getRXTGLISTDLTTYPE()) {
			if (rxList.getListID().intValue() >= entryNumber) {
				entryNumber = rxList.getListID().intValue() + 1;
			}
		}

		File rxListTemplate = new File(getClass().getClassLoader().getResource("rxlist-template.xml").getFile());
		JAXBContext jaxbContext = JAXBContext.newInstance(RXTGLISTDLTTYPE.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		RXTGLISTDLTTYPE rxList = (RXTGLISTDLTTYPE) jaxbUnmarshaller.unmarshal(rxListTemplate);

		rxList.setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLHTYPE().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLHTYPE().getRXTGLEDSZ().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLHTYPE().getRXTGLEQMAX().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLHTYPE().getRXTGLISTDLHTYPEFTLLISHID().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLHTYPE().getRXTGLISTDLHTYPEFTLLISHTYPE().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLHTYPE().getRXTGLLEQMAX().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLHTYPE().getRXTGLSTCTG().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLHTYPE().getRXTGRXTGLISTALIAS().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLHTYPE().getRXTGRXTGLISTALIAS().setValue(talkGroupName);
		rxList.getRXTGLISTDLLTYPE().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLLTYPE().getRXTGDGUCLCOMP().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLLTYPE().getRXTGDGUCLCOMPID().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLLTYPE().getRXTGDGUCLCOMPID().setValue(BigInteger.valueOf(tgId));
		rxList.getRXTGLISTDLLTYPE().getRXTGDGUCLCOMPTYPE().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLLTYPE().getRXTGLISTDLLTYPENTLLISHID().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLLTYPE().getRXTGLISTDLLTYPENTLLISHTYPE().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLLTYPE().getRXTGLLEQ().setListID(BigInteger.valueOf(entryNumber));
		rxList.getRXTGLISTDLLTYPE().getRXTGLRESERVED().setListID(BigInteger.valueOf(entryNumber));

		codeplug.getAPPPARTITION().getRXTGLISTDLHTYPEGRP().getRXTGLISTDLTTYPE().add(rxList);

		return entryNumber;
	}

	private int findTalkgroupRxListId(Talkgroup talkgroup) throws Exception {
		int tgId = this.findTalkgroupId(talkgroup);
		for (RXTGLISTDLTTYPE rxList : codeplug.getAPPPARTITION().getRXTGLISTDLHTYPEGRP().getRXTGLISTDLTTYPE()) {
			if (rxList.getRXTGLISTDLLTYPE().getRXTGDGUCLCOMPID().getValue().intValue() == tgId) {
				return rxList.getListID().intValue();
			}
		}

		return this.addRxList(tgId, "TG" + talkgroup.getTalkGroupId());
	}

	private ZNPERASGNDLTTYPE findZone(String zoneName) throws ZoneNotFoundException {
		for (ZNPERASGNDLTTYPE zone : codeplug.getAPPPARTITION().getZNPERASGNDLHTYPEGRP().getZNPERASGNDLTTYPE()) {
			MotoTools.logger.debug("Looking for zone " + zoneName + ", checking "
					+ zone.getZNPERASGNDLHTYPE().getZPZONEALIAS().getContent());
			if (zone.getZNPERASGNDLHTYPE().getZPZONEALIAS().getContent().equals(zoneName)) {
				MotoTools.logger.debug("Found zone " + zoneName + " (ID " + zone.getListID().intValue() + ")");
				return zone;
			}
		}

		throw new ZoneNotFoundException();
	}

	/**
	 * Warning, not thread safe!
	 * 
	 * @param zoneName
	 * @return
	 */
	private ZNPERASGNDLTTYPE findOrCreateZone(String zoneName) {
		try {
			return this.findZone(zoneName);
		} catch (ZoneNotFoundException e) {

			int entryNumber = 0;

			for (ZNPERASGNDLTTYPE zone : codeplug.getAPPPARTITION().getZNPERASGNDLHTYPEGRP().getZNPERASGNDLTTYPE()) {
				if (zone.getListID().intValue() >= entryNumber) {
					entryNumber = zone.getListID().intValue() + 1;
				}
			}

			MotoTools.logger.info("Zone " + zoneName + " not found, creating it now using ID " + entryNumber + "...");

			ZNPERASGNDLTTYPE zone = new ZNPERASGNDLTTYPE();
			zone.setListID(BigInteger.valueOf(entryNumber));
			zone.setApplicable("Enabled");
			zone.setSortType("Position");

			ZNPERASGNDLHTYPE zoneHeader = new ZNPERASGNDLHTYPE();
			zoneHeader.setListID(BigInteger.valueOf(entryNumber));
			zoneHeader.setApplicable("Enabled");

			// Unknown
			ZPLSTCTG zplstctg = new ZPLSTCTG();
			zplstctg.setListID(BigInteger.valueOf(entryNumber));
			zplstctg.setApplicable("Enabled");
			zplstctg.setValue(BigInteger.valueOf(1));
			zoneHeader.setZPLSTCTG(zplstctg);

			// Unknown
			ZPLEDSZ zpledsz = new ZPLEDSZ();
			zpledsz.setListID(BigInteger.valueOf(entryNumber));
			zpledsz.setApplicable("Enabled");
			zpledsz.setValue(BigInteger.valueOf(6));
			zoneHeader.setZPLEDSZ(zpledsz);

			// Unknown
			ZPLEQMAX zpleqmax = new ZPLEQMAX();
			zpleqmax.setListID(BigInteger.valueOf(entryNumber));
			zpleqmax.setApplicable("Enabled");
			zpleqmax.setValue(BigInteger.valueOf(160));
			zoneHeader.setZPLEQMAX(zpleqmax);

			// Unknown
			ZPLLEQMAX zplleqmax = new ZPLLEQMAX();
			zplleqmax.setListID(BigInteger.valueOf(entryNumber));
			zplleqmax.setApplicable("Enabled");
			zplleqmax.setValue(BigInteger.valueOf(1));
			zoneHeader.setZPLLEQMAX(zplleqmax);

			// Unknown
			ZNPERASGNDLHTYPEFTLLISHTYPE znperasgndlhtypeftllishtype = new ZNPERASGNDLHTYPEFTLLISHTYPE();
			znperasgndlhtypeftllishtype.setListID(BigInteger.valueOf(entryNumber));
			znperasgndlhtypeftllishtype.setApplicable("Enabled");
			znperasgndlhtypeftllishtype.setValue("ZN_PER_ASGN_DLL_TYPE");
			zoneHeader.setZNPERASGNDLHTYPEFTLLISHTYPE(znperasgndlhtypeftllishtype);

			// Unknown
			ZNPERASGNDLHTYPEFTLLISHID znperasgndlhtypeftllishid = new ZNPERASGNDLHTYPEFTLLISHID();
			znperasgndlhtypeftllishid.setListID(BigInteger.valueOf(entryNumber));
			znperasgndlhtypeftllishid.setApplicable("Enabled");
			znperasgndlhtypeftllishid.setValue(BigInteger.valueOf(entryNumber));
			zoneHeader.setZNPERASGNDLHTYPEFTLLISHID(znperasgndlhtypeftllishid);

			// Zone name
			ZPZONEALIAS zoneAlias = new ZPZONEALIAS();
			zoneAlias.setListID(BigInteger.valueOf(entryNumber));
			zoneAlias.setApplicable("Enabled");
			zoneAlias.setContent(zoneName);
			zoneHeader.setZPZONEALIAS(zoneAlias);

			// Unknown
			ZPZONETYPE zoneType = new ZPZONETYPE();
			zoneType.setListID(BigInteger.valueOf(entryNumber));
			zoneType.setApplicable("Enabled");
			zoneType.setValue(BigInteger.valueOf(1));
			zoneHeader.setZPZONETYPE(zoneType);

			// Unknown
			ZPCBZONE cbZone = new ZPCBZONE();
			cbZone.setListID(BigInteger.valueOf(entryNumber));
			cbZone.setApplicable("Enabled");
			cbZone.setValue(BigInteger.valueOf(0));
			zoneHeader.setZPCBZONE(cbZone);

			// Unknown
			ZPZI zpzi = new ZPZI();
			zpzi.setListID(BigInteger.valueOf(entryNumber));
			zpzi.setApplicable("Enabled");
			zpzi.setValue(BigInteger.valueOf(1));
			zoneHeader.setZPZI(zpzi);

			// Unknown
			ZPZVFNLITEM zpzvflnitem = new ZPZVFNLITEM();
			zpzvflnitem.setListID(BigInteger.valueOf(entryNumber));
			zpzvflnitem.setApplicable("Disabled");
			zpzvflnitem.setTypeID("NONE");
			zpzvflnitem.setValue(BigInteger.valueOf(65535));
			zoneHeader.setZPZVFNLITEM(zpzvflnitem);

			// Unknown
			ZPZVFNLITEMTYPE zpzvflnitemtype = new ZPZVFNLITEMTYPE();
			zpzvflnitemtype.setListID(BigInteger.valueOf(entryNumber));
			zpzvflnitemtype.setApplicable("Disabled");
			zpzvflnitemtype.setValue("NONE");
			zoneHeader.setZPZVFNLITEMTYPE(zpzvflnitemtype);

			// Unknown
			ZPZVFNLITEMID zpzvflnitemid = new ZPZVFNLITEMID();
			zpzvflnitemid.setListID(BigInteger.valueOf(entryNumber));
			zpzvflnitemid.setApplicable("Disabled");
			zpzvflnitemid.setValue(BigInteger.valueOf(65535));
			zoneHeader.setZPZVFNLITEMID(zpzvflnitemid);

			// Set the zone header
			zone.setZNPERASGNDLHTYPE(zoneHeader);

			// Add the zone to the codeplug
			codeplug.getAPPPARTITION().getZNPERASGNDLHTYPEGRP().getZNPERASGNDLTTYPE().add(zone);

			return zone;
		}

	}

	private STSRCHDLTTYPE findRoamList(String roamListName) throws RoamListNotFoundException {
		for (STSRCHDLTTYPE roamList : codeplug.getAPPPARTITION().getSTSRCHDLHTYPEGRP().getSTSRCHDLTTYPE()) {
			if (roamList.getSTSRCHDLHTYPE().getSSPSCANLISTALIAS().getValue().equals(roamListName)) {
				MotoTools.logger
						.debug("Found roam list " + roamListName + " (ID " + roamList.getListID().intValue() + ")");
				return roamList;
			}
		}

		throw new RoamListNotFoundException();
	}

	/**
	 * Warning, not thread safe!
	 * 
	 * @param zoneName
	 * @return
	 * @throws JAXBException
	 */
	private STSRCHDLTTYPE findOrCreateRoamList(String roamListName) throws JAXBException {
		try {
			return this.findRoamList(roamListName);
		} catch (RoamListNotFoundException e) {

			int entryNumber = 0;

			for (STSRCHDLTTYPE roamList : codeplug.getAPPPARTITION().getSTSRCHDLHTYPEGRP().getSTSRCHDLTTYPE()) {
				if (roamList.getListID().intValue() >= entryNumber) {
					entryNumber = roamList.getListID().intValue() + 1;
				}
			}

			MotoTools.logger
					.info("Roam List " + roamListName + " not found, creating it now using ID " + entryNumber + "...");

			File roamListTemplate = new File(
					getClass().getClassLoader().getResource("roam-list-template.xml").getFile());
			JAXBContext jaxbContext = JAXBContext.newInstance(STSRCHDLTTYPE.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			STSRCHDLTTYPE roamList = (STSRCHDLTTYPE) jaxbUnmarshaller.unmarshal(roamListTemplate);
			roamList.setListID(BigInteger.valueOf(entryNumber));

			roamList.getSTSRCHDLHTYPE().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPDSGTXMEMID().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPDSGTXMEMTYPE().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPLEDSZ().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPLEQMAX().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPLLEQMAX().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPLSTCTG().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPLSTDYNMCDTMNHMCH().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPPERSITEROAMINGRSSITHRESHOLDENABLE()
					.setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPPLLOCKCHMARKEN().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPPR1MEMID().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPPR1MEMTYPE().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPPR2MEMID().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPPR2MEMTYPE().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPRESERVED().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPRSSITHRFORROAM().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPSCANLISTALIAS().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSSPSCANLISTALIAS().setValue(roamListName);
			roamList.getSTSRCHDLHTYPE().getSSPTLKSCNEN().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSTSRCHDLHTYPEFTLLISHID().setListID(BigInteger.valueOf(entryNumber));
			roamList.getSTSRCHDLHTYPE().getSTSRCHDLHTYPEFTLLISHTYPE().setListID(BigInteger.valueOf(entryNumber));

			codeplug.getAPPPARTITION().getSTSRCHDLHTYPEGRP().getSTSRCHDLTTYPE().add(roamList);

			return roamList;
		}

	}

	private void addChannelToRoamList(String roamListName, int channelId) {
		STSRCHDLTTYPE roamList;
		try {
			roamList = this.findOrCreateRoamList(roamListName);
			this.addChannelToRoamList(roamList, channelId);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addChannelToRoamList(STSRCHDLTTYPE roamList, int channelId) {

		// Need to set the channel to an IP Site Connect channel
		// TODO: This doesn't seem to be the correct entry!

		for (CNVPERCMPTYPE channel : codeplug.getAPPPARTITION().getCNVPERCMPTYPEGRP().getCNVPERCMPTYPE()) {
			if (channel.getListID().intValue() == channelId) {
				for (Object child : channel.getCPPERSTYPEOrCPSELECT5CHAN()) {
					if (child instanceof CPMLTSTPSNLTIND) {
						((CPMLTSTPSNLTIND) child).setValue(BigInteger.valueOf(1));
						;
					}
				}
			}
		}

		// The listLetID seems to be unique to the scan list entry, across all scan
		// lists (and issued sequentially)
		int entryNumber = 0;

		for (STSRCHDLTTYPE roamListSearch : codeplug.getAPPPARTITION().getSTSRCHDLHTYPEGRP().getSTSRCHDLTTYPE()) {
			for (STSRCHDLLTYPE roamListEntrySearch : roamListSearch.getSTSRCHDLLTYPE()) {
				if (roamListEntrySearch.getListLetID().intValue() >= entryNumber) {
					entryNumber = roamListEntrySearch.getListLetID().intValue() + 1;
				}
			}
		}

		STSRCHDLLTYPE roamListEntry = new STSRCHDLLTYPE();
		roamListEntry.setApplicable("Enabled");
		;
		roamListEntry.setListID(roamList.getListID());
		roamListEntry.setListLetID(BigInteger.valueOf(entryNumber));

		STSRCHDLLTYPENTLLISHTYPE ntllishtype = new STSRCHDLLTYPENTLLISHTYPE();
		ntllishtype.setApplicable("Enabled");
		;
		ntllishtype.setListID(roamList.getListID());
		ntllishtype.setListLetID(BigInteger.valueOf(entryNumber));
		ntllishtype.setValue("NULL");
		roamListEntry.setSTSRCHDLLTYPENTLLISHTYPE(ntllishtype);

		STSRCHDLLTYPENTLLISHID ntllishid = new STSRCHDLLTYPENTLLISHID();
		ntllishid.setApplicable("Enabled");
		;
		ntllishid.setListID(roamList.getListID());
		ntllishid.setListLetID(BigInteger.valueOf(entryNumber));
		ntllishid.setValue(BigInteger.valueOf(0));
		roamListEntry.setSTSRCHDLLTYPENTLLISHID(ntllishid);

		SSPLLEQ lleq = new SSPLLEQ();
		lleq.setApplicable("Enabled");
		;
		lleq.setListID(roamList.getListID());
		lleq.setListLetID(BigInteger.valueOf(entryNumber));
		lleq.setValue(BigInteger.valueOf(1));
		roamListEntry.setSSPLLEQ(lleq);

		SSPCNVPERCOMP cnvpercomp = new SSPCNVPERCOMP();
		cnvpercomp.setApplicable("Enabled");
		;
		cnvpercomp.setListID(roamList.getListID());
		cnvpercomp.setListLetID(BigInteger.valueOf(entryNumber));
		cnvpercomp.setValue(BigInteger.valueOf(channelId));
		cnvpercomp.setTypeID("CNV_PER_CMP_TYPE");
		roamListEntry.setSSPCNVPERCOMP(cnvpercomp);

		SSPCNVPERCOMPTYPE cnvpercomptype = new SSPCNVPERCOMPTYPE();
		cnvpercomptype.setApplicable("Enabled");
		;
		cnvpercomptype.setListID(roamList.getListID());
		cnvpercomptype.setListLetID(BigInteger.valueOf(entryNumber));
		cnvpercomptype.setValue("CNV_PER_CMP_TYPE");
		roamListEntry.setSSPCNVPERCOMPTYPE(cnvpercomptype);

		SSPCNVPERCOMPID cnvpercompid = new SSPCNVPERCOMPID();
		cnvpercompid.setApplicable("Enabled");
		;
		cnvpercompid.setListID(roamList.getListID());
		cnvpercompid.setListLetID(BigInteger.valueOf(entryNumber));
		cnvpercompid.setValue(BigInteger.valueOf(channelId));
		roamListEntry.setSSPCNVPERCOMPID(cnvpercompid);

		SSPLRESERVED reserved = new SSPLRESERVED();
		reserved.setApplicable("Enabled");
		;
		reserved.setListID(roamList.getListID());
		reserved.setListLetID(BigInteger.valueOf(entryNumber));
		reserved.setValue(BigInteger.valueOf(0));
		roamListEntry.setSSPLRESERVED(reserved);

		roamList.getSTSRCHDLLTYPE().add(roamListEntry);
	}

	private SCANPERDLTTYPE findScanList(String scanListName) throws ScanListNotFoundException {
		for (SCANPERDLTTYPE scanList : codeplug.getAPPPARTITION().getSCANPERDLHTYPEGRP().getSCANPERDLTTYPE()) {
			if (scanList.getSCANPERDLHTYPE().getSPSCANLISTALIAS().getContent().equals(scanListName)) {
				MotoTools.logger
						.debug("Found scan list " + scanListName + " (ID " + scanList.getListID().intValue() + ")");
				return scanList;
			}
		}

		throw new ScanListNotFoundException();
	}

	/**
	 * Warning, not thread safe!
	 * 
	 * @param zoneName
	 * @return
	 * @throws JAXBException
	 */
	private SCANPERDLTTYPE findOrCreateScanList(String scanListName) throws JAXBException {
		try {
			return this.findScanList(scanListName);
		} catch (ScanListNotFoundException e) {

			int entryNumber = 0;

			for (SCANPERDLTTYPE scanList : codeplug.getAPPPARTITION().getSCANPERDLHTYPEGRP().getSCANPERDLTTYPE()) {
				if (scanList.getListID().intValue() >= entryNumber) {
					entryNumber = scanList.getListID().intValue() + 1;
				}
			}

			MotoTools.logger
					.info("Scan List " + scanListName + " not found, creating it now using ID " + entryNumber + "...");

			File scanListTemplate = new File(
					getClass().getClassLoader().getResource("scan-list-template.xml").getFile());
			JAXBContext jaxbContext = JAXBContext.newInstance(SCANPERDLTTYPE.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SCANPERDLTTYPE scanList = (SCANPERDLTTYPE) jaxbUnmarshaller.unmarshal(scanListTemplate);
			scanList.setListID(BigInteger.valueOf(entryNumber));

			scanList.setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSCANPERDLHTYPEFTLLISHID().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSCANPERDLHTYPEFTLLISHTYPE().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSCANRADTBPRIOSAMP().setListID(BigInteger.valueOf(entryNumber));

			scanList.getSCANPERDLHTYPE().getSCSCANSIGHT().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPATACKSC().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPDISPTXCH().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPDISPVTCHRCV().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPDSGTXMEM().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPDSGTXMEMID().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPDSGTXMEMTYPE().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPERLMT().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPLEDSZ().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPLEQMAX().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPLLEQMAX().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPLSTCTG().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPPLLOCKCHMARKEN().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPPR1MEM().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPPR1MEMID().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPPR1MEMTYPE().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPPR2MEM().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPPR2MEMID().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPPR2MEMTYPE().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPSCANLISTALIAS().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPSCANLISTALIAS().setContent(scanListName);
			scanList.getSCANPERDLHTYPE().getSPSCHSKIPCYCLE().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPSCLSTTYPE().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPSCPLTYPE().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPTLKSCNEN().setListID(BigInteger.valueOf(entryNumber));
			scanList.getSCANPERDLHTYPE().getSPVTSCPRETMDL().setListID(BigInteger.valueOf(entryNumber));

			codeplug.getAPPPARTITION().getSCANPERDLHTYPEGRP().getSCANPERDLTTYPE().add(scanList);

			return scanList;
		}

	}

	private void addChannelToScanList(String scanListName, int channelId) {

		SCANPERDLTTYPE scanList;
		try {
			scanList = this.findOrCreateScanList(scanListName);

			this.addChannelToScanList(scanList, channelId);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addChannelToScanList(SCANPERDLTTYPE scanList, int channelId) {

		// The listLetID seems to be unique to the scan list entry, across all scan
		// lists (and issued sequentially)
		int entryNumber = 0;

		for (SCANPERDLTTYPE scanListSearch : codeplug.getAPPPARTITION().getSCANPERDLHTYPEGRP().getSCANPERDLTTYPE()) {
			for (SCANPERDLLTYPE scanListEntrySearch : scanListSearch.getSCANPERDLLTYPE()) {
				if (scanListEntrySearch.getListLetID().intValue() >= entryNumber) {
					entryNumber = scanListEntrySearch.getListLetID().intValue() + 1;
				}
			}
		}

		SCANPERDLLTYPE scanListEntry = new SCANPERDLLTYPE();
		scanListEntry.setApplicable("Enabled");
		scanListEntry.setListID(scanList.getListID());
		scanListEntry.setListLetID(BigInteger.valueOf(entryNumber));

		SCANPERDLLTYPENTLLISHTYPE scanPerDllTypeNtlLishType = new SCANPERDLLTYPENTLLISHTYPE();
		scanPerDllTypeNtlLishType.setApplicable("Enabled");
		scanPerDllTypeNtlLishType.setListID(scanList.getListID());
		scanPerDllTypeNtlLishType.setListLetID(BigInteger.valueOf(entryNumber));
		scanPerDllTypeNtlLishType.setValue("NULL");
		scanListEntry.setSCANPERDLLTYPENTLLISHTYPE(scanPerDllTypeNtlLishType);

		SCANPERDLLTYPENTLLISHID scanPerDllTypeNtlLishId = new SCANPERDLLTYPENTLLISHID();
		scanPerDllTypeNtlLishId.setApplicable("Enabled");
		scanPerDllTypeNtlLishId.setListID(scanList.getListID());
		scanPerDllTypeNtlLishId.setListLetID(BigInteger.valueOf(entryNumber));
		scanPerDllTypeNtlLishId.setValue(BigInteger.valueOf(0));
		scanListEntry.setSCANPERDLLTYPENTLLISHID(scanPerDllTypeNtlLishId);

		SPCNVPERCOMP spCnvPerComp = new SPCNVPERCOMP();
		spCnvPerComp.setApplicable("Enabled");
		spCnvPerComp.setListID(scanList.getListID());
		spCnvPerComp.setListLetID(BigInteger.valueOf(entryNumber));
		spCnvPerComp.setTypeID("CNV_PER_CMP_TYPE");
		spCnvPerComp.setValue(BigInteger.valueOf(channelId));
		scanListEntry.setSPCNVPERCOMP(spCnvPerComp);

		SPCNVPERCOMPID spCnvPerCompId = new SPCNVPERCOMPID();
		spCnvPerCompId.setApplicable("Enabled");
		spCnvPerCompId.setListID(scanList.getListID());
		spCnvPerCompId.setListLetID(BigInteger.valueOf(entryNumber));
		spCnvPerCompId.setValue(BigInteger.valueOf(channelId));
		scanListEntry.setSPCNVPERCOMPID(spCnvPerCompId);

		SPCNVPERCOMPTYPE spCnvPerCompType = new SPCNVPERCOMPTYPE();
		spCnvPerCompType.setApplicable("Enabled");
		spCnvPerCompType.setListID(scanList.getListID());
		spCnvPerCompType.setListLetID(BigInteger.valueOf(entryNumber));
		spCnvPerCompType.setValue("CNV_PER_CMP_TYPE");
		scanListEntry.setSPCNVPERCOMPTYPE(spCnvPerCompType);

		SPLLEQ spLleq = new SPLLEQ();
		spLleq.setApplicable("Enabled");
		spLleq.setListID(scanList.getListID());
		spLleq.setListLetID(BigInteger.valueOf(entryNumber));
		spLleq.setValue(BigInteger.valueOf(1));
		scanListEntry.setSPLLEQ(spLleq);

		SPLRESERVED splReserved = new SPLRESERVED();
		splReserved.setApplicable("Enabled");
		splReserved.setListID(scanList.getListID());
		splReserved.setListLetID(BigInteger.valueOf(entryNumber));
		splReserved.setValue(BigInteger.valueOf(0));
		scanListEntry.setSPLRESERVED(splReserved);

		scanList.getSCANPERDLLTYPE().add(scanListEntry);

	}

	private void addChannelToZone(int channelId, ZNPERASGNDLTTYPE zone) {
		ZNPERASGNDLLTYPE channel = new ZNPERASGNDLLTYPE();
		channel.setListID(zone.getListID());
		channel.setApplicable("Enabled");
		channel.setListLetID(BigInteger.valueOf(channelId));

		// Unknown
		ZNPERASGNDLLTYPENTLLISHTYPE znperasgndlltypeftllishtype = new ZNPERASGNDLLTYPENTLLISHTYPE();
		znperasgndlltypeftllishtype.setListID(zone.getListID());
		znperasgndlltypeftllishtype.setApplicable("Enabled");
		znperasgndlltypeftllishtype.setValue("ZN_PER_ASGN_DLL_TYPE");
		channel.setZNPERASGNDLLTYPENTLLISHTYPE(znperasgndlltypeftllishtype);

		// Unknown
		ZNPERASGNDLLTYPENTLLISHID znperasgndlltypeftllishid = new ZNPERASGNDLLTYPENTLLISHID();
		znperasgndlltypeftllishid.setListID(zone.getListID());
		znperasgndlltypeftllishid.setApplicable("Enabled");
		znperasgndlltypeftllishid.setValue(BigInteger.valueOf(0));
		channel.setZNPERASGNDLLTYPENTLLISHID(znperasgndlltypeftllishid);

		// Unknown
		ZPLLEQ zplleq = new ZPLLEQ();
		zplleq.setListID(zone.getListID());
		zplleq.setListLetID(BigInteger.valueOf(channelId));
		zplleq.setApplicable("Enabled");
		zplleq.setValue(BigInteger.valueOf(1));
		channel.setZPLLEQ(zplleq);

		// Unknown
		ZPPERSIT zppersit = new ZPPERSIT();
		zppersit.setListID(zone.getListID());
		zppersit.setListLetID(BigInteger.valueOf(channelId));
		zppersit.setApplicable("Enabled");
		zppersit.setTypeID("CNV_PER_CMP_TYPE");
		zppersit.setValue(BigInteger.valueOf(channelId));
		channel.setZPPERSIT(zppersit);

		// Unknown
		ZPPERSITTYPE zppersittype = new ZPPERSITTYPE();
		zppersittype.setListID(zone.getListID());
		zppersittype.setListLetID(BigInteger.valueOf(channelId));
		zppersittype.setApplicable("Enabled");
		zppersittype.setValue("CNV_PER_CMP_TYPE");
		channel.setZPPERSITTYPE(zppersittype);

		// Unknown
		ZPPERSITID zppersitid = new ZPPERSITID();
		zppersitid.setListID(zone.getListID());
		zppersitid.setListLetID(BigInteger.valueOf(channelId));
		zppersitid.setApplicable("Enabled");
		zppersitid.setValue(BigInteger.valueOf(channelId));
		channel.setZPPERSITID(zppersitid);

		// Unknown
		ZPLRESERVED zplreserved = new ZPLRESERVED();
		zplreserved.setListID(zone.getListID());
		zplreserved.setListLetID(BigInteger.valueOf(channelId));
		zplreserved.setApplicable("Enabled");
		zplreserved.setValue(BigInteger.valueOf(0));
		channel.setZPLRESERVED(zplreserved);

		zone.getZNPERASGNDLLTYPE().add(channel);

	}

	private LTDCODEPLUG loadXml(String sourceXml) {
		File file = new File(sourceXml);
		return loadXml(file);
	}

	private LTDCODEPLUG loadXml(File sourceXml) {
		LTDCODEPLUG codeplug = new LTDCODEPLUG();
		;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(LTDCODEPLUG.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			codeplug = (LTDCODEPLUG) jaxbUnmarshaller.unmarshal(sourceXml);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return codeplug;
	}

	public void toXml(String filename) throws JAXBException, FileNotFoundException {
		JAXBContext contextObj = JAXBContext.newInstance(LTDCODEPLUG.class);

		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshallerObj.marshal(codeplug, new FileOutputStream(filename));
	}

	public String toXml() throws JAXBException {
		StringWriter writer = new StringWriter();
		JAXBContext contextObj = JAXBContext.newInstance(LTDCODEPLUG.class);

		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshallerObj.marshal(codeplug, writer);

		return writer.toString();
	}

	/**
	 * Some useful reflection methods. They're here for posterity - not used any
	 * more :(
	 */
	static ArrayList<Method> findGettersSetters(Class<?> c) {
		ArrayList<Method> list = new ArrayList<Method>();
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods)
			if (isGetter(method) || isSetter(method))
				list.add(method);
		return list;
	}

	static ArrayList<Method> findGetters(Class<?> c) {
		ArrayList<Method> list = new ArrayList<Method>();
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods)
			if (isGetter(method))
				list.add(method);
		return list;
	}

	static ArrayList<Method> findSetters(Class<?> c) {
		ArrayList<Method> list = new ArrayList<Method>();
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods)
			if (isSetter(method))
				list.add(method);
		return list;
	}

	public static boolean isGetter(Method method) {
		if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0) {
			if (method.getName().matches("^get[A-Z].*") && !method.getReturnType().equals(void.class))
				return true;
			if (method.getName().matches("^is[A-Z].*") && method.getReturnType().equals(boolean.class))
				return true;
		}
		return false;
	}

	public static boolean isSetter(Method method) {
		return Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(void.class)
				&& method.getParameterTypes().length == 1 && method.getName().matches("^set[A-Z].*");
	}

}
