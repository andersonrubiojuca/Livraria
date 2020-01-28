function dinheiro(i) {
	var v = i.value.replace(/\D/g,'');
	v = (v/100).toFixed(2) + '';
	v = v.replace(".", ",");
	i.value = v;
}

$(document).ready(function(){
	$("#sumario").change(function(){
		lerInput(this);
		
	});
});

function lerInput(input){
		var reader = new FileReader();
		
		reader.onload = function(e){
			$('#img-base').attr('src',e.target.result);
			$('#legenda').text('Nova Imagem');
		}
		reader.readAsDataURL(input.files[0]);
}