$(function () {
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    $('#dataTable').DataTable({
        "language": {
            "url": "../locales/datatables-french.json"
        }
    });

    $("#gameNameSearch").on("keyup", function (e) {
        if(e.keyCode == 8 || e.keyCode == 46) {
            $("#results").empty();
        }
        var val = $(this).val();
        if (val.length >= 3) {
            $("#waiting").show();
            $("#results").empty();
            $.ajax({
                url: "/api/search",
                type: "POST",
                data: { name: val },
                dataType: "json",
                success: function (response) {
                   $("#waiting").hide();
                   response.results.forEach(function (result) {
                       $("#results").append(
                           "<div class='col-lg-2'>" +
                               "<img class='mt20 mb10' src='" + result.image.thumb_url + "' height='150' width='125'>" +
                               "<br>" +
                               "<button data-toggle='modal' data-target='#gameModal' id='"+result.id+"' class='games btn btn-success btn-xs mb20'>" +
                                   "<span class='glyphicon glyphicon-plus'></span>" +
                               "</button>" +
                           "</div>"
                       );
                   });
                   $(document.body).on('click', 'button', function (element) {
                       $("#myModalLabel").empty().append("Ajouter un jeu :");
                       $("#saveButton").removeClass("disabled");
                       $("#deleteButton").hide();
                       $.ajax({
                           url: "/api/search",
                           type: "POST",
                           data: { id: element.currentTarget.id },
                           dataType: "json",
                           success: function (response) {
                               $(".giantbombIdField").val(response.results.id);
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

    $(".dataRow").on('click', function (element) {
        $("#myModalLabel").empty().append("Editer un jeu :");
        $("#saveButton").addClass("disabled");
        $("#deleteButton").show();
        $.ajax({
            url: "/api/game/" + element.currentTarget.id + "/show",
            type: "GET",
            dataType: "json",
            success: function (response) {
                console.log(response);
                $(".giantbombIdField").val(response.giantbombId);
                $(".nameField").val(response.name);
                $(".dateField").val(response.releaseDate);
            },
            error: function (response) {

            }
        });
    });
})();
