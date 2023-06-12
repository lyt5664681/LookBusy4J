## 占用空闲机器的CPU与内存，用来忽悠甲方爸爸
## 配置说明
| 配置项                     | 示例值   | 解释                               |
|---------------------------|---------|-----------------------------------|
| server.port               | 8282    | 服务器监听的端口号                   |
| busy.thread.count         | 10      | 线程数量                        |
| busy.cpu-polling.enable   | true    | 是否启用CPU轮询          |
| busy.cpu-polling.time     | 50000   | CPU轮询的时间间隔（毫秒）              |
| busy.mem-polling.enable   | true    | 是否启用内存轮询           |
| busy.mem-polling.time     | 50000   | 内存轮询的时间间隔（毫秒）               |

#### 增加消耗可以增加“线程数量”，减小轮询时间间隔。
#### 另外启动时,需要在start.sh中设置堆内存大小,建议设为物理内存的一半.,设置这两个值-Xms1024m -Xmx1024m