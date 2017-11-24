# HoverScrollView
scrollview嵌套viewpager  解决上下左右滑动冲突的一个方案简单demo
</br>
</br>
跟ConstraintLayout实现的效果有点类似，不过ConstraintLayout在appbarlayout里加入过多item之后(超过一屏)，就会出现一些乱跳、卡顿的问题 ， demo比较简单，就三个文件，如果各位大侠喜欢，欢迎star。

## 预览
![预览](https://github.com/tangqianfeng007/HoverScrollView/blob/master/files/hover.gif)

## 关于下拉刷新
DEMO里下拉刷新是用的![SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)(非常舒服的一个下拉刷新库，手动点赞)。
</br>
</br>
经过测试，同样完美支持![Android-PullToRefresh](https://github.com/chrisbanes/Android-PullToRefresh)的下拉刷新，只是有点麻烦的是需要把PullToRefreshScrollView的泛型从Scrollview改成HoverScrollview。
</br>
</br>
其他下拉刷新的兼容性还有待测试。当然如果你没有下拉刷新的需求，直接使用HoverScrollview也是可以的。
