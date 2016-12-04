<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page trimDirectiveWhitespaces="true" %>
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
                
                
                <p>Bids:</p>
                <div class="row">
                
                <small>Click to sort bids lowest to highest based on header.</small>
                <br/>
                <br/>
                <p> Filter: </p>
                <div class ="col-md-6 col-sm-6">
               <span class="Filters">
                <label>Has Coins</label>
				<select class="form-control" id="coinSel">
				  <option value='g'>More than</option>
				  <option value = 'l'>Less Than</option>
				  <option value = 'e'>Equal to</option>				  
				</select>
				<input type="number" id='cVal'/>
				</span>
				<br/><br/>
				 <span class="Filters">
                <label>Offer</label>
				<select class="form-control" id="offerSel">
				  <option value='g'>More than</option>
				  <option value = 'l'>Less Than</option>
				  <option value = 'e'>Equal to</option>				  
				</select>
				<input type="number" id='oVal'/>
				</span>
				<br/><br/>
				 <span class="Filters">
                <label>No of Skills</label>
				<select class="form-control" id="skillSel">
				  <option value='g'>More than</option>
				  <option value = 'l'>Less Than</option>
				  <option value = 'e'>Equal to</option>				  
				</select>
				<input type="number" id='sVal'min='0' max='5'/>
				</span>
				<br/><br/>
				<span class="Filters">
				 <label for="skills">Select Relevant Categories </label>
                  <select multiple class="form-control" id="skillSet">
	                  <option value='A'>Algorithms</option>
	                  <option value='B'>Basic Programming</option>
	                  <option value='C'>Cooking</option>
	                  <option value='D'>Databases</option>
	                  <option value='E'>Exam Tips</option>
                  </select>
                </span>          
				</div>
				</div>
				
				<div class="row">
				<button class="btn btn-success" id="filterthis">Apply Filter</button>
				<button class="btn btn-default" id="filterclear">Clear Filter</button>
                </div>
                <div class="row">
					<table class="table tablesorter">
						<thead>
						<tr>
							<th>Name</th><th>Coins</th><th>Offer</th><th>No of Skills</th><th>Skills Known</th><th></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="row" items="${bidrows}">  
						<tr class="prrows">
							<td class="prlink"><a href='ViewProfile?profile=<c:out value="${row.reqid}"/>'> ${row.name} </a></td>
							<td class="prcoins"><c:out value="${row.coins}"/></td>
							<td class="proff"><c:out value="${row.offer}"/></td>
							<td class="prnosk"><c:out value="${row.skills}" /></td>
							<td class="prsk"><c:out value="${row.skilllist}" /> </td>
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
		$('#filterthis').click(function(){
			$('.prrows').hide();
			var op = document.getElementById("coinSel").value;
			var val = document.getElementById("cVal").value;
			var d = document.getElementsByClassName("prcoins");
			if(op == 'g'){
				for(i=0;i < d.length; i++){
					var k = parseInt(val);
					var di = d[i].innerHTML;
					if(parseInt(di) > k){
						
						$(".prcoins:contains('"+ di +"')").parent().show();
					}
				}
			}
			else if(op == 'l'){
				for(i=0;i < d.length; i++){
					var k = parseInt(val);
					var di = d[i].innerHTML;
					if(parseInt(di) < k){
						
						$(".prcoins:contains('"+ di +"')").parent().show();
					}
				}
			}else{
				for(i=0;i < d.length; i++){
					var k = parseInt(val);
					var di = d[i].innerHTML;
					if(parseInt(di) == k){
						
						$(".prcoins:contains('"+ di +"')").parent().show();
					}
				}
			}
			
			op = document.getElementById("skillSel").value;
			val = document.getElementById("sVal").value;
			d = document.getElementsByClassName("prnosk");
			if(op == 'g'){
				for(i=0;i < d.length; i++){
					var k = parseInt(val);
					var di = d[i].innerHTML;
					if(parseInt(di) > k){
						alert(di);
						$(".prnosk:contains('"+ di +"')").parent().show();
					}
				}
			}
			else if(op == 'l'){
				for(i=0;i < d.length; i++){
					var k = parseInt(val);
					var di = d[i].innerHTML;
					if(parseInt(di) < k){
						
						$(".prnosk:contains('"+ di +"')").parent().show();
					}
				}
			}else{
				for(i=0;i < d.length; i++){
					var k = parseInt(val);
					var di = d[i].innerHTML;
					if(parseInt(di) == k){
						
						$(".prnosk:contains('"+ di +"')").parent().show();
					}
				}
			}
			
			op = document.getElementById("offerSel").value;
			val = document.getElementById("oVal").value;
			d = document.getElementsByClassName("proff");
			if(op == 'g'){
				for(i=0;i < d.length; i++){
					var k = parseInt(val);
					var di = d[i].innerHTML;
					if(parseInt(di) > k){
						
						$(".proff:contains('"+ di +"')").parent().show();
					}
				}
			}
			else if(op == 'l'){
				for(i=0;i < d.length; i++){
					var k = parseInt(val);
					var di = d[i].innerHTML;
					if(parseInt(di) < k){
						
						$(".proff:contains('"+ di +"')").parent().show();
					}
				}
			}else{
				for(i=0;i < d.length; i++){
					var k = parseInt(val);
					var di = d[i].innerHTML;
					if(parseInt(di) == k){
						
						$(".proff:contains('"+ di +"')").parent().show();
					}
				}
			}
			
			var foo = [];
			$('#skillSet :selected').each(function(i, selected){ 
				  foo[i] = $(selected).val(); 
				});
			d = document.getElementsByClassName("prsk");
			
				
					for(i =0;i < foo.length;i++){
									
						
						$(".prsk:contains('"+ foo[i] +"')").parent().show();
					
				}
			
			
		});
		
		$('#filterclear').click(function(){
			$('.prrows').show();
		});
		
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
	            5: { 
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
