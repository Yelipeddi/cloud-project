var searchresults={};
jQuery(function($) {
	"use strict";
	

	//Insert Google Analytics Script.
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());

	  gtag('config', 'UA-110577221-1');

	
	 //Mouse Over on Tabs.
    $(".tabbar_user").hover(function(){
    	
    	$(this).css("background-color", "#00a8ff");
    },function()
    {
    	$(this).css("background-color", "");
    	}

    );
	console.log("Get Subscription Data From Service");
	$('#welcome_message').append(localStorage.getItem("useremail"));
	var useremail=localStorage.getItem("useremail");

	//Get the Current Lits of Subscriptions for User.
	 $.getJSON("subscriptions?useremail="+useremail, function(result){
		 console.log(result);
		 for (var a in result.subscriptionList) 
		 {
			 if(result.subscriptionList[a]!=="")
			 {
			 $("#active_subscription_list").append('<span class="destroy" id="'+result.subscriptionList[a]+'" >'+result.subscriptionList[a]+'<span style="color:red;"> x </span></span>');
			 }
			}
	    });
	 function loadSearchStrings(result){
		 for (var a in result.suggestedTechnologies) 
		 {
		 var injectHTML='<li class="result-entry" id="parent_'+result.suggestedTechnologies[a]+'" data-type="type" data-analytics-type="merchant"><div class="media"><div class="media-body"><h4 class="media-heading suggestedtech">'+result.suggestedTechnologies[a]+'</h4></div></div></li>';
		 $("#suggestedList").append(injectHTML);
		 }
		 
/*
		 $( "#searchbar" ).autocomplete({
		      source: searchresults.suggestedTechnologies
		    });*/
	 }
	 
	
	 
	 //Get the Technology List Available to the user other than the ones already subscribed to.
	 $.getJSON("suggestedlist?useremail="+useremail, function(result){
		 console.log(result);
		 searchresults=result;
		 loadSearchStrings(result);
	    });
	 
	  //loadSearchStrings($('#searchbar').attr('source'));

	 
	 
	var owlPricing;
	var ratio = 2;
	
	setup();
	// Window Load
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
		if(!this.hash) return;
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

	$('.modal-popup .close-link').click(function(event){
		event.preventDefault();
		$('#modal1').modal('hide');
	});

	$(window).on("resize", function() {
		$('.modal:visible').each(centerModal);
	});
	
	
	//Search bar Related.
	//Open Search    
    $('.form-search').click(function (event) {
        $(".instant-results").fadeIn('slow').css('height', 'auto');
        event.stopPropagation();
    });

    $('body').click(function () {
        $(".instant-results").fadeOut('500');
    });
    
    
    
    //Submit User Subscription Related Changes.
    $("#saveSubscription").click(function(){
        console.log("Save Subscription");
        var completeText=$('#active_subscription_list').text();
        console.log('Save Subscriptions');
        console.log(completeText);
        
        var res = completeText.split(" ");
        
        var techListUpdated=[];
        
        console.log(res);
        for (var i = 0; i < res.length; i++) 
        {
        		if(res[i]!='' &&  res[i]!='x')
        			{
        			techListUpdated.push(res[i]);
        			console.log(res[i]);
        			}
        }
        console.log(techListUpdated);
        var queryValue='';
        for(var a=0;a<techListUpdated.length;a++){
            console.log('hello');

        	if(!(a==techListUpdated.length-1))
        	{	
        		queryValue+=techListUpdated[a]+','
        	}
        	else
        	{
                console.log('yo');
        		queryValue+=techListUpdated[a];
        	}
        	
        }
        
        console.log("Query\t"+queryValue);
        
        var url="subscriptions?useremail="+useremail+"&subscriptionlist="+queryValue;
        console.log(url);
        //Send the Complete Text Content to Service and Let Service do its work.
        $.post(url, {}, function(result)
            {
                console.log('Profile Details Successfuly Saved');
                alert('Profile Details Successfuly Saved');
                
            });
        
        
        
    }); 
	
	
});

//When User Changes or Subscribes to New Technology.
$(document).on('click','.suggestedtech',function()
		{
		var removeElement="'."+event.target.id+"'";
    	console.log($(this).html()); 
    	
    	//Now Add the item to view.
    	var divTobeDeleted="#parent_"+$(this).html();
    	var injection='<span class="destroy" id="'+$(this).html()+'">'+$(this).html()+'<span style="color:red;"> x </span></span>';
    	 $(divTobeDeleted).remove();
    	 var injectHTML='<li class="result-entry" id="parent_'+$(this).html()+'" data-type="type" data-analytics-type="merchant"><div class="media"><div class="media-body"><h4 class="media-heading suggestedtech">'+$(this).html()+'</h4></div></div></li>';
		 $("#active_subscription_list").append(injection);
		 
		 
		 //Remove the above selected item from search results
//		 if(searchresults!=null){
//			 
//			 searchresults.suggestedTechnologies=remove(searchresults.suggestedTechnologies,$(this).html());
//			 loadSearchStrings(searchresults);
//		 }
    	
	});

function remove(array, element) {
    const index = array.indexOf(element);
    array.splice(index, 1);
    return array;
}

//When User Removes any Existing Technology from list.
$(document).on('click','.destroy',function()
		{
		var removeElement="'."+event.target.id+"'";
    	console.log($(this).attr('id')); 

    	var injectHTML='<li class="result-entry" id="parent_'+$(this).attr('id')+'" data-type="type" data-analytics-type="merchant"><div class="media"><div class="media-body"><h4 class="media-heading suggestedtech">'+$(this).attr('id')+'</h4></div></div></li>';
	    $(this).remove();
		$("#suggestedList").append(injectHTML);

	});



//Calling rest api for saving searched technology in dynamodb.
$("#searchbtn").click(function(){
	
	var username = localStorage.getItem("useremail");
	var searchquery = $('#searchbar').val();
	var url= "search?useremail="+username+"&searchquery="+searchquery;
	 $.ajax({
     headers:{  
    "Accept":"application/json",
    "Content-type":"application/x-www-form-urlencoded"
      },  
      url:url,
      success:function(response){
    	  $('#searchbar').val("");
       console.log("added to dynamodb");
      }
    });
});
function myFunction() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("searchbar");
    filter = input.value.toUpperCase();
    ul = document.getElementById("suggestedList");
    li = ul.getElementsByTagName("li");
    console.log(li);
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("h4")[0];
        if(a!==undefined){
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";

        }
        }
    }
}



