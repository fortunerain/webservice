var main = {
    init: function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        })
    },
    save: function () {
        var data = {
            name: $('#name').val(),
            phone: $('#phone').val(),
            address: $('#address').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/customer',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('등록 되었습니다.');
            window.location.href = '/customer';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};
main.init();