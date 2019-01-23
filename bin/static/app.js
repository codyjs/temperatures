$(document).ready(function() {
	$('#postForm').submit(onPost);
	$('#putForm').submit(onPut);
	$('#deleteForm').submit(onDelete);
});

function fetchTemps() {
	$.ajax({
		url: 'http://localhost:8080/api/temperature/',
		type: 'GET',
		success: data => {
			$('#results').text(JSON.stringify(data));
			console.log(data);
		},
		error: (jqxhr, textStatus, error) => {
			$('#results').text(error);
			console.log('Error:', textStatus, error);
		},
	});
}

function onPost(event) {
	event.preventDefault();
	
	$.ajax({
		url: 'http://localhost:8080/api/temperature/',
		type: 'POST',
		data: JSON.stringify({
			value: $('#postValue').val()
		}),
		dataType: 'json',
		contentType: 'application/json',
		success: data => {
			console.log(data);
		},
		error: (jqxhr, textStatus, error) => {
			console.log('Error:', textStatus, error);
		},
	});
	
	$('#postValue').val('');
}

function onPut(event) {
	event.preventDefault();
	
	$.ajax({
		url: 'http://localhost:8080/api/temperature/' + $('#putId').val(),
		type: 'PUT',
		data: JSON.stringify({
			id: $('#putId').val(),
			value: $('#putValue').val()
		}),
		dataType: 'json',
		contentType: 'application/json',
		success: data => {
			console.log(data);
		},
		error: (jqxhr, textStatus, error) => {
			console.log('Error:', textStatus, error);
		},
	});
	
	$('#putId').val('');
	$('#putValue').val('');
}

function onDelete(event) {
	event.preventDefault();
	
	$.ajax({
		url: 'http://localhost:8080/api/temperature/' + $('#deleteId').val(),
		type: 'DELETE',
		success: data => {
			console.log(data);
		},
		error: (jqxhr, textStatus, error) => {
			console.log('Error:', textStatus, error);
		},
	});
	
	$('#deleteId').val('');
}
