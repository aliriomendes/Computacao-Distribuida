<html xmlns="http://www.w3.org/1999/xhtml\" xml:lang=\"pt\">
<head>
    <META http-equiv="Pragma" content="no-cache">
    <META http-equiv="Cache-Control" content="no-cache,no-store">
    <META HTTP-EQUIV="Content-Script-Type" CONTENT="text/javascript">
    <META charset="utf-8">
    <title>Alpha Team</title>
    <style type="text/css" title="jetty">
        @import url(css/jetty.css);
        @import url(css/menu_styles.css);
    </style>
    <script language="javascript" type="text/javascript">
        var wsUri = "ws://localhost:8181/echo";
        var output, input, send;

        function init() {
            output = document.getElementById("output");
            input = document.getElementById("input");
            testWebSocket();
        }

        function testWebSocket() {
            websocket = new WebSocket(wsUri, "echo");
            websocket.onopen = function(evt) { onOpen(evt) };
            websocket.onclose = function(evt) { onClose(evt) };
            websocket.onmessage = function(evt) { onMessage(evt) };
            websocket.onerror = function(evt) { onError(evt) };
        }

        function onOpen(evt) {
            writeToScreen("CONNECTED");
        }

        function onClose(evt) {
            writeToScreen("DISCONNECTED");
        }

        function onMessage(evt) {
            writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data+'<\/span>');
        }

        function onError(evt) {
            writeToScreen('<span style="color: red;">ERROR:<\/span> ' + evt.data);
        }

        function doSend() {
            writeToScreen('<span style="color: red;">SENDING:<\/span> ' + input.value);
            websocket.send(input.value);
        }

        function writeToScreen(message) {
            output.innerHTML = message
        }

        window.addEventListener("load", init, false);
    </script>
</head>
<body>

<div id="header">

</div>

<div id="content">
    <div id='cssmenu'>
        <ul>
            <li class='active'><a href='#'>Home</a></li>
            <li><a href="fileUpload.html/">Inserir Recurso</a></li>
            <li><a href="resourceList.jsp">Listar Recursos</a></li>
            <li><a href="listarNos.jsp">Listar nós</a></li>
        </ul>
    </div>
    <h1>Welcome to Alpha Team Cloud Solutions</h1>

    <img class="home_background" src="images/home_background.png">

    <h2>WebSocket Test</h2>
    <p>Click the send button to transmit the text to the WebSocket server. The server will echo back the text.</p>
    <textarea id="input">Sample Text</textarea>
    <button onclick="doSend()">Send</button>
    <div id="output"></div>
</div>
</body>
</html>
