$(function(){
    
    // $(".mail-icons-left").change(function() {
    //     if(this.checked) {
            
    //     }
    // });
    $(document).ready(function() {
        $('.check-all').click(function() {
            var checked = this.checked;
            $('input[type="checkbox"]').each(function() {
            this.checked = checked;
        });
        })
    });

    var click = false;
    $(".mail-icons-left i").click(function(){
        click = !click;
        // $(this).removeClass('icon-clicked');
        // $(this).addClass('icon-clicked');
        // return false;
        if(click)
        {
            $(this).css("color", "orange");
            //click = false
        }
        else{
            $(this).css("color", "");
        }
        
        
        //return false;
    });

});
