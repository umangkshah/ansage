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
				$('#orreg').hide();
			});
			</script>   
            <div class="col-md-9">
                <div class="row">
                <p class="lead">Register</p>
                <p>Please Fill out All Fields</p>
                    <form method=POST action="Registration">
                        
                        <div class="form=group">
                          <label for="namepls">First Name*</label>
                          <input type="text" class="form-control" id="namepls" name="namepls" placeholder=" Your Name Please." required/>
                        </div>
                        
                        <div class="form-group">
                          <label for="email">
                          Email address *
                          </label>
                          <input type="email" class="form-control" id="email" name="emailid" aria-describedby="emailHelp" placeholder="Enter email" required/>
                          <small id="emailHelp" class="form-text text-muted">
                          This will be used as your username while signing in.
                          </small>
                        </div>

                        <div class="form-group">
                          <label for="Password1">Password *</label>
                          <input type="password" class="form-control" id="pass" name="pass" placeholder="Password" required/>
                        </div> 

                        <div class="form-group">
                          <label for="Password2">Confirm Password *</label>
                          <input type="password" class="form-control" id="pass2" name="pass2" placeholder="Reenter Password" required/>
                        </div>

                        <div class="form-group">
                          <label for="skills">I  Can Answer </label>
                          <select multiple class="form-control" id="skills" name="skills">
                          <option value='A'>Algorithms</option>
                          <option value='B'>Basic Programming</option>
                          <option value='C'>Cooking</option>
                          <option value='D'>Databases</option>
                          <option value='E'>Exam Tips</option>
                          </select>
                        </div>
                        
                        <div class="form=group">
                          <label for="tagline">I  am </label>
                          <input type="text" class="form-control" id="tagline" name="tagline" placeholder=" a Student at.. the CEO of.. a Master Chef .." />
                        </div>
              
                        <div class="form-group">
                          <label for="exampleTextarea">A Short Bio.</label>
                          <textarea class="form-control" id="bio" name="bio" rows="3"></textarea>
                        </div>
                        <!-- 
                        <div class="form-group">
                        <label for="exampleInputFile">Upload a Profile Picture</label>
                        <input type="file" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp">
                        <small id="fileHelp" class="form-text text-muted">Max File Size = 1 MB</small>
                        </div>
						             -->
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
