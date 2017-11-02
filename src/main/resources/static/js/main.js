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
                               "<button data-toggle='modal' id='"+result.id+"' class='games btn btn-success btn-sm mb20' " +
                           // "data-target='#gameAddModal' " +
                               // "onclick='$(\".nameField\").val() == result.name; $(\".developerField\").val() == 2; console.log($(\".nameField\").val()); console.log($(\".developerField\").val())'" +
                           "> " +
                                   "<span class='glyphicon glyphicon-plus-sign'></span> Ajouter" +
                               "</button>" +
                           "</div>"
                       );
                   });
                   var elements = $(".games").map(function() {
                       return this;
                   }).get();
                   $.each(elements, function (index, element) {
                       console.log($.type(element));
                       element.bind("click", function () {
                           //noinspection JSAnnotator
                           $(".nameField").val() = result.name;
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
