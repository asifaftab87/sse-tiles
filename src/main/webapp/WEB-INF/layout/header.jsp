<h2>Header.jsp</h2>

<script>
	const url = '<%=request.getAttribute("serviceUrl")%>';
	console.log('hello world : ' + url);
	if (url === null || url === 'null') {
		console.log('url is null');
	} else {
		if (window.EventSource == null) {
			alert('The browser does not support Server-Sent Events');
		} else {
			var eventSource = new EventSource(url);
			eventSource.onopen = function() {
				console.log('connection is established');
			};
			eventSource.onerror = function(error) {
				console.log('connection state: ' + eventSource.readyState
						+ ', error: ' + event);
			};
			eventSource.onmessage = function(event) {
				console.log('id: ' + event.lastEventId + ', data: '
						+ event.data);
				if (event.data.endsWith('.')) {
					alert("Data uploaded successfully");
					eventSource.close();
					console.log('connection is closed');
				}
			};
		}
	}
</script>