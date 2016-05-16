## JavaBean 规范 ##

JavaBean 是 Java 开发语言中一个可以重复使用、基于“一次编写，到处运行”理念的Java组件。
特点是序列化机制，有无参构造器，每个属性都被private修饰，访问属性时需使用public修饰的getter和setter方法。


### 具体规范

1、JavaBean类必须是一个公共类（public修饰）。
2、JavaBean类必须有一个空的构造函数。
3、JavaBean类的实例变量必须是private的。
4、属性值应该通过一组存取方法（getXxx 和 setXxx）来访问：对于每个属性，应该有匹配的公用 getter和setter方法。


### 命名规则
                  
1、对于一个名称为 xxx的属性，通常要写两个方法：getXxx()和 setXxx()。
注意，把"get"或"set"后面的第一个字母换成小写就能得到属性的名称。"get"方法返回的类型要与"set"方法里参数的类型相同。
2、对于布尔型属性，可以使用以上"get"和"set"的方式，也可以把"get"替换成"is"。
3、Bean的普通方法不必遵循以上的命名规则，不过它们必须是public的。
4、对于事件，所有事件类的名字必须以Event结尾，且必须继承自EventObject类。
比如bean会生成XxxEvent类型的事件，那么监听器接口必须命名为XxxListener，同时，添加和删除监听器的方法必须命名为：
addXxxListener(XxxListener)和removeXxxListener(XxxListener)。
倘若不这样写，也会通过编译，但bean在使用的时候可能会出现故障，因为自省机制无法识别出你的事件。


### 将JavaBean打包
                      
在把JavaBean加入到某个支持Bean的可视化构建工具之前，必须把它打包进一个标准的Bean容器，也就是一个JAR文件，
它里面包含了Bean的所有.class文件以及能表明"这是一个Bean"的清单(manifest)文件。

清单文件是一个文本文件，遵循特定的格式，看起来像：
Manifest-Version: 1.0
Name: bean/Frog.class
Java-Bean: True

第二行为类文件路径名(包括包名)，第三行表明"这是一个Bean"，没有第三行，程序构建工具将不能把类识别成Bean。必须把清单文件放在class文件所在包的根目录的上层目录中。
一旦把Bean正确地打包成JAR文件，就可以把它导入支持Bean的程序构建环境中，导入的方式根据不同的工具可能会有所不同。


### JavaBean与可视化编程

组件只不过就是一段代码，通常以类的形式出现。对于组件来说，关键在于要具有"能够被应用程序构建工具侦测其属性和事件的"能力。
引入JavaBean，Java把可视化组件的创建带到了最高的层次，因为Bean就是一个类。
要编写一个Bean，不必添加任何特殊代码或者使用任何特殊的语言功能，实际上唯一要做的就是对方法的名称作少许修改。
通过方法的名称就能告诉应用程序构建工具，这是一个属性、一个事件还是只是一个普通方法。