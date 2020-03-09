<%@ page contentType = "text/html; charset = UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>

<html>
   <head>
      <title>File Upload Success</title>
   </head>
   
   <body>
      You have successfully uploaded <s:property value = "myFileFileName"/><br/>
      Size before compression <s:property value = "beforeSize"/><br/>
      Size after compression <s:property value = "afterSize"/><br/>
	  Time Taken <s:property value = "timeTaken"/> ms<br/>
      Compression Ratio <s:property value = "compressionPercentage"/><br/>
      <form action="downloadFile">
      	<input type="hidden" name="fileLocation" value ="<s:property value="compressedFile"/>">
      	<input type="submit" value="Click here"> to download.
      </form>
   </body>
</html>