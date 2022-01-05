
    $(".filterSearch").keyup(function () {

        var val = this.value;
        $("table").find("tr").each(function(index) {
            if (index === 0)
                return;

            var trouver = false;
            $(this).find('td').each(function () {

                trouver = trouver || $(this).text().indexOf(val) !== -1;
            });

            $(this).toggle(trouver);

        });
});
