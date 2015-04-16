<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/menu.css" type="text/css"/>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<div align="center">
<table id="layout">
	<tr>
		<td height="70px" colspan="2" BGCOLOR="#ffffff">
			<tiles:insertAttribute name="header1"/>
		</td>
	</tr>
	<tr height="380px">
		<td width="500px" BGCOLOR="#ffffff">
			<div><tiles:insertAttribute name="body1"/></div>
		</td>
	</tr>
</table>
</div>
</body>
</html>