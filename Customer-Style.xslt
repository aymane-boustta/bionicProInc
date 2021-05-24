<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
      <table border="1">
   <p><b>Full Name: </b><xsl:value-of select="//name_surname" /></p>
   <p><b>Age: </b><xsl:value-of select="//age" /></p>
   <p><b>Gender: </b><xsl:value-of select="//gender" /></p>
   <p><b>Mobile Phone: </b><xsl:value-of select="//phone" /></p>
   <p><b>E-mail: </b><xsl:value-of select="//email" /></p>
   <p><b>Street: </b><xsl:value-of select="//street" /></p>
   <p><b>City: </b><xsl:value-of select="//city" /></p>
   <p><b>Postal Code: </b><xsl:value-of select="//postal_code" /></p>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>