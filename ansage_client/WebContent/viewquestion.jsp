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
		$('title').text("Ansage | View Question");
		//$('#askq').addClass("active"); 
		
	});		
	</script>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <jsp:include page="sidebar.jsp" />
			
			<c:set var = "viewer" value="${sessionScope.PROID}" scope="session" />
			<c:set var = "oid" value="${qdets.oid}" scope="session" />
			<c:set var = "admin" value="0" scope="session" />
			<c:set var = "logged" value="0" scope="session" />
			<c:set var = "bidded" value="0" scope="session" />
			
			
			<c:if test="${sessionScope.PROID != null}">
				<c:set var = "logged" value="1" scope="session" />
				<c:if test="${oid == sessionScope.PROID}">
					<c:set var = "admin" value="1" scope="session" />
			</c:if>
			</c:if>
			
            
			
                
            <div class="col-md-9">
                <div class="row">
                    <p class="lead" id="loginmsg"> ${qdets.oname}'s Question.</p>
                    
                    <h4> ${qdets.question}</h4>
                    <p>${qdets.descr}</p>
                    
                </div>
           
           <c:if test="${logged == 0 }">
           	<h4> Login to Bid and View Answers</h4>
           </c:if>
           <c:if test="${logged == 1 }">
           <!-- 
                <h4>Answers:</h4>
                <br/>
                <div class="row">
                	<p><b>Sreenivas:</b></p>
                	<p>
                	Hold your hands at an 40 degree angle and use the chopsticks to eat food
                	</p>
                </div>
                 -->
           </c:if>
           <c:if test="${admin == 1}"> 
          	<c:if test="${fn:length(bidrows) > 0}">
       			<c:set var = "bidded" value="0" scope="session" />
                <div class="row">
                	<h4>Accept more bids to get more answers</h4>
                </div>
                
                <h4>Bids:</h4>
                <small>Click to sort bids lowest to highest based on header.</small>
		                <div class="row">
							<table class="table tablesorter">
								<thead>
								<tr>
									<th>Name</th><th>Coins</th><th>Offer</th><th>Skills Known</th><th></th>
								</tr>
								</thead>
								<tbody>
								<c:forEach var="row" items="${bidrows}">  
									<tr>
										<td><a href='ViewProfile?profile=<c:out value="${row.reqid}"/>'> ${row.name} </a></td>
										<td><c:out value="${row.coins}"/></td>
										<td><c:out value="${row.offer}"/></td>
										<td><c:out value="${row.skills}" /></td>
										<td><button class="addtocart" id="${row.bidid}">Accept</button></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>                
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
		$('#biddingform').hide();
		$('#dontbid').hide();
		
		$('#showbidform').click(function(){
			$('#biddingform').show();
			$('#dontbid').show();
		});
		
		$('#dontbid').click(function(){
			$('#biddingform').hide();
			$(this).hide();
		});
		
		$('.addtocart').click(function(){
			var k = this.id;
			$.get("AddToCart?bid="+k, function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                var no = parseInt(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
            	var noc = parseInt(document.getElementById("cartcount").innerHTML);
                var finc = no + noc;
                $('#cartcount').text(finc);
			});
		});
		
		$("table").tablesorter({ 
	        // pass the headers argument and assing a object 
	        headers: { 
	            // assign the secound column (we start counting zero) 
	            4: { 
	                // disable it by setting the property sorter to false 
	                sorter: false 
	            }
	        } 
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

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
