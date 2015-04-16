<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Struts 2 Format Date Example!</title>

<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
type="text/css"/>

<s:head theme="ajax" />

</head>
<body>


<s:form action="DateTimePicker" method="POST">

<s:datetimepicker name="todayDate" label="Format (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>

<s:datetimepicker name="todayDate" label="Format (dd-MMM-yyyy)" displayFormat="dd-MMM-yyyy"/>


</s:form>

</body>

</html>
