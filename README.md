## 软件定义

软件性质：基于B/S架构的作品展示小程序

基本功能：游客及登录用户可以浏览主页板块及各版块帖子概况，查看帖子详细内容；登录用户可以评论帖子、下载附件及关注作者，关注后可以在关注页收到动态。

技术栈：基于B/S（Browser，Server）Web项目，JavaEE

1） SpringBoot

Spring：解耦，IoC, AOP

SpringMVC：处理用户的请求，代替Servlet。

MyBatis：访问数据库，持久层框架，封装JDBC的底层操作，只需要配置SQL语句，支持动态SQL，懒加载，缓存。

2） WXML、WCSS、JavaScript。

3） MySQL

## 需求分析

### 功能分析

#### 角色

<table>
  <tr>
    <th>角色</th>
    <th>功能名</th>
    <th>功能描述</th>
    <th>详情</th>
  </tr>
  <tr>
    <th rowspan="6">游客</th>
    <th rowspan="3">主页</th>
    <th colspan="2">打开小程序后默认的显示页</th>
  </tr>
  <tr>
    <th>轮播图</th>
    <th>热门推荐帖子、广告位等</th>
  </tr>
  <tr>
    <th>板块</th>
    <th>打开小程序后显示所有板块，点击某个板块后显示当前板块所有帖子（分页查询）</th>
  </tr>
  <tr>
    <th>动态</th>
    <th>游客不可用</th>
    <th>点击后跳转到 我的-登录 页面</th>
  </tr>
  <tr>
    <th>消息</th>
    <th>游客不可用</th>
    <th>点击后跳转到 我的-登录 页面</th>
  </tr>
  <tr>
    <th>我的</th>
    <th>登录页面</th>
    <th>点击后跳转到 我的-登录 页面</th>
  </tr>


<table>
  <tr>
    <th>角色</th>
    <th>功能名</th>
    <th>功能描述</th>
    <th>详情</th>
  </tr>
  <tr>
    <th rowspan="8">登录用户</th>
    <th rowspan="3">主页</th>
    <th colspan="2">打开小程序后默认的显示页</th>
  </tr>
  <tr>
    <th>轮播图</th>
    <th>推荐帖子广告位等</th>
  </tr>
  <tr>
    <th>板块</th>
    <th>打开小程序后显示所有板块</th>
  </tr>
  <tr>
    <th>动态</th>
    <th>查看关注用户的动态</th>
    <th>点击显示所有关注用户发的帖子，按时间倒序</th>
  </tr>
  <tr>
    <th>消息</th>
    <th>查看收到的评论消息</th>
    <th>显示收到的所有评论及帖子信息，按时间倒序，可快捷回复</th>
  </tr>
  <tr>
    <th rowspan="3">我的</th>
    <th colspan="2">显示微信登录按钮，登录后显示个人信息，收藏及关注用户</th>
  </tr>
  <tr>
    <th>我的收藏</th>
    <th>显示用户收藏的帖子</th>
  </tr>
  <tr>
    <th>我的关注</th>
    <th>显示用户关注的用户</th>
  </tr>
