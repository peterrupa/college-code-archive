#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/imgproc/imgproc_c.h"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/opencv.hpp"
#include "stdlib.h"
#include "stdio.h"

// range for identifying ocean color
#define MIN_BLUE 50
#define MAX_BLUE 200
#define MIN_GREEN 0
#define MAX_GREEN 50
#define MIN_RED 0
#define MAX_RED 50

using namespace cv;
using namespace std;

int main( int argc, char** argv ){

    Mat src = imread(argv[1], 1);
    Mat scale = imread("./scales/colorscale_hot.jpg", 1);
    Mat output = src.clone();
    int threshold;

    if( !src.data ){
	   return -1;
    }
    
    for (int i = 0; i < src.rows; i++){
        for (int j = 0; j < src.cols;  j++) {
            uchar blue = src.at<Vec3b>(i,j).val[0];
            uchar green = src.at<Vec3b>(i,j).val[1];
            uchar red = src.at<Vec3b>(i,j).val[2];
            
            // check if pixel value is within range
            if(
                blue >= MIN_BLUE && blue <= MAX_BLUE &&
                green >= MIN_GREEN && green <= MAX_GREEN &&
                red >= MIN_RED && red <= MAX_RED
            ) {
                // blue
                output.at<Vec3b>(i,j)[0] = scale.at<Vec3b>(0, blue)[0];

                // green
                output.at<Vec3b>(i,j)[1] = scale.at<Vec3b>(0, blue)[1];

                // red
                output.at<Vec3b>(i,j)[2] = scale.at<Vec3b>(0, blue)[2];
            }
        }
    }
    imshow("output", output);

    waitKey(0);
}
