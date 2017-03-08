# MyDemo-OkhttpUtilsPackaing
对okhttputils的封装类，okhttputils见：https://github.com/wangxiyue16/OkhttpUtils-packaing.

这个Demo是对okhttpUtils-packaing库的演示,里面使用了普通的解析以及使用Gson解析两种方式,Gson解析需要添加工具里面Plugins里面的GsonFormat插件(这个插件方便你写gson需要的bean类,不添加也可以,但是bean需要自己写),该Demo让你更直观了解okhttputils-packaing这个库,使用起来更方便

## 用法

* 第一种使用普通解析	
你可以通过	`onNetworkResponse()`方法里面的`nr.getText()`得到String类型的网络数据,然后手动解析json数据到bean对象,并返回该对象,
`onNetworkSuccess()`方法里把拿到的result强转成你传递过来的bean类型即可
  
* 第二种使用Gson解析
同样,你可以通过	`onNetworkResponse()`方法里面的`nr.getText()`得到String类型的网络数据,通过Gson的`gson.fromJson()`方法得到对应的bean对象,并返回该对象,
`onNetworkSuccess()`方法里把拿到的result强转成你传递过来的bean类型即可
