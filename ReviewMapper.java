import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ReviewMapper extends Mapper<Object, Text, Text, Text>{
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		/* Configuration conf  = context.getConfiguration();
		String business_iid =conf.get("review.average.business.id"); */
		String[] strTk;
		String reviewText, starRating, business_id, predictedRating;

        strTk = value.toString().split("\",\"");
        if (strTk.length >= 6) {
            business_id = strTk[2];
            starRating = strTk[3];
            reviewText = strTk[5];
            predictedRating = Float.toString(ReviewSentimentAnalyzer.getRating(reviewText));
            context.write(new Text(business_id), new Text(starRating + " " + predictedRating));
        }
	}
}
