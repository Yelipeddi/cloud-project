<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>SCSP</title>
	<meta name="description" content="Software Catalog Subscription Portal" />
	<meta name="keywords" content="technotify,staytuned,subscribe,alerts" />
	<meta name="author" content="Ravali,Supreetha,Abhinaya" />
	
	<!-- Favicons (created with http://realfavicongenerator.net/)-->
	
<spring:url value="/resources/img" var="images" />
 
	  <img src="${images}/favicons/manifest.json"/>
	  	 <img src="${images}/favicons/browserconfig.xml"/>
	  
	
	
	
<!-- 	<link rel="apple-touch-icon" sizes="60x60" href="img/favicons/apple-touch-icon-60x60.png"> -->
<!-- 	<link rel="icon" type="image/png" href="img/favicons/favicon-32x32.png" sizes="32x32"> -->
<!-- 	<link rel="icon" type="image/png" href="img/favicons/favicon-16x16.png" sizes="16x16"> -->
<!-- 	<link rel="manifest" href="img/favicons/manifest.json"> -->
<!-- 	<link rel="shortcut icon" href="img/favicons/favicon.ico"> -->
	<meta name="msapplication-TileColor" content="#00a8ff">
<!-- 	<meta name="msapplication-config" content="img/favicons/browserconfig.xml"> -->
<!-- 	<meta name="theme-color" content="#ffffff"> -->
	
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
    
     
    
    
    <spring:url value="/resources/css/cardio.css" var="cardioCSS" />
    <link href="${cardioCSS}" rel="stylesheet" />
    
  
</head>


<body>
	<div class="preloader">
		  	 <img src="${images}/loader.gif"/>
	</div>
	<nav class="navbar">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right main-nav">
					<li><a href="#intro">Intro</a></li>
					<li><a href="#team">Team</a></li>
					<li><a href="#" data-toggle="modal" data-target="#modallogin" class="btn btn-blue">Login</a></li>
					
					<li><a href="#" data-toggle="modal" data-target="#modal1" class="btn btn-blue">Sign Up</a></li>
					
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<header id="intro">
		<div class="container">
			<div class="table">
				<div class="header-text">
					<div class="row">
						<div class="col-md-12 text-center">
							<h3 class="light white">SCSP</h3>
							<h1 class="white typed">Stay Updated and Learn More</h1>
							<span class="typed-cursor">|</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<section>
		<div class="cut cut-top"></div>
		<div class="container">
			<div class="row intro-tables">
				<div class="col-md-4">
					<div class="intro-table intro-table-first">
						<h5 class="white heading threecards text-center">Know Tech Updates</h5>
					<!-- 	<div class="owl-carousel owl-schedule bottom">
							<div class="item">
								<div class="schedule-row row">
									<div class="col-xs-6">
										<h5 class="regular white">Early Exercise</h5>
									</div>
									<div class="col-xs-6 text-right">
										<h5 class="white">8:30 - 10:00</h5>
									</div>
								</div>
								<div class="schedule-row row">
									<div class="col-xs-6">
										<h5 class="regular white">Muscle Building</h5>
									</div>
									<div class="col-xs-6 text-right">
										<h5 class="white">8:30 - 10:00</h5>
									</div>
								</div>
								<div class="schedule-row row">
									<div class="col-xs-6">
										<h5 class="regular white">Cardio</h5>
									</div>
									<div class="col-xs-6 text-right">
										<h5 class="white">8:30 - 10:00</h5>
									</div>
								</div>
							</div>
							<div class="item">
								<div class="schedule-row row">
									<div class="col-xs-6">
										<h5 class="regular white">Early Exercise</h5>
									</div>
									<div class="col-xs-6 text-right">
										<h5 class="white">8:30 - 10:00</h5>
									</div>
								</div>
								<div class="schedule-row row">
									<div class="col-xs-6">
										<h5 class="regular white">Muscle Building</h5>
									</div>
									<div class="col-xs-6 text-right">
										<h5 class="white">8:30 - 10:00</h5>
									</div>
								</div>
								<div class="schedule-row row">
									<div class="col-xs-6">
										<h5 class="regular white">Cardio</h5>
									</div>
									<div class="col-xs-6 text-right">
										<h5 class="white">8:30 - 10:00</h5>
									</div>
								</div>
							</div>
							<div class="item">
								<div class="schedule-row row">
									<div class="col-xs-6">
										<h5 class="regular white">Early Exercise</h5>
									</div>
									<div class="col-xs-6 text-right">
										<h5 class="white">8:30 - 10:00</h5>
									</div>
								</div>
								<div class="schedule-row row">
									<div class="col-xs-6">
										<h5 class="regular white">Muscle Building</h5>
									</div>
									<div class="col-xs-6 text-right">
										<h5 class="white">8:30 - 10:00</h5>
									</div>
								</div>
								<div class="schedule-row row">
									<div class="col-xs-6">
										<h5 class="regular white">Cardio</h5>
									</div>
									<div class="col-xs-6 text-right">
										<h5 class="white">8:30 - 10:00</h5>
									</div>
								</div>
							</div>
						</div>
					 --></div>
				</div>
				<div class="col-md-4">
					<div class="intro-table intro-table-hover">
						<h5 class="white heading threecards text-center">Get Twitter Follower Recommendations</h5>
					
					</div>
				</div>
				<div class="col-md-4">
					<div class="intro-table intro-table-third">
						<h5 class="white heading threecards text-center">Get Latest Tech Versions</h5>
					
				</div>
			</div>
		</div>
	</section>
	
	<section id="team" class="section gray-bg">
		<div class="container">
			<div class="row title text-center">
				<h2 class="margin-top">Team</h2>
				<h4 class="light muted">We're a dream team!</h4>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="team text-center">
						<div class="cover">
							<div class="overlay text-center">
								
							</div>
						</div>
						<img src=""  class="avatar">
						<div class="title">
							<h4>Ravali Nagabandi</h4>
							<h5 class="muted regular">Founder, Developer</h5>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="team text-center">
						<div class="cover">
							<div class="overlay text-center">
								
							</div>
						</div>
						<img src=""  class="avatar">
						<div class="title">
							<h4>Supreetha MG</h4>
							<h5 class="muted regular">Co-Founder, Developer</h5>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="team text-center">
						<div class="cover">
							<div class="overlay text-center">
								
							</div>
						</div>
						<img src="" class="avatar">
						<div class="title">
							<h4>Abhinaya Yelipeddi</h4>
							<h5 class="muted regular">Co-Founder, Developer</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id="chatbox" style="margin-left: 78%;">
		<h4 style="text-align:  left">SCSP Help Powered by Lex</h4>
	<p style="width: 400px">
		<a href="https://aws.amazon.com/lex/" title="Amazon Lex (product)" target="_new"></a>
	</p>
	<div id="conversation" style="width: 400px; height: 400px; border: 1px solid #ccc; background-color: #eee; padding: 4px; overflow: scroll"></div>
	<form id="chatform" style="margin-top: 10px" onsubmit="return pushChat();">
		<input type="text" id="wisdom" size="80" value="" placeholder="I need a hotel room">
	</form>
		</div>
		
	</section>
	<div class="modal fade" id="modallogin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content modal-popup">
				<a href="#" class="close-link"><i class="icon_close_alt2"></i></a>
				<h3 class="white">Login</h3>
				<form action="loginProcess" class="popup-form" method="post">
					<input type="text" class="form-control form-white" name="email" placeholder="Email Address">
					<input type="password" class="form-control form-white" name="password" placeholder="Password">
					<button type="submit" class="btn btn-submit">Login</button>
				</form>
			</div>
		</div>
	</div>
		<div class="modal fade" id="modal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content modal-popup">
				<a href="#" class="close-link"><i class="icon_close_alt2"></i></a>
				<h3 class="white">Sign Up</h3>
				<form class="popup-form" id="registerForm">
					<input type="text" class="form-control form-white"  id="firstname" name="firstname" placeholder="Firstname">
					<input type="text" class="form-control form-white"  id="lastname" name="lastname" placeholder="Lastname">
					
					<input type="text" class="form-control form-white" id="email" name="email" placeholder="Email Address">
					
					<input type="password" class="form-control form-white" id="password" name="password" placeholder="Password">
						
					<div class="checkbox-holder text-left">
						<div class="checkbox">
							<input type="checkbox" value="None" id="squaredOne" />
							<label for="squaredOne"><span>I Agree to the <strong>Terms &amp; Conditions</strong></span></label>
						</div>
					</div>
					<button type="button" class="btn btn-submit" id="regsiterBtn">Submit</button>
				</form>
			</div>
		</div>
	</div>
	
	
	
	
	<!-- Holder for mobile navigation -->
	<div class="mobile-nav">
		<ul>
		</ul>
		<a href="#" class="close-link"><i class="arrow_up"></i></a>
	</div>
		<div id="testErrorMessage" style="display:none;">${message}</div>
	
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
    
    <spring:url value="/resources/js/sweetalert.js" var="sweetAlert" />
    <script src="${sweetAlert}"></script>
	
	<spring:url value="/resources/js/googleanalytics.js" var="googleAnalytics" />
    <script src="${googleAnalytics}"></script>
    
    <spring:url value="/resources/js/amazonlex.js" var="amazonLexMainJs" />
    <script src="${amazonLexMainJs}"></script>
    
    <spring:url value="/resources/js/amazonlex_home.js" var="amazonlexHomsJs" />
    <script src="${amazonlexHomsJs}"></script>
    
	<spring:url value="/resources/js/main.js" var="mainJsS" />
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
