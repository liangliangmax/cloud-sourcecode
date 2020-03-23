var getTeachers = ()=>{

    let p = new Promise(function (resolve, reject) {
        $.ajax({
            url:"/data/teachers",
            type:'get',
            async:true,
            contentType:'application/json;charset-UTF-8',
            dataType:'json',

            success:function (data) {
                if(data){
                    var times = data[0].times;

                    var table  = $("#table");

                    var head = $("<thead ></thead>");

                    var headTr = $("<tr></tr>");
                    headTr.append($("<th style='width: 100px;'></th>"));

                    for(var i = 0;i<times.length;i++){
                        var headTh = $("<th>"+times[i]+"</th>");
                        headTr.append(headTh);
                    }

                    head.append(headTr);

                    var body = $("<tbody></tbody>");

                    for(var j = 0;j<data.length;j++){
                        var bodyTr = $("<tr></tr>");

                        var bodyTdName = $("<td>"+data[j].teacherName+"</td>")

                        bodyTr.append(bodyTdName);

                        for(var k = 0;k<times.length;k++){
                            var bodyTdTime = $('<td id = "'+data[j].teacherId+'_'+times[k].replace(/-/g,'').replace(/:/g,'').replace(' ','')+'" onclick="lock(\''+data[j].teacherId+'\',\''+times[k]+ '\')"></td>');

                            bodyTr.append(bodyTdTime);
                        }

                        body.append(bodyTr)


                    }

                    table.append(head);

                    table.append(body)

                    resolve();
                }
            },
            error: function(){

            }
        });
    });

    return p;

}





var socket;

$(function () {
    connect2Server()
    getTeachers()
        .then(function () {
            refreshLock()
        })

});


function connect2Server() {
    socket = io.connect("http://localhost:9999",{'reconnect':true});

    socket.on("lock success",(data)=>{

        data = JSON.parse(data);

        console.log(data.lockKey)

        console.log($("#"+data.lockKey))

        $("#"+data.lockKey).css("background-color","red");

    });

    socket.on("lock release",(data)=>{

        data = JSON.parse(data);

        $("#"+data.lockKey).css("background-color","");

    });


    socket.on('reconnect', () => {
        log('you have been reconnected');
        refreshLock()
    });

}

function lock(teacherId,time) {
    socket.emit('try locking',{
        teacherId:teacherId,
        time:time
    });
}


//刷新锁的状态
function refreshLock() {
    $.ajax({
        url:"/data/refreshLock",
        type:'get',
        async:true,
        contentType:'application/json;charset-UTF-8',
        dataType:'json',

        success:function (data) {
            if(data && data.length !=0){
                for(var index in data){
                    $("#"+data[index]).css("background-color","red");
                }
            }

        },
        error: function(){

        }
    });
}