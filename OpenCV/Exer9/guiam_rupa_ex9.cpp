#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/imgproc/imgproc_c.h"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/opencv.hpp"
#include "stdlib.h"
#include "stdio.h"

using namespace cv;
using namespace std;

int compare(const void * a, const void * b){
  return (*(int*)a - *(int*)b);
}

int main( int argc, char** argv ){
    VideoCapture cap(argv[1]);
    VideoCapture cap2(argv[1]);

    int numFrames = cap.get(CAP_PROP_FRAME_COUNT); // get total number of frames in the sequence
    printf("number of frames: %d\n", numFrames);

    int numLearnFrames = 100;	          		        // total number of "learning" frames
    int frameInterval = numFrames/numLearnFrames;   // skip every frameInterval
    int foregroundDifferenceThreshold = 30;         // threshold set to determin change
    int list[numFrames];
    int row, col;
    int counter = 0;

    Mat input, gray;
    Mat grayFrame[numLearnFrames];

    // fetch the frames from which the background will be learned

    for (int f = 0; f < numFrames; f++) {
      Mat frame, gray;
      cap >> frame;  // get each frame

      if(counter == numLearnFrames) break;
      // if current frame is a sample for background learning
      if(f % frameInterval == 0) {
        cvtColor(frame, gray, COLOR_BGR2GRAY);
        grayFrame[counter] = gray;
        counter++;
      }
    }

    row = grayFrame[0].rows;
    col = grayFrame[0].cols;

    // compute the background gray image
    Mat background(row, col, CV_8UC1, Scalar(255));

    // for every pixel (x,y)
    for (int x = 0; x < row; x++) {
    	for (int y = 0; y < col; y++) {
    	    for (int f = 0; f < numLearnFrames; f++) {
      	     list[f] = (int) grayFrame[f].at<uchar>(x,y); // put every frame's (x,y) value in a list
          }
          qsort(list, numLearnFrames, sizeof(int), compare);
          background.at<uchar>(x,y) = list[numLearnFrames / 2]; // get median value
    	}
    }

    namedWindow("background", WINDOW_NORMAL);
    imshow("background", background);

    namedWindow("output", WINDOW_NORMAL);

    // subtract each image from the background
    for (int f = 0; f < numFrames; f++) {
  	  Mat frame, reference;
      cap2 >> frame;
      cap2 >> reference;

      cvtColor(frame, gray, CV_BGR2GRAY);
      Mat binary(row, col, CV_8UC3, Scalar(255,255,255));

      blur(gray, gray, Size(3,3));
      // convert each color image to gray scale
  	  for (int x = 0; x < row; x++) {
    	    for (int y = 0; y < col; y++){
            //check if theres a major diff in learned background and current frame
          	if(abs(gray.at<uchar>(x,y)-background.at<uchar>(x,y)) > foregroundDifferenceThreshold) {
      	        binary.at<Vec3b>(x,y)[0] = reference.at<Vec3b>(x,y)[0];   // copy foreground
		            binary.at<Vec3b>(x,y)[1] = reference.at<Vec3b>(x,y)[1];   // copy foreground
		            binary.at<Vec3b>(x,y)[2] = reference.at<Vec3b>(x,y)[2];   // copy foreground
	          }
     	    }
  	  }

      medianBlur(binary, binary, 3);

      // save the input and binary images into one image positioned side by side
    	Size sz1 = frame.size();
    	Size sz2 = binary.size();

      Mat output(sz1.height, sz1.width+sz2.width, CV_8UC3);
    	Mat left(output, Rect(0, 0, sz1.width, sz1.height));
    	frame.copyTo(left);
    	Mat right(output, Rect(sz1.width, 0, sz2.width, sz2.height));
      binary.copyTo(right);
      imshow("output", output);
      waitKey(20);
    }

    waitKey(0);
}

//References: background substitution in imageLab2012
