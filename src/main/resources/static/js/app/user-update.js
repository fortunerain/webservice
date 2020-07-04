var main = {
    init: function () {
        var _this = this;
        $('#btn-update').on('click', function () {
            _this.update();
        })
    },
    update: function () {
        var data = {
            role: $('#role').val(),
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/user/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('권한이 수정 되었습니다.');
            window.location.href = '/user';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};
main.init();