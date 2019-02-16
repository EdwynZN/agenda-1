
$(document).ready()

//CHECK: create a preview of the image.
function readURL(input) {

  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function(e) {
      $('#profileImage').attr('src', e.target.result);
    }
    reader.readAsDataURL(input.files[0]);
  }
}

$("#inputSubir").change(function() {
  readURL(this);
  //console.log("imagen cargada");
});

$(document).on('submit', '#formImage', function( e ) {
        e.preventDefault();
        console.log("imagen");
    /*$.ajax( {
        url: $(this).attr('action'),
        type: 'POST',
        data: new FormData( this ),
        processData: false,
        contentType: false
    }).done(function( data ) {
        //do stuff with the data you got back.
    });*/

});