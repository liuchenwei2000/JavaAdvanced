## 日志级别 ##

记录日志 API 提供了多个报告级别，以及在程序执行期间改变报告级别的能力。

    
    级别                             作用                                数值
    OFF                   不报告任何日志消息                          Integer.MAX_VALUE
    SEVERE                只报告SEVERE级别上的日志消息                 1000
    WARNING               报告WARNING和SEVERE级别上的日志消息          900
    INFO                  报告INFO及以上级别的日志消息                  800
    CONFIG                报告CONFIG及以上级别的日志消息                700
    FINE                  报告FINE及以上级别的日志消息                  500
    FINER                 报告FINER及以上级别的日志消息                 400
    FINEST                报告FINEST及以上级别的日志消息                300
    ALL                   报告所有的日志消息                           Integer.MIN_VALUE

甚至可以继承 java.util.Logging.Level，来定义我们自己的日志级别。

尽管不是强制的，但是将要使用日志记录器的类名传递给记录器是明智的，这使得我们可以操纵在同一包层次中（按包目录结构的粒度）的日志记录器组的记录日志级别。

如果我们传递一个代表名字空间(如包名)的字符串给 getLogger()，作为结果的 Logger 将会控制那个名字空间的记录日志的精确级别。
也就是，所有在那个名字空间内的包都将因日志记录器精确级别的改变而受到影响。
每个 Logger 都追踪其现有的祖先 Logger ，如果一个子 Logger 已经设置了记录日志级别，那么它将使用该级别而不是其父 Logger 的记录日志级别。
一旦子 Logger 具有它自己的记录日志级别，改变其父 Logger 的记录日志级别不会影响到该子 Logger 的记录日志级别。