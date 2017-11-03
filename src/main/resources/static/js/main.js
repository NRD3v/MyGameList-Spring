$(document).ready(function () {
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    $('#dataTable').DataTable();

    $("#gameNameSearch").on("keyup", function (e) {
        if(e.keyCode == 8 || e.keyCode == 46) {
            $("#results").empty();
        }
        var val = $(this).val();
        if (val.length >= 3) {
           $("#results").empty();
           $.ajax({
               url: "/api/search",
               type: "POST",
               data: { name: val },
               dataType: "json",
               success: function (response) {
                   response.results.forEach(function (result) {
                       $("#results").append(
                           "<div class='col-lg-2'>" +
                               "<img class='mt20 mb10' src='" + result.image.thumb_url + "' height='150' width='125'>" +
                               "<br>" +
                               "<button data-toggle='modal' data-target='#gameAddModal' id='"+result.id+"' class='games btn btn-success btn-xs mb20'>" +
                                   "<span class='glyphicon glyphicon-plus'></span>" +
                               "</button>" +
                           "</div>"
                       );
                   });
                   $(document.body).on('click', 'button', function (element) {
                       $.ajax({
                           url: "/api/search",
                           type: "POST",
                           data: { id: element.currentTarget.id },
                           dataType: "json",
                           success: function (response) {
                               $(".nameField").val(response.results.name);
                               $(".dateField").val(response.results.original_release_date);
                           },
                           error: function (response) {

                           }
                       });
                   });
               },
               error: function (response) {
                   console.log("Error:" + JSON.stringify(response));
               }
           });
        }
    });
})();
