var main = {
    init: function () {
        var _this = this;
        $('#btn-update').on('click', function () {
            _this.update();
        })

        $('#btn-delete').on('click', function () {
            _this.delete();
        })
    },
    update: function () {
        var data = {
            name: $('#name').val(),
            phone: $('#phone').val(),
            address: $('#address').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/customer/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('수정 되었습니다.');
            window.location.href = '/customer';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    delete: function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/customer/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('삭제 되었습니다.');
            window.location.href = '/customer';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};
main.init();