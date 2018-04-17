## ES的相关配置
ES的默认配置还是比较好的，需要很少的配置。并且大部分配置是可以通过更新集群的API进行更改的。但是配置中应该包括一些需要提供的配置，如：node.name和paths，或者还有cluster.name和network.host配置用来加入一个集群。
ES的配置主要有elasticsearch.yml和log4j.properties两个配置，一个是关于ES的配置，一个是关于日志文件配置。这些文件在$ES_HOME/config下。这个配置的更改可以通过path.conf进行更改
<pre>
./bin/elasticsearch -Epath.conf=/path/to/my/config/
</pre>
配置文件的格式是yaml格式的，这里用数据和日志的路径的配置做演示
<pre>
path:
    data: /var/lib/elasticsearch
    logs: /var/log/elasticsearch
</pre>
配置同样也可以按照如下格式配置
<pre>
path.data: /var/lib/elasticsearch
path.logs: /var/log/elasticsearch
</pre>
对于ES的配置同样可以通过环境变量的替换来实现，比如$P{...}，然后在环境变量中设置这些信息即可。如下：
<pre>
cluster.name: ${ES_CLUSTER_NAME}
</pre>
然后在节点export ES_CLUSTER_NAME=es_cluster引入环境变量即可。
对于如果你不想配置在配置文件中的信息，你可以使用这两个配置：${prompt.text} or ${prompt.secret}，${prompt.secret}对你输入的信息不会显示出来，${prompt.text}可以在终端显示出你的输入信息。
<pre>
node:
  name: ${prompt.text}
</pre>
当你在启动时，你就会被提示输入这个参数值：
<pre>
Enter value for [node.name]:
</pre>
需要注意的是，如果配置了参数，但是你在后台启动ES的话，是无法完成参数替换的，同时，也无法正常启动ES进程。
# 日志配置
ES使用的是log4j2的日志文件配置，配置文件名log4j2.properties。ES对外暴露了三个参数${sys:es.logs.base_path}, ${sys:es.logs.cluster_name}, 和 ${sys:es.logs.node_name}，这些参数将会在配置文件引用，决定具体的日志文件路径。${sys:es.logs.base_path}这个将被解析为具体文件的跟路径（path.logs），${sys:es.logs.cluster_name}是有cluster.name决定，${sys:es.logs.node_name}与node.name有关，但是在默认的配置中并没有使用该参数。
如：我们将path.logs设置为/var/log/elasticsearch，集群的名称为cluster_es，那么${sys:es.logs.base_path}${sys:file.separator}${sys:es.logs.cluster_name}.log将会解析为/var/log/elasticsearch/cluster_es.log
<pre>
#采用RollingFile类型的Appender
appender.rolling.type = RollingFile 
appender.rolling.name = rolling
#日志的位置
appender.rolling.fileName = ${sys:es.logs.base_path}${sys:file.separator}${sys:es.logs.cluster_name}.log 
appender.rolling.layout.type = PatternLayout
appender.rolling.layout.pattern = [%d{ISO8601}][%-5p][%-25c] %.10000m%n
#日志压缩的格式
appender.rolling.filePattern = ${sys:es.logs.base_path}${sys:file.separator}${sys:es.logs.cluster_name}-%d{yyyy-MM-dd}.log 
appender.rolling.policies.type = Policies
#基于时间的滚动策略
appender.rolling.policies.time.type = TimeBasedTriggeringPolicy 
#周期为一天
appender.rolling.policies.time.interval = 1 
#以天为边界滚动日志文件
appender.rolling.policies.time.modulate = true 
</pre>
如果在appender.rolling.filePattern选项值加上.gz和.zip后缀，则滚动出的日志文件会被自动压缩 
如果需要配置日志删除老化策略，参考如下配置
<pre>
#配置DefaultRolloverStrategy
appender.rolling.strategy.type = DefaultRolloverStrategy 
#配置用于处理rollover的删除操作
appender.rolling.strategy.action.type = Delete 
#Elasticsearch日志的基本路径
appender.rolling.strategy.action.basepath = ${sys:es.logs.base_path} 
#当处理rollovers时的申请条件
appender.rolling.strategy.action.condition.type = IfLastModified 
#日志的保留天数
appender.rolling.strategy.action.condition.age = 7D 
#直接删除7天以上匹配格式的文件
appender.rolling.strategy.action.PathConditions.type = IfFileName 
#删除匹配${sys:es.logs.cluster_name}-*它的文件，但是过期的废弃文件和慢文件不删除。（这块理解不清，暂时这样写吧）
appender.rolling.strategy.action.PathConditions.glob = ${sys:es.logs.cluster_name}-* 
</pre>
