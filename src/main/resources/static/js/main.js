$(function () {
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

   $("#gameNameSearch").on("keyup", function () {
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
                               "<img class='mt20 mb10' style='border-radius:10px;' src='" + result.image.thumb_url + "' height='150' width='150'>" +
                               "<span data-toggle='modal' data-target='#gameAddModal'" +
                                   " id='gameAdd"+result.id+"' class='btn btn-success btn-sm mb20'>" +
                                   "<span class='glyphicon glyphicon-plus-sign'></span> Ajouter" +
                               "</span>" +
                           "</div>"
                       );
                       $("#gameAdd"+result.id).on("click", function () {
                           $("#fieldName").value = result.name
                       })
                   });
               },
               error: function (response) {
                   console.log("Error:" + JSON.stringify(response));
               }
               //.done(function(data) { };
           });
       }
   });
})();
