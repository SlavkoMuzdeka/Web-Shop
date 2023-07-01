$(document).ready(function() {
	$('#users-link').addClass('active');
	
	var currentUrl = window.location.search;
	var action = new URLSearchParams(currentUrl).get('action');
	var navLinks = $('.nav-link');
	
	if (action === 'users') {
		$('#users-link').addClass('active');
	} else if (action === 'categories') {
		navLinks.removeClass('active');
		$('#categories-link').addClass('active');
	} else if (action === 'statistics') {
		navLinks.removeClass('active');
		$('#statistics-link').addClass('active');
	}
});


$(document).ready(function() {
	$('#superCategoryCheckbox').change(function() {
		if ($(this).is(':checked')) {
			$('#superCategorySelect').show();
			$('#superCategory').attr("required", true);
		} else {
			$('#superCategory').attr("required", false);
			$('#superCategorySelect').hide();
		}
	});

	$('#nextButton').click(function() {
		var numAttributes = parseInt($('#numAttributes').val());
		console.log(numAttributes);
		var attributeFieldsHtml = '';
		for (var i = 1; i <= numAttributes; i++) {
			attributeFieldsHtml += '<div class="form-group">';
			attributeFieldsHtml += '<label for="attribute' + i + '">Attribute ' + i + ':</label>';
			attributeFieldsHtml += '<div class="input-group">';
			attributeFieldsHtml += '<input type="text" class="form-control" id="attribute' + i + '" name="attribute' + i + '"required>';
			attributeFieldsHtml += '<select class="form-control" id="select' + i + '" name="select' + i + '" required>';
			attributeFieldsHtml += '<option value="">--Select Type--</option>'
			attributeFieldsHtml += '<option value="text">Text</option>';
			attributeFieldsHtml += '<option value="number">Number</option>';
			attributeFieldsHtml += '</select>';
			attributeFieldsHtml += '</div>';
			attributeFieldsHtml += '</div>';
		}
		$("#numAttributes").attr("readonly", true);
		$('#attributeFields').html(attributeFieldsHtml);
		$('#nextButton').hide();
		$('#submitButton').show();
	});
	
	$("#categoryModal").on('hidden.bs.modal', function() {
		$('#superCategorySelect').hide();
		$("#numAttributes").attr("readonly", false);
		$('#nextButton').show();
		$('#submitButton').hide();
		$('#superCategory').attr("required", false);
		var form = $(this).find('form')[0];
		form.reset();
	});
});

