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