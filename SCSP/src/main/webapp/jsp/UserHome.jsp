<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>SCSP- UserHome Settings</title>
	<meta name="description" content="Software Catalog Subscription Portal" />
	<meta name="keywords" content="technotify,staytuned,subscribe,alerts" />
	<meta name="author" content="Ravali,Supreetha,Abhinaya" />
	
	<!-- Favicons (created with http://realfavicongenerator.net/)-->
	
<spring:url value="/resources/img" var="images" />
 <img src="${images}/favicons/manifest.json"/>
<img src="${images}/favicons/browserconfig.xml"/>
<%-- <img src="${images}/userhome/Java.png"/> --%>

<meta name="msapplication-TileColor" content="#00a8ff">
	<spring:url value="/resources/css/grid.scss" var="gridScss" />
    <link href="${gridScss}" rel="stylesheet" />
    
    <spring:url value="/resources/css/style.css" var="styleCss" />
    <link href="${styleCss}" rel="stylesheet" />
    
	<spring:url value="/resources/css/main.css" var="mainCss" />	
	<link href="${mainCss}" rel="stylesheet" />
    	
    
   	<spring:url value="/resources/css/normalize.css" var="normalizeCSS" />
    <link href="${normalizeCSS}" rel="stylesheet" />
    
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCSS" />
    <link href="${bootstrapCSS}" rel="stylesheet" />
    
    <spring:url value="/resources/css/owl.css" var="owlCSS" />
    <link href="${owlCSS}" rel="stylesheet" />
    
     <spring:url value="/resources/css/animate.css" var="animateCSS" />
    <link href="${animateCSS}" rel="stylesheet" />
    
    <spring:url value="/resources/fonts/font-awesome-4.1.0/css/font-awesome.min.css" var="fontCSS" />
    <link href="${fontCSS}" rel="stylesheet" />
    
    <spring:url value="/resources/fonts/eleganticons/et-icons.css" var="eticonsCSS" />
    <link href="${eticonsCSS}" rel="stylesheet" />
    
    <spring:url value="/resources/css/cardio_userhome.css" var="cardioCSS" />
    <link href="${cardioCSS}" rel="stylesheet" />
    
  
</head>


<body>
	<div class="preloader">
		  	 <img src="${images}/loader.gif"/>
			
	</div>
	<nav class="navbar userhomenav">
		<div class="container userhomecontianer">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				
			</div>
			<div id="welcome_message">Welcome ${email}</div>
			
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right main-nav">
					<li class="tabbar_user"><a href="userhome">Home</a></li>
					<li class="tabbar_user"><a href="usersubscriptions">SubscriptionList</a></li>
					<li class="tabbar_user"> <a href="usersettings">Settings</a></li>
					<li class="tabbar_user"><a href="launchpage">Log Out</a></li>
					
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<header id="intro" style="height:100%;">
		<div class="container">
			<div class="table col-md-6" id="searchbartable">
			<div class="card" style="overflow: auto;
    height: 100%;">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs profile-tab" role="tablist">
                                <li class="nav-item"> <a class="nav-link active" data-toggle="tab" href="#updates" role="tab">Updates</a> </li>
                                <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#newsfeed" role="tab">News Feed</a> </li>
                                <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#twitter" role="tab">Twitter Recommendations</a> </li>
                                
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                            
                            	<div class="tab-pane active" id="updates" role="tabpanel">
                                    <div class="card-block">
                                       <div class="userupdates">
                                       
                                       </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="newsfeed" role="tabpanel">
                                    <div class="card-block">
                                       <div class="newsfeed">
                                       
                                       </div>
                                    </div>
                                </div>
                                 <div class="tab-pane" id="twitter" role="tabpanel">
                                    <div class="card-block">
                                       <div class="twitter">
                                       
                                       </div>
                                    </div>
                                </div>
                               
                        </div>
        </div></div>
     </header>
	<!-- Holder for mobile navigation -->
	<div class="mobile-nav">
		<ul>
		</ul>
		<a href="#" class="close-link"><i class="arrow_up"></i></a>
	</div>
	<!-- Scripts -->
	
	<spring:url value="/resources/js/jquery-1.11.1.min.js" var="jqueryMain" />
    <script src="${jqueryMain}"></script>
    
    <spring:url value="/resources/js/owl.carousel.min.js" var="owlCarMain" />
    <script src="${owlCarMain}"></script>
	
	<spring:url value="/resources/js/bootstrap.min.js" var="bootStrapMain" />
    <script src="${bootStrapMain}"></script>
	
	<spring:url value="/resources/js/wow.min.js" var="wowminJS" />
    <script src="${wowminJS}"></script>
    
    <spring:url value="/resources/js/typewriter.js" var="typewriterJS" />
    <script src="${typewriterJS}"></script>
	
	 <spring:url value="/resources/js/jquery.onepagenav.js" var="jqueryOnePage" />
    <script src="${jqueryOnePage}"></script>
	
	<spring:url value="/resources/js/jquery.onepagenav.js" var="jqueryOnePage" />
    <script src="${jqueryOnePage}"></script>
	
	<spring:url value="/resources/js/googleanalytics.js" var="googleAnalytics" />
    <script src="${googleAnalytics}"></script>
    
	<spring:url value="/resources/js/userhome.js" var="mainJsS" />
    <script src="${mainJsS}"></script>
<!-- 	<script src="../js/jquery-1.11.1.min.js"></script> -->
<!-- 	<script src="../js/owl.carousel.min.js"></script> -->
<!-- 	<script src="../js/bootstrap.min.js"></script> -->
	<!-- <script src="../js/wow.min.js"></script>
	<script src="../js/typewriter.js"></script>
	<script src="../js/jquery.onepagenav.js"></script>
	<script src="../js/main.js"></script> -->
</body>

</html>
