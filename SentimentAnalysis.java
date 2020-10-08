import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ReviewSentimentAnalyzer {
  public static float getRating(String input) throws IOException {
  	List<String> posWords = new ArrayList<String>();
  	List<String> negWords = new ArrayList<String>();

	Scanner inputNegativeFile = new Scanner(FileSystem.get(new Configuration()).open(new Path("/lib_Jon/negative.txt")));
  	Scanner inputPositiveFile = new Scanner(FileSystem.get(new Configuration()).open(new Path("/lib_Jon/positive.txt")));

    while (inputPositiveFile.hasNext()) {
      posWords.add(inputPositiveFile.nextLine());
    }

    while (inputNegativeFile.hasNext()) {
      negWords.add(inputNegativeFile.nextLine());
    }

    inputNegativeFile.close();
    inputPositiveFile.close();

    // Clean up input
		input = input.toLowerCase();
		input = input.trim();
		input = input.replaceAll("[^a-zA-Z0-9\\s]", "");

    // Calculate the negative and positive counts
    int negCounter = 0;
    int posCounter = 0;
    String[] words = input.split(" ");
    for (int i = 0; i < words.length; i++) {
      if (posWords.contains(words[i])) {
        posCounter++;
      }
      if (negWords.contains(words[i])) {
        negCounter++;
      }
    }

    int result = (posCounter - negCounter);
    float total = (float) posCounter + (float) negCounter;
    float rating = 0f;
    int is_zero = Float.compare(total, 0f);
    if (is_zero == 0) {
      rating = 3f;
    }
    else {
      rating = 1f + ((float) posCounter / total)*4f;
    }
    return rating;

  }

  public static void main(String[] args) throws IOException, FileNotFoundException {
    float rating  = getRating("This is a wonderful hike on a well maintained and easy to follow trail. I've hiked this one a couple of times and look forward to doing it again. But it is bad and good.");
    System.out.println(rating);
  }
}