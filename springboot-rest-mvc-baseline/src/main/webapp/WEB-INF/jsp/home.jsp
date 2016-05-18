<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body onload="getUserInfo()">

	Welcome home!
	<br/><div id="section"></div>

</body>

<script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
<script>
	function getUserInfo(){
		$.ajax({
	        url: "rest/common/basicuserinfo",
	        type: "GET",
	        data: null ,
	        success: function (response) {
	           document.getElementById("section").innerHTML = JSON.stringify(response);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        	document.getElementById("section").innerHTML = "Error: "+errorThrown;
	        }


	    });
	}
</script>
</html>