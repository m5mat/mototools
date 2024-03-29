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
 *         &lt;element ref="{}ACCY_VOLCTRLEN"/&gt;
 *         &lt;element ref="{}ACCY_VOLFDBKEN"/&gt;
 *         &lt;element ref="{}ACCY_3RDPPCBLMD"/&gt;
 *         &lt;element ref="{}ACCY_PWRUPDEL"/&gt;
 *         &lt;element ref="{}ACCY_NUMENT"/&gt;
 *         &lt;element ref="{}ACCY_VNDRID"/&gt;
 *         &lt;element ref="{}ACCY_DATARVTCHNL"/&gt;
 *         &lt;element ref="{}ACCY_DATARVTCHNLTYPE"/&gt;
 *         &lt;element ref="{}ACCY_DATARVTCHNLID"/&gt;
 *         &lt;element ref="{}ACCY_PREDATARVTCHNL"/&gt;
 *         &lt;element ref="{}ACCY_PREDATARVTCHNLTYPE"/&gt;
 *         &lt;element ref="{}ACCY_PREDATARVTCHNLID"/&gt;
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
    "accyvolctrlen",
    "accyvolfdbken",
    "accy3RDPPCBLMD",
    "accypwrupdel",
    "accynument",
    "accyvndrid",
    "accydatarvtchnl",
    "accydatarvtchnltype",
    "accydatarvtchnlid",
    "accypredatarvtchnl",
    "accypredatarvtchnltype",
    "accypredatarvtchnlid"
})
@XmlRootElement(name = "ACCESSORY_CONNECTOR_CMP_TYPE")
public class ACCESSORYCONNECTORCMPTYPE {

    @XmlElement(name = "ACCY_VOLCTRLEN", required = true)
    protected ACCYVOLCTRLEN accyvolctrlen;
    @XmlElement(name = "ACCY_VOLFDBKEN", required = true)
    protected ACCYVOLFDBKEN accyvolfdbken;
    @XmlElement(name = "ACCY_3RDPPCBLMD", required = true)
    protected ACCY3RDPPCBLMD accy3RDPPCBLMD;
    @XmlElement(name = "ACCY_PWRUPDEL", required = true)
    protected ACCYPWRUPDEL accypwrupdel;
    @XmlElement(name = "ACCY_NUMENT", required = true)
    protected ACCYNUMENT accynument;
    @XmlElement(name = "ACCY_VNDRID", required = true)
    protected ACCYVNDRID accyvndrid;
    @XmlElement(name = "ACCY_DATARVTCHNL", required = true)
    protected ACCYDATARVTCHNL accydatarvtchnl;
    @XmlElement(name = "ACCY_DATARVTCHNLTYPE", required = true)
    protected ACCYDATARVTCHNLTYPE accydatarvtchnltype;
    @XmlElement(name = "ACCY_DATARVTCHNLID", required = true)
    protected ACCYDATARVTCHNLID accydatarvtchnlid;
    @XmlElement(name = "ACCY_PREDATARVTCHNL", required = true)
    protected ACCYPREDATARVTCHNL accypredatarvtchnl;
    @XmlElement(name = "ACCY_PREDATARVTCHNLTYPE", required = true)
    protected ACCYPREDATARVTCHNLTYPE accypredatarvtchnltype;
    @XmlElement(name = "ACCY_PREDATARVTCHNLID", required = true)
    protected ACCYPREDATARVTCHNLID accypredatarvtchnlid;
    @XmlAttribute(name = "Applicable", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String applicable;
    @XmlAttribute(name = "ListID", required = true)
    protected BigInteger listID;

    /**
     * Gets the value of the accyvolctrlen property.
     * 
     * @return
     *     possible object is
     *     {@link ACCYVOLCTRLEN }
     *     
     */
    public ACCYVOLCTRLEN getACCYVOLCTRLEN() {
        return accyvolctrlen;
    }

    /**
     * Sets the value of the accyvolctrlen property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCYVOLCTRLEN }
     *     
     */
    public void setACCYVOLCTRLEN(ACCYVOLCTRLEN value) {
        this.accyvolctrlen = value;
    }

    /**
     * Gets the value of the accyvolfdbken property.
     * 
     * @return
     *     possible object is
     *     {@link ACCYVOLFDBKEN }
     *     
     */
    public ACCYVOLFDBKEN getACCYVOLFDBKEN() {
        return accyvolfdbken;
    }

    /**
     * Sets the value of the accyvolfdbken property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCYVOLFDBKEN }
     *     
     */
    public void setACCYVOLFDBKEN(ACCYVOLFDBKEN value) {
        this.accyvolfdbken = value;
    }

    /**
     * Gets the value of the accy3RDPPCBLMD property.
     * 
     * @return
     *     possible object is
     *     {@link ACCY3RDPPCBLMD }
     *     
     */
    public ACCY3RDPPCBLMD getACCY3RDPPCBLMD() {
        return accy3RDPPCBLMD;
    }

    /**
     * Sets the value of the accy3RDPPCBLMD property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCY3RDPPCBLMD }
     *     
     */
    public void setACCY3RDPPCBLMD(ACCY3RDPPCBLMD value) {
        this.accy3RDPPCBLMD = value;
    }

    /**
     * Gets the value of the accypwrupdel property.
     * 
     * @return
     *     possible object is
     *     {@link ACCYPWRUPDEL }
     *     
     */
    public ACCYPWRUPDEL getACCYPWRUPDEL() {
        return accypwrupdel;
    }

    /**
     * Sets the value of the accypwrupdel property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCYPWRUPDEL }
     *     
     */
    public void setACCYPWRUPDEL(ACCYPWRUPDEL value) {
        this.accypwrupdel = value;
    }

    /**
     * Gets the value of the accynument property.
     * 
     * @return
     *     possible object is
     *     {@link ACCYNUMENT }
     *     
     */
    public ACCYNUMENT getACCYNUMENT() {
        return accynument;
    }

    /**
     * Sets the value of the accynument property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCYNUMENT }
     *     
     */
    public void setACCYNUMENT(ACCYNUMENT value) {
        this.accynument = value;
    }

    /**
     * Gets the value of the accyvndrid property.
     * 
     * @return
     *     possible object is
     *     {@link ACCYVNDRID }
     *     
     */
    public ACCYVNDRID getACCYVNDRID() {
        return accyvndrid;
    }

    /**
     * Sets the value of the accyvndrid property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCYVNDRID }
     *     
     */
    public void setACCYVNDRID(ACCYVNDRID value) {
        this.accyvndrid = value;
    }

    /**
     * Gets the value of the accydatarvtchnl property.
     * 
     * @return
     *     possible object is
     *     {@link ACCYDATARVTCHNL }
     *     
     */
    public ACCYDATARVTCHNL getACCYDATARVTCHNL() {
        return accydatarvtchnl;
    }

    /**
     * Sets the value of the accydatarvtchnl property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCYDATARVTCHNL }
     *     
     */
    public void setACCYDATARVTCHNL(ACCYDATARVTCHNL value) {
        this.accydatarvtchnl = value;
    }

    /**
     * Gets the value of the accydatarvtchnltype property.
     * 
     * @return
     *     possible object is
     *     {@link ACCYDATARVTCHNLTYPE }
     *     
     */
    public ACCYDATARVTCHNLTYPE getACCYDATARVTCHNLTYPE() {
        return accydatarvtchnltype;
    }

    /**
     * Sets the value of the accydatarvtchnltype property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCYDATARVTCHNLTYPE }
     *     
     */
    public void setACCYDATARVTCHNLTYPE(ACCYDATARVTCHNLTYPE value) {
        this.accydatarvtchnltype = value;
    }

    /**
     * Gets the value of the accydatarvtchnlid property.
     * 
     * @return
     *     possible object is
     *     {@link ACCYDATARVTCHNLID }
     *     
     */
    public ACCYDATARVTCHNLID getACCYDATARVTCHNLID() {
        return accydatarvtchnlid;
    }

    /**
     * Sets the value of the accydatarvtchnlid property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCYDATARVTCHNLID }
     *     
     */
    public void setACCYDATARVTCHNLID(ACCYDATARVTCHNLID value) {
        this.accydatarvtchnlid = value;
    }

    /**
     * Gets the value of the accypredatarvtchnl property.
     * 
     * @return
     *     possible object is
     *     {@link ACCYPREDATARVTCHNL }
     *     
     */
    public ACCYPREDATARVTCHNL getACCYPREDATARVTCHNL() {
        return accypredatarvtchnl;
    }

    /**
     * Sets the value of the accypredatarvtchnl property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCYPREDATARVTCHNL }
     *     
     */
    public void setACCYPREDATARVTCHNL(ACCYPREDATARVTCHNL value) {
        this.accypredatarvtchnl = value;
    }

    /**
     * Gets the value of the accypredatarvtchnltype property.
     * 
     * @return
     *     possible object is
     *     {@link ACCYPREDATARVTCHNLTYPE }
     *     
     */
    public ACCYPREDATARVTCHNLTYPE getACCYPREDATARVTCHNLTYPE() {
        return accypredatarvtchnltype;
    }

    /**
     * Sets the value of the accypredatarvtchnltype property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCYPREDATARVTCHNLTYPE }
     *     
     */
    public void setACCYPREDATARVTCHNLTYPE(ACCYPREDATARVTCHNLTYPE value) {
        this.accypredatarvtchnltype = value;
    }

    /**
     * Gets the value of the accypredatarvtchnlid property.
     * 
     * @return
     *     possible object is
     *     {@link ACCYPREDATARVTCHNLID }
     *     
     */
    public ACCYPREDATARVTCHNLID getACCYPREDATARVTCHNLID() {
        return accypredatarvtchnlid;
    }

    /**
     * Sets the value of the accypredatarvtchnlid property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCYPREDATARVTCHNLID }
     *     
     */
    public void setACCYPREDATARVTCHNLID(ACCYPREDATARVTCHNLID value) {
        this.accypredatarvtchnlid = value;
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
