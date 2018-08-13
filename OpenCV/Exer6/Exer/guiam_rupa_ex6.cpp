#include "opencv2/core/core.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include <opencv/highgui.h>
#include "iostream"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "fstream"

#define THRESHOLD 200
#define NUMBER_OF_QUESTIONS 39
#define NUMBER_OF_CRITERIA 7
#define NUMBER_OF_INPUT 100

using namespace cv;
using namespace std;

int main(int argc, char** argv) {
    int stats[NUMBER_OF_QUESTIONS][NUMBER_OF_CRITERIA];
    Mat src, binarized;

    //load csv file that contains coordinates of circles for criteria
    ifstream file("fields39.csv");
    vector < vector < vector<int> > > questions;

    for(int i=0; i<NUMBER_OF_QUESTIONS; i++) {
      vector< vector<int> > criteria;

      for(int j=0; j<NUMBER_OF_CRITERIA; j++) {
        vector<int> coordinates;
        int x, y;

        file >> y >> x;
        stats[i][j] = 0;
        //save coordinate of each circle center
        coordinates.push_back(x);
        coordinates.push_back(y);
        criteria.push_back(coordinates);
      }

      questions.push_back(criteria);
    }

    file.close();

     const string extension = ".jpg";
     //read all image input
     for(int count=1; count <= NUMBER_OF_INPUT; count++) {

       ostringstream convert;
       //constrcut filename
       if(count < 10) convert << "forms/000" << count << extension;
       else if(count < 100) convert << "forms/00" << count << extension;
       else convert << "forms/0" << count << extension;

       string filename  = convert.str();

       cout << "filename: " << filename << "\n";
       //reads image
       src = imread(filename, 0);

       if(!src.data) {
          printf("Invalid Image\n");
          return -1;
        }

      // binarize image using static threshold
      threshold(src, binarized, THRESHOLD, 255, THRESH_BINARY_INV);

      int erosion_size = 3;

      Mat element = getStructuringElement(
          MORPH_RECT,
          Size(2*erosion_size+1, 2*erosion_size+1),
          Point(erosion_size, erosion_size)
      );

      // erode
      erode(binarized, binarized, element);

      // dilate
      dilate(binarized, binarized, element);
      dilate(binarized, binarized, element);

      // remove non-shaded regions
      rectangle(
          binarized,
          Point(0, 0),
          Point(binarized.cols, 150),
          Scalar(0, 0, 0),
          -1,
          8
      );

      rectangle(
          binarized,
          Point(0, 0),
          Point(50, binarized.rows),
          Scalar(0, 0, 0),
          -1,
          8
      );

      rectangle(
          binarized,
          Point(0, binarized.rows - 100),
          Point(binarized.cols, binarized.rows),
          Scalar(0, 0, 0),
          -1,
          8
      );

      rectangle(
          binarized,
          Point(150, 150),
          Point(650, 250),
          Scalar(0, 0, 0),
          -1,
          8
      );

      rectangle(
          binarized,
          Point(600, 1370),
          Point(925, 1700),
          Scalar(0, 0, 0),
          -1,
          8
      );

       for(int i=0; i<NUMBER_OF_QUESTIONS; i++) {
         vector< vector<int> > criteria = questions[i];

         for(int j=0; j<NUMBER_OF_CRITERIA; j++) {
            vector<int> coordinates = criteria[j];

            int value = binarized.at<uchar>(coordinates[0], coordinates[1]);
            //if pixel value of the coordinate of the circle is a forground,
            //increment counter
            if(value == 255) stats[i][j]++;
          }
      }

    }

    //create an output file
    ofstream output_file("results.txt");

    for (int i = 0; i <NUMBER_OF_QUESTIONS; i++) {
      printf("QUESTION %d \tSA: %d\tA: %d\tSLA: %d\tNAD:%d\tSLD: %d\tD: %d\tSD: %d\n",
            i, stats[i][0], stats[i][1], stats[i][2], stats[i][3],
            stats[i][4], stats[i][5], stats[i][6]);
      //write output to file
      output_file << "QUESTION " << i << "\n\t";
      output_file << "SA: " << stats[i][0] << "\t";
      output_file << "A: " << stats[i][1] << "\t";
      output_file << "SLA: " << stats[i][2] << "\t";
      output_file << "NAD: " << stats[i][3] << "\t";
      output_file << "SLD: " << stats[i][4] << "\t";
      output_file << "D: " << stats[i][5] << "\t";
      output_file << "SD: " << stats[i][6] << "\t\n";
    }

    output_file.close();

}
