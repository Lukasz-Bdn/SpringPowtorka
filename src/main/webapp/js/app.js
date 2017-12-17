$(function () {

    var baseUrl = 'http://localhost:8080/SpringPowtorka/';
    var list = $('ul.books');
    var form = $('form.save');

    function renderList() {
      $.getJSON({
          url: baseUrl+'books'
      }).done(function (data) {
          console.log(data);
          list.empty();
          data.forEach(function (book) {
              list.append($('<li>',{
                  'data-id':book.id
              }).append('<span>'+book.title+'</span>')
                .append('<button class="delete">Delete</button>')
                .append('<div>'));
          })
      });
    }

    list.on('click', 'button.delete', function(e) {
      console.log("Weszlo w delete");
      $.ajax({
        url: baseUrl + 'books/remove/' +
          $(e.currentTarget).closest('li').data('id'),
        type: 'DELETE'
      }).done(function() {
        console.log("Weszlo w delete glebiej");
        renderList();
      });
      console.log("Przed stop prop");
      e.stopPrapagation();
    });

    list.on('click','li',function (e) {
        $.getJSON({
            url: baseUrl+'books/'+$(e.currentTarget).data('id')
        }).done(function (book) {
            var html = $('<table>');
            for(var key in book) {
                html.append($('<tr>')
                    .append($('<td>',{text:key}))
                    .append($('<td>',{text:book[key]})))
            }
            $(e.currentTarget).find('div').html(html);
        })
    });

    form.on('submit', function(e) {
      var data = {};
      $(this).find('input[type!=submit]').each(function (index, elem) {
        data[elem.name] = elem.value
      })

      $.post({
        headers: {
        'Content-Type': 'application/json'
        },
        url: baseUrl+'books/add',
        data: JSON.stringify(data)
      }).done(function(res) {
        console.log(res);
        renderList();
      }).fail(function(xhr, status, error) {
        console.log(xhr, status, error);
      })
      this.reset();
      e.preventDefault();
    })

    renderList();
});
