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
                   _.each(response.results, function (result) {
                       console.log(result);
                       $("#results").append(
                           "<div class='col-lg-2'>" +
                               "<img class='mb10' src='" + result.image.thumb_url + "' height='150' width='125'>" +
                               "<br>" +
                               "<button data-toggle='modal' data-target='#gameModal' id='"+result.id+"' class='addGame btn btn-success btn-xs mb20'>" +
                                   "<span class='glyphicon glyphicon-plus'></span>" +
                               "</button>" +
                           "</div>"
                       );
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
        $("#saveButton").val("Editer");
        $("#deleteButton").show();
        $.ajax({
            url: "/api/game/" + element.currentTarget.id + "/show",
            type: "GET",
            dataType: "json",
            success: function (response) {
                console.log(response);
                $(".giantbombIdGameField").val(response.giantbombId);
                $(".nameField").val(response.name);
                $(".releaseDateField").val(response.releaseDate);
            },
            error: function (response) {

            }
        });
    });

    $(document.body).on('click', 'button', function (element) {
        console.log(element);
        $("#myModalLabel").empty().append("Ajouter un jeu :");
        $("#saveButton").val("Ajouter");
        $("#deleteButton").hide();
        $.ajax({
            url: "/api/search",
            type: "POST",
            data: { id: element.currentTarget.id },
            dataType: "json",
            success: function (response) {
                console.log(response);
                $(".giantbombIdGameField").val(response.results.id);
                $(".nameField").val(response.results.name);
                $(".releaseDateField").val(response.results.original_release_date);
                $(".developerField").val(response.results.developers[0].name);
                $(".giantbombIdDeveloperField").val(response.results.developers[0].id);
                // _.each(response.developers, function (developer) {
                //     _.first(developer, function () {
                //         $(".developerField").append(developer.name);
                //         $(".giantbombIdDeveloperField").append(developer.id);
                //     });
                //     _.rest(developer, function () {
                //         $(".developerField").append(", " + developer.name);
                //         $(".giantbombIdDeveloperField").append(", " + developer.id);
                //     });
                // });
            },
            error: function (response) {

            }
        });
    });
})();
