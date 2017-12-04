<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SCSP Test Application Version Check</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

$( document ).ready(function() {
    console.log( "ready!" );
    var datetime = new Date();
    $('#latestVersion').attr('value',datetime.getTime());
});
</script>
</head>
<body>
<input id="latestVersion" name="1"/>
</body>
</html>