//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.30 at 11:46:48 AM GMT 
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
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}SLT_5_DECODER_STATUS_DLL_TYPE_NTLLISHTYPE"/>
 *         &lt;element ref="{}SLT_5_DECODER_STATUS_DLL_TYPE_NTLLISHID"/>
 *         &lt;element ref="{}S5DECSTSLDLL_LLEQ"/>
 *         &lt;element ref="{}S5DECSTSLDLL_5TSTSA"/>
 *         &lt;element ref="{}S5DECSTSLDLL_5TSTSNUM"/>
 *         &lt;element ref="{}S5DECSTSLDLL_RESERVED"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Applicable" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="ListID" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="ListLetID" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "slt5DECODERSTATUSDLLTYPENTLLISHTYPE",
    "slt5DECODERSTATUSDLLTYPENTLLISHID",
    "s5DECSTSLDLLLLEQ",
    "s5DECSTSLDLL5TSTSA",
    "s5DECSTSLDLL5TSTSNUM",
    "s5DECSTSLDLLRESERVED"
})
@XmlRootElement(name = "SLT_5_DECODER_STATUS_DLL_TYPE")
public class SLT5DECODERSTATUSDLLTYPE {

    @XmlElement(name = "SLT_5_DECODER_STATUS_DLL_TYPE_NTLLISHTYPE", required = true)
    protected SLT5DECODERSTATUSDLLTYPENTLLISHTYPE slt5DECODERSTATUSDLLTYPENTLLISHTYPE;
    @XmlElement(name = "SLT_5_DECODER_STATUS_DLL_TYPE_NTLLISHID", required = true)
    protected SLT5DECODERSTATUSDLLTYPENTLLISHID slt5DECODERSTATUSDLLTYPENTLLISHID;
    @XmlElement(name = "S5DECSTSLDLL_LLEQ", required = true)
    protected S5DECSTSLDLLLLEQ s5DECSTSLDLLLLEQ;
    @XmlElement(name = "S5DECSTSLDLL_5TSTSA", required = true)
    protected S5DECSTSLDLL5TSTSA s5DECSTSLDLL5TSTSA;
    @XmlElement(name = "S5DECSTSLDLL_5TSTSNUM", required = true)
    protected S5DECSTSLDLL5TSTSNUM s5DECSTSLDLL5TSTSNUM;
    @XmlElement(name = "S5DECSTSLDLL_RESERVED", required = true)
    protected S5DECSTSLDLLRESERVED s5DECSTSLDLLRESERVED;
    @XmlAttribute(name = "Applicable", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String applicable;
    @XmlAttribute(name = "ListID", required = true)
    protected BigInteger listID;
    @XmlAttribute(name = "ListLetID", required = true)
    protected BigInteger listLetID;

    /**
     * Gets the value of the slt5DECODERSTATUSDLLTYPENTLLISHTYPE property.
     * 
     * @return
     *     possible object is
     *     {@link SLT5DECODERSTATUSDLLTYPENTLLISHTYPE }
     *     
     */
    public SLT5DECODERSTATUSDLLTYPENTLLISHTYPE getSLT5DECODERSTATUSDLLTYPENTLLISHTYPE() {
        return slt5DECODERSTATUSDLLTYPENTLLISHTYPE;
    }

    /**
     * Sets the value of the slt5DECODERSTATUSDLLTYPENTLLISHTYPE property.
     * 
     * @param value
     *     allowed object is
     *     {@link SLT5DECODERSTATUSDLLTYPENTLLISHTYPE }
     *     
     */
    public void setSLT5DECODERSTATUSDLLTYPENTLLISHTYPE(SLT5DECODERSTATUSDLLTYPENTLLISHTYPE value) {
        this.slt5DECODERSTATUSDLLTYPENTLLISHTYPE = value;
    }

    /**
     * Gets the value of the slt5DECODERSTATUSDLLTYPENTLLISHID property.
     * 
     * @return
     *     possible object is
     *     {@link SLT5DECODERSTATUSDLLTYPENTLLISHID }
     *     
     */
    public SLT5DECODERSTATUSDLLTYPENTLLISHID getSLT5DECODERSTATUSDLLTYPENTLLISHID() {
        return slt5DECODERSTATUSDLLTYPENTLLISHID;
    }

    /**
     * Sets the value of the slt5DECODERSTATUSDLLTYPENTLLISHID property.
     * 
     * @param value
     *     allowed object is
     *     {@link SLT5DECODERSTATUSDLLTYPENTLLISHID }
     *     
     */
    public void setSLT5DECODERSTATUSDLLTYPENTLLISHID(SLT5DECODERSTATUSDLLTYPENTLLISHID value) {
        this.slt5DECODERSTATUSDLLTYPENTLLISHID = value;
    }

    /**
     * Gets the value of the s5DECSTSLDLLLLEQ property.
     * 
     * @return
     *     possible object is
     *     {@link S5DECSTSLDLLLLEQ }
     *     
     */
    public S5DECSTSLDLLLLEQ getS5DECSTSLDLLLLEQ() {
        return s5DECSTSLDLLLLEQ;
    }

    /**
     * Sets the value of the s5DECSTSLDLLLLEQ property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5DECSTSLDLLLLEQ }
     *     
     */
    public void setS5DECSTSLDLLLLEQ(S5DECSTSLDLLLLEQ value) {
        this.s5DECSTSLDLLLLEQ = value;
    }

    /**
     * Gets the value of the s5DECSTSLDLL5TSTSA property.
     * 
     * @return
     *     possible object is
     *     {@link S5DECSTSLDLL5TSTSA }
     *     
     */
    public S5DECSTSLDLL5TSTSA getS5DECSTSLDLL5TSTSA() {
        return s5DECSTSLDLL5TSTSA;
    }

    /**
     * Sets the value of the s5DECSTSLDLL5TSTSA property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5DECSTSLDLL5TSTSA }
     *     
     */
    public void setS5DECSTSLDLL5TSTSA(S5DECSTSLDLL5TSTSA value) {
        this.s5DECSTSLDLL5TSTSA = value;
    }

    /**
     * Gets the value of the s5DECSTSLDLL5TSTSNUM property.
     * 
     * @return
     *     possible object is
     *     {@link S5DECSTSLDLL5TSTSNUM }
     *     
     */
    public S5DECSTSLDLL5TSTSNUM getS5DECSTSLDLL5TSTSNUM() {
        return s5DECSTSLDLL5TSTSNUM;
    }

    /**
     * Sets the value of the s5DECSTSLDLL5TSTSNUM property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5DECSTSLDLL5TSTSNUM }
     *     
     */
    public void setS5DECSTSLDLL5TSTSNUM(S5DECSTSLDLL5TSTSNUM value) {
        this.s5DECSTSLDLL5TSTSNUM = value;
    }

    /**
     * Gets the value of the s5DECSTSLDLLRESERVED property.
     * 
     * @return
     *     possible object is
     *     {@link S5DECSTSLDLLRESERVED }
     *     
     */
    public S5DECSTSLDLLRESERVED getS5DECSTSLDLLRESERVED() {
        return s5DECSTSLDLLRESERVED;
    }

    /**
     * Sets the value of the s5DECSTSLDLLRESERVED property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5DECSTSLDLLRESERVED }
     *     
     */
    public void setS5DECSTSLDLLRESERVED(S5DECSTSLDLLRESERVED value) {
        this.s5DECSTSLDLLRESERVED = value;
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

    /**
     * Gets the value of the listLetID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getListLetID() {
        return listLetID;
    }

    /**
     * Sets the value of the listLetID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setListLetID(BigInteger value) {
        this.listLetID = value;
    }

}
