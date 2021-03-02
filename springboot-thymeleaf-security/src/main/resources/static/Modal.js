<script>
$(function(){
    var text = 'Некоторый текст, который необходимо вставить в модальное окно';
    $('#myModalEditBtn').click(function(e){
        // отменяем стандартное поведение браузера при нажатии на ссылку
        e.preventDefault();
        // передаем в контент элемента с id="myP" содержимое переменной text
        $('#myP').text(user.id);
        // открываем модальное окно
        $('#myModalEdit').modal('show');
    });
});
</script>