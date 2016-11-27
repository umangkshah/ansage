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
		$('title').text("Ansage | Ask A Question");
		$('#askq').addClass("active"); 
		
	});		
	</script>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <jsp:include page="sidebar.jsp" />

                
            <div class="col-md-9">
                <div class="row">
                    <p class="lead" id="loginmsg">Post a new Question</p>
                    
                    <form id="qsubmit" action="Question" method=POST>
                     <div class="form-group">
                        <label for="mainQ">
                        Question
                        </label>
                        <input type="text" name="mainQ" maxlength='80' class="form-control" id="mainQ" aria-describedby="mainQ" placeholder="What would you like to know?" />
                        <small id="helpQ" class="form-text text-muted">
                        Please be precise and use details for further clarification.
                        </small>
                        <div class="form-group">
                          <label for="skills">Select Relevant Categories </label>
                          <select multiple class="form-control" id="skills" name="skills">
                          <option value='A'>Algorithms</option>
                          <option value='B'>Basic Programming</option>
                          <option value='C'>Cooking</option>
                          <option value='D'>Databases</option>
                          <option value='E'>Exam Tips</option>
                          </select>
                        </div>
                    </div>
                   
	
                    <div class="form-group">
                        <label for="descrQ">Additional Details. (500 char max.)</label>
                        <textarea class="form-control" id="descrQ" name="descrQ" rows="4" maxlength='500'></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                    <% if(session.getAttribute("PROID")==null){ %>
                    <script>
                    	$(document).ready(function(){
                    	$('#qsubmit').hide();
                    	$('#loginmsg').text("Please Login or Register to Ask Questions");
                    		
                    	});
                    <% } %>	
                    </script>
                </div>
            </div>

        </div>

    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
