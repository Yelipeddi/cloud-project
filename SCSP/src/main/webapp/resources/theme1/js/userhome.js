jQuery(function($) {
    "use strict";

    console.log("UserHome for \t User" + $('#welcome_message').html());


    //Mouse Over on Tabs.
    $(".tabbar_user").hover(function(){

    	$(this).css("background-color", "#00a8ff");
    },function()
    {
    	$(this).css("background-color", "");
    	}

    );

    //Set LocalStorage Variable to Save User for Session.
    if ($('#welcome_message').html().includes("@"))
    {
        var welcome_message = $('#welcome_message').html();
        welcome_message = welcome_message.replace('Welcome ', '');
        localStorage.setItem("useremail", welcome_message);
    }
    else
    {
    	//Return to the same page then get the value from local storage
        $('#welcome_message').append(localStorage.getItem("useremail"));

    }


   //Load User Home Related.
    var useremail=localStorage.getItem("useremail");
    console.log(useremail);
    //User Notifications or Updates.

  //Get the Current List of Updates for User.
    var url="userupdates?useremail="+useremail;
    console.log(url);
	 $.getJSON(url, function(result){
		 console.log(result);
		 for (var a in result.updates)
		 {
			 if(result.updates[a]!=="")
			 {

				 var cardItem='<div class="sl-item"><div class="sl-left"> <img src="resources/img/userhome/'+result.updates[a].techname+'.png"> </div><div class="sl-right"><div><a href="#" class="link">';
				    cardItem+=result.updates[a].techname;
				    cardItem+='</a><br></div> <span class="sl-date"></span><blockquote class="m-t-10" style="color:black;">';
				    cardItem+=result.updates[a]["update"];
				    cardItem+='<br><a target="_blank" href="'+result.updates[a]["url"]+'">Please find more details here </a>';
				    cardItem+='</blockquote></div></div></div>';
				    $(".userupdates").append(cardItem);
			 }
			}

		 //For News.

		 //Get the New news from News API.
		 var newsapiURL="https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=";
		 $.getJSON(newsapiURL, function(newsdata)
		 {
			 console.log(newsdata);

			 for (var a in newsdata.articles)
			 {
				 if(newsdata.articles[a]!=="")
				 {

					 var cardItem='<div class="sl-item"><div class="sl-left"> <img src="resources/img/userhome/techcrunch-logo.png"> </div><div class="sl-right"><div><a href="#" class="link">';
					    cardItem+=newsdata.articles[a].source.name;
					    cardItem+='</a><br></div> <span class="sl-date"></span><blockquote class="m-t-10" style="color:black;">';
					    cardItem+=newsdata.articles[a].title;
					    cardItem+='<br><a target="_blank" href="'+newsdata.articles[a].url+'">Please find more details here </a>';
					    cardItem+='</blockquote></div></div></div>';
					    $(".newsfeed").append(cardItem);


					/* var cardItem='<div class="sl-item"><div class="sl-right"><div><a href="#" class="link">';
					    cardItem+=newsdata.articles[a].source.name;
					    cardItem+='</a></div> <span class="sl-date"></span><blockquote class="m-t-10" style="color:black;">';
					    cardItem+=newsdata.articles[a].title;
					    cardItem+='<br><a href="'+newsdata.articles[a].url+'">Reference</a>';
					    cardItem+='</blockquote></div></div></div>';
					    $(".newsfeed").append(cardItem);*/
				 }
				}
		 });


		 //Get the Twitter Recommendations.
		 var twitterRecAPI="usertwitterrecommendations?useremail="+useremail;
		 $.getJSON(twitterRecAPI, function(twitterRecData)
		 {
			 console.log(twitterRecData);

			 for (var a in twitterRecData.followers)
			 {
				 if(twitterRecData.followers[a]!==null)
				 {


					 var cardItem='<div class="sl-item"><div class="sl-left"> <img src="resources/img/userhome/twitter.png"> </div><div class="sl-right" style="margin-top: -3%;"><div><a href="#" class="link">';
					    cardItem+='Twitter';
					    cardItem+='</a><br></div> <span class="sl-date"></span><blockquote class="m-t-10" style="color:black;">';
					    cardItem+=twitterRecData.followers[a].name;
					    cardItem+='<br><a target="_blank" href="'+twitterRecData.followers[a].url+'">To Follow, Click Here </a>';
					    cardItem+='</blockquote></div></div></div>';
					    $(".twitter").append(cardItem);


					/* var cardItem='<div class="sl-item"><div class="sl-right"><div><a href="#" class="link">';
					    //cardItem+=twitterRecData.followers[a].name;
					    cardItem+='</a></div> <span class="sl-date"></span><blockquote class="m-t-10" style="color:black;">';
					    cardItem+=twitterRecData.followers[a].name;
					    cardItem+='<br><a target="_blank" href="'+twitterRecData.followers[a].url+'">To Follow, Click Here:</a>';
					    cardItem+='</blockquote></div></div></div>';
					    $(".twitter").append(cardItem);*/
				 }
				}
		 });
	 });

//		 for (var a in result.news)
//		 {
//			 if(result.news[a]!=="")
//			 {
//
//				 var cardItem='<div class="sl-item"><div class="sl-right"><div><a href="#" class="link">';
//				    cardItem+="Java";
//				    cardItem+='</a></div> <span class="sl-date"></span><blockquote class="m-t-10" style="color:black;">';
//				    cardItem+=result.news[a];
//				    cardItem+='</blockquote></div></div></div>';
//				    $(".newsfeed").append(cardItem);
//			 }
//			}

		 //Twitter Recomendations
//		 for (var a in result.twitterRecommendations)
//		 {
//			 if(result.twitterRecommendations[a]!=="")
//			 {
//
//				 var cardItem='<div class="sl-item"><div class="sl-right"><div><a href="#" class="link">';
//				    cardItem+="Java";
//				    cardItem+='</a></div> <span class="sl-date"></span><blockquote class="m-t-10" style="color:black;">';
//				    cardItem+=result.twitterRecommendations[a];
//				    cardItem+='</blockquote></div></div></div>';
//				    $(".twitter").append(cardItem);
//			 }
//			}
//
//	    });
//
//    var cardItem='<div class="sl-item"><div class="sl-right"><div><a href="#" class="link">John Doe</a> <span class="sl-date">5 minutes ago</span><blockquote class="m-t-10">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt</blockquote></div></div></div>';
//    $('.profiletimeline').append(cardItem);
//    $('.profiletimeline').append("<hr>");
//
//    $('.profiletimeline').append(cardItem);
//    $('.profiletimeline').append("<hr>");
//
//    $('.profiletimeline').append(cardItem);
//    $('.profiletimeline').append("<hr>");
//
//    $('.profiletimeline').append(cardItem);
//    $('.profiletimeline').append("<hr>");
//
//    $('.profiletimeline').append(cardItem);
//    $('.profiletimeline').append("<hr>");




    //Styling and Other
    var owlPricing;
    var ratio = 2;

    setup();
    function setup() {

        // Preloader
        $('.intro-tables, .parallax, header').css('opacity', '0');
        $('.preloader').addClass('animated fadeOut').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
            $('.preloader').hide();
            $('.parallax, header').addClass('animated fadeIn').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
                $('.intro-tables').addClass('animated fadeInUp').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');
            });
        });

        // Header Init
        if ($(window).height() > $(window).width()) {
            var ratio = $('.parallax').width() / $('.parallax').height();
            $('.parallax img').css('height', ($(window).height()) + 'px');
            $('.parallax img').css('width', $('.parallax').height() * ratio + 'px');
        }

        $('header').height($(window).height() + 80);
        $('section .cut').each(function() {
            if ($(this).hasClass('cut-top'))
                $(this).css('border-right-width', $(this).parent().width() + "px");
            else if ($(this).hasClass('cut-bottom'))
                $(this).css('border-left-width', $(this).parent().width() + "px");
        });

        // Sliders Init
        $('.owl-schedule').owlCarousel({
            singleItem: true,
            pagination: true
        });
        $('.owl-testimonials').owlCarousel({
            singleItem: true,
            pagination: true
        });
        $('.owl-twitter').owlCarousel({
            singleItem: true,
            pagination: true
        });

        // Typing Intro Init
        $(".typed").typewriter({
            speed: 60
        });

        // Popup Form Init
        var i = 0;
        var interval = 0.15;
        $('.popup-form .dropdown-menu li').each(function() {
            $(this).css('animation-delay', i + "s");
            i += interval;
        });
        $('.popup-form .dropdown-menu li a').click(function(event) {
            event.preventDefault();
            $(this).parent().parent().prev('button').html($(this).html());
        });

        // Onepage Nav
        $('.navbar.navbar-fixed-top .navbar-nav').onePageNav({
            currentClass: 'active',
            changeHash: false,
            scrollSpeed: 400,
            filter: ':not(.btn)'
        });
    }

    // Window Resize
    $(window).resize(function() {
        $('header').height($(window).height());
    });

    // Pricing Box Click Event
    $('.pricing .box-main').click(function() {
        $('.pricing .box-main').removeClass('active');
        $('.pricing .box-second').removeClass('active');
        $(this).addClass('active');
        $(this).next($('.box-second')).addClass('active');
        $('#pricing').css("background-image", "url(" + $(this).data('img') + ")");
        $('#pricing').css("background-size", "cover");
    });

    // Mobile Nav
    $('body').on('click', 'nav .navbar-toggle', function() {
        event.stopPropagation();
        $('.mobile-nav').addClass('active');
    });

    $('body').on('click', '.mobile-nav a', function(event) {
        $('.mobile-nav').removeClass('active');
        if (!this.hash) return;
        event.preventDefault();
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            event.stopPropagation();
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            if (target.length) {
                $('html,body').animate({
                    scrollTop: target.offset().top
                }, 1000);
                return false;
            }
        }
    });

    $('body').on('click', '.mobile-nav a.close-link', function(event) {
        $('.mobile-nav').removeClass('active');
        event.preventDefault();
    });

    $('body').on('click', 'nav.original .navbar-nav a:not([data-toggle])', function() {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            event.stopPropagation();
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            if (target.length) {
                $('html,body').animate({
                    scrollTop: target.offset().top
                }, 1000);
                return false;
            }
        }
    });

    function centerModal() {
        $(this).css('display', 'block');
        var $dialog = $(this).find(".modal-dialog"),
            offset = ($(window).height() - $dialog.height()) / 2,
            bottomMargin = parseInt($dialog.css('marginBottom'), 10);

        // Make sure you don't hide the top part of the modal w/ a negative margin
        // if it's longer than the screen height, and keep the margin equal to
        // the bottom margin of the modal
        if (offset < bottomMargin) offset = bottomMargin;
        $dialog.css("margin-top", offset);
    }

    $('.modal').on('show.bs.modal', centerModal);

    $('.modal-popup .close-link').click(function(event) {
        event.preventDefault();
        $('#modal1').modal('hide');
    });

    $(window).on("resize", function() {
        $('.modal:visible').each(centerModal);
    });

});
