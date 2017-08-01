# ActiveMQTest
[http://www.cnblogs.com/hoojo/p/active_mq_jms_apache_activeMQ.html](http://www.cnblogs.com/hoojo/p/active_mq_jms_apache_activeMQ.html) 

(1)、点对点方式（point-to-point）

点对点的消息发送方式主要建立在 Message Queue,Sender,reciever上，Message Queue 存贮消息，Sneder 发送消息，receive接收消息.具体点就是Sender Client发送Message Queue ,而 receiver Cliernt从Queue中接收消息和"发送消息已接受"到Quere,确认消息接收。消息发送客户端与接收客户端没有时间上的依赖，发送客户端可以在任何时刻发送信息到Queue，而不需要知道接收客户端是不是在运行

(2)、发布/订阅 方式（publish/subscriber Messaging）

发布/订阅方式用于多接收客户端的方式.作为发布订阅的方式，可能存在多个接收客户端，并且接收端客户端与发送客户端存在时间上的依赖。一个接收端只能接收他创建以后发送客户端发送的信息。作为subscriber ,在接收消息时有两种方法，destination的receive方法，和实现message listener 接口的onMessage 方法。



# 发送消息的基本步骤：

(1)、创建连接使用的工厂类JMS ConnectionFactory

(2)、使用管理对象JMS ConnectionFactory建立连接Connection，并启动

(3)、使用连接Connection 建立会话Session

(4)、使用会话Session和管理对象Destination创建消息生产者MessageSender

(5)、使用消息生产者MessageSender发送消息

 

消息接收者从JMS接受消息的步骤

(1)、创建连接使用的工厂类JMS ConnectionFactory

(2)、使用管理对象JMS ConnectionFactory建立连接Connection，并启动

(3)、使用连接Connection 建立会话Session

(4)、使用会话Session和管理对象Destination创建消息接收者MessageReceiver

(5)、使用消息接收者MessageReceiver接受消息，需要用setMessageListener将MessageListener接口绑定到MessageReceiver消息接收者必须实现了MessageListener接口，需要定义onMessage事件方法。