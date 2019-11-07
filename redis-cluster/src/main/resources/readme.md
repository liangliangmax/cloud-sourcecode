# redis 集群搭建

### 方法一：使用redis自带的脚本创建集群

在redis的安装目录中有util/create-cluster目录，里面有个官方自带的脚本create-cluster.

```
#!/bin/bash

# Settings
PORT=5000        # 这里需要修改
TIMEOUT=2000
NODES=6          # 这里需要修改
REPLICAS=1       # 这里需要求改
PASSWORD=123456  # 这里需要修改

# You may want to put the above config parameters into config.sh in order to
# override the defaults without modifying this script.

if [ -a config.sh ]
then
    source "config.sh"
fi

# Computed vars
ENDPORT=$((PORT+NODES))

if [ "$1" == "start" ]
then
    while [ $((PORT < ENDPORT)) != "0" ]; do
        PORT=$((PORT+1))
        echo "Starting $PORT"
        ../../bin/redis-server --bind 0.0.0.0 --protected-mode no --port $PORT --cluster-enabled yes --cluster-config-file nodes-${PORT}.conf --cluster-node-timeout $TIMEOUT --appendonly yes --appendfilename appendonly-${PORT}.aof --dbfilename dump-${PORT}.rdb --logfile ${PORT}.log --daemonize yes --masterauth $PASSWORD --requirepass $PASSWORD
    done
    exit 0
fi

if [ "$1" == "create" ]
then
    HOSTS=""
    while [ $((PORT < ENDPORT)) != "0" ]; do
        PORT=$((PORT+1))
        HOSTS="$HOSTS 127.0.0.1:$PORT"
    done
    ../../bin/redis-cli -a $PASSWORD --cluster create $HOSTS --cluster-replicas $REPLICAS
    exit 0
fi

if [ "$1" == "stop" ]
then
    while [ $((PORT < ENDPORT)) != "0" ]; do
        PORT=$((PORT+1))
        echo "Stopping $PORT"
        ../../bin/redis-cli -a $PASSWORD -p $PORT shutdown nosave
    done
    exit 0
fi

if [ "$1" == "watch" ]
then
    PORT=$((PORT+1))
    while [ 1 ]; do
        clear
        date
        ../../bin/redis-cli -a $PASSWORD -p $PORT cluster nodes | head -30
        sleep 1
    done
    exit 0
fi

if [ "$1" == "tail" ]
then
    INSTANCE=$2
    PORT=$((PORT+INSTANCE))
    tail -f ${PORT}.log
    exit 0
fi

if [ "$1" == "call" ]
then
    while [ $((PORT < ENDPORT)) != "0" ]; do
        PORT=$((PORT+1))
        ../../bin/redis-cli -a $PASSWORD -p $PORT $2 $3 $4 $5 $6 $7 $8 $9
    done
    exit 0
fi

if [ "$1" == "clean" ]
then
    rm -rf *.log
    rm -rf appendonly*.aof
    rm -rf dump*.rdb
    rm -rf nodes*.conf
    exit 0
fi

if [ "$1" == "clean-logs" ]
then
    rm -rf *.log
    exit 0
fi

echo "Usage: $0 [start|create|stop|watch|tail|clean]"
echo "start       -- Launch Redis Cluster instances."
echo "create      -- Create a cluster using redis-cli --cluster create."
echo "stop        -- Stop Redis Cluster instances."
echo "watch       -- Show CLUSTER NODES output (first 30 lines) of first node."
echo "tail <id>   -- Run tail -f of instance at base port + ID."
echo "clean       -- Remove all instances data, logs, configs."
echo "clean-logs  -- Remove just instances logs."


```
其中标记需要修改的地方需要重点关注一下

修改完成之后运行 
1. ./create-cluster start  启动节点
2. ./create-cluster create 将节点弄成集群

3. ./create-cluster stop 停止集群并停止每个节点

这种方法很容易就创建redis集群


### 方法二：自己创建脚本，启动集群
1、创建文件夹cluster
2、在cluster文件夹中创建5001，5002，5003，5004，5005，5006六个文件夹
3、修改redis.conf文件
```
bind 0.0.0.0

protected-mode no

port 15001  # 每个都不一样

daemonize yes

dir /usr/local/redis/5001   # 每个都不一样

masterauth 123456

requirepass 123456

appendonly yes

cluster-enabled yes

appendfilename "appendonly.aof"

cluster-config-file nodes.conf

cluster-node-timeout 15000

```

4、然后将redis.conf文件拷贝到每个文件夹下面
5、编写启动脚本start.sh
```
/usr/local/redis/bin/redis-server /usr/local/redis/5001/redis.conf
/usr/local/redis/bin/redis-server /usr/local/redis/5002/redis.conf
/usr/local/redis/bin/redis-server /usr/local/redis/5003/redis.conf
/usr/local/redis/bin/redis-server /usr/local/redis/5004/redis.conf
/usr/local/redis/bin/redis-server /usr/local/redis/5005/redis.conf
/usr/local/redis/bin/redis-server /usr/local/redis/5006/redis.conf

/usr/local/redis/bin/redis-cli -a 123456 --cluster create 10.4.249.99:15001 10.4.249.99:15002 10.4.249.99:15003 10.4.249.99:15004 10.4.249.99:15005 10.4.249.99:15006 --cluster-replicas 1 


```

6、编写关闭脚本stop.sh
```
/usr/local/redis/bin/redis-cli -a 123456 -c -p 15001 shutdown
/usr/local/redis/bin/redis-cli -a 123456 -c -p 15002 shutdown
/usr/local/redis/bin/redis-cli -a 123456 -c -p 15003 shutdown
/usr/local/redis/bin/redis-cli -a 123456 -c -p 15004 shutdown
/usr/local/redis/bin/redis-cli -a 123456 -c -p 15005 shutdown
/usr/local/redis/bin/redis-cli -a 123456 -c -p 15006 shutdown

```

7、给sh文件赋执行权限，即可启动集群


