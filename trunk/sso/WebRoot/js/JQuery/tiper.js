

function tiper(xoff, yoff, id) {
    this.xOffset = xoff;
    this.yOffset = yoff;
    var style = style
    if (!id) {
        var id = 'tiper';
    }
    if (!xoff) {
        this.xOffset = 20;
    }
    if (!yoff) {
        this.yOffset = 20;
    }
    $(".tiper").hover(

    function (e) {
        var div_id = this.id;
        var content = $('.' + div_id).html();
        this.top = (e.pageY + yOffset);
        this.left = (e.pageX + xOffset);

        $('body').append('<div id="' + id + '">' + content + '</div>');
        $('div#' + id).css("top", this.top + "px").css("left", this.left + "px").fadeIn("slow");

    }, function () {
        $('div#' + id).fadeOut("slow").remove();
    }).mousemove(

    function (e) {
        this.top = (e.pageY + yOffset);
        this.left = (e.pageX + xOffset);

        $('div#' + id).css("top", this.top + "px").css("left", this.left + "px");
    });

};

