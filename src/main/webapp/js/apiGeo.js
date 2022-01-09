//source : adrien.poupa.fr
$("#cp").autocomplete({
    source: function (request, response) {
        $.ajax({
            url: "https://api-adresse.data.gouv.fr/search/?q=" + $("#cp").val() + "&type=municipality&autocomplete=1",
            data: {q: request.term},
            dataType: "json",
            success: function (data) {
                response($.map(data.features, function (item) {
                    return {
                        label: item.properties.postcode + " – " + item.properties.city,
                        city: item.properties.city,
                        value: item.properties.postcode
                    };
                }));
            }
        });
    },

    select: function (event, ui) {
        $("#ville").val(ui.item.city);
    }
});
$("#ville").autocomplete({
    source: function (request, response) {
        $.ajax({
            url: "https://api-adresse.data.gouv.fr/search/?city=" + $("#ville").val(),
            data: {q: request.term},
            dataType: "json",
            success: function (data) {
                var cities = [];
                response($.map(data.features, function (item) {

                    if ($.inArray(item.properties.postcode, cities) === -1) {
                        cities.push(item.properties.postcode);
                        return {
                            label: item.properties.postcode + " – " + item.properties.city,
                            postcode: item.properties.postcode,
                            value: item.properties.city
                        };
                    }
                }));
            }

        });
    },


    select: function (event, ui) {
        $('#cp').val(ui.item.postcode);
    }
});

$("#adresse").autocomplete({
    source: function (request, response) {
        $.ajax({
            url: "https://api-adresse.data.gouv.fr/search/?postcode="+ $("#cp").val() ,
            data: { q: request.term },
            dataType: "json",
            success: function (data) {
                response($.map(data.features, function (item) {
                    console.log(item);
                    return { label: item.properties.name, value: item.properties.name};
                }));
            }
        });
    }
});


