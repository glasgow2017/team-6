var layer2;

var correctAnswers = [];
var wrongAnswers = [];
var numOfQuestions = 8;

/*var mapSettings = {
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
}; */

function setResults(){

    //layer2.setStyle(resultsMapStyle);

    $('.score-list').each(function(i){
        var incorrectPointer = 0;
        $(this).children('li').each(function(i){
            var $this = $(this);
            var answer = $this.data('answer');
            var answerT = $this.text().split('. ')[1];
            var num = i+1;
            if (correctCountries.indexOf(answer) == -1) {
                var wrongAnswer = wrongAnswers[incorrectPointer];
                $this.html(num+'. <span class="wrong">'+wrongAnswer+'</span> '+answerT);
                incorrectPointer++;
            }
        });
    });

    var score = correctAnswers.length;
    $('.answers-correct').html(score);

    var congrats = 'Congratulations!';
    var greatJob = 'Great job!';
    var goodJob = 'Good try!';
    var match = 'match';

    if (score == numOfQuestions) {
        $('.beat-or-match').text(match);
        $('.score-message').text(congrats);
    }
    else if (score >= numOfQuestions * 2 / 3) {
        $('.score-message').text(greatJob);
    } else if (score > 0) {
        $('.score-message').text(goodJob);
    }
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

$(window).resize(function(){
    sizeSlides();
});

$('.btn-answer').on('click', function(){
    var $this = $(this);
    if ($this.data('correct') == true && correctAnswers.indexOf($this.data('answer')) == -1) {
        correctAnswers.push($this.data('answer'));
    } else {
        wrongAnswers.push($this.data('answer'));
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