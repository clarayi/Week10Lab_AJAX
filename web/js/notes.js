$(document).ready(function() {
   $("#noteContent").blur(function() {
       $.get("notes?" + $("#hiddenID").val(), function(response) {
           document.getElementById("noteContent").innerHTML = response;
       });
   });
});

