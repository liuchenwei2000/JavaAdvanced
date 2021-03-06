## TCP/IP协议简介 ##

大多数计算机语言的网络通信基本编程模式都采用 Socket（套接字）的形式，Java也是一样，这种模式是基于目前流行的TCP/IP通信协议的。

TCP/IP（Transmission Control Protocol/Internet Protocol），传输控制协议/因特网互联协议，又叫网络通讯协议。
这个协议是Internet最基本的协议，也是Internet的基础。它由网络层的IP协议和传输层的TCP协议组成，还包括UDP协议、ICMP协议和其他一些协议的协议组。

TCP/IP 定义了电子设备（如计算机）如何接入因特网，以及数据如何在它们之间传输的标准。
它是面向连接的传输、端到端的通信、可靠的（确保传输数据的正确性，不会丢失或乱序）、采用字节流方式（即以字节为单位传输字节序列）。

TCP 协议广泛的用于互联网中，最典型的例子是Web服务器与浏览器之间的通信。
TCP 提供了一种端口机制，用于区分各种网络应用程序，让它们在各自的通信端口上各行其道。
端口地址是 16 bit，可以有在 0-65535范围内的端口号。常见的端口号有 Telnet的23、FTP的21、HTTP的80、SMTP的25。
