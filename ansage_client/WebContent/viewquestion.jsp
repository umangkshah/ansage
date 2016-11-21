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
		$('title').text("Ansage | View Question");
		//$('#askq').addClass("active"); 
		
	});		
	</script>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <jsp:include page="sidebar.jsp" />

                
            <div class="col-md-9">
                <div class="row">
                    <p class="lead" id="loginmsg">Umang's Question.</p>
                    
                    <h4> How do you use chopsticks?</h4>
                    <p>Please guide me on how the hands can be used to operate chopsticks.</p>
                    <a class="btn btn-success" href="http://localhost:9080/webSvcs/QServices/close/161">Close</a>
                </div>
                <h4>Bids:</h4>
                <div class="row">
					<table class="table">
						<tr>
							<th>Name</th><th>Coins</th><th>Offer</th><th>Skills Known</th>
						</tr>
						<tr>
							<td>Umang</td>
							<td>900</td>
							<td>12</td>
							<td>4</td>
						</tr>
						<tr>
							<td>Rahul</td>
							<td>500</td>
							<td>8</td>
							<td>5</td>
						</tr>
					</table>                
                </div>
                
                <h4>Answers:</h4>
                <br/>
                <div class="row">
                	<p><b>Sreenivas:</b></p>
                	<p>
                	Hold your hands at an 40 degree angle and use the chopsticks to eat food
                	</p>
                </div>
                <div class="row">
                	<h4>Accept more bids to get more answers</h4>
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
