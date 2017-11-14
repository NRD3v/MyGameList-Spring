$(function () {
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    $('#dataTable').DataTable({
        "aLengthMenu": [ 50, 100, "All" ],
        "language": {
            "url": "js/datatables-french.json"
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
                           "<div class='p10 col-lg-2'>" +
                               "<div class='gameContainer'>" +
                                   "<img data-toggle='modal' data-target='#gameModal' id='"+result.id+"' " +
                                        "class='addGame mt20 mb20' src='" + result.image.thumb_url + "' height='150' width='125'>" +
                               "</div>" +
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
                console.log(response.developerId);
                $(".nameField").val(response.gameName);
                $(".giantbombIdGameField").val(response.gameGbId);
                $(".releaseDateField").val(response.releaseDate);
            },
            error: function (response) {

            }
        });
    });

    $(document.body).on('click', $(".addGame"), function (element) {
        console.log(element);
        $("#myModalLabel").empty().append("Ajouter un jeu :");
        $("#saveButton").val("Ajouter");
        $("#deleteButton").hide();
        $.ajax({
            url: "/api/search",
            type: "POST",
            data: { id: element.target.id },
            dataType: "json",
            success: function (response) {
                var developersName = [];
                var developersGiantbombId = [];
                _.each(response.results.developers, function (developer) {
                    developersName.push(developer.name);
                    developersGiantbombId.push(developer.id);
                });
                $(".giantbombIdGameField").val(response.results.id);
                $(".nameField").val(response.results.name);
                $(".releaseDateField").val(response.results.original_release_date);
                $(".developerField").val(developersName.toString());
                $(".giantbombIdDeveloperField").val(developersGiantbombId.toString());
            },
            error: function (response) {

            }
        });
    });
})();
