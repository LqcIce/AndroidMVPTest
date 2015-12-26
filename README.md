# AndroidMVPTest
一个依据MVP模式写的Android例子

参考文章：http://blog.csdn.net/vector_yi/article/details/24719873

通过实践，发现采用MVP模式，可以实现任务分离，多人协作。一人开发Model负责数据存取，一人负责VIew即Activity与显示界面，
一人根据另外两人提供的接口和需求，负责Presenter编写，实现主要的业务逻辑，联通这两部分

而且便于技术升级，比如目前数据存储于数据库，如果以后改为存储于Map表中等，只需修改Model中的逻辑即可，Presenter与View并不会受到影响
