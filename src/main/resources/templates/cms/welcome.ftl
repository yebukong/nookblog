<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>欢迎页面-X-admin2.0</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="./css/font.css">
        <link rel="stylesheet" href="./css/xadmin.css">
    </head>
    <body>
    <div class="x-body layui-anim layui-anim-upbit">
        <blockquote class="layui-elem-quote">
            <a  href="javascript:void(0)" onclick="location.reload()" title="点击刷新"><i>欢迎管理员</i></a>：
            <span class="x-red">小空空</span>！服务器当前时间:${.now?string["yyyy-MM-dd HH:mm:ss "]}</blockquote>
        <fieldset class="layui-elem-field">
            <legend>数据统计</legend>
            <div class="layui-field-box">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <div class="layui-carousel x-admin-carousel x-admin-backlog" lay-anim="" lay-indicator="inside" lay-arrow="none" style="width: 100%; height: 90px;">
                                <div carousel-item="">
                                    <ul class="layui-row layui-col-space10 layui-this">
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>文章数</h3>
                                                <p>
                                                    <cite>???</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>评论数</h3>
                                                <p>
                                                    <cite>???</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>总访问数</h3>
                                                <p>
                                                    <cite>???</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>待定</h3>
                                                <p>
                                                    <cite>???</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>待定</h3>
                                                <p>
                                                    <cite>???</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>待定</h3>
                                                <p>
                                                    <cite>???</cite></p>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>开源地址</legend>
            <div class="layui-field-box">
                <table class="layui-table" lay-skin="line">
                    <tbody>
                    <tr>
                        <td >
                            <a class="x-a" href="/" target="_blank">码云</a>
                        </td>
                    </tr>
                    <tr>
                        <td >
                            <a class="x-a" href="/" target="_blank">GitHub</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>系统信息</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th>CMS版本</th>
                            <td>0.8.0</td></tr>
                        <tr>
                            <th>CMS地址</th>
                            <td>https://www.yebukong.com/MineCMS</td></tr>
                        <tr>
                            <th>操作系统</th>
                            <td>${osName}</td></tr>
                        <tr>
                            <th>容器版本</th>
                            <td>Tomcat 9.0</td></tr>
                        <tr>
                            <th>JAVA信息</th>
                            <td>版本号:${javaVersion} 版本供应商: ${javaVendor} 类版本号: ${javaClassVersion}</td></tr>
                        <tr>
                            <th>JAVA环境相关</th>
                            <td>安装目录:${javaHome} 临时文件路径: ${javaIoTmpdir}</td></tr>
                        <tr>
                            <th>Spring-boot版本</th>
                            <td>${springBootVersion}</td></tr>
                        <tr>
                            <th>监听端口</th>
                            <td>${serverPort}</td></tr>
                        <tr>
                            <th>访问路径</th>
                            <td>${serverServletContextPath}</td></tr>
                        <tr>
                            <th>jar包类型</th>
                            <td>${springProfilesActive}</td></tr>
                        <tr>
                            <th>MYSQL版本</th>
                            <td>5.5.53</td></tr>
                        <tr>
                            <th>Editor.md 版本</th>
                            <td>5.0.18</td></tr>
                        <tr>
                            <th>layui版本</th>
                            <td>2.4.3</td></tr>    
                    </tbody>
                </table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>成员</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th>版权所有</th>
                            <td>yebukong
                                <a href="https://www.yebukong.com/" class='x-a' target="_blank">博客首页</a></td>
                        </tr>
                        <tr>
                            <th>开发者</th>
                            <td>叶不空(yebukong@live.com)</td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <blockquote class="layui-elem-quote layui-quote-nm">CMS前台基于<a href="http://x.xuebingsi.com/" target="_blank">X-admin V2.0</a>，特别感谢<a href="https://www.layui.com/" target="_blank">Layui</a>。</blockquote>
    </div>
    </body>
</html>