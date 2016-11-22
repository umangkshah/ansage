<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="header.html" %>

<body>
 <% //ProfilePojo pp = (ProfilePojo)request.getAttribute("rows"); 
 	//if(pp == null) out.println("Error");
 %>
	
    <!-- Navigation -->
   <%@ include file="nav.jsp" %>
	<script>
	$(document).ready(function(){
		$('title').text("Ansage | Home");
		//$('#lisearch').addClass("active"); 
		$('.navbar-brand').addClass("brand-home").attr("href","#");
	});		
	</script>

    <header class="image-bg-fluid-height">
        <img class="img-responsive img-center" src="images/dp.jpg" alt="">
    </header>

    <!-- Content Section -->
    <section>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
               
                
                    <h4 class="section-heading">${rows.name}</h4>
                    <p class="lead section-lead">Coins: <b>204</b></p>
                    <p class="lead section-lead">${rows.tagline}</p>
                    <p class="section-paragraph">${rows.bio}</p>
                    <p class="lead section-lead">${rows.skills}</p>
                 
                    <p class="lead section-lead">Questions Asked:</p>
                    <p class="lead section-lead">Questions Answered:</p>
                </div>
            </div>
        </div>
    </section>

     
   
    <!-- Fixed Height Image Aside -->
    <!-- Image backgrounds are set within the full-width-pics.css file. -->
    

    <!-- Content Section -->
    
<hr>
    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container -->
    </footer>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
