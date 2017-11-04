var map2, layer2, firstname, signed;
var pageLanguage = document.getElementById('body').className;
var countries = ['Chad', 'Central African Republic', 'South Sudan', 'Niger', 'Mali', 'Guinea', 'Burkina Faso', 'Liberia', 'Ethiopia'];
var correctCountries = [];
var wrongCountries = [];

var validationMessages = {
    'en': {
        firstname: "Please enter your first name",
        lastname: "Please enter your last name",
        email: {
            required: "Please enter your email address",
            email: "Please enter a valid email address"
        },
        country: "Please select your country",
        zip: "Please enter your zip/postal code",
    },
}

var mapSettings = {
    center: [7.536764322084078, 13.18359375],
    zoom: 3,
    attributionControl: false,
    zoomControl: false,
    scrollWheelZoom: false,
    touchZoom: false,
    doubleClickZoom: false,
    boxZoom: false,
    keyboard: false,
    dragging: false
};

function initMap() {
    if ($(window).width() < 992) {
        mapSettings.zoom = 2.8;
        mapSettings.center = [11.127932363612956, 17.476044816311003];
    }
    if ($(window).width() < 768) {
        mapSettings.zoom = 2.7;
        mapSettings.center = [-3.947162428584497, 17.15142666044858];
        mapSettings.tap = false;
    }

    $.getJSON('/js/africa.json', function(data){
        //results map non-signers
        map2 = L.map('map2', mapSettings);
        layer2 = L.geoJson(data, {
            clickable: false,
            style: initMapStyle,
            onEachFeature: addNumbersToCountriesMap2
        }).addTo(map2);
    })
}

function initMapStyle(feature) {
    var style = {
        color: '#000000',
        weight: 2,
        fill: true,
        fillColor: 'transparent',
        fillOpacity: 1
    }
    if (countries.indexOf(feature.properties.geounit) != -1) {
        style.fillColor = "#D1CEC7";
    }
    return style;
}

function addNumbersToCountriesMap2(feature, layer) {
    if (countries.indexOf(feature.properties.geounit) != -1) {
        L.marker(layer.getBounds().getCenter(), {
            icon: L.divIcon({
                className: 'map-label',
                html: countries.indexOf(feature.properties.geounit)+1,
                iconSize: [20, 20]
            })
        }).addTo(map2);
    }
}

function setResults(){
    layer2.setStyle(resultsMapStyle);

    $('.score-list').each(function(i){
        var incorrectPointer = 0;
        $(this).children('li').each(function(i){
            var $this = $(this);
            var country = $this.data('country');
            var countryT = $this.text().split('. ')[1];
            var num = i+1;
            if (correctCountries.indexOf(country) == -1) {
                if (pageLanguage == 'fr') {
                    var wrongCountry = countriesFR[wrongCountries[incorrectPointer]];
                } else if (pageLanguage == 'nl') {
                    var wrongCountry = countriesNL[wrongCountries[incorrectPointer]];
                } else {
                    var wrongCountry = wrongCountries[incorrectPointer];
                }
                $this.html(num+'. <span class="wrong">'+wrongCountry+'</span> '+countryT);
                incorrectPointer++;
            }
        });
    });

    var score = correctCountries.length;
    $('.answers-correct').html(score);
    if (pageLanguage == 'fr') {
        //var congrats = 'Bravo, vous faites partie des x%!';
        var congrats = 'Bravo!';
        var greatJob = 'Bien joué!';
        var goodJob = 'Pas mal!';
        var match = 'égaler';
    } else if (pageLanguage == 'nl') {
        //var congrats = 'Gefeliciteerd, je behoort tot de X%!';
        var congrats = 'Gefeliciteerd!';
        var greatJob = 'Goed gedaan!';
        var goodJob = 'Goed geprobeerd!';
        var match = 'evenaren';
    } else {
        var congrats = 'Congratulations!';
        var greatJob = 'Great job!';
        var goodJob = 'Good try!';
        var match = 'match';
    }

    if (score == 9) {
        $('.beat-or-match').text(match);
        $('.score-message').text(congrats);
    }
    else if (score >= 6) {
        $('.score-message').text(greatJob);
    } else if (score > 0) {
        $('.score-message').text(goodJob);
    }

    gtag('event', 'score', {
        'event_category': '9countries',
        'event_label': score
    });

    fbq('track', 'Lead', {value: 0.0, currency: 'GBP'});
    fbq('trackCustom', 'quizComplete', {score: score});
}

function resultsMapStyle(feature) {
    var style = {
        color: '#000000',
        weight: 1.3,
        fill: true,
        fillColor: 'transparent',
        fillOpacity: 1
    }
    if (countries.indexOf(feature.properties.geounit) != -1) {
        style.fillColor = "#D1CEC7";
    }
    if (correctCountries.indexOf(feature.properties.geounit) != -1) {
        style.fillColor = "#FFA07A";
    }
    return style;
}

function goToNextSlide($clicked) {
    var nextSlide = $clicked.closest('.slide').next();
    nextSlide.show();
    $(window).scrollTo(nextSlide, 800);
}

function goToSlide(target) {
    var nextSlide = $(target);
    nextSlide.show();
    $(window).scrollTo(nextSlide, 800);
}

function sizeSlides() {
    var headerHeight = $('.main-header').outerHeight();
    var footerHeight = $('.main-footer').outerHeight();
    var windowHeight = $(window).height();
    var offset = 50;
    if ($(window).width() < 768) {
        offset = 10;
    }
    $('.slide').css({
        'min-height': windowHeight - footerHeight,
        'padding-top': headerHeight + offset
    });
}

function addFirstname(firstname) {
    var firstname = firstname.charAt(0).toUpperCase() + firstname.slice(1);
    $('.firstname-replace').html(firstname);
    $('.firstname-replace-comma-before').text(', '+firstname);
    $('.firstname-replace-comma-after').parent().each(function(){
        var og = $(this).text();
        var newStr = firstname+', '+og.charAt(0).toLowerCase() + og.slice(1);
        $(this).text(newStr);
    })
}

$('#signup-form').validate({
    submitHandler: function(form, e) {
        e.preventDefault();
        signed = true;
        var form = $(form);
        var name = form.find('input[name="firstname"]').val()+' '+form.find('input[name="lastname"]').val();
        var data = form.serialize();
        var data = data.substring(data.indexOf('email='))+'&name='+encodeURIComponent(name)+'&action_9countries_score='+correctCountries.length;
        $.post('https://act.one.org/rest/v1/action/', data);
        $('.thanks-sign').show();
        goToSlide('.slide-results');
        addFirstname(form.find('input[name="firstname"]').val())
        gtag('event', 'sign', {
            'event_category': '9countries',
            'event_label': 'petition signed'
        });
        fbq('track', 'CompleteRegistration', {value: 0.0, currency: 'GBP'});
    },
    rules: {
        firstname: {
            required: true
        },
        lastname: {
            required: true
        },
        email: {
            required: true,
            email: true
        },
        country: {
            required: true
        },
        zip:{
            required: {
                depends: function(element) {
                    var requiredCountries = ['Belgium', 'France', 'Germany', 'Netherlands', 'Canada', 'United States', 'United Kingdom'];
                    var selectedCountry = $(element).parent().prev().children('.country').val();
                    return requiredCountries.indexOf(selectedCountry) != -1;
                }
            },
            belgium_postcode: {
                depends: function(element) {
                    return $(element).parent().prev().children('.country').val() === 'Belgium';
                }
            },
            france_postcode: {
                depends: function(element) {
                    return $(element).parent().prev().children('.country').val() === 'France';
                }
            },
            germany_postcode: {
                depends: function(element) {
                    return $(element).parent().prev().children('.country').val() === 'Germany';
                }
            },
            nl_postcode: {
                depends: function(element) {
                    return $(element).parent().prev().children('.country').val() === 'Netherlands';
                }
            },
            canada_postcode: {
                depends: function(element) {
                    return $(element).parent().prev().children('.country').val() === 'Canada';
                }
            },
            us_postcode: {
                depends: function(element) {
                    return $(element).parent().prev().children('.country').val() === 'United States';
                }
            },
            uk_postcode: {
                depends: function(element) {
                    return $(element).parent().prev().children('.country').val() === 'United Kingdom';
                }
            },
        }
    },
    messages: {
        firstname: validationMessages[pageLanguage].firstname,
        lastname: validationMessages[pageLanguage].lastname,
        email: {
            required: validationMessages[pageLanguage].email.required,
            email: validationMessages[pageLanguage].email.email
        },
        country: validationMessages[pageLanguage].country,
        zip: validationMessages[pageLanguage].zip
    }
});

jQuery.validator.addMethod('belgium_postcode', function(value, element) {
    return this.optional(element) || /^[0-9]{4}$/i.test(value);
}, validationMessages[pageLanguage].zip);

jQuery.validator.addMethod('france_postcode', function(value, element) {
    return this.optional(element) || /^[0-9]{5}$/i.test(value);
}, validationMessages[pageLanguage].zip);

jQuery.validator.addMethod('germany_postcode', function(value, element) {
    return this.optional(element) || /^[0-9]{5}$/i.test(value);
}, validationMessages[pageLanguage].zip);

jQuery.validator.addMethod('nl_postcode', function(value, element) {
    return this.optional(element) || /^[1-9][0-9]{3}\s?[a-zA-Z]{2}$/.test(value);
}, validationMessages[pageLanguage].zip);

jQuery.validator.addMethod('canada_postcode', function(value, element) {
    return this.optional(element) || /^[ABCEGHJKLMNPRSTVXY]\d[ABCEGHJKLMNPRSTVWXYZ] *\d[ABCEGHJKLMNPRSTVWXYZ]\d$/i.test(value);
}, validationMessages[pageLanguage].zip);

jQuery.validator.addMethod('us_postcode', function(value, element) {
    return this.optional(element) || /^\d{5}(-\d{4})?$/.test(value);
}, validationMessages[pageLanguage].zip);

jQuery.validator.addMethod('uk_postcode', function(value, element) {
    return this.optional(element) || /^((([A-PR-UWYZ][0-9])|([A-PR-UWYZ][0-9][0-9])|([A-PR-UWYZ][A-HK-Y][0-9])|([A-PR-UWYZ][A-HK-Y][0-9][0-9])|([A-PR-UWYZ][0-9][A-HJKSTUW])|([A-PR-UWYZ][A-HK-Y][0-9][ABEHMNPRVWXY]))\s?([0-9][ABD-HJLNP-UW-Z]{2})|(GIR)\s?(0AA))$/i.test(value);
}, validationMessages[pageLanguage].zip);

function encodeHTML(s) {
    var t = s.replace(/&/g, '&amp;');
    var u = t.replace(/[<]/g, '&lt;');
    var v = u.replace(/"/g, '&quot;');
    var w = v.replace(/[>]/g, '&gt;');
    return w;
}

function getParameterByName(name, url) {
    if (!url) {
        url = window.location.href;
    }
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return '';
    if (!results[2]) return '';
    var length = 30;
    resultingString = encodeHTML(decodeURIComponent(results[2].replace(/\+/g, " ")));
    return resultingString.substring(0, length);
}

function userAlreadySignedPetition() {
    signed = true;
    $('#sign-petition-ask, .slide-pre-results .btn-no').hide();
    var buttonText = 'Show me my results';
    if (pageLanguage == 'fr') {
        var buttonText = 'Voir mes résultats';
    } else if (pageLanguage == 'nl') {
        var buttonText = 'Toon mijn resultaten';
    } else {
        var buttonText = 'Show me my results';
    }
    var resultsButton = $('.slide-pre-results .btn-sign');
    resultsButton.text(buttonText).off();
    resultsButton.on('click', function(){
        goToSlide('.slide-results');
    })
}

$('.btn-submit').on('click', function(){
    $(this).closest('.signup-form').submit();
})

$('.country').on('change', function(){
    var optInCountries = ['Germany', 'Canada'];
    if (optInCountries.indexOf(this.value) != -1) {
        $('.privacy-policy-checkbox').show();
        $('.privacy-policy').hide();
    } else {
        $('.privacy-policy-checkbox').hide();
        $('.privacy-policy').show();
    }
})

$('.btn-country').on('click', function(){
    var $this = $(this);
    if ($this.data('correct') == true && correctCountries.indexOf($this.data('country')) == -1) {
        correctCountries.push($this.data('country'));
    } else {
        wrongCountries.push($this.data('country'));
    }
    if ($this.hasClass('final-q')) {
        setResults();
    }
    goToNextSlide($this);
    var question = $this.parent().attr('id');
    gtag('event', 'click', {
        'event_category': '9countries',
        'event_label': question + ' completed'
    });
});

$('.next').on('click', function() {
    var nextSlide = $(this).closest('.slide').next();
    nextSlide.show();
    $(window).scrollTo(nextSlide, 800);
});

$('.btn-no, .skip-signing').on('click', function(){
    goToSlide('.slide-results');
    gtag('event', 'click', {
        'event_category': '9countries',
        'event_label': 'will you sign petition? no'
    });
})

$('.btn-sign').on('click', function(){
    gtag('event', 'click', {
        'event_category': '9countries',
        'event_label': 'will you sign petition? yes'
    });
})


$('.btn-share-yes').on('click', function(){
    goToSlide('.share-tool');
    gtag('event', 'click', {
        'event_category': '9countries',
        'event_label': 'will you share? yes'
    });
})

$('.btn-share-no').on('click', function(){
    signed ? goToSlide('.share-petition') : $('.btn-show-score').click();
    gtag('event', 'click', {
        'event_category': '9countries',
        'event_label': 'will you share? no'
    });
})

$('.btn-show-score').on('click', function(){
    var width = $(window).width();
    $('.score-container').slideToggle(function(){
        map2.invalidateSize();
        var $el = $(this);
        if ($el.is(':visible')) {
            $(window).scrollTo($(this), {
                duration: 500,
                offset: -150
            });
        }
    });
})

$('.btn-facebook.game').on('click', function(){
    var score = correctCountries.length;
    var url = window.location.href.split('?')[0];
    var shareUrl = encodeURIComponent(url+'?score='+score);
    window.open('https://facebook.com/sharer.php?u='+shareUrl, '_blank', 'toolbar=no,location=0,status=no,menubar=no,scrollbars=yes,resizable=yes,width=600,height=250,top=300,left=300');
    gtag('event', 'share', {
        'event_category': '9countries',
        'event_label': 'facebook share - game'
    });
})

$('.btn-facebook.petition').on('click', function(){
    var shareUrl = encodeURIComponent($(this).data('url'));
    window.open('https://facebook.com/sharer.php?u='+shareUrl, '_blank', 'toolbar=no,location=0,status=no,menubar=no,scrollbars=yes,resizable=yes,width=600,height=250,top=300,left=300');
    gtag('event', 'share', {
        'event_category': '9countries',
        'event_label': 'facebook share - petition'
    });
})

$('.btn-twitter').on('click', function(){
    var $this = $(this);
    if ($this.hasClass('game')) {
        var url = encodeURIComponent(window.location.href.split('?')[0]);
        var shareType = 'game';
    } else {
        var url = encodeURIComponent($this.data('url'));
        var shareType = 'petition';
    }
    var text = encodeURIComponent($this.data('msg'));
    window.open('https://twitter.com/intent/tweet?text='+text+'&url='+url, '_blank', 'toolbar=no,location=0,status=no,menubar=no,scrollbars=yes,resizable=yes,width=600,height=250,top=300,left=300');
    gtag('event', 'share', {
        'event_category': '9countries',
        'event_label': 'twitter share - '+shareType
    });
})

$('.btn-whatsapp').on('click', function(){
    var $this = $(this);
    var shareType = $this.hasClass('game') ? 'game' : 'petition';
    gtag('event', 'share', {
        'event_category': '9countries',
        'event_label': 'whatsapp share - '+shareType
    });
})

$('.language-select').on('change', function(){
    window.location = window.location.origin+this.value;
})

$(window).resize(function(){
    sizeSlides();
});

$(document).ready(function(){
    sizeSlides();
    initMap();
    if (getParameterByName('signed') == 'true') {
        userAlreadySignedPetition();
    }
    if (getParameterByName('source')) {
        $('input[name="source"]').val(getParameterByName('source'))
    }
})