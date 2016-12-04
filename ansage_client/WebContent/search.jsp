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
		$('title').text("Ansage | Search");
		$('#lisearch').addClass("active"); 
	});	
	</script>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

           <jsp:include page="sidebar.jsp" />

                
            <div class="col-md-9">
                <div class="row">
               	<form method=GET action=Search>
                    <div class="input-group">
                        <input type="text" name="query" class="form-control" placeholder="Search for...">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">Go!</button>
                        </span>
                    </div>
                </form>
                </div>
                <br class="clear"></br>
               
                <c:if test="${results == 1}">
                 <div class="row">
                    <p class="lead">Search Results</p>
                    
                </div>
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
                <label>No of Bids</label>
				<select class="form-control" id="offerSel">
				  <option value='g'>More than</option>
				  <option value = 'l'>Less Than</option>
				  <option value = 'e'>Equal to</option>				  
				</select>
				<input type="number" id='oVal'/>
				</span>
				<br/><br/>
				
				 <span class="Filters">
                <label>Asker Name:</label>
                
                	<c:forEach var="idrow" items="${names}">
                		<input type="checkbox" name="names" value="${idrow}" />${idrow}
                	</c:forEach>
               
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
							<th>Question</th>
							<th>Description</th>
							<th>Name</th>							
							<th>Owner Coins</th>
							<th>No of Bids</th>
							<th>Categories</th>
						
						</tr>
						</thead>
						<tbody>
						<c:forEach var="row" items="${rows}">  
						<tr class="prrows">
							<td class="prqs"><a href='ViewQuestion?question=<c:out value="${row.qid}"/>'> <c:out value="${row.question}"/> </a></td>
							<td class="prdesc"><c:out value="${row.descr}"/></td>
							<td class="prname"><a class="prlink" href='ViewProfile?profile=<c:out value="${row.oid}"/>'> ${row.oname} </a></td>
							<td class="prcoins"><c:out value="${row.ocoins}"/></td>
							<td class="proff"><c:out value="${row.nbids}"/></td>
							<td class="prsk"><c:out value="${row.category}" /> </td>
							
						</tr>
						</c:forEach>
						</tbody>
					</table>                
                </div>
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
			/*
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
			*/
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
			
					for(i =0;i < foo.length;i++){
						$(".prsk:contains('"+ foo[i] +"')").parent().show();
				}
			
			var bar = [];
			$.each($("input[name='names']:checked"), function() {
				  bar.push($(this).val());
				  // or you can do something to the actual checked checkboxes by working directly with  'this'
				  // something like $(this).hide() (only something useful, probably) :P
				});	
			
			for(i =0;i < bar.length;i++){
				
				$(".prlink:contains('"+ bar[i] +"')").parent().parent().show();
		}
					
			});
		$('#filterclear').click(function(){
			$('.prrows').show();
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
