<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
      <table border="1">
   <p><b>Name : </b><xsl:value-of select="//name" /></p>
   <p><b>Body's part : </b><xsl:value-of select="//bodypart" /></p>
   <p><b>Price : </b><xsl:value-of select="//price" /></p>
   <p><b>Creation date : </b><xsl:value-of select="//date_creation" /></p>
   
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>