function dinheiro(i) {
	var v = i.value.replace(/\D/g,'');
	v = (v/100).toFixed(2) + '';
	v = v.replace(".", ",");
	i.value = v;
}

$(function () {
    $('#fupload').change(function() {
         $('.nomeImagem').html('<b>Arquivo Selecionado:</b>' + $(this).val());
    });
});