<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<div class="col-md-3" id = "sidebar">

                <p class="lead">Actions</p>
                <% if(session.getAttribute("USER") == null) { %>  
                <div class="well">
                    <p id="indicate">Sign In.</p>
                    <br/>
                     <form action="LoginService" method="POST">
                        <div class="form-group">
                        <label for="LoginEmail">
                        Email address
                        </label>
                        <input type="email" name="emailid" class="form-control" id="emailid" aria-describedby="emailHelp" placeholder="Enter email" />
                       
                        </div>
						
                        <div class="form-group">
                        <label for="LoginPass">Password</label>
                        <input name="passwd" type="password" class="form-control" id="passwd" placeholder="Password" />
                        </div> 
                        
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                    <br/>
                    <p id="orreg">Or 
                    <a href="register.jsp"> Register</a></p>
                </div>
                <% } else { %>
                <!-- Else this -->
                <div class="well">
                Welcome, 
				<%= session.getAttribute("NAME")%>
                </div>
                <div class="list-group">
                    <% out.println("<a href=\'profile.jsp?userid=" + session.getAttribute("PROID") + "\'>"); %>
                    <! --a href='profile.jsp?userid=12' class="list-group-item"-->My Profile</a>
                    <a href="ask.html" class="list-group-item">Post Question</a>
                    <a href="answer.html" class="list-group-item">Answer Questions</a>
                    <a href="#" class="list-group-item">My Cents: 
                    <%= session.getAttribute("COINS")%>
                    </a>
                </div>
                <% } %>
            </div>
            
