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
 *         &lt;element ref="{}CBP_TOPBTNBCO"/&gt;
 *         &lt;element ref="{}CBP_TOPBTNSHORT"/&gt;
 *         &lt;element ref="{}CBP_TOPBTNSHORT_PORT"/&gt;
 *         &lt;element ref="{}CBP_TOPBTNLONG"/&gt;
 *         &lt;element ref="{}CBP_TOPBTNLONG_PORT"/&gt;
 *         &lt;element ref="{}CBP_S1BTNBCO"/&gt;
 *         &lt;element ref="{}CBP_S1BTNSHORT"/&gt;
 *         &lt;element ref="{}CBP_S1BTNLONG"/&gt;
 *         &lt;element ref="{}CBP_S2BTNBCO"/&gt;
 *         &lt;element ref="{}CBP_S2BTNSHORT"/&gt;
 *         &lt;element ref="{}CBP_S2BTNLONG"/&gt;
 *         &lt;element ref="{}CBP_S3BTNBCO"/&gt;
 *         &lt;element ref="{}CBP_S3BTNSHORT"/&gt;
 *         &lt;element ref="{}CBP_S3BTNLONG"/&gt;
 *         &lt;element ref="{}CBP_F1BTNBCO"/&gt;
 *         &lt;element ref="{}CBP_F1BTNSHORT"/&gt;
 *         &lt;element ref="{}CBP_F1BTNSHORT_PORT"/&gt;
 *         &lt;element ref="{}CBP_F1BTNLONG"/&gt;
 *         &lt;element ref="{}CBP_F1BTNLONG_PORT"/&gt;
 *         &lt;element ref="{}CBP_F2BTNBCO"/&gt;
 *         &lt;element ref="{}CBP_F2BTNSHORT"/&gt;
 *         &lt;element ref="{}CBP_F2BTNSHORT_PORT"/&gt;
 *         &lt;element ref="{}CBP_F2BTNLONG"/&gt;
 *         &lt;element ref="{}CBP_F2BTNLONG_PORT"/&gt;
 *         &lt;element ref="{}CBP_ACCYNODOTBTNBCO"/&gt;
 *         &lt;element ref="{}CBP_ACCYNODOTBTNSHORT"/&gt;
 *         &lt;element ref="{}CBP_ACCYNODOTBTNLONG"/&gt;
 *         &lt;element ref="{}CBP_ACCY1DOTBTNBCO"/&gt;
 *         &lt;element ref="{}CBP_ACCY1DOTBTNSHORT"/&gt;
 *         &lt;element ref="{}CBP_ACCY1DOTBTNLONG"/&gt;
 *         &lt;element ref="{}CBP_ACCY2DOTBTNBCO"/&gt;
 *         &lt;element ref="{}CBP_ACCY2DOTBTNSHORT"/&gt;
 *         &lt;element ref="{}CBP_ACCY2DOTBTNLONG"/&gt;
 *         &lt;element ref="{}CBP_ACCYORGBTNBCO"/&gt;
 *         &lt;element ref="{}CBP_ACCYORGBTNSHORT"/&gt;
 *         &lt;element ref="{}CBP_ACCYORGBTNLONG"/&gt;
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
    "cbptopbtnbco",
    "cbptopbtnshort",
    "cbptopbtnshortport",
    "cbptopbtnlong",
    "cbptopbtnlongport",
    "cbps1BTNBCO",
    "cbps1BTNSHORT",
    "cbps1BTNLONG",
    "cbps2BTNBCO",
    "cbps2BTNSHORT",
    "cbps2BTNLONG",
    "cbps3BTNBCO",
    "cbps3BTNSHORT",
    "cbps3BTNLONG",
    "cbpf1BTNBCO",
    "cbpf1BTNSHORT",
    "cbpf1BTNSHORTPORT",
    "cbpf1BTNLONG",
    "cbpf1BTNLONGPORT",
    "cbpf2BTNBCO",
    "cbpf2BTNSHORT",
    "cbpf2BTNSHORTPORT",
    "cbpf2BTNLONG",
    "cbpf2BTNLONGPORT",
    "cbpaccynodotbtnbco",
    "cbpaccynodotbtnshort",
    "cbpaccynodotbtnlong",
    "cbpaccy1DOTBTNBCO",
    "cbpaccy1DOTBTNSHORT",
    "cbpaccy1DOTBTNLONG",
    "cbpaccy2DOTBTNBCO",
    "cbpaccy2DOTBTNSHORT",
    "cbpaccy2DOTBTNLONG",
    "cbpaccyorgbtnbco",
    "cbpaccyorgbtnshort",
    "cbpaccyorgbtnlong"
})
@XmlRootElement(name = "CBP_CMP_TYPE")
public class CBPCMPTYPE {

    @XmlElement(name = "CBP_TOPBTNBCO", required = true)
    protected CBPTOPBTNBCO cbptopbtnbco;
    @XmlElement(name = "CBP_TOPBTNSHORT", required = true)
    protected CBPTOPBTNSHORT cbptopbtnshort;
    @XmlElement(name = "CBP_TOPBTNSHORT_PORT", required = true)
    protected CBPTOPBTNSHORTPORT cbptopbtnshortport;
    @XmlElement(name = "CBP_TOPBTNLONG", required = true)
    protected CBPTOPBTNLONG cbptopbtnlong;
    @XmlElement(name = "CBP_TOPBTNLONG_PORT", required = true)
    protected CBPTOPBTNLONGPORT cbptopbtnlongport;
    @XmlElement(name = "CBP_S1BTNBCO", required = true)
    protected CBPS1BTNBCO cbps1BTNBCO;
    @XmlElement(name = "CBP_S1BTNSHORT", required = true)
    protected CBPS1BTNSHORT cbps1BTNSHORT;
    @XmlElement(name = "CBP_S1BTNLONG", required = true)
    protected CBPS1BTNLONG cbps1BTNLONG;
    @XmlElement(name = "CBP_S2BTNBCO", required = true)
    protected CBPS2BTNBCO cbps2BTNBCO;
    @XmlElement(name = "CBP_S2BTNSHORT", required = true)
    protected CBPS2BTNSHORT cbps2BTNSHORT;
    @XmlElement(name = "CBP_S2BTNLONG", required = true)
    protected CBPS2BTNLONG cbps2BTNLONG;
    @XmlElement(name = "CBP_S3BTNBCO", required = true)
    protected CBPS3BTNBCO cbps3BTNBCO;
    @XmlElement(name = "CBP_S3BTNSHORT", required = true)
    protected CBPS3BTNSHORT cbps3BTNSHORT;
    @XmlElement(name = "CBP_S3BTNLONG", required = true)
    protected CBPS3BTNLONG cbps3BTNLONG;
    @XmlElement(name = "CBP_F1BTNBCO", required = true)
    protected CBPF1BTNBCO cbpf1BTNBCO;
    @XmlElement(name = "CBP_F1BTNSHORT", required = true)
    protected CBPF1BTNSHORT cbpf1BTNSHORT;
    @XmlElement(name = "CBP_F1BTNSHORT_PORT", required = true)
    protected CBPF1BTNSHORTPORT cbpf1BTNSHORTPORT;
    @XmlElement(name = "CBP_F1BTNLONG", required = true)
    protected CBPF1BTNLONG cbpf1BTNLONG;
    @XmlElement(name = "CBP_F1BTNLONG_PORT", required = true)
    protected CBPF1BTNLONGPORT cbpf1BTNLONGPORT;
    @XmlElement(name = "CBP_F2BTNBCO", required = true)
    protected CBPF2BTNBCO cbpf2BTNBCO;
    @XmlElement(name = "CBP_F2BTNSHORT", required = true)
    protected CBPF2BTNSHORT cbpf2BTNSHORT;
    @XmlElement(name = "CBP_F2BTNSHORT_PORT", required = true)
    protected CBPF2BTNSHORTPORT cbpf2BTNSHORTPORT;
    @XmlElement(name = "CBP_F2BTNLONG", required = true)
    protected CBPF2BTNLONG cbpf2BTNLONG;
    @XmlElement(name = "CBP_F2BTNLONG_PORT", required = true)
    protected CBPF2BTNLONGPORT cbpf2BTNLONGPORT;
    @XmlElement(name = "CBP_ACCYNODOTBTNBCO", required = true)
    protected CBPACCYNODOTBTNBCO cbpaccynodotbtnbco;
    @XmlElement(name = "CBP_ACCYNODOTBTNSHORT", required = true)
    protected CBPACCYNODOTBTNSHORT cbpaccynodotbtnshort;
    @XmlElement(name = "CBP_ACCYNODOTBTNLONG", required = true)
    protected CBPACCYNODOTBTNLONG cbpaccynodotbtnlong;
    @XmlElement(name = "CBP_ACCY1DOTBTNBCO", required = true)
    protected CBPACCY1DOTBTNBCO cbpaccy1DOTBTNBCO;
    @XmlElement(name = "CBP_ACCY1DOTBTNSHORT", required = true)
    protected CBPACCY1DOTBTNSHORT cbpaccy1DOTBTNSHORT;
    @XmlElement(name = "CBP_ACCY1DOTBTNLONG", required = true)
    protected CBPACCY1DOTBTNLONG cbpaccy1DOTBTNLONG;
    @XmlElement(name = "CBP_ACCY2DOTBTNBCO", required = true)
    protected CBPACCY2DOTBTNBCO cbpaccy2DOTBTNBCO;
    @XmlElement(name = "CBP_ACCY2DOTBTNSHORT", required = true)
    protected CBPACCY2DOTBTNSHORT cbpaccy2DOTBTNSHORT;
    @XmlElement(name = "CBP_ACCY2DOTBTNLONG", required = true)
    protected CBPACCY2DOTBTNLONG cbpaccy2DOTBTNLONG;
    @XmlElement(name = "CBP_ACCYORGBTNBCO", required = true)
    protected CBPACCYORGBTNBCO cbpaccyorgbtnbco;
    @XmlElement(name = "CBP_ACCYORGBTNSHORT", required = true)
    protected CBPACCYORGBTNSHORT cbpaccyorgbtnshort;
    @XmlElement(name = "CBP_ACCYORGBTNLONG", required = true)
    protected CBPACCYORGBTNLONG cbpaccyorgbtnlong;
    @XmlAttribute(name = "Applicable", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String applicable;
    @XmlAttribute(name = "ListID", required = true)
    protected BigInteger listID;

    /**
     * Gets the value of the cbptopbtnbco property.
     * 
     * @return
     *     possible object is
     *     {@link CBPTOPBTNBCO }
     *     
     */
    public CBPTOPBTNBCO getCBPTOPBTNBCO() {
        return cbptopbtnbco;
    }

    /**
     * Sets the value of the cbptopbtnbco property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPTOPBTNBCO }
     *     
     */
    public void setCBPTOPBTNBCO(CBPTOPBTNBCO value) {
        this.cbptopbtnbco = value;
    }

    /**
     * Gets the value of the cbptopbtnshort property.
     * 
     * @return
     *     possible object is
     *     {@link CBPTOPBTNSHORT }
     *     
     */
    public CBPTOPBTNSHORT getCBPTOPBTNSHORT() {
        return cbptopbtnshort;
    }

    /**
     * Sets the value of the cbptopbtnshort property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPTOPBTNSHORT }
     *     
     */
    public void setCBPTOPBTNSHORT(CBPTOPBTNSHORT value) {
        this.cbptopbtnshort = value;
    }

    /**
     * Gets the value of the cbptopbtnshortport property.
     * 
     * @return
     *     possible object is
     *     {@link CBPTOPBTNSHORTPORT }
     *     
     */
    public CBPTOPBTNSHORTPORT getCBPTOPBTNSHORTPORT() {
        return cbptopbtnshortport;
    }

    /**
     * Sets the value of the cbptopbtnshortport property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPTOPBTNSHORTPORT }
     *     
     */
    public void setCBPTOPBTNSHORTPORT(CBPTOPBTNSHORTPORT value) {
        this.cbptopbtnshortport = value;
    }

    /**
     * Gets the value of the cbptopbtnlong property.
     * 
     * @return
     *     possible object is
     *     {@link CBPTOPBTNLONG }
     *     
     */
    public CBPTOPBTNLONG getCBPTOPBTNLONG() {
        return cbptopbtnlong;
    }

    /**
     * Sets the value of the cbptopbtnlong property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPTOPBTNLONG }
     *     
     */
    public void setCBPTOPBTNLONG(CBPTOPBTNLONG value) {
        this.cbptopbtnlong = value;
    }

    /**
     * Gets the value of the cbptopbtnlongport property.
     * 
     * @return
     *     possible object is
     *     {@link CBPTOPBTNLONGPORT }
     *     
     */
    public CBPTOPBTNLONGPORT getCBPTOPBTNLONGPORT() {
        return cbptopbtnlongport;
    }

    /**
     * Sets the value of the cbptopbtnlongport property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPTOPBTNLONGPORT }
     *     
     */
    public void setCBPTOPBTNLONGPORT(CBPTOPBTNLONGPORT value) {
        this.cbptopbtnlongport = value;
    }

    /**
     * Gets the value of the cbps1BTNBCO property.
     * 
     * @return
     *     possible object is
     *     {@link CBPS1BTNBCO }
     *     
     */
    public CBPS1BTNBCO getCBPS1BTNBCO() {
        return cbps1BTNBCO;
    }

    /**
     * Sets the value of the cbps1BTNBCO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPS1BTNBCO }
     *     
     */
    public void setCBPS1BTNBCO(CBPS1BTNBCO value) {
        this.cbps1BTNBCO = value;
    }

    /**
     * Gets the value of the cbps1BTNSHORT property.
     * 
     * @return
     *     possible object is
     *     {@link CBPS1BTNSHORT }
     *     
     */
    public CBPS1BTNSHORT getCBPS1BTNSHORT() {
        return cbps1BTNSHORT;
    }

    /**
     * Sets the value of the cbps1BTNSHORT property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPS1BTNSHORT }
     *     
     */
    public void setCBPS1BTNSHORT(CBPS1BTNSHORT value) {
        this.cbps1BTNSHORT = value;
    }

    /**
     * Gets the value of the cbps1BTNLONG property.
     * 
     * @return
     *     possible object is
     *     {@link CBPS1BTNLONG }
     *     
     */
    public CBPS1BTNLONG getCBPS1BTNLONG() {
        return cbps1BTNLONG;
    }

    /**
     * Sets the value of the cbps1BTNLONG property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPS1BTNLONG }
     *     
     */
    public void setCBPS1BTNLONG(CBPS1BTNLONG value) {
        this.cbps1BTNLONG = value;
    }

    /**
     * Gets the value of the cbps2BTNBCO property.
     * 
     * @return
     *     possible object is
     *     {@link CBPS2BTNBCO }
     *     
     */
    public CBPS2BTNBCO getCBPS2BTNBCO() {
        return cbps2BTNBCO;
    }

    /**
     * Sets the value of the cbps2BTNBCO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPS2BTNBCO }
     *     
     */
    public void setCBPS2BTNBCO(CBPS2BTNBCO value) {
        this.cbps2BTNBCO = value;
    }

    /**
     * Gets the value of the cbps2BTNSHORT property.
     * 
     * @return
     *     possible object is
     *     {@link CBPS2BTNSHORT }
     *     
     */
    public CBPS2BTNSHORT getCBPS2BTNSHORT() {
        return cbps2BTNSHORT;
    }

    /**
     * Sets the value of the cbps2BTNSHORT property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPS2BTNSHORT }
     *     
     */
    public void setCBPS2BTNSHORT(CBPS2BTNSHORT value) {
        this.cbps2BTNSHORT = value;
    }

    /**
     * Gets the value of the cbps2BTNLONG property.
     * 
     * @return
     *     possible object is
     *     {@link CBPS2BTNLONG }
     *     
     */
    public CBPS2BTNLONG getCBPS2BTNLONG() {
        return cbps2BTNLONG;
    }

    /**
     * Sets the value of the cbps2BTNLONG property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPS2BTNLONG }
     *     
     */
    public void setCBPS2BTNLONG(CBPS2BTNLONG value) {
        this.cbps2BTNLONG = value;
    }

    /**
     * Gets the value of the cbps3BTNBCO property.
     * 
     * @return
     *     possible object is
     *     {@link CBPS3BTNBCO }
     *     
     */
    public CBPS3BTNBCO getCBPS3BTNBCO() {
        return cbps3BTNBCO;
    }

    /**
     * Sets the value of the cbps3BTNBCO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPS3BTNBCO }
     *     
     */
    public void setCBPS3BTNBCO(CBPS3BTNBCO value) {
        this.cbps3BTNBCO = value;
    }

    /**
     * Gets the value of the cbps3BTNSHORT property.
     * 
     * @return
     *     possible object is
     *     {@link CBPS3BTNSHORT }
     *     
     */
    public CBPS3BTNSHORT getCBPS3BTNSHORT() {
        return cbps3BTNSHORT;
    }

    /**
     * Sets the value of the cbps3BTNSHORT property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPS3BTNSHORT }
     *     
     */
    public void setCBPS3BTNSHORT(CBPS3BTNSHORT value) {
        this.cbps3BTNSHORT = value;
    }

    /**
     * Gets the value of the cbps3BTNLONG property.
     * 
     * @return
     *     possible object is
     *     {@link CBPS3BTNLONG }
     *     
     */
    public CBPS3BTNLONG getCBPS3BTNLONG() {
        return cbps3BTNLONG;
    }

    /**
     * Sets the value of the cbps3BTNLONG property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPS3BTNLONG }
     *     
     */
    public void setCBPS3BTNLONG(CBPS3BTNLONG value) {
        this.cbps3BTNLONG = value;
    }

    /**
     * Gets the value of the cbpf1BTNBCO property.
     * 
     * @return
     *     possible object is
     *     {@link CBPF1BTNBCO }
     *     
     */
    public CBPF1BTNBCO getCBPF1BTNBCO() {
        return cbpf1BTNBCO;
    }

    /**
     * Sets the value of the cbpf1BTNBCO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPF1BTNBCO }
     *     
     */
    public void setCBPF1BTNBCO(CBPF1BTNBCO value) {
        this.cbpf1BTNBCO = value;
    }

    /**
     * Gets the value of the cbpf1BTNSHORT property.
     * 
     * @return
     *     possible object is
     *     {@link CBPF1BTNSHORT }
     *     
     */
    public CBPF1BTNSHORT getCBPF1BTNSHORT() {
        return cbpf1BTNSHORT;
    }

    /**
     * Sets the value of the cbpf1BTNSHORT property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPF1BTNSHORT }
     *     
     */
    public void setCBPF1BTNSHORT(CBPF1BTNSHORT value) {
        this.cbpf1BTNSHORT = value;
    }

    /**
     * Gets the value of the cbpf1BTNSHORTPORT property.
     * 
     * @return
     *     possible object is
     *     {@link CBPF1BTNSHORTPORT }
     *     
     */
    public CBPF1BTNSHORTPORT getCBPF1BTNSHORTPORT() {
        return cbpf1BTNSHORTPORT;
    }

    /**
     * Sets the value of the cbpf1BTNSHORTPORT property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPF1BTNSHORTPORT }
     *     
     */
    public void setCBPF1BTNSHORTPORT(CBPF1BTNSHORTPORT value) {
        this.cbpf1BTNSHORTPORT = value;
    }

    /**
     * Gets the value of the cbpf1BTNLONG property.
     * 
     * @return
     *     possible object is
     *     {@link CBPF1BTNLONG }
     *     
     */
    public CBPF1BTNLONG getCBPF1BTNLONG() {
        return cbpf1BTNLONG;
    }

    /**
     * Sets the value of the cbpf1BTNLONG property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPF1BTNLONG }
     *     
     */
    public void setCBPF1BTNLONG(CBPF1BTNLONG value) {
        this.cbpf1BTNLONG = value;
    }

    /**
     * Gets the value of the cbpf1BTNLONGPORT property.
     * 
     * @return
     *     possible object is
     *     {@link CBPF1BTNLONGPORT }
     *     
     */
    public CBPF1BTNLONGPORT getCBPF1BTNLONGPORT() {
        return cbpf1BTNLONGPORT;
    }

    /**
     * Sets the value of the cbpf1BTNLONGPORT property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPF1BTNLONGPORT }
     *     
     */
    public void setCBPF1BTNLONGPORT(CBPF1BTNLONGPORT value) {
        this.cbpf1BTNLONGPORT = value;
    }

    /**
     * Gets the value of the cbpf2BTNBCO property.
     * 
     * @return
     *     possible object is
     *     {@link CBPF2BTNBCO }
     *     
     */
    public CBPF2BTNBCO getCBPF2BTNBCO() {
        return cbpf2BTNBCO;
    }

    /**
     * Sets the value of the cbpf2BTNBCO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPF2BTNBCO }
     *     
     */
    public void setCBPF2BTNBCO(CBPF2BTNBCO value) {
        this.cbpf2BTNBCO = value;
    }

    /**
     * Gets the value of the cbpf2BTNSHORT property.
     * 
     * @return
     *     possible object is
     *     {@link CBPF2BTNSHORT }
     *     
     */
    public CBPF2BTNSHORT getCBPF2BTNSHORT() {
        return cbpf2BTNSHORT;
    }

    /**
     * Sets the value of the cbpf2BTNSHORT property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPF2BTNSHORT }
     *     
     */
    public void setCBPF2BTNSHORT(CBPF2BTNSHORT value) {
        this.cbpf2BTNSHORT = value;
    }

    /**
     * Gets the value of the cbpf2BTNSHORTPORT property.
     * 
     * @return
     *     possible object is
     *     {@link CBPF2BTNSHORTPORT }
     *     
     */
    public CBPF2BTNSHORTPORT getCBPF2BTNSHORTPORT() {
        return cbpf2BTNSHORTPORT;
    }

    /**
     * Sets the value of the cbpf2BTNSHORTPORT property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPF2BTNSHORTPORT }
     *     
     */
    public void setCBPF2BTNSHORTPORT(CBPF2BTNSHORTPORT value) {
        this.cbpf2BTNSHORTPORT = value;
    }

    /**
     * Gets the value of the cbpf2BTNLONG property.
     * 
     * @return
     *     possible object is
     *     {@link CBPF2BTNLONG }
     *     
     */
    public CBPF2BTNLONG getCBPF2BTNLONG() {
        return cbpf2BTNLONG;
    }

    /**
     * Sets the value of the cbpf2BTNLONG property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPF2BTNLONG }
     *     
     */
    public void setCBPF2BTNLONG(CBPF2BTNLONG value) {
        this.cbpf2BTNLONG = value;
    }

    /**
     * Gets the value of the cbpf2BTNLONGPORT property.
     * 
     * @return
     *     possible object is
     *     {@link CBPF2BTNLONGPORT }
     *     
     */
    public CBPF2BTNLONGPORT getCBPF2BTNLONGPORT() {
        return cbpf2BTNLONGPORT;
    }

    /**
     * Sets the value of the cbpf2BTNLONGPORT property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPF2BTNLONGPORT }
     *     
     */
    public void setCBPF2BTNLONGPORT(CBPF2BTNLONGPORT value) {
        this.cbpf2BTNLONGPORT = value;
    }

    /**
     * Gets the value of the cbpaccynodotbtnbco property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCYNODOTBTNBCO }
     *     
     */
    public CBPACCYNODOTBTNBCO getCBPACCYNODOTBTNBCO() {
        return cbpaccynodotbtnbco;
    }

    /**
     * Sets the value of the cbpaccynodotbtnbco property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCYNODOTBTNBCO }
     *     
     */
    public void setCBPACCYNODOTBTNBCO(CBPACCYNODOTBTNBCO value) {
        this.cbpaccynodotbtnbco = value;
    }

    /**
     * Gets the value of the cbpaccynodotbtnshort property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCYNODOTBTNSHORT }
     *     
     */
    public CBPACCYNODOTBTNSHORT getCBPACCYNODOTBTNSHORT() {
        return cbpaccynodotbtnshort;
    }

    /**
     * Sets the value of the cbpaccynodotbtnshort property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCYNODOTBTNSHORT }
     *     
     */
    public void setCBPACCYNODOTBTNSHORT(CBPACCYNODOTBTNSHORT value) {
        this.cbpaccynodotbtnshort = value;
    }

    /**
     * Gets the value of the cbpaccynodotbtnlong property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCYNODOTBTNLONG }
     *     
     */
    public CBPACCYNODOTBTNLONG getCBPACCYNODOTBTNLONG() {
        return cbpaccynodotbtnlong;
    }

    /**
     * Sets the value of the cbpaccynodotbtnlong property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCYNODOTBTNLONG }
     *     
     */
    public void setCBPACCYNODOTBTNLONG(CBPACCYNODOTBTNLONG value) {
        this.cbpaccynodotbtnlong = value;
    }

    /**
     * Gets the value of the cbpaccy1DOTBTNBCO property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCY1DOTBTNBCO }
     *     
     */
    public CBPACCY1DOTBTNBCO getCBPACCY1DOTBTNBCO() {
        return cbpaccy1DOTBTNBCO;
    }

    /**
     * Sets the value of the cbpaccy1DOTBTNBCO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCY1DOTBTNBCO }
     *     
     */
    public void setCBPACCY1DOTBTNBCO(CBPACCY1DOTBTNBCO value) {
        this.cbpaccy1DOTBTNBCO = value;
    }

    /**
     * Gets the value of the cbpaccy1DOTBTNSHORT property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCY1DOTBTNSHORT }
     *     
     */
    public CBPACCY1DOTBTNSHORT getCBPACCY1DOTBTNSHORT() {
        return cbpaccy1DOTBTNSHORT;
    }

    /**
     * Sets the value of the cbpaccy1DOTBTNSHORT property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCY1DOTBTNSHORT }
     *     
     */
    public void setCBPACCY1DOTBTNSHORT(CBPACCY1DOTBTNSHORT value) {
        this.cbpaccy1DOTBTNSHORT = value;
    }

    /**
     * Gets the value of the cbpaccy1DOTBTNLONG property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCY1DOTBTNLONG }
     *     
     */
    public CBPACCY1DOTBTNLONG getCBPACCY1DOTBTNLONG() {
        return cbpaccy1DOTBTNLONG;
    }

    /**
     * Sets the value of the cbpaccy1DOTBTNLONG property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCY1DOTBTNLONG }
     *     
     */
    public void setCBPACCY1DOTBTNLONG(CBPACCY1DOTBTNLONG value) {
        this.cbpaccy1DOTBTNLONG = value;
    }

    /**
     * Gets the value of the cbpaccy2DOTBTNBCO property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCY2DOTBTNBCO }
     *     
     */
    public CBPACCY2DOTBTNBCO getCBPACCY2DOTBTNBCO() {
        return cbpaccy2DOTBTNBCO;
    }

    /**
     * Sets the value of the cbpaccy2DOTBTNBCO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCY2DOTBTNBCO }
     *     
     */
    public void setCBPACCY2DOTBTNBCO(CBPACCY2DOTBTNBCO value) {
        this.cbpaccy2DOTBTNBCO = value;
    }

    /**
     * Gets the value of the cbpaccy2DOTBTNSHORT property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCY2DOTBTNSHORT }
     *     
     */
    public CBPACCY2DOTBTNSHORT getCBPACCY2DOTBTNSHORT() {
        return cbpaccy2DOTBTNSHORT;
    }

    /**
     * Sets the value of the cbpaccy2DOTBTNSHORT property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCY2DOTBTNSHORT }
     *     
     */
    public void setCBPACCY2DOTBTNSHORT(CBPACCY2DOTBTNSHORT value) {
        this.cbpaccy2DOTBTNSHORT = value;
    }

    /**
     * Gets the value of the cbpaccy2DOTBTNLONG property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCY2DOTBTNLONG }
     *     
     */
    public CBPACCY2DOTBTNLONG getCBPACCY2DOTBTNLONG() {
        return cbpaccy2DOTBTNLONG;
    }

    /**
     * Sets the value of the cbpaccy2DOTBTNLONG property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCY2DOTBTNLONG }
     *     
     */
    public void setCBPACCY2DOTBTNLONG(CBPACCY2DOTBTNLONG value) {
        this.cbpaccy2DOTBTNLONG = value;
    }

    /**
     * Gets the value of the cbpaccyorgbtnbco property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCYORGBTNBCO }
     *     
     */
    public CBPACCYORGBTNBCO getCBPACCYORGBTNBCO() {
        return cbpaccyorgbtnbco;
    }

    /**
     * Sets the value of the cbpaccyorgbtnbco property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCYORGBTNBCO }
     *     
     */
    public void setCBPACCYORGBTNBCO(CBPACCYORGBTNBCO value) {
        this.cbpaccyorgbtnbco = value;
    }

    /**
     * Gets the value of the cbpaccyorgbtnshort property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCYORGBTNSHORT }
     *     
     */
    public CBPACCYORGBTNSHORT getCBPACCYORGBTNSHORT() {
        return cbpaccyorgbtnshort;
    }

    /**
     * Sets the value of the cbpaccyorgbtnshort property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCYORGBTNSHORT }
     *     
     */
    public void setCBPACCYORGBTNSHORT(CBPACCYORGBTNSHORT value) {
        this.cbpaccyorgbtnshort = value;
    }

    /**
     * Gets the value of the cbpaccyorgbtnlong property.
     * 
     * @return
     *     possible object is
     *     {@link CBPACCYORGBTNLONG }
     *     
     */
    public CBPACCYORGBTNLONG getCBPACCYORGBTNLONG() {
        return cbpaccyorgbtnlong;
    }

    /**
     * Sets the value of the cbpaccyorgbtnlong property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPACCYORGBTNLONG }
     *     
     */
    public void setCBPACCYORGBTNLONG(CBPACCYORGBTNLONG value) {
        this.cbpaccyorgbtnlong = value;
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
