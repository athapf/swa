<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL">

    <xsl:output method="text" encoding="utf-8"/>

    <xsl:variable name="lower-case" select="'abcdefghijklmnopqrstuvwxyz'"/>
    <xsl:variable name="upper-case" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>

    <xsl:template match="bpmn:process">
        <xsl:text>    </xsl:text>
        <xsl:value-of select="translate(./@name,$lower-case,$upper-case)"/>
        <xsl:choose>
            <xsl:when test="count(./following::bpmn:process)&gt;0">
                <xsl:text>,</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text>;</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:text>&#xA;</xsl:text>
    </xsl:template>

    <xsl:template match="/">
        <xsl:text>package de.thaso.swa.be.process.model;&#xA;</xsl:text>
        <xsl:text>&#xA;</xsl:text>
        <xsl:text>/**&#xA;</xsl:text>
        <xsl:text> * ModelEnum&#xA;</xsl:text>
        <xsl:text> *&#xA;</xsl:text>
        <xsl:text> * GENERATED CODE - DO NOT MODIFY ANYTHING IN THIS FILE!&#xA;</xsl:text>
        <xsl:text> */&#xA;</xsl:text>
        <xsl:text>public enum ProcessModel {&#xA;</xsl:text>
        <xsl:apply-templates select="./descendant::bpmn:process"/>
        <xsl:text>}&#xA;</xsl:text>
    </xsl:template>

</xsl:stylesheet>