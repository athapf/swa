<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL">

    <xsl:output method="text" encoding="utf-8"/>

    <xsl:variable name="lower-case" select="'abcdefghijklmnopqrstuvwxyz'"/>
    <xsl:variable name="upper-case" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>

    <xsl:template match="bpmn:task">
        <xsl:text>    </xsl:text>
        <xsl:value-of select="translate(./@name,$lower-case,$upper-case)"/>
        <xsl:text>("</xsl:text>
        <xsl:value-of select="./@name"/>
        <xsl:text>")</xsl:text>
        <xsl:choose>
            <xsl:when test="count(./following-sibling::bpmn:task)&gt;0">
                <xsl:text>,</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text>;</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:text>&#xA;</xsl:text>
    </xsl:template>

    <xsl:template match="/">
        <xsl:variable name="class" select="./descendant::bpmn:process/@name"/>

        <xsl:text>package de.thaso.swa.be.process.model;&#xA;</xsl:text>
<!--
        <xsl:text>&#xA;</xsl:text>
        <xsl:text>import de.thaso.swa.be.process.model.State;&#xA;</xsl:text>
-->
        <xsl:text>&#xA;</xsl:text>
        <xsl:text>/**&#xA;</xsl:text>
        <xsl:text> * </xsl:text>
        <xsl:value-of select="$class"/>
        <xsl:text>State&#xA;</xsl:text>
        <xsl:text> *&#xA;</xsl:text>
        <xsl:text> * GENERATED CODE - DO NOT MODIFY ANYTHING IN THIS FILE!&#xA;</xsl:text>
        <xsl:text> */&#xA;</xsl:text>
        <xsl:text>public enum </xsl:text>
        <xsl:value-of select="$class"/>
        <xsl:text>State implements State {&#xA;</xsl:text>
        <xsl:apply-templates select="./descendant::bpmn:task"/>
        <xsl:text>&#xA;</xsl:text>
        <xsl:text>    private String stateName;&#xA;</xsl:text>
        <xsl:text>&#xA;</xsl:text>
        <xsl:text>    </xsl:text>
        <xsl:value-of select="$class"/>
        <xsl:text>State(final String stateName) {&#xA;</xsl:text>
        <xsl:text>        this.stateName = stateName;&#xA;</xsl:text>
        <xsl:text>    }&#xA;</xsl:text>
        <xsl:text>}&#xA;</xsl:text>
    </xsl:template>

</xsl:stylesheet>