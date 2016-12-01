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
                
                <h4>Cart:</h4>
		                <div class="row">
							<table class="table">
								
								<tr>
									<th>Name</th><th>Offer</th><th>Quantity</th><th></th>
								</tr>
								
								<c:forEach var="row" items="${rows}">  
									<tr>
									
									
										<td><a href='ViewProfile?profile=<c:out value="${row.reqid}"/>'> ${row.name} </a></td>
										<td class= "offer"><c:out value="${row.offer}"/></td>
										<td><input class="qty" type='number' min = '1' max = '10' value ='<c:out value="${row.qty}"/>' /></td>
										<td><button class="removefromcart" id="${row.bidid}">Remove</button></td>
									
									</tr>
								</c:forEach>
								
								
								<td colspan='2'>Total</td>
								<td id="totalo"></td>
								<td id="totalq"></td>
							</table>   
							<button id="savechanges">Save</button>
							<button id="checkout">Checkout</button>             
		                </div>
          	 </c:if>
           </c:if> 
           
            </div>

        </div>
	<script>
	
	/*
	var cusid_ele = document.getElementsByClassName('qty');
	var sum = 0;
	for (var i = 0; i < cusid_ele.length; ++i) {
	    sum = sum + parseInt(cusid_ele[i].value);
	}
	var cusid_ele = document.getElementsByClassName('qty');
	var sum = 0;
	for (var i = 0; i < cusid_ele.length; ++i) {
	    sum = sum + parseInt(cusid_ele[i].value);
	}*/
	$(document).ready(function(){
		//$('#total').text(sum);
		
		/*$('.qty').change(function(){
			
		});*/
		
		$('.removefromcart').click(function(){
			var k = this.id;
			$.get("RemoveCart?bid="+k, function(responseText) {
                var no = parseInt(responseText);
            	if(no == 1)
            		$('.removefromcart').find(k).hide();
			});
		});
		
		$('#savechanges').click(function(){
			var qts = document.getElementsByClassName('qty');
			var bidids = document.getElementsByClassName('removefromcart');
			var update = '';
			for(var i = 0;i < bidids.length; i++){
				update = update + '!' + bidids[i].id + '-' + qts[i].value;
			}
			$.ajax({
			     url:'UpdateCart',
			     type: 'post',
			     data: {items: update},
			     success: function (data) {
			            $('#totalo').html(data);
			    }
			});
		});
		
		$('#checkout').click(function(){
			$.get("Transaction", function(responseText) {
                var no = parseInt(responseText);
            	
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
