<%@ page language="java" pageEncoding="gb2312"%>
<html>  
<body>
   		<%
   			String account = (String)session.getAttribute("account");
   			out.println(account.length());
   		 %>
  </body>
</html>
