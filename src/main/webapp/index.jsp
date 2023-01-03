





<%-- <%@ taglib uri="/struts-tags" prefix="s" %>
<s:form action="loginAction">
<s:textfield name="username" label="Product Id"></s:textfield>
<s:textfield name="password" label="Product Name"></s:textfield>
<s:submit value="save"></s:submit>

</s:form> - --%>
<h1>home</h1>

<%-- <hr/>  
<h3>Login Form</h3>  
<%  
String profile_msg=(String)request.getAttribute("profile_msg");  
if(profile_msg!=null){  
out.print(profile_msg);  
}  
String login_msg=(String)request.getAttribute("login_msg");  
if(login_msg!=null){  
out.print(login_msg);  
}  
 %>  
 <br/>  
<form action="loginprocess.jsp" method="post">  
Email:<input type="text" name="email"/><br/><br/>  
Password:<input type="password" name="password"/><br/><br/>  
<input type="submit" value="login"/>"  
</form>   --%>