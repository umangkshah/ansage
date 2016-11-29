<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="header.html" %>

<body>

    <!-- Navigation -->
  	<%@ include file="nav.jsp" %>
	<script>
	$(document).ready(function(){
		$('title').text("Ansage | Questions");
		$('#liqs').addClass("active"); 
		
	});		
	</script>
    <!-- Page Content -->
    <div class="container">
	
        <div class="row">

            <jsp:include page="sidebar.jsp" />

            
            <div class="col-md-9">
                
            
				
                <div class="row">
                <p class="lead">Questions</a></p>
                <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
				<c:forEach var="row" items="${rows}">  
					<div class="col-sm-4 col-lg-4 col-md-4">
					<div class="well well-lg">
					<div class="caption">
					  <p>
					  
					  <a href="ViewQuestion?question=<c:out value='${row.qid}'/>">
					<c:out value="${row.question}"/>
					</a>
					<br/>
					Bids: <c:out value="${row.nbids}"/>	      			              
					     </p>
					     <p>
					    <span class="label label-success">Cooking</span>
					    <span class="label label-success">Household</span>
					    </p>
					  </div>
					</div>
					</div>
				</c:forEach>
                </div>

                </div>

            </div>

        </div>

    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

        <!-- Footer -->
        <%@ include file="footer.html" %>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>