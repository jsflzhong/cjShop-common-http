#2016-7-31
添加了:封装了datagrid需要的数据格式的pojo:EasyUIDataGridResult
添加了:封装了tree需要的数据格式的pojo:EasyUITreeNode
添加了:封装了kindEditor需要的上传图片功能所返回的数据格式的pojo:PictureResult.java

引入了2个工具类:  
	FastDFSClient.java 上传图片到Fastdfs用的. 
	JsonUtils.java  把各种数据与JSON之间转换用的.

去看cjSshop-back工程里的readme.txt文件. 以后全写在那边.

#2016-7-30
此工程,保存的是: 项目中用到的通用的工具类以及通用的pojo. 

打包方式是jar包。

注意,由于其他工程都要引用此工程的依赖.所以,每次在此工程中添加完对象后,记得要run as--maven install到本地仓库中才可以被引用到.