//根据笔记ID加载笔记列表
function loadBookNotes() {
    //清除笔记本列表的选中样式
    $("#book_ul a").removeClass("checked");
    //添加笔记本列表的选中样式
    $(this).find("a").addClass("checked");
    //1.获取参数
    var bookId = $(this).data("bookId");
    //2.参数格式校验
    //3.发送Ajax
    $.ajax({
        url:base_path+"/note/loadNotes.do",
        type:"post",
        data:{"bookId":bookId},
        dataType:"json",
        success:function (result) {
            if(result.status == 0){
                //清空原有笔记列表
                $("#note_ul li").remove();
            }
            //获取服务器返回的笔记集合信息
            var notes = result.data;
            //循环生成noteli元素
            for(var i = 0; i < notes.length;i++){
                //获取笔记标题和笔记ID
                var noteId = notes[i].cn_note_id;
                var noteTitle = notes[i].cn_note_title;
                //创建一个笔记li元素
                createNoteLi(noteId,noteTitle);
                if(notes[i].cn_note_type_id == '2'){
                    //此时这个笔记已经被分享，应该加上分享图标
                    var img = '<i class="fa fa-sitemap"></i>';
                    $("#note_ul li:last").find(".btn_slide_down").before(img);
                }
            }
        },
        error:function () {
            alert("查询笔记异常");
        }
    });
}
//创建笔记li
function createNoteLi(noteId,noteTitle) {
    var sli = '';
    sli +=' <li class="online"> ';
    sli +=' <a> ';
    sli +=' <i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button> ';
    sli +=' </a> ';
    sli +=' <div class="note_menu" tabindex="-1"> ';
    sli +='<dl>';
    sli +=' <dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
    sli +=' <dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
    sli +='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
    sli +='</dl>';
    sli +='</div>';
    sli +='</li>';
    //将noteId绑定到li元素上
    var $li = $(sli);
    $li.data("noteId",noteId);
    //将li元素追加到note_ul下
    $("#note_ul").append($li);
}
//根据笔记ID加载笔记信息
function loadNote() {
    $("#note_ul a").removeClass("checked");
    $(this).find("a").addClass("checked");
    //1.获取请求参数
    var noteId = $(this).data("noteId");
    //2.请求参数格式校验
    //3.发送Ajax
    $.ajax({
        url:base_path+"/note/loadNote.do",
        type:"post",
        data:{"noteId":noteId},
        dataType:"json",
        success:function (result) {
            if(result.status == 0){
                //获取笔记标题
                var title = result.data.cn_note_title;
                //获取笔记内容
                var body = result.data.cn_note_body;
                //设置到编辑区
                $("#input_note_title").val(title);
                //使用富文本编辑器设置内容
                um.setContent(body);
            }
        },
        error:function () {
            alert("显示笔记异常");
        }
    });

}
//保存笔记
function updateNote() {
    //1.获取请求参数
    var title = $("#input_note_title").val().trim();
    var body = um.getContent();
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    //2.参数格式校验
        if($li.length == 0){
            alert("请选择要保存的笔记");
        }else if(title == ""){
            $("#note_title_span").html("<font color='red'>标题不能为空</font>")
        }else{
            //3.发送Ajax
            $.ajax({
                url:base_path+"/note/update.do",
                type:"post",
                data:{"title":title,"noteId":noteId,"body":body},
                dataType:"json",
                success:function (result) {
                    //更新笔记列表中的li元素
                    var sli = '';
                    sli +=' <i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button> ';
                    //将选中li元素的a内容替换成sli
                    $li.find("a").html(sli);
                    //提示保存成功信息
                    alert(result.msg);
                },
                error:function () {
                    alert("更新笔记异常")
                }
            });
        }

}

/**
 * 创建笔记
 */
function addNote() {
    //1.获取参数
    var userId = getCookie("uid");
    var bookId = $("#book_ul a.checked").parent().data("bookId");
    var noteTitle = $("#input_note").val().trim();
    $("#note_span").html = "";
    //2.参数格式校验
    var ok = true;
    if(userId == null){
        ok = false;
        window.location.href = "log_in.html";
    }
    if(noteTitle == ""){
        ok = false;
        $("#note_span").html("笔记名不能为空");
    }
    if(ok){
        //3.发送Ajax
        $.ajax({
            url:base_path+"/note/add.do",
            type:"post",
            data:{"userId":userId,"bookId":bookId,"noteTitle":noteTitle},
            dataType:"json",
            success:function (result) {
                //1.关闭对话框
                closeAlertWindow();
                if(result.status == 0){
                    //2.添加新的noteLi
                    var noteId = result.data.cn_note_id;
                    var noteTitle = result.data.cn_note_title;
                    createNoteLi(noteId,noteTitle);
                }
                //3.弹出提示信息
                alert(result.msg)
            },
            error:function () {
                alert("创建笔记异常");
            }
        });
    }

}

/**
 * 笔记菜单的显示
 */
function showMenu() {
    hideMenu();
    //this:指的是下拉菜单按钮
    var $menu = $(this).parent().next()
    $menu.slideDown(1000);
    $("#note_ul a").removeClass("checked");
    $(this).parent().addClass("checked");
    //jQuery解决冒泡事件
    return false;
}

/**
 * 隐藏笔记菜单
 */
function hideMenu() {
    $("#note_ul div").hide();
}

/**
 * 删除笔记操作
 */
function deleteNote() {
    //1.获取请求参数
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    //2.参数格式校验
    //3.发送Ajax
    $.ajax({
        url:base_path+"/note/delete.do",
        type:"post",
        data:{"noteId":noteId},
        dataType:"json",
        success:function (result) {
            //1.关闭删除对话框
            closeAlertWindow();
            if(result.status == 0){
                //2.删除对应的li
                $li.remove();
            }
            //提示信息
            alert(result.msg);
        },
        error:function () {
            alert("删除笔记异常")
        }
    });
}

/**
 * 移动笔记
 */
function moveNote() {
    //1.获取参数
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    //获取移动到的笔记ID
    var bookId = $("#moveSelect").val();
    //2.参数格式校验
    //3.发送Ajax
    $.ajax({
        url:base_path+"/note/move.do",
        type:"post",
        data:{"noteId":noteId,"bookId":bookId},
        dataType:"json",
        success:function (result) {
            if(result.status == 0){
                //1.删除选中的笔记
                $li.remove();
            }
            //弹窗提示信息
            alert(result.msg);
        },
        error:function () {
            alert("移动笔记异常");
        }
    });
}

/**
 * 分享笔记
 */
function shareNote() {
    //1.获取请求参数
    //获取选中的笔记ID
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    //2.参数格式校验
    //3.发送Ajax
    $.ajax({
        url:base_path+"/note/share.do",
        type:"post",
        data:{"noteId":noteId},
        dataType:"json",
        success:function (result) {
            if(result.status == 0){
                //添加分享标记
                var img = '<i class="fa fa-sitemap"></i>';
                $li.find(".btn_slide_down").before(img);
            }
            alert(result.msg);
        },
        error:function () {
            alert("分享笔记异常");
        }
    });
}