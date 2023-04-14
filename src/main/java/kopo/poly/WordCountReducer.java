package kopo.poly;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
    throws IOException, InterruptedException {

        //단어별 빈도수를 계산하기 위한 변수
        int wordCount = 0;

        //Suffle and Sort로 인해 단어별로 데이터들의 값들이 List구조로 저장됨
        // 이협건 : {1,1,1,1,1,1,1,1,1,1,1}
        // 모든 값은 1이기에 모두 더하기 해도 됨
        for(IntWritable value: values){
            //값을 모두 더하기
            wordCount += value.get();

        }

        //분석 결과 파일에 데이터 저장하기
        context.write(key, new IntWritable(wordCount));
    }
}
