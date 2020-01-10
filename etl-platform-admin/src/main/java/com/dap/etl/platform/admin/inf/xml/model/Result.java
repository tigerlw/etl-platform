//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.12.25 时间 06:06:34 PM CST 
//


package com.dap.etl.platform.admin.inf.xml.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lines_input" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lines_output" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lines_read" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lines_written" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lines_updated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lines_rejected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lines_deleted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nr_errors" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nr_files_retrieved" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entry_nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exit_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="is_stopped" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="log_channel_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="log_text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="elapsedTimeMillis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="executionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}result" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "linesInput",
    "linesOutput",
    "linesRead",
    "linesWritten",
    "linesUpdated",
    "linesRejected",
    "linesDeleted",
    "nrErrors",
    "nrFilesRetrieved",
    "entryNr",
    "exitStatus",
    "isStopped",
    "logChannelId",
    "logText",
    "elapsedTimeMillis",
    "executionId",
    "result"
})
@XmlRootElement(name = "result")
public class Result {

    @XmlElement(name = "lines_input")
    protected String linesInput;
    @XmlElement(name = "lines_output")
    protected String linesOutput;
    @XmlElement(name = "lines_read")
    protected String linesRead;
    @XmlElement(name = "lines_written")
    protected String linesWritten;
    @XmlElement(name = "lines_updated")
    protected String linesUpdated;
    @XmlElement(name = "lines_rejected")
    protected String linesRejected;
    @XmlElement(name = "lines_deleted")
    protected String linesDeleted;
    @XmlElement(name = "nr_errors")
    protected String nrErrors;
    @XmlElement(name = "nr_files_retrieved")
    protected String nrFilesRetrieved;
    @XmlElement(name = "entry_nr")
    protected String entryNr;
    @XmlElement(name = "exit_status")
    protected String exitStatus;
    @XmlElement(name = "is_stopped")
    protected String isStopped;
    @XmlElement(name = "log_channel_id")
    protected String logChannelId;
    @XmlElement(name = "log_text")
    protected String logText;
    protected String elapsedTimeMillis;
    protected String executionId;
    protected List<Result> result;

    /**
     * 获取linesInput属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinesInput() {
        return linesInput;
    }

    /**
     * 设置linesInput属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinesInput(String value) {
        this.linesInput = value;
    }

    /**
     * 获取linesOutput属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinesOutput() {
        return linesOutput;
    }

    /**
     * 设置linesOutput属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinesOutput(String value) {
        this.linesOutput = value;
    }

    /**
     * 获取linesRead属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinesRead() {
        return linesRead;
    }

    /**
     * 设置linesRead属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinesRead(String value) {
        this.linesRead = value;
    }

    /**
     * 获取linesWritten属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinesWritten() {
        return linesWritten;
    }

    /**
     * 设置linesWritten属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinesWritten(String value) {
        this.linesWritten = value;
    }

    /**
     * 获取linesUpdated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinesUpdated() {
        return linesUpdated;
    }

    /**
     * 设置linesUpdated属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinesUpdated(String value) {
        this.linesUpdated = value;
    }

    /**
     * 获取linesRejected属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinesRejected() {
        return linesRejected;
    }

    /**
     * 设置linesRejected属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinesRejected(String value) {
        this.linesRejected = value;
    }

    /**
     * 获取linesDeleted属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinesDeleted() {
        return linesDeleted;
    }

    /**
     * 设置linesDeleted属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinesDeleted(String value) {
        this.linesDeleted = value;
    }

    /**
     * 获取nrErrors属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrErrors() {
        return nrErrors;
    }

    /**
     * 设置nrErrors属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrErrors(String value) {
        this.nrErrors = value;
    }

    /**
     * 获取nrFilesRetrieved属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrFilesRetrieved() {
        return nrFilesRetrieved;
    }

    /**
     * 设置nrFilesRetrieved属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrFilesRetrieved(String value) {
        this.nrFilesRetrieved = value;
    }

    /**
     * 获取entryNr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryNr() {
        return entryNr;
    }

    /**
     * 设置entryNr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryNr(String value) {
        this.entryNr = value;
    }

    /**
     * 获取exitStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExitStatus() {
        return exitStatus;
    }

    /**
     * 设置exitStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExitStatus(String value) {
        this.exitStatus = value;
    }

    /**
     * 获取isStopped属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsStopped() {
        return isStopped;
    }

    /**
     * 设置isStopped属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsStopped(String value) {
        this.isStopped = value;
    }

    /**
     * 获取logChannelId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogChannelId() {
        return logChannelId;
    }

    /**
     * 设置logChannelId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogChannelId(String value) {
        this.logChannelId = value;
    }

    /**
     * 获取logText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogText() {
        return logText;
    }

    /**
     * 设置logText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogText(String value) {
        this.logText = value;
    }

    /**
     * 获取elapsedTimeMillis属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElapsedTimeMillis() {
        return elapsedTimeMillis;
    }

    /**
     * 设置elapsedTimeMillis属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElapsedTimeMillis(String value) {
        this.elapsedTimeMillis = value;
    }

    /**
     * 获取executionId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutionId() {
        return executionId;
    }

    /**
     * 设置executionId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutionId(String value) {
        this.executionId = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the result property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Result }
     * 
     * 
     */
    public List<Result> getResult() {
        if (result == null) {
            result = new ArrayList<Result>();
        }
        return this.result;
    }

}
