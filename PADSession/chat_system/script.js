$(document).ready(function() {
    $('#chat-form').on('submit', function(e) {
        e.preventDefault();
        
        var message = $('#chat-box').val();
        
        var html = '<li>' +
            '<div class="row">' +
                    '<div class="col s12">' +
                        '<div class="card-panel">' +
                            message
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</li>';
        
        $('#messages').append(html);
        
        $('#chat-box').val('');
    });
});