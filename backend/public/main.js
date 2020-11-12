var allData
$(document).ready(function() {

    $.ajax({
        url: "http://localhost:9000/api/v1/estagiario"
    }).then(function(data, status, jqxhr) {
        $('.greeting-id').append(data[0].id);
        $('.greeting-content').append(data[0].nome);
        allData = data;
        console.log(jqxhr);
    });
});