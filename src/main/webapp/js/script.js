$(function() {
	$("#countItemsForm").submit(function() {
		var countItems = $(":input", this).val();

		if (!validateCountItems(countItems)) {
			alert("Invalid number of items per page!");
			return false;
		}
		
		return true;
	});
});

$(function() {
	$("#pageNumberForm").submit(function() {
		var pageNumber = $(":input", this).val();

		if (!validatePageNumber(pageNumber)) {
			alert("Invalid page number!");
			return false;
		}
		
		return true;
	});
});

function isBlank(value) {
	return (value == null || $.trim(value).length == 0);
}

function validateCountItems(countItems) {
	var regexCountItems = /^[1-9]\d*$/;

	if (isBlank(countItems)) {
		return false;
	}

	if (!regexCountItems.test(countItems)) {
		return false;
	}

	return true;
}

function validatePageNumber(pageNumber) {
	var regexPageNumber = /^[1-9]\d*$/;

	if (isBlank(pageNumber)) {
		return false;
	}

	if (!regexPageNumber.test(pageNumber)) {
		return false;
	}

	return true;
}
