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
		$('title').text("Ansage | Search");
		$('#lisearch').addClass("active"); 
	});	
	</script>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <div class="col-md-3">
                <div class="well">
                    Sign In.<br/>
                     <form>
                        <div class="form-group">
                        <label for="LoginEmail">
                        Email address
                        </label>
                        <input type="email" class="form-control" id="emailid" aria-describedby="emailHelp" placeholder="Enter email" />
                       
                        </div>

                        <div class="form-group">
                        <label for="LoginPass">Password</label>
                        <input type="password" class="form-control" id="passwd" placeholder="Password" />
                        </div> 
                        
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                    <br/>
                    Or 
                    <a href="logreg.html"> Register</a>
                </div>           
            </div>

                
            <div class="col-md-9">
                <div class="row">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search for...">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="button">Go!</button>
                        </span>
                    </div>
                </div>
                <br class="clear"></br>
                <div class="row">
                    <p class="lead">Search Results</p>
                    
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
