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
		$('#formdet').hide();
		$('#edit').click(function(){$('#formdet').show();});
		
		
	});	
	</script>
	
	

    <header class="image-bg-fluid-height">
        <img class="img-responsive img-center" src="images/dp.jpg" alt="">
    </header>

    <!-- Content Section -->
    <section>
        <div class="container">
            <div class="row">
                <div class="col-md-12" id="org">
                   <h4 class="section-heading" id="username">${rows.name}</h4>
                    <p class="lead section-lead" id="usercoins">Coins: <b>204</b></p>
                    <p class="lead section-lead" id="usertagline">${rows.tagline}</p>
                    <p class="section-paragraph" id="userbio">${rows.bio}</p>
                    <p class="lead section-lead" id="userskills">${rows.skills}</p>
                 </div>
            </div>
        </div>
    </section>
     <div class="container" id="formdet">
		 <p class="lead">Update</p>
                <p>Update Fields</p>
    			<form method=POST action="EditProfile">
                        
                        <div class="form=group">
                          <label for="namepls">Name</label>
                          <input type="text" class="form-control" id="name" name="name"/>
                          
                        </div>
                        <div class="form=group">
                       <label for="tagline">I  am </label>
                          <input type="text" class="form-control" id="tagline" name="tagline"/>
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
                       <div class="form-group">
                          <label for="exampleTextarea">A Short Bio.</label>
                          <textarea class="form-control" id="bio" name="bio" rows="3"></textarea>
                          </div>
                          <input type="hidden" name="profid" value='<%= request.getParameter("profile")%>' />
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <button type="cancel" class="btn btn-primary">Cancel</button>
                    </form>
                    </div>
   
    <input type="button" id="edit" onclick="change()" class="btn btn-primary"  value="Edit Profile">
   
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
 <script>
function change()
{
document.getElementById("org").style.display = "none";
	document.getElementById("formdet").style.visibility = "visible";
	document.getElementById("edit").style.display = "none";
document.getElementById("name").value=document.getElementById("username").innerHTML;
document.getElementById("tagline").value=document.getElementById("usertagline").innerHTML;
document.getElementById("bio").innerHTML=document.getElementById("userbio").innerHTML;
var text=document.getElementById("userskills").innerHTML;
var select=document.getElementById("skills");
for(i=0;i<text.length;i++)
	{
	for(j=0;j<select.options.length;j++)
		{
		if(select.options[j].value==text[i])
			{
			
			select.options[j].selected=true;
			}
		}
	}

}
</script>
    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
</body>

</html>
