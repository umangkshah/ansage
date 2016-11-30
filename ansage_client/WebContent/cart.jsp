<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="header.html" %>

<body>
    <!-- Navigation -->
    <%@ include file="nav.jsp" %>
	<script>
	$(document).ready(function(){
		$('title').text("Ansage | Shopping Cart");
		$('#licart').addClass("active"); 
		
	});		
	</script>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <jsp:include page="sidebar.jsp" />
		
			<c:set var = "admin" value="0" scope="session" />
			<c:set var = "logged" value="0" scope="session" />
			
			<c:if test="${sessionScope.PROID != null}">
				<c:set var = "logged" value="1" scope="session" />
			</c:if>
			
            
			
            <div class="col-md-9">
                
           
           <c:if test="${logged == 0 }">
           	<h4> Login to Bid and View Answers</h4>
           </c:if>
           
           <c:if test="${logged == 1 }">
                
           <c:if test="${fn:length(rows) > 0}">
       			<c:set var = "bidded" value="0" scope="session" />
                <div class="row">
                	<h4>Accept more bids to get more answers</h4>
                </div>
                
                <h4>Bids:</h4>
		                <div class="row">
							<table class="table">
								
								<tr>
									<th>Name</th><th>Offer</th><th>Quantity</th><th></th>
								</tr>
								<c:forEach var="row" items="${rows}">  
									<tr>
										<td><a href='ViewProfile?profile=<c:out value="${row.reqid}"/>'> ${row.name} </a></td>
										<td><c:out value="${row.offer}"/></td>
										<td><input type='number' min = '1' max = '10' value ='<c:out value="${row.qty}"/>' /></td>
										<td><button class="removefromcart" id="${row.bidid}">Remove</button></td>
									</tr>
								</c:forEach>
								<td colspan='3'>Total</td>
								<td id="total"></td>
							</table>   
							<button >Save</button>
							<button>Checkout</button>             
		                </div>
          	 </c:if>
           </c:if> 
           <c:if test="${logged == 1 }">
           	<c:if test="${admin == 0 }">
           	<c:if test="${bidded == 0}">
            	<button type="button" class="btn btn-success" id="showbidform">Make Bid</button>	
             <form id = "biddingform" action="MakeBid" method=POST>
             	<div class="form=group">
                       <label for="namepls">Offer:</label>
                       <input type="number" min = "0" max="50" class="form-control" id="offerval" name="offerval" placeholder="Coins you want" required/>
                 </div>
                 <input type="hidden" value="${qdets.qid}" name="qid" />
                 <button type="submit" class="btn btn-primary">Submit</button>
             </form>
            	<button type="button" class="btn btn-default" id="dontbid">Cancel</button>
            </c:if>
            </c:if>
           </c:if>   
            </div>

        </div>
	<script>
	$(document).ready(function(){
		
		$('.removefromcart').click(function(){
			var k = this.id;
			$.get("RemoveCart?bid="+k, function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                var no = parseInt(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
            	var noc = parseInt(document.getElementById("cartcount").innerHTML);
                var finc = no + noc;
                $('#cartcount').text(finc);
			});
		});
		
	});
	</script>
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
