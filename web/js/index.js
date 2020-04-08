var url_load_novel_list = "/healthylife/list";
var current_section = "B";
var current_page_no = 1;
var is_reload = false;

/**
 * 开始加载小说列表
 * @param that
 */
function loadbooks(that) {
    is_reload = false;
    $("#paragraph_load_more").text("加载更多");
    var e = window.event;
    current_page_no = 1;
    $("p.head-tab-paragraph").css("color","black");
    if (e.target.id == undefined) {
        $("p#A").css("color","#f0a54d");
        current_section = "中国文学";
    } else {
        $("p#" + e.target.id).css("color","#f0a54d");
        current_section = e.target.textContent;
    }
    $(".body-container").remove();
    onloadbooks();
}

/**
 * 加载小说列表
 */
function onloadbooks() {
    var param = "section=" + current_section + "&page_no=" + current_page_no;
    if ($("#paragraph_load_more").text() == '加载完毕') {

    }else {
        httpRequest("GET",url_load_novel_list,param,function (data) {
            var str = "";
            for (var i = 0;i < data.length;i++) {
                str = str +
                    "<div class='body-container'" + " id='" + data[i].id + "' onclick='openNovelDetail(this)'>" +
                        "<div class='container_img_cover'>" +
                            "<img class='img_cover' src='data:image/jpeg;base64," + data[i].cover + "' /> " +
                        "</div>" +
                        "<div class='div_book_info'>" +
                            "<p class='p-body-bookname'>" + data[i].name + "</p>" +
                        "</div>" +
                    "</div>"
            }
            if (data.length>=20) {
                current_page_no = current_page_no + 1;
            }else {
                $("#paragraph_load_more").text("加载完毕");
            }
            $(".body-parent-container").append(str);
        },function (data) {

        },function (data) {
            $("#paragraph_load_more").text("加载完毕");
        });
    }

}

/**
 * 跳转到章节详情页面
 * @param that
 */
function openNovelDetail(that) {
    window.open("chapter.html?healthylife=" + $(that).attr("id"));
}

/**
 * 跳转到章节详情页面
 * @param that
 */
function openSearch(that) {
    window.open("search.html");
}

/**
 *
 * 章节详情页面
 *
 */

var url_load_chapter_detail = "/healthylife/chapter/detail";
var url_load_chapter_list = "/healthylife/chapter/list";
var current_param = "";
var current_chapter_data;
var current_chapter_page_no = 1;

var div = "<div class=\"div\"></div>";
var is_show_alert = false;

var body_parent_container = "<div class=\"body-parent-container-chapter\"></div>";

function onRecieveChapterLoad() {
    var param = window.location.search;
    current_param = param.substr(1,param.length);
    onload(current_param+"&chapter=" + current_chapter_page_no);
}

/**
 * 加载章节详情
 * @param param
 */
function onload(param) {
    httpRequest("GET",url_load_chapter_detail,param,function (data) {
        $(".body-parent-container-chapter").remove();
        console.log(JSON.stringify(data));
        $("body").prepend(body_parent_container);
        window.scroll(0,0);
        $(".body-parent-container-chapter").prepend(data[0].chapter_content);
        $(".body-parent-container-chapter").css("margin-bottom","90px");
        $("title").text(data[0].chapter_name);
        $("#chapter_name").text("当前阅读：" + data[0].chapter_name);
        $("#chapter_name").css("font-weight","400");
        $("#chapter_name").css("font-size","16px");
        $("#chapter_name").css("margin-top","12px");

        current_chapter_data = data;
        current_chapter_page_no = data[0].chapter_id;
        if (data[0].is_first_chapter == "yes") {
            $("#foot-previous-page").css("color","#aaaaaa");
            $("#foot-previous-page").children().text("已是第一章")
        }else {
            $("#foot-previous-page").css("color","#000000");
            $("#foot-previous-page").children().text("上一章")
        }
        if (data[0].is_last_chapter == "yes") {
            $("#foot-next-page").css("color","#aaaaaa");
            $("#foot-next-page").children().text("已是最后一章")
        }else {
            $("#foot-next-page").css("color","#000000");
            $("#foot-next-page").children().text("下一章")
        }

        $(".body-parent-container-chapter-list").css("display","none");
        $(".body-parent-container-chapter").css("display","");
        $("#paragraph-show-index").text("查看目录");
        $(".body-parent-container-chapter-list").children().remove();
        is_show_alert = false;
    },function (data) {

    },function (data) {

    });
}

/**
 * 跳转到其他章节
 * @param that
 */
function jumptochapter(that) {
    var e = window.event;
    if ($(that).attr("id") == "foot-previous-page") {
        if (current_chapter_data[0].is_first_chapter== "yes") {

        } else {
            current_chapter_page_no = current_chapter_page_no-1;
            onRecieveChapterLoad();
        }
    } else if ($(that).attr("id") == "foot-next-page") {
        if (current_chapter_data[0].is_last_chapter== "yes") {

        } else {
            current_chapter_page_no = parseInt(current_chapter_page_no)+1;
            onRecieveChapterLoad();
        }

    } else {
        current_chapter_page_no = parseInt($(that).attr("id"));
        onRecieveChapterLoad();
    }
}

/**
 * 显示小说目录
 */

var saved_scrollTop = 0;
var data_chapter;
function showchapteralert() {
    if (is_show_alert == false) {//未显示目录时
        saved_scrollTop = $(document).scrollTop();
        $(".body-parent-container-chapter-list").css("display","");
        $(".body-parent-container-chapter").css("display","none");
        $("#paragraph-show-index").text("返回");
        if (data_chapter == null) {
            onLoadChapterList();
        } else {
            for (var i = 0;i<data_chapter.length;i++) {
                var indent = data_chapter[i].chapter_grade-1;
                var text_color = "#000";
                if (indent != 0){
                    text_color = "#333";
                }
                $(".body-parent-container-chapter-list").append("<div class=\"div\" id=\"" + data_chapter[i].id +"\"" +
                    " onclick=\"jumptochapter(this)\"><p" +
                    " class=\"p-chapter-list\" style='margin-left: " + indent + "em;color: " + text_color +"'" + ">" + data_chapter[i].chapter_name + "</p></div>")
            }
            $(".body-parent-container-chapter-list").css("display","");
            var current_scrollTop = 0;
            var page_no = current_chapter_page_no;
            $("div#" + page_no).css("font-weight:bold");
            current_scrollTop = $("div#" + page_no).last().offset().top-46;
            window.scrollTo(0,current_scrollTop);
        }
    }else {//正在显示目录时
        $(".body-parent-container-chapter-list").css("display","none");
        $(".body-parent-container-chapter").css("display","");
        $("#paragraph-show-index").text("查看目录");
        $(".body-parent-container-chapter-list").children().remove();
        window.scroll(0,saved_scrollTop);
    }
    is_show_alert = !is_show_alert;
}

/**
 * 加载显示目录
 */
function onLoadChapterList() {
    httpRequest("GET",url_load_chapter_list,current_param, function (data) {
        data_chapter = data;
        for (var i = 0;i<data.length;i++) {
            var indent = data[i].chapter_grade-1;
            var text_color = "#000";
            if (indent != 0){
                text_color = "#333";
            }
            $(".body-parent-container-chapter-list").append("<div class=\"div\" id=\"" + data[i].id +"\"" +
                " onclick=\"jumptochapter(this)\"><p" +
                " class=\"p-chapter-list\" style='margin-left: " + indent + "em;color: " + text_color +"'" + ">" + data[i].chapter_name + "</p></div>")
        }
        $(".body-parent-container-chapter-list").css("display","");
        var current_scrollTop = 0;
        var page_no = current_chapter_page_no;
        console.log(page_no);
        $("div#" + page_no).css("font-weight:bold");
        current_scrollTop = $("div#" + page_no).last().offset().top-56;//定位到在读章节
        window.scrollTo(0,current_scrollTop);
    },function (data) {

    },function (data) {

    });
}

//搜索
var url_search = "/search";
function onLoadSearchResult() {
    var keyword = $("#search").val();
    console.log("keyword:" + keyword);
    if (keyword == "") {

    }else if (keyword.search("'") != -1) {

    }else if (keyword.search("--") != -1) {

    }else if (keyword.search("#") != -1) {

    } else {
        var param = "keyword=" + keyword;
        httpRequest("GET",url_search,param,function (data) {
            $(".body-container").remove();
            var str = "";
            for (var i = 0;i < data.length;i++) {
                if (data[i].type != "author") {
                    str = str +
                        "<div class='body-container' id='" + data[i].id + "' onclick='openNovelDetail(this)'>" +
                            "<div class='container_img_cover'>" +
                                "<img class='img_cover' src='data:image/jpeg;base64," + data[i].cover + "' >" +
                            "</div>" +
                            "<div class='div_book_info'>" +
                                "<p class=\"p-body-bookname\">" + data[i].name + "</p>" +
                            "</div>" +
                        "</div>";
                }
            }
            $(".body-parent-container").append(str);
        },function (data) {

        },function (data) {

        });
    }
}


/**
 * 公共模块
 * @param method
 * @param url
 * @param param
 * @param successArr
 * @param successDic
 * @param error
 */
function httpRequest(method,url,param,successArr,successDic,error) {
    $.ajax({
        type: method,
        contentType: 'application/json;charset=UTF-8',
        url: url + "?" + param,
        success: function (result) {
            var data = JSON.parse(result);
            if (result.startsWith("{", 0)) {
                successDic(data);
            } else if (result.startsWith("[", 0)) {
                successArr(data);
            } else {
                error(result);
            }

        },
        error: function (e) {
        }
    })
}