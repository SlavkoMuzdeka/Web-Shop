function modifyTableView() {
	const selectedOption = $('#typeOfShownMess').val();

	$('#tableBodyQuestions tr').each(function() {
		const row = $(this);

		if (selectedOption === "All") {
			row.show();
		} else if (selectedOption === 'Reviewed') {
			if (row.find('input[name="reviewed"]').is(':checked')) {
				row.show();
			} else {
				row.hide();
			}
		} else if (selectedOption === 'Unreviewed') {
			if (!row.find('input[name="reviewed"]').is(':checked')) {
				row.show();
			} else {
				row.hide();
			}
		}
	});
}

function searchContent() {
	var value = $('#searchContent').val().toLowerCase();
	$("#tableBodyQuestions tr").filter(function() {
		$(this).toggle($(this).find('td:nth-child(2)').text().toLowerCase().indexOf(value) > -1)
	});
}

function showModal(title, content, mail, usId, checkboxId) {
	$.ajax({
		type: 'POST',
		url: 'messageReviewed.jsp',
		data: { id: usId },
		success: function() {
			$('#recipient').val(mail);
			$('#subject').val(title);
			$('#message').val(content);
			$('#usId').val(usId);
			$('#myModal').modal('show');
			if (checkboxId) {
				$('#' + checkboxId).prop('checked', true);
			}
		},
		error: function() {
			alert('Error');
		}
	});
}

function sendMail() {
	var from = $('#from').val();
	var to = $('#recipient').val();
	var subject = $('#subject').val();
	var message = $('#message').val();
	var answer = $('#answer').val();

	$.ajax({
		type: 'POST',
		url: 'sendMail.jsp',
		data: {
			'from': from,
			'to': to,
			'subject': subject,
			'message': message,
			'answer': answer
		},
		success: function() {
			alert('Email sent successfully!');
		},
		error: function(error) {
			alert('Error sending email: ' + error);
		}
	});
}