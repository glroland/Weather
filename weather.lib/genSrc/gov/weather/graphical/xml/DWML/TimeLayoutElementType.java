//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.02.03 at 08:12:21 AM EST 
//


package gov.weather.graphical.xml.DWML;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for time-layoutElementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="time-layoutElementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="layout-key" type="{}layout-keyType"/>
 *         &lt;sequence maxOccurs="unbounded">
 *           &lt;element name="start-valid-time" type="{}start-valid-timeType" maxOccurs="unbounded"/>
 *           &lt;element name="end-valid-time" type="{http://www.w3.org/2001/XMLSchema}dateTime" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="time-coordinate" use="required" type="{}time-coordinateType" />
 *       &lt;attribute name="summarization" type="{}summarizationType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "time-layoutElementType", propOrder = {
    "layoutKey",
    "startValidTimeAndEndValidTime"
})
public class TimeLayoutElementType {

    @XmlElement(name = "layout-key", required = true)
    protected String layoutKey;
    @XmlElements({
        @XmlElement(name = "end-valid-time", required = true, type = XMLGregorianCalendar.class),
        @XmlElement(name = "start-valid-time", required = true, type = StartValidTimeType.class)
    })
    protected List<Object> startValidTimeAndEndValidTime;
    @XmlAttribute(name = "time-coordinate", required = true)
    protected TimeCoordinateType timeCoordinate;
    @XmlAttribute
    protected String summarization;

    /**
     * Gets the value of the layoutKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLayoutKey() {
        return layoutKey;
    }

    /**
     * Sets the value of the layoutKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLayoutKey(String value) {
        this.layoutKey = value;
    }

    /**
     * Gets the value of the startValidTimeAndEndValidTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the startValidTimeAndEndValidTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStartValidTimeAndEndValidTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * {@link StartValidTimeType }
     * 
     * 
     */
    public List<Object> getStartValidTimeAndEndValidTime() {
        if (startValidTimeAndEndValidTime == null) {
            startValidTimeAndEndValidTime = new ArrayList<Object>();
        }
        return this.startValidTimeAndEndValidTime;
    }

    /**
     * Gets the value of the timeCoordinate property.
     * 
     * @return
     *     possible object is
     *     {@link TimeCoordinateType }
     *     
     */
    public TimeCoordinateType getTimeCoordinate() {
        return timeCoordinate;
    }

    /**
     * Sets the value of the timeCoordinate property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeCoordinateType }
     *     
     */
    public void setTimeCoordinate(TimeCoordinateType value) {
        this.timeCoordinate = value;
    }

    /**
     * Gets the value of the summarization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummarization() {
        return summarization;
    }

    /**
     * Sets the value of the summarization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummarization(String value) {
        this.summarization = value;
    }

}