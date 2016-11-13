<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="header.html" %>

<body>
    <!-- Navigation -->
    <%@ include file="nav.jsp" %>
	

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <jsp:include page="sidebar.jsp" />

			<script>
			$(document).ready(function(){
				$('title').text("Register | Ansage");
				$('#indicate').text("Already a member ? Sign In.");
			});		
			</script>   
            <div class="col-md-9">
                <div class="row">
                <p class="lead">Register</p>
                    <form>

                        <div class="form-group">
                        <label for="exampleInputEmail1">
                        Email address
                        </label>
                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email" />
                        <small id="emailHelp" class="form-text text-muted">
                        We'll never share your email with anyone else.
                        </small>
                        </div>

                        <div class="form-group">
                        <label for="Password1">Password</label>
                        <input type="password" class="form-control" id="pass" placeholder="Password" />
                        </div> 

                        <div class="form-group">
                        <label for="Password2">Confirm Password</label>
                        <input type="password" class="form-control" id="pass2" placeholder="Reenter Password" />
                        </div>

                        <div class="form-group">
                        <label for="exampleSelect1">Example select</label>
                        <select class="form-control" id="exampleSelect1">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        </select>
                        </div>

                        <div class="form-group">
                        <label for="exampleSelect2">Select what you know..</label>
                        <select multiple class="form-control" id="exampleSelect2">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        </select>
                        </div>

                        <div class="form-group">
                        <label for="exampleTextarea">A Short Bio.</label>
                        <textarea class="form-control" id="exampleTextarea" rows="3"></textarea>
                        </div>
                        <div class="form-group">
                        <label for="exampleInputFile">Upload a Profile Picture</label>
                        <input type="file" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp">
                        <small id="fileHelp" class="form-text text-muted">This is some placeholder block-level help text for the above input. It's a bit lighter and easily wraps to a new line.</small>
                        </div>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>

        </div>

    </div>
    <!-- /.container -->

    <div class="container">

        <hr>
		<%@ include file="footer.html" %>
        <!-- Footer -->
        

    </div>
    <!-- /.container -->

   
    

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
