
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="resources//bootstrap/css/bootstrap-theme.css" rel="stylesheet"
	type="text/css" />
<link href="resources/bootstrap/css/bootstrap-theme.css.map" rel="stylesheet"
	type="text/css" />
<link href="resources//WEB-INF/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet"
	type="text/css" />
<link href="resources/bootstrap/css/bootstrap-themecss" rel="stylesheet"
	type="text/css" />
<link href="resources/bootstrap/css/bootstrapcss.map" rel="stylesheet"
	type="text/css" />
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
	
<link href="resources/bootstrap/css/formapp.css" rel="stylesheet"
	type="text/css" />
	<style>


#header {
	background-image: url("resources/bootstrap/images/bg-header.png");
    height: auto;
}

td
{
font-size:15.4px;}

</style>

<script>
/* Method to check if search field is empty */
function search(){
	if (document.find.name.value == "") {
		alert("Enter search text please");
		document.find.name.focus();
		return false;
	}

}
</script>

</head>

<body>
<div id="header" class="container" style="width:100%" >  
	<a href="http://www.optimusinfo.com/" target="_blank"><img src="resources/bootstrap/images/optimusinfologo.png" id="logoset"></img></a>     
		<h3 style="color:white;float:left;"><b>Project List</b></h3>
		
		
		<c:if test="${showallprojects=='yes'}" >	
		<a href="showall" class="btn btn-primary" id="viewallbtn" style="margin-left:0px;margin-right:0px;">View All Projects</a>
		 </c:if>
		 <c:if test="${Reset=='no'}">
		 <a href="showall" class="btn btn-primary"  id="viewallbtn" >View All Projects</a>
		 </c:if>
		 <form role="form"  action="show" method="get" name="find" id=formsearch  onsubmit="return search()">
<div style="width:320px">
			<input class="form-control" type="text" name="name"
							placeholder="e.g., project or customer name" id="searchbutton1" ><input
							class="btn btn-primary" type="submit" onclick="return search()" value="Search" id="showallbtn"> 
</div>
	</form>				</div> 
			<div class="container"
		style="width: 100%;float:left">
		<c:if test="${project.size()==0}">
		<h1 style="text-align:center">No Project Found</h1>
			</c:if></div>


<c:if test="${Reset=='no'}"> 
<h1 style="text-align:center">No Project with this id</h1>


</c:if>
	<c:if test="${Reset==null}"> 	
  <c:if test="${project.size()!=0}">  
 <div class="container" style='width:75%'>
 
		<table class="table table-responsive">
		<tr><td  style="width:40%;"><b>Name of Project</b></td>
		
		<td  style="width:40%"><b>Customer Name</b></td>
		<td style="text-align:center;width:200px"><b>Verification</b></td>
		<td style="text-align:center;width:200px"><b>ResetVerification</b></td>
		
		
		
		
		
<c:forEach items="${project}" var="project">

<tr><td><a href="showid?id=${project.id}"><c:out value="${project.natureofproject}" /></a>
					</td>
					
					

<td> <a href="showid?id=${project.id}"><c:out value="${project.delcontact}" /></a>
					</td>
     
      <c:if test="${project.approve==true}">
     <td style="text-align:center">Verified</td>
 
		</c:if>
		 <c:if test="${project.approve==false}">
     <td style="text-align:center">Not Verified

</td>
		</c:if>
		<td style="text-align:center">  <a href="resetVerification?primaryId=${project.id}">Reset
</a></td>
					</tr>
					
	</c:forEach>
	</table>	

</div></c:if>
</c:if>
</body>

</html>