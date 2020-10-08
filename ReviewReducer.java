import java.io.IOException;
import java.util.StringTokenizer;
import java.lang.Math;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReviewReducer extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		String output = "";
		String[] strTk;
		String starRating, predictedRating;
		Float diffRating, totalDiff = 0f, avgDiff = 0f;
		Integer count = 0;

		for(Text value : values) {
			strTk = value.toString().split(" ");
			if (strTk.length >= 2) {
				starRating = strTk[0];
				predictedRating = strTk[1];

				diffRating = Math.abs(Float.parseFloat(starRating) - Float.parseFloat(predictedRating));
				totalDiff += diffRating;
				count += 1;
			}
		}

		if (count.equals(0)) {
			avgDiff = 3f;
		} else {
			avgDiff = (float)totalDiff / (float)count;
		}

 		context.write(key, new Text(Float.toString(avgDiff)));
	}
}