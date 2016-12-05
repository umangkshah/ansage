<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="header.html" %>

<body>

    <!-- Navigation -->
  	<%@ include file="nav.jsp" %>
	<script>
	$(document).ready(function(){
		$('title').text("Ansage | Home");
		//$('#lisearch').addClass("active"); 
		$('.navbar-brand').addClass("brand-home").attr("href","#");
	});		
	</script>
    <!-- Page Content -->
    <div class="container">
	
        <div class="row">

            <jsp:include page="sidebar.jsp" />

            
            <div class="col-md-9">
                
            	<c:if test="${sessionScope.PROID != null}">
					<div class="row">
						<h4>Your Last Login Was on: ${sessionScope.DATE} from: ${sessionScope.LOCN}</h4>
					</div>
				</c:if>
                <div class="row">
                <p class="lead">Invalid Username or password. Try Again.</p>
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
    
