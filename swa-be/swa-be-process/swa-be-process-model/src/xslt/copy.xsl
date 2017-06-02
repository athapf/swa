<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:output method="xml" encoding="utf-8"/>

    <xsl:template match="/">
<!--
        <xsl:comment>
            XSLT Version = <xsl:copy-of select="system-property('xsl:version')"/>
            XSLT Vendor = <xsl:copy-of select="system-property('xsl:vendor')"/>
            XSLT Vendor URL = <xsl:copy-of select="system-property('xsl:vendor-url')"/>
        </xsl:comment>
-->
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

</xsl:stylesheet>
