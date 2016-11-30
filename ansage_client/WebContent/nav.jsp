  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">ANSAGE</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li id="lisearch">
                        <a href="search.jsp">Search</a>
                    </li>
                    <li id="liqs">
                        <a href="DisplayQuestion">View Questions</a>
                    </li>
                   
                    <% if(session.getAttribute("USER") != null) { %>  
                     <li>
                        <a href="MyQuestions">My Questions</a>
                    </li>
                    <li class="dropdown navbar-right">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Account <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                        <li><a href="#"></a></li>
                        <li id="liprof"><% out.println("<a href=\'ViewProfile?profile=" + session.getAttribute("PROID") + "\'>"); %>
                    Profile</a></li>
                        <li><a href="cart.jsp">My Cart <span class="badge" id="cartcount"> 0</span></a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="logout.jsp">Sign Out</a></li>
                        
                      </ul>
                	</li>
                	<% // } else %>
                	<% } %>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>