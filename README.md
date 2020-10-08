## Sentiment-Analysis-on-Product-Reviews
> Aim of this project is to perform sentiment analysis on large Yelp datasets of customer reviews from on kaggle using MapReduce and the Hadoop Distributed System.

<hr>

## Table of Contents
* About dataset
* Environment
* Map Reduce program

<hr>

## About dataset
This dataset is a subset of Yelp's businesses, reviews, and user data.</br>
Originally put together for the Yelp Dataset Challenge to conduct research or analysis on Yelp's data and share their discoveries.</br>
In total, there are :</br>
5,200,000 user reviews</br>
Information on 174,000 businesses</br>
The data spans 11 metropolitan areas</br>

Dataset Link: https://www.kaggle.com/yelp-dataset/yelp-dataset

<hr>

## Environment
Hadoop, HDFS, Java, Intellij/Visual Studio

<hr>

## Extract and Transform the Data
* Data is processed as a long string and then tokenized to obtain for each record the Business ID, Review ID, Review text, and Star rating of the yelp reviews dataset.</br>
* Validate the accuracy of the star rating of written reviews and calculate the sentiment of each business and giving out a predicted star rating. </br>
* Calculates the average star difference for each business.</br>

### Phase 1
* Mapper: map the Review text using Review ID as key.</br>
* Reducer: perform sentiment analysis on the array of Review text for each business, then output the predicted star rating for each review ID, actual star rating and business ID.</br>

### Phase 2:
* Mapper: map the predicted star rating, actual star rating to business ID.</br>
* Reducer: for each review, calculate the difference between predicted star rating and actual star rating then average them for each business ID, output them as business ID, 
starDiff.</br>

### Phase 3:
* Mapper: Map all the starDiff to a reducer.</br>
* Reducer: calculate the average of all the starDiff of the business ID.</br>

