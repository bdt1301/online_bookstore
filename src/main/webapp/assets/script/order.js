function incrementQuantity(index) {
	var quantityField = document.getElementById('quantity' + index);
	var quantity = parseInt(quantityField.value);
	quantityField.value = quantity + 1;
}

function decrementQuantity(index) {
	var quantityField = document.getElementById('quantity' + index);
	var quantity = parseInt(quantityField.value);
	if (quantity > 0) {
		quantityField.value = quantity - 1;
	}
}