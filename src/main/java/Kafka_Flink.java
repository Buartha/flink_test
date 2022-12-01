public class Kafka_Flink {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> src = env.socketTextStream("localhost", 8888);

        src.flatMap(new LineSplitter()).setParallelism(1)
                .keyBy(0)
                .sum(1).setParallelism(1)
                .print();

        env.execute("Java WordCount from SocketTextStream Example");
    }
}
