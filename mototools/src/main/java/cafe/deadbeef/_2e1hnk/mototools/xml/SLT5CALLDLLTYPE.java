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
 *         &lt;element ref="{}SLT_5_CALL_DLL_TYPE_NTLLISHTYPE"/&gt;
 *         &lt;element ref="{}SLT_5_CALL_DLL_TYPE_NTLLISHID"/&gt;
 *         &lt;element ref="{}S5CLDLL_LLEQ"/&gt;
 *         &lt;element ref="{}S5CLDLL_5TCALLA"/&gt;
 *         &lt;element ref="{}S5CLDLL_5TCALLADD"/&gt;
 *         &lt;element ref="{}S5CLDLL_5TTELEGRAM"/&gt;
 *         &lt;element ref="{}S5CLDLL_5TTELEGRAMTYPE"/&gt;
 *         &lt;element ref="{}S5CLDLL_5TTELEGRAMID"/&gt;
 *         &lt;element ref="{}S5CLDLL_RINGTYPE"/&gt;
 *         &lt;element ref="{}S5CDT_RESERVED"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Applicable" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *       &lt;attribute name="ListID" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="ListLetID" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "slt5CALLDLLTYPENTLLISHTYPE",
    "slt5CALLDLLTYPENTLLISHID",
    "s5CLDLLLLEQ",
    "s5CLDLL5TCALLA",
    "s5CLDLL5TCALLADD",
    "s5CLDLL5TTELEGRAM",
    "s5CLDLL5TTELEGRAMTYPE",
    "s5CLDLL5TTELEGRAMID",
    "s5CLDLLRINGTYPE",
    "s5CDTRESERVED"
})
@XmlRootElement(name = "SLT_5_CALL_DLL_TYPE")
public class SLT5CALLDLLTYPE {

    @XmlElement(name = "SLT_5_CALL_DLL_TYPE_NTLLISHTYPE", required = true)
    protected SLT5CALLDLLTYPENTLLISHTYPE slt5CALLDLLTYPENTLLISHTYPE;
    @XmlElement(name = "SLT_5_CALL_DLL_TYPE_NTLLISHID", required = true)
    protected SLT5CALLDLLTYPENTLLISHID slt5CALLDLLTYPENTLLISHID;
    @XmlElement(name = "S5CLDLL_LLEQ", required = true)
    protected S5CLDLLLLEQ s5CLDLLLLEQ;
    @XmlElement(name = "S5CLDLL_5TCALLA", required = true)
    protected S5CLDLL5TCALLA s5CLDLL5TCALLA;
    @XmlElement(name = "S5CLDLL_5TCALLADD", required = true)
    protected S5CLDLL5TCALLADD s5CLDLL5TCALLADD;
    @XmlElement(name = "S5CLDLL_5TTELEGRAM", required = true)
    protected S5CLDLL5TTELEGRAM s5CLDLL5TTELEGRAM;
    @XmlElement(name = "S5CLDLL_5TTELEGRAMTYPE", required = true)
    protected S5CLDLL5TTELEGRAMTYPE s5CLDLL5TTELEGRAMTYPE;
    @XmlElement(name = "S5CLDLL_5TTELEGRAMID", required = true)
    protected S5CLDLL5TTELEGRAMID s5CLDLL5TTELEGRAMID;
    @XmlElement(name = "S5CLDLL_RINGTYPE", required = true)
    protected S5CLDLLRINGTYPE s5CLDLLRINGTYPE;
    @XmlElement(name = "S5CDT_RESERVED", required = true)
    protected S5CDTRESERVED s5CDTRESERVED;
    @XmlAttribute(name = "Applicable", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String applicable;
    @XmlAttribute(name = "ListID", required = true)
    protected BigInteger listID;
    @XmlAttribute(name = "ListLetID", required = true)
    protected BigInteger listLetID;

    /**
     * Gets the value of the slt5CALLDLLTYPENTLLISHTYPE property.
     * 
     * @return
     *     possible object is
     *     {@link SLT5CALLDLLTYPENTLLISHTYPE }
     *     
     */
    public SLT5CALLDLLTYPENTLLISHTYPE getSLT5CALLDLLTYPENTLLISHTYPE() {
        return slt5CALLDLLTYPENTLLISHTYPE;
    }

    /**
     * Sets the value of the slt5CALLDLLTYPENTLLISHTYPE property.
     * 
     * @param value
     *     allowed object is
     *     {@link SLT5CALLDLLTYPENTLLISHTYPE }
     *     
     */
    public void setSLT5CALLDLLTYPENTLLISHTYPE(SLT5CALLDLLTYPENTLLISHTYPE value) {
        this.slt5CALLDLLTYPENTLLISHTYPE = value;
    }

    /**
     * Gets the value of the slt5CALLDLLTYPENTLLISHID property.
     * 
     * @return
     *     possible object is
     *     {@link SLT5CALLDLLTYPENTLLISHID }
     *     
     */
    public SLT5CALLDLLTYPENTLLISHID getSLT5CALLDLLTYPENTLLISHID() {
        return slt5CALLDLLTYPENTLLISHID;
    }

    /**
     * Sets the value of the slt5CALLDLLTYPENTLLISHID property.
     * 
     * @param value
     *     allowed object is
     *     {@link SLT5CALLDLLTYPENTLLISHID }
     *     
     */
    public void setSLT5CALLDLLTYPENTLLISHID(SLT5CALLDLLTYPENTLLISHID value) {
        this.slt5CALLDLLTYPENTLLISHID = value;
    }

    /**
     * Gets the value of the s5CLDLLLLEQ property.
     * 
     * @return
     *     possible object is
     *     {@link S5CLDLLLLEQ }
     *     
     */
    public S5CLDLLLLEQ getS5CLDLLLLEQ() {
        return s5CLDLLLLEQ;
    }

    /**
     * Sets the value of the s5CLDLLLLEQ property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5CLDLLLLEQ }
     *     
     */
    public void setS5CLDLLLLEQ(S5CLDLLLLEQ value) {
        this.s5CLDLLLLEQ = value;
    }

    /**
     * Gets the value of the s5CLDLL5TCALLA property.
     * 
     * @return
     *     possible object is
     *     {@link S5CLDLL5TCALLA }
     *     
     */
    public S5CLDLL5TCALLA getS5CLDLL5TCALLA() {
        return s5CLDLL5TCALLA;
    }

    /**
     * Sets the value of the s5CLDLL5TCALLA property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5CLDLL5TCALLA }
     *     
     */
    public void setS5CLDLL5TCALLA(S5CLDLL5TCALLA value) {
        this.s5CLDLL5TCALLA = value;
    }

    /**
     * Gets the value of the s5CLDLL5TCALLADD property.
     * 
     * @return
     *     possible object is
     *     {@link S5CLDLL5TCALLADD }
     *     
     */
    public S5CLDLL5TCALLADD getS5CLDLL5TCALLADD() {
        return s5CLDLL5TCALLADD;
    }

    /**
     * Sets the value of the s5CLDLL5TCALLADD property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5CLDLL5TCALLADD }
     *     
     */
    public void setS5CLDLL5TCALLADD(S5CLDLL5TCALLADD value) {
        this.s5CLDLL5TCALLADD = value;
    }

    /**
     * Gets the value of the s5CLDLL5TTELEGRAM property.
     * 
     * @return
     *     possible object is
     *     {@link S5CLDLL5TTELEGRAM }
     *     
     */
    public S5CLDLL5TTELEGRAM getS5CLDLL5TTELEGRAM() {
        return s5CLDLL5TTELEGRAM;
    }

    /**
     * Sets the value of the s5CLDLL5TTELEGRAM property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5CLDLL5TTELEGRAM }
     *     
     */
    public void setS5CLDLL5TTELEGRAM(S5CLDLL5TTELEGRAM value) {
        this.s5CLDLL5TTELEGRAM = value;
    }

    /**
     * Gets the value of the s5CLDLL5TTELEGRAMTYPE property.
     * 
     * @return
     *     possible object is
     *     {@link S5CLDLL5TTELEGRAMTYPE }
     *     
     */
    public S5CLDLL5TTELEGRAMTYPE getS5CLDLL5TTELEGRAMTYPE() {
        return s5CLDLL5TTELEGRAMTYPE;
    }

    /**
     * Sets the value of the s5CLDLL5TTELEGRAMTYPE property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5CLDLL5TTELEGRAMTYPE }
     *     
     */
    public void setS5CLDLL5TTELEGRAMTYPE(S5CLDLL5TTELEGRAMTYPE value) {
        this.s5CLDLL5TTELEGRAMTYPE = value;
    }

    /**
     * Gets the value of the s5CLDLL5TTELEGRAMID property.
     * 
     * @return
     *     possible object is
     *     {@link S5CLDLL5TTELEGRAMID }
     *     
     */
    public S5CLDLL5TTELEGRAMID getS5CLDLL5TTELEGRAMID() {
        return s5CLDLL5TTELEGRAMID;
    }

    /**
     * Sets the value of the s5CLDLL5TTELEGRAMID property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5CLDLL5TTELEGRAMID }
     *     
     */
    public void setS5CLDLL5TTELEGRAMID(S5CLDLL5TTELEGRAMID value) {
        this.s5CLDLL5TTELEGRAMID = value;
    }

    /**
     * Gets the value of the s5CLDLLRINGTYPE property.
     * 
     * @return
     *     possible object is
     *     {@link S5CLDLLRINGTYPE }
     *     
     */
    public S5CLDLLRINGTYPE getS5CLDLLRINGTYPE() {
        return s5CLDLLRINGTYPE;
    }

    /**
     * Sets the value of the s5CLDLLRINGTYPE property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5CLDLLRINGTYPE }
     *     
     */
    public void setS5CLDLLRINGTYPE(S5CLDLLRINGTYPE value) {
        this.s5CLDLLRINGTYPE = value;
    }

    /**
     * Gets the value of the s5CDTRESERVED property.
     * 
     * @return
     *     possible object is
     *     {@link S5CDTRESERVED }
     *     
     */
    public S5CDTRESERVED getS5CDTRESERVED() {
        return s5CDTRESERVED;
    }

    /**
     * Sets the value of the s5CDTRESERVED property.
     * 
     * @param value
     *     allowed object is
     *     {@link S5CDTRESERVED }
     *     
     */
    public void setS5CDTRESERVED(S5CDTRESERVED value) {
        this.s5CDTRESERVED = value;
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
