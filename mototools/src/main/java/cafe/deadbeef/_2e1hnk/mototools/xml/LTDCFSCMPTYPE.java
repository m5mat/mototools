//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0-b170531.0717 
//         See <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2018.02.07 at 05:56:42 PM GMT 
//


package cafe.deadbeef._2e1hnk.mototools.xml;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}CFS_ILLEGCPCONFCNT"/&gt;
 *         &lt;element ref="{}CFS_INVALIDFECODECNT"/&gt;
 *         &lt;element ref="{}CFS_IPSITECON"/&gt;
 *         &lt;element ref="{}CFS_CAPPLUS"/&gt;
 *         &lt;element ref="{}CFS_DIGITAL"/&gt;
 *         &lt;element ref="{}CFS_ENHPRIVACY"/&gt;
 *         &lt;element ref="{}CFS_TRANSINT"/&gt;
 *         &lt;element ref="{}CFS_DMM"/&gt;
 *         &lt;element ref="{}CFS_NEPTUNE"/&gt;
 *         &lt;element ref="{}CFS_ENHGPS"/&gt;
 *         &lt;element ref="{}CFS_MTRTOPHNFWCVT"/&gt;
 *         &lt;element ref="{}CFS_DGTLPHNPTC"/&gt;
 *         &lt;element ref="{}CFS_5TONE"/&gt;
 *         &lt;element ref="{}CFS_GPSEN"/&gt;
 *         &lt;element ref="{}CFS_AESPRIVACY"/&gt;
 *         &lt;element ref="{}CFS_LCP"/&gt;
 *         &lt;element ref="{}CFS_CONNECT_PLUS_MANDOWN"/&gt;
 *         &lt;element ref="{}CFS_BTDATASERVICE"/&gt;
 *         &lt;element ref="{}CFS_DGTEMGR"/&gt;
 *         &lt;element ref="{}CFS_RMTMNT"/&gt;
 *         &lt;element ref="{}CFS_RDIHBT"/&gt;
 *         &lt;element ref="{}CFS_ATEXCSAMANDOWN"/&gt;
 *         &lt;element ref="{}CFS_BTPERMANENTDISCOVERABLEMODE"/&gt;
 *         &lt;element ref="{}CFS_PDT"/&gt;
 *         &lt;element ref="{}CFS_CBFREQUENCY"/&gt;
 *         &lt;element ref="{}CFS_EOB"/&gt;
 *         &lt;element ref="{}CFS_EOT"/&gt;
 *         &lt;element ref="{}CFS_KEEPALIVE"/&gt;
 *         &lt;element ref="{}CFS_MULTIBUTTONPTT"/&gt;
 *         &lt;element ref="{}CFS_TTS"/&gt;
 *         &lt;element ref="{}CFS_RESERVED"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Applicable" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *       &lt;attribute name="ListID" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cfsillegcpconfcnt",
    "cfsinvalidfecodecnt",
    "cfsipsitecon",
    "cfscapplus",
    "cfsdigital",
    "cfsenhprivacy",
    "cfstransint",
    "cfsdmm",
    "cfsneptune",
    "cfsenhgps",
    "cfsmtrtophnfwcvt",
    "cfsdgtlphnptc",
    "cfs5TONE",
    "cfsgpsen",
    "cfsaesprivacy",
    "cfslcp",
    "cfsconnectplusmandown",
    "cfsbtdataservice",
    "cfsdgtemgr",
    "cfsrmtmnt",
    "cfsrdihbt",
    "cfsatexcsamandown",
    "cfsbtpermanentdiscoverablemode",
    "cfspdt",
    "cfscbfrequency",
    "cfseob",
    "cfseot",
    "cfskeepalive",
    "cfsmultibuttonptt",
    "cfstts",
    "cfsreserved"
})
@XmlRootElement(name = "LTD_CFS_CMP_TYPE")
public class LTDCFSCMPTYPE {

    @XmlElement(name = "CFS_ILLEGCPCONFCNT", required = true)
    protected CFSILLEGCPCONFCNT cfsillegcpconfcnt;
    @XmlElement(name = "CFS_INVALIDFECODECNT", required = true)
    protected CFSINVALIDFECODECNT cfsinvalidfecodecnt;
    @XmlElement(name = "CFS_IPSITECON", required = true)
    protected CFSIPSITECON cfsipsitecon;
    @XmlElement(name = "CFS_CAPPLUS", required = true)
    protected CFSCAPPLUS cfscapplus;
    @XmlElement(name = "CFS_DIGITAL", required = true)
    protected CFSDIGITAL cfsdigital;
    @XmlElement(name = "CFS_ENHPRIVACY", required = true)
    protected CFSENHPRIVACY cfsenhprivacy;
    @XmlElement(name = "CFS_TRANSINT", required = true)
    protected CFSTRANSINT cfstransint;
    @XmlElement(name = "CFS_DMM", required = true)
    protected CFSDMM cfsdmm;
    @XmlElement(name = "CFS_NEPTUNE", required = true)
    protected CFSNEPTUNE cfsneptune;
    @XmlElement(name = "CFS_ENHGPS", required = true)
    protected CFSENHGPS cfsenhgps;
    @XmlElement(name = "CFS_MTRTOPHNFWCVT", required = true)
    protected CFSMTRTOPHNFWCVT cfsmtrtophnfwcvt;
    @XmlElement(name = "CFS_DGTLPHNPTC", required = true)
    protected CFSDGTLPHNPTC cfsdgtlphnptc;
    @XmlElement(name = "CFS_5TONE", required = true)
    protected CFS5TONE cfs5TONE;
    @XmlElement(name = "CFS_GPSEN", required = true)
    protected CFSGPSEN cfsgpsen;
    @XmlElement(name = "CFS_AESPRIVACY", required = true)
    protected CFSAESPRIVACY cfsaesprivacy;
    @XmlElement(name = "CFS_LCP", required = true)
    protected CFSLCP cfslcp;
    @XmlElement(name = "CFS_CONNECT_PLUS_MANDOWN", required = true)
    protected CFSCONNECTPLUSMANDOWN cfsconnectplusmandown;
    @XmlElement(name = "CFS_BTDATASERVICE", required = true)
    protected CFSBTDATASERVICE cfsbtdataservice;
    @XmlElement(name = "CFS_DGTEMGR", required = true)
    protected CFSDGTEMGR cfsdgtemgr;
    @XmlElement(name = "CFS_RMTMNT", required = true)
    protected CFSRMTMNT cfsrmtmnt;
    @XmlElement(name = "CFS_RDIHBT", required = true)
    protected CFSRDIHBT cfsrdihbt;
    @XmlElement(name = "CFS_ATEXCSAMANDOWN", required = true)
    protected CFSATEXCSAMANDOWN cfsatexcsamandown;
    @XmlElement(name = "CFS_BTPERMANENTDISCOVERABLEMODE", required = true)
    protected CFSBTPERMANENTDISCOVERABLEMODE cfsbtpermanentdiscoverablemode;
    @XmlElement(name = "CFS_PDT", required = true)
    protected CFSPDT cfspdt;
    @XmlElement(name = "CFS_CBFREQUENCY", required = true)
    protected CFSCBFREQUENCY cfscbfrequency;
    @XmlElement(name = "CFS_EOB", required = true)
    protected CFSEOB cfseob;
    @XmlElement(name = "CFS_EOT", required = true)
    protected CFSEOT cfseot;
    @XmlElement(name = "CFS_KEEPALIVE", required = true)
    protected CFSKEEPALIVE cfskeepalive;
    @XmlElement(name = "CFS_MULTIBUTTONPTT", required = true)
    protected CFSMULTIBUTTONPTT cfsmultibuttonptt;
    @XmlElement(name = "CFS_TTS", required = true)
    protected CFSTTS cfstts;
    @XmlElement(name = "CFS_RESERVED", required = true)
    protected CFSRESERVED cfsreserved;
    @XmlAttribute(name = "Applicable", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String applicable;
    @XmlAttribute(name = "ListID", required = true)
    protected BigInteger listID;

    /**
     * Gets the value of the cfsillegcpconfcnt property.
     * 
     * @return
     *     possible object is
     *     {@link CFSILLEGCPCONFCNT }
     *     
     */
    public CFSILLEGCPCONFCNT getCFSILLEGCPCONFCNT() {
        return cfsillegcpconfcnt;
    }

    /**
     * Sets the value of the cfsillegcpconfcnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSILLEGCPCONFCNT }
     *     
     */
    public void setCFSILLEGCPCONFCNT(CFSILLEGCPCONFCNT value) {
        this.cfsillegcpconfcnt = value;
    }

    /**
     * Gets the value of the cfsinvalidfecodecnt property.
     * 
     * @return
     *     possible object is
     *     {@link CFSINVALIDFECODECNT }
     *     
     */
    public CFSINVALIDFECODECNT getCFSINVALIDFECODECNT() {
        return cfsinvalidfecodecnt;
    }

    /**
     * Sets the value of the cfsinvalidfecodecnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSINVALIDFECODECNT }
     *     
     */
    public void setCFSINVALIDFECODECNT(CFSINVALIDFECODECNT value) {
        this.cfsinvalidfecodecnt = value;
    }

    /**
     * Gets the value of the cfsipsitecon property.
     * 
     * @return
     *     possible object is
     *     {@link CFSIPSITECON }
     *     
     */
    public CFSIPSITECON getCFSIPSITECON() {
        return cfsipsitecon;
    }

    /**
     * Sets the value of the cfsipsitecon property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSIPSITECON }
     *     
     */
    public void setCFSIPSITECON(CFSIPSITECON value) {
        this.cfsipsitecon = value;
    }

    /**
     * Gets the value of the cfscapplus property.
     * 
     * @return
     *     possible object is
     *     {@link CFSCAPPLUS }
     *     
     */
    public CFSCAPPLUS getCFSCAPPLUS() {
        return cfscapplus;
    }

    /**
     * Sets the value of the cfscapplus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSCAPPLUS }
     *     
     */
    public void setCFSCAPPLUS(CFSCAPPLUS value) {
        this.cfscapplus = value;
    }

    /**
     * Gets the value of the cfsdigital property.
     * 
     * @return
     *     possible object is
     *     {@link CFSDIGITAL }
     *     
     */
    public CFSDIGITAL getCFSDIGITAL() {
        return cfsdigital;
    }

    /**
     * Sets the value of the cfsdigital property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSDIGITAL }
     *     
     */
    public void setCFSDIGITAL(CFSDIGITAL value) {
        this.cfsdigital = value;
    }

    /**
     * Gets the value of the cfsenhprivacy property.
     * 
     * @return
     *     possible object is
     *     {@link CFSENHPRIVACY }
     *     
     */
    public CFSENHPRIVACY getCFSENHPRIVACY() {
        return cfsenhprivacy;
    }

    /**
     * Sets the value of the cfsenhprivacy property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSENHPRIVACY }
     *     
     */
    public void setCFSENHPRIVACY(CFSENHPRIVACY value) {
        this.cfsenhprivacy = value;
    }

    /**
     * Gets the value of the cfstransint property.
     * 
     * @return
     *     possible object is
     *     {@link CFSTRANSINT }
     *     
     */
    public CFSTRANSINT getCFSTRANSINT() {
        return cfstransint;
    }

    /**
     * Sets the value of the cfstransint property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSTRANSINT }
     *     
     */
    public void setCFSTRANSINT(CFSTRANSINT value) {
        this.cfstransint = value;
    }

    /**
     * Gets the value of the cfsdmm property.
     * 
     * @return
     *     possible object is
     *     {@link CFSDMM }
     *     
     */
    public CFSDMM getCFSDMM() {
        return cfsdmm;
    }

    /**
     * Sets the value of the cfsdmm property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSDMM }
     *     
     */
    public void setCFSDMM(CFSDMM value) {
        this.cfsdmm = value;
    }

    /**
     * Gets the value of the cfsneptune property.
     * 
     * @return
     *     possible object is
     *     {@link CFSNEPTUNE }
     *     
     */
    public CFSNEPTUNE getCFSNEPTUNE() {
        return cfsneptune;
    }

    /**
     * Sets the value of the cfsneptune property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSNEPTUNE }
     *     
     */
    public void setCFSNEPTUNE(CFSNEPTUNE value) {
        this.cfsneptune = value;
    }

    /**
     * Gets the value of the cfsenhgps property.
     * 
     * @return
     *     possible object is
     *     {@link CFSENHGPS }
     *     
     */
    public CFSENHGPS getCFSENHGPS() {
        return cfsenhgps;
    }

    /**
     * Sets the value of the cfsenhgps property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSENHGPS }
     *     
     */
    public void setCFSENHGPS(CFSENHGPS value) {
        this.cfsenhgps = value;
    }

    /**
     * Gets the value of the cfsmtrtophnfwcvt property.
     * 
     * @return
     *     possible object is
     *     {@link CFSMTRTOPHNFWCVT }
     *     
     */
    public CFSMTRTOPHNFWCVT getCFSMTRTOPHNFWCVT() {
        return cfsmtrtophnfwcvt;
    }

    /**
     * Sets the value of the cfsmtrtophnfwcvt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSMTRTOPHNFWCVT }
     *     
     */
    public void setCFSMTRTOPHNFWCVT(CFSMTRTOPHNFWCVT value) {
        this.cfsmtrtophnfwcvt = value;
    }

    /**
     * Gets the value of the cfsdgtlphnptc property.
     * 
     * @return
     *     possible object is
     *     {@link CFSDGTLPHNPTC }
     *     
     */
    public CFSDGTLPHNPTC getCFSDGTLPHNPTC() {
        return cfsdgtlphnptc;
    }

    /**
     * Sets the value of the cfsdgtlphnptc property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSDGTLPHNPTC }
     *     
     */
    public void setCFSDGTLPHNPTC(CFSDGTLPHNPTC value) {
        this.cfsdgtlphnptc = value;
    }

    /**
     * Gets the value of the cfs5TONE property.
     * 
     * @return
     *     possible object is
     *     {@link CFS5TONE }
     *     
     */
    public CFS5TONE getCFS5TONE() {
        return cfs5TONE;
    }

    /**
     * Sets the value of the cfs5TONE property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFS5TONE }
     *     
     */
    public void setCFS5TONE(CFS5TONE value) {
        this.cfs5TONE = value;
    }

    /**
     * Gets the value of the cfsgpsen property.
     * 
     * @return
     *     possible object is
     *     {@link CFSGPSEN }
     *     
     */
    public CFSGPSEN getCFSGPSEN() {
        return cfsgpsen;
    }

    /**
     * Sets the value of the cfsgpsen property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSGPSEN }
     *     
     */
    public void setCFSGPSEN(CFSGPSEN value) {
        this.cfsgpsen = value;
    }

    /**
     * Gets the value of the cfsaesprivacy property.
     * 
     * @return
     *     possible object is
     *     {@link CFSAESPRIVACY }
     *     
     */
    public CFSAESPRIVACY getCFSAESPRIVACY() {
        return cfsaesprivacy;
    }

    /**
     * Sets the value of the cfsaesprivacy property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSAESPRIVACY }
     *     
     */
    public void setCFSAESPRIVACY(CFSAESPRIVACY value) {
        this.cfsaesprivacy = value;
    }

    /**
     * Gets the value of the cfslcp property.
     * 
     * @return
     *     possible object is
     *     {@link CFSLCP }
     *     
     */
    public CFSLCP getCFSLCP() {
        return cfslcp;
    }

    /**
     * Sets the value of the cfslcp property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSLCP }
     *     
     */
    public void setCFSLCP(CFSLCP value) {
        this.cfslcp = value;
    }

    /**
     * Gets the value of the cfsconnectplusmandown property.
     * 
     * @return
     *     possible object is
     *     {@link CFSCONNECTPLUSMANDOWN }
     *     
     */
    public CFSCONNECTPLUSMANDOWN getCFSCONNECTPLUSMANDOWN() {
        return cfsconnectplusmandown;
    }

    /**
     * Sets the value of the cfsconnectplusmandown property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSCONNECTPLUSMANDOWN }
     *     
     */
    public void setCFSCONNECTPLUSMANDOWN(CFSCONNECTPLUSMANDOWN value) {
        this.cfsconnectplusmandown = value;
    }

    /**
     * Gets the value of the cfsbtdataservice property.
     * 
     * @return
     *     possible object is
     *     {@link CFSBTDATASERVICE }
     *     
     */
    public CFSBTDATASERVICE getCFSBTDATASERVICE() {
        return cfsbtdataservice;
    }

    /**
     * Sets the value of the cfsbtdataservice property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSBTDATASERVICE }
     *     
     */
    public void setCFSBTDATASERVICE(CFSBTDATASERVICE value) {
        this.cfsbtdataservice = value;
    }

    /**
     * Gets the value of the cfsdgtemgr property.
     * 
     * @return
     *     possible object is
     *     {@link CFSDGTEMGR }
     *     
     */
    public CFSDGTEMGR getCFSDGTEMGR() {
        return cfsdgtemgr;
    }

    /**
     * Sets the value of the cfsdgtemgr property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSDGTEMGR }
     *     
     */
    public void setCFSDGTEMGR(CFSDGTEMGR value) {
        this.cfsdgtemgr = value;
    }

    /**
     * Gets the value of the cfsrmtmnt property.
     * 
     * @return
     *     possible object is
     *     {@link CFSRMTMNT }
     *     
     */
    public CFSRMTMNT getCFSRMTMNT() {
        return cfsrmtmnt;
    }

    /**
     * Sets the value of the cfsrmtmnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSRMTMNT }
     *     
     */
    public void setCFSRMTMNT(CFSRMTMNT value) {
        this.cfsrmtmnt = value;
    }

    /**
     * Gets the value of the cfsrdihbt property.
     * 
     * @return
     *     possible object is
     *     {@link CFSRDIHBT }
     *     
     */
    public CFSRDIHBT getCFSRDIHBT() {
        return cfsrdihbt;
    }

    /**
     * Sets the value of the cfsrdihbt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSRDIHBT }
     *     
     */
    public void setCFSRDIHBT(CFSRDIHBT value) {
        this.cfsrdihbt = value;
    }

    /**
     * Gets the value of the cfsatexcsamandown property.
     * 
     * @return
     *     possible object is
     *     {@link CFSATEXCSAMANDOWN }
     *     
     */
    public CFSATEXCSAMANDOWN getCFSATEXCSAMANDOWN() {
        return cfsatexcsamandown;
    }

    /**
     * Sets the value of the cfsatexcsamandown property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSATEXCSAMANDOWN }
     *     
     */
    public void setCFSATEXCSAMANDOWN(CFSATEXCSAMANDOWN value) {
        this.cfsatexcsamandown = value;
    }

    /**
     * Gets the value of the cfsbtpermanentdiscoverablemode property.
     * 
     * @return
     *     possible object is
     *     {@link CFSBTPERMANENTDISCOVERABLEMODE }
     *     
     */
    public CFSBTPERMANENTDISCOVERABLEMODE getCFSBTPERMANENTDISCOVERABLEMODE() {
        return cfsbtpermanentdiscoverablemode;
    }

    /**
     * Sets the value of the cfsbtpermanentdiscoverablemode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSBTPERMANENTDISCOVERABLEMODE }
     *     
     */
    public void setCFSBTPERMANENTDISCOVERABLEMODE(CFSBTPERMANENTDISCOVERABLEMODE value) {
        this.cfsbtpermanentdiscoverablemode = value;
    }

    /**
     * Gets the value of the cfspdt property.
     * 
     * @return
     *     possible object is
     *     {@link CFSPDT }
     *     
     */
    public CFSPDT getCFSPDT() {
        return cfspdt;
    }

    /**
     * Sets the value of the cfspdt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSPDT }
     *     
     */
    public void setCFSPDT(CFSPDT value) {
        this.cfspdt = value;
    }

    /**
     * Gets the value of the cfscbfrequency property.
     * 
     * @return
     *     possible object is
     *     {@link CFSCBFREQUENCY }
     *     
     */
    public CFSCBFREQUENCY getCFSCBFREQUENCY() {
        return cfscbfrequency;
    }

    /**
     * Sets the value of the cfscbfrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSCBFREQUENCY }
     *     
     */
    public void setCFSCBFREQUENCY(CFSCBFREQUENCY value) {
        this.cfscbfrequency = value;
    }

    /**
     * Gets the value of the cfseob property.
     * 
     * @return
     *     possible object is
     *     {@link CFSEOB }
     *     
     */
    public CFSEOB getCFSEOB() {
        return cfseob;
    }

    /**
     * Sets the value of the cfseob property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSEOB }
     *     
     */
    public void setCFSEOB(CFSEOB value) {
        this.cfseob = value;
    }

    /**
     * Gets the value of the cfseot property.
     * 
     * @return
     *     possible object is
     *     {@link CFSEOT }
     *     
     */
    public CFSEOT getCFSEOT() {
        return cfseot;
    }

    /**
     * Sets the value of the cfseot property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSEOT }
     *     
     */
    public void setCFSEOT(CFSEOT value) {
        this.cfseot = value;
    }

    /**
     * Gets the value of the cfskeepalive property.
     * 
     * @return
     *     possible object is
     *     {@link CFSKEEPALIVE }
     *     
     */
    public CFSKEEPALIVE getCFSKEEPALIVE() {
        return cfskeepalive;
    }

    /**
     * Sets the value of the cfskeepalive property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSKEEPALIVE }
     *     
     */
    public void setCFSKEEPALIVE(CFSKEEPALIVE value) {
        this.cfskeepalive = value;
    }

    /**
     * Gets the value of the cfsmultibuttonptt property.
     * 
     * @return
     *     possible object is
     *     {@link CFSMULTIBUTTONPTT }
     *     
     */
    public CFSMULTIBUTTONPTT getCFSMULTIBUTTONPTT() {
        return cfsmultibuttonptt;
    }

    /**
     * Sets the value of the cfsmultibuttonptt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSMULTIBUTTONPTT }
     *     
     */
    public void setCFSMULTIBUTTONPTT(CFSMULTIBUTTONPTT value) {
        this.cfsmultibuttonptt = value;
    }

    /**
     * Gets the value of the cfstts property.
     * 
     * @return
     *     possible object is
     *     {@link CFSTTS }
     *     
     */
    public CFSTTS getCFSTTS() {
        return cfstts;
    }

    /**
     * Sets the value of the cfstts property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSTTS }
     *     
     */
    public void setCFSTTS(CFSTTS value) {
        this.cfstts = value;
    }

    /**
     * Gets the value of the cfsreserved property.
     * 
     * @return
     *     possible object is
     *     {@link CFSRESERVED }
     *     
     */
    public CFSRESERVED getCFSRESERVED() {
        return cfsreserved;
    }

    /**
     * Sets the value of the cfsreserved property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFSRESERVED }
     *     
     */
    public void setCFSRESERVED(CFSRESERVED value) {
        this.cfsreserved = value;
    }

    /**
     * Gets the value of the applicable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicable() {
        return applicable;
    }

    /**
     * Sets the value of the applicable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicable(String value) {
        this.applicable = value;
    }

    /**
     * Gets the value of the listID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getListID() {
        return listID;
    }

    /**
     * Sets the value of the listID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setListID(BigInteger value) {
        this.listID = value;
    }

}
