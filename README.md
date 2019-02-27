
# nookblog

nookblog 是一款基于SpringBoot开发的博客发布系统。

## 特性

- 界面简洁
- Markdown文章发布【编辑器基于[Editor.md](https://gitee.com/pandao/editor.md)】
- gitalk评论支持
- 文章支持静态化

[博客展示](https://yebukong.com/)

## 介绍

 nookblog 基于springboot开发，数据库采用mysql，持久层框架使用[MyBatis-Plus](https://gitee.com/baomidou/mybatis-plus)，文章模板引擎使用FreeMarker，CMS界面基于[X-admin V2.0](https://gitee.com/daniuit/X-admin/),博客界面来源于互联网，特别感谢[layui](https://gitee.com/sentsin/layui)，[Editor.md](https://gitee.com/pandao/editor.md)。
 
## 界面预览

![博客页](https://images.gitee.com/uploads/images/2019/0222/174514_a6b111a3_884684.png "blog.png")

![cms页](https://images.gitee.com/uploads/images/2019/0222/174607_529f0b4f_884684.png "cms.png")

![新增文章](https://images.gitee.com/uploads/images/2019/0222/174950_b7f8df16_884684.png "add.png")

## 暂定后续 

 - 考虑放弃文章保存到数据库的思路，改为放入单独的.md文件中，数据库配置将移入.json文件保存
 - 多md编辑器支持，支持插入音乐，视频
 - 多博客主题支持 
 - 自建评论模块
 - cms采用前后端分离模式
 - 博客SEO优化

## 开源协议

[MIT](LICENSE)

 :alien: 
