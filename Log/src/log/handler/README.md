## 日志处理器 ##

每个 Logger 对象可以有一到多个与之相关联的 Handler（处理器）对象，用于将 Logger 对象传递来的 LogRecord 发布出去（如控制台、内存、文件、网络等等）。
每个 Handler 对象提供一种用于发布日志消息的策略，该消息包含在 LogRecord 对象中。
为了创建一种新类型的 Handler，只需从 Handler 类继承并覆盖其 publish() 方法（连同 flush() 和 e() 一起，来处理在 Handler 中可能使用到的所有流）。
根日志记录器缺省地总是与一个处理器相关联，将输出发送到控制台。

Java预置的处理器：
    
    StreamHandler        将格式化的记录写到OutputStream中
    ConsoleHandler       将格式化的记录写到System.err中
    FileHandler          将格式化的日志记录写到一个单一文件，或者一个交替的记录文件集合中
    SocketHandler        将格式化的日志记录写到远程TCP端口中
    MemoryHandler        在内存中缓存日志记录
