$(document).ready(function () {
    
    function setCookie(key, value, expiry) {
        var expires = new Date();
        expires.setTime(expires.getTime() + (expiry * 24 * 60 * 60 * 1000));
        document.cookie = key + '=' + value + ';expires=' + expires.toUTCString();
    }

    function getCookie(key) {
        var keyValue = document.cookie.match('(^|;) ?' + key + '=([^;]*)(;|$)');
        return keyValue ? keyValue[2] : null;
    }
    
    function getParam(name){
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        if (results == null) {
            return '';
         }
         return results[1] || 0;
     };
   
    var urlAll = '/RatesWeb/api/rates';
    var detailUrl = '/RatesWeb/api/rates/'+getParam('name');
    var callAllUrl = urlAll;
    var callDetailUrl = detailUrl;
    if (getCookie('useDB') == 1)
    {
        callurl = urlAll+'?usedb=true';
        callDetailUrl = detailUrl+'?usedb=true';
        $(".switch input").prop("checked", false);
    }
    else
    {
        callurl = urlAll;
        callDetailUrl = detailUrl;
        $(".switch input").prop("checked", true);
    }
    
    
    var table = $('#rates').DataTable({
        ajax: {
            url: callurl,
            dataSrc: ''
        },
        columns: [ 
            { data: 'shortName' },
            { data: 'name' },
            { data: 'country' },
            { data: 'amount' },
            { data: 'valBuy' },
            { data: 'valSell' },
            { data: 'shortName' }
        ],
        "columnDefs": [ {
            "targets": 6,
            "data": "download_link",
            "render": function ( data, type, row, meta ) {
              return '<a href="./detail.html?name='+data+'">Open</a>';
            }
          } ]
    });
    
    $(".switch input").change(function() {
        if(this.checked) {
            setCookie('useDB','0','1');
            table.ajax.url( urlAll ).load();
        }
        else
        {
            setCookie('useDB','1','1');
            table.ajax.url( urlAll+'?usedb=true' ).load();
        }
    });
    
    

        
    
    
    $('#detailRate').DataTable({
        paging: false,
        searching: false, 
        info: false,
        ajax: {
            url: callDetailUrl,
            dataSrc: ''
        },
        columns: [ 
            { data: 'shortName' },
            { data: 'name' },
            { data: 'country' },
            { data: 'amount' },
            { data: 'valBuy' },
            { data: 'valSell' }
        ]
    });
    
    

    
});