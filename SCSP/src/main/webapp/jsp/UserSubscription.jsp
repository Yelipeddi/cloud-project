<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>SCSP- UserHome</title>
	<meta name="description" content="Software Catalog Subscription Portal" />
	<meta name="keywords" content="technotify,staytuned,subscribe,alerts" />
	<meta name="author" content="Ravali,Supreetha,Abhinaya" />
	
	<!-- Favicons (created with http://realfavicongenerator.net/)-->
	
<spring:url value="/resources/img" var="images" />
 <img src="${images}/favicons/manifest.json"/>
<img src="${images}/favicons/browserconfig.xml"/>
<meta name="msapplication-TileColor" content="#00a8ff">
	
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
    
    <spring:url value="/resources/css/cardio_usersubscription.css" var="cardioCSS" />
    <link href="${cardioCSS}" rel="stylesheet" />
    
  
</head>


<body>
	<div class="preloader">
		  	 <img src="${images}/loader.gif"/>
			
	</div>
	<nav class="navbar userhomenav navbarcustom">
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
			<div id="welcome_message">Welcome </div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right main-nav">
					<li class="tabbar_user"><a href="userhome">Home</a></li>
					<li class="tabbar_user"><a href="usersubscriptions">SubscriptionList</a></li>
					<li class="tabbar_user"><a href="usersettings">Settings</a></li>
					<li class="tabbar_user"><a href="launchpage">Log Out</a></li>
					
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<header id="intro">
		<div class="container" style="margin-top: 10%;">
			<div class="table col-md-6" id="searchbartable" >
			
			<div id="search-form" class="search-form js-search-form">
            <form class="form-search" role="search" action="#" method="get">
                <div class="input-group">
                    <input type="text" class="form-control" onkeyup="myFunction()" placeholder="Search for Technologies and Frameworks" id="searchbar"/>
                    <span class="input-group-btn">
                        <button class="btn btn-info searchbtnicon" type="button" id="searchbtn">
                            <i class="fa fa-search searchbtnicon"></i>
                        </button>
                    </span>
                </div>
                
            </form>
            <div class="instant-results">
                <ul class="list-unstyled result-bucket" id="suggestedList">
                </ul>
            </div>
        <div class="sampleresults">
        <div class="bs-example" data-example-id="textarea-form-control"> 
        <form style="margin-top: 3%;margin-left: -1%;"> 
        <div class="editable" id="active_subscription_list" class="form-control" rows="3"></div>
<!--         <textarea id="active_subscription_list" class="form-control" rows="3" placeholder="Textarea"></textarea>  -->
          <button type="button" class="btn btn-default searchformbtn" id="saveSubscription">Save Subscriptions</button>
        
        </form> 
        </div>
        
       
        </div>
        
        </div>
			</div></div></header>
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
    
	 <spring:url value="/resources/js/jqueryui.js" var="jqueryuiJS" />
    <script src="${jqueryuiJS}"></script>
    
    <spring:url value="/resources/js/googleanalytics.js" var="googleAnalytics" />
    <script src="${googleAnalytics}"></script>
    
	<spring:url value="/resources/js/usersubscription.js" var="mainJsS" />
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
