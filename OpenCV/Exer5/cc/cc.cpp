#include <iostream>
#include <vector>
#include <math.h>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/imgcodecs.hpp>

using namespace std;
using namespace cv;

int main(int argc, const char * argv[]) {

    if(argc != 2){
        printf("Usage: ./cc <Image_Path>\n");
        return -1;
    }

    Mat src1 = imread(argv[1], 2);
    Mat src = src1;

    if(!src.data)
    {
        printf("No image data \n");
        return -1;
    }

    imshow("Original", src);

    for(int i=0; i<src.rows; i++){
	for(int j=0; j<src.cols; j++){
	    if(src1.at<uchar>(i,j) < 150){
		src.at<uchar>(i,j) = 0;
	    }
	    else{
		src.at<uchar>(i,j) = 255;
	    }
	}
    }

    // Get connected components and stats
    
    const int connectivity_8 = 8;
    Mat labels, stats, centroids;

    int nLabels = connectedComponentsWithStats(src, labels, stats, centroids, connectivity_8, CV_32S);

    // Print number of components
    printf("Number of connected components: %d\n\n", nLabels);

    // Create images of components
    Mat component[nLabels];
    char *windowLabel;
    for(int i=0; i<nLabels; i++){
	compare(labels, i, component[i], CMP_EQ);
	sprintf(windowLabel,"Component %d", i);
	imshow(windowLabel, component[i]);
    }

    // Identify what component does one pixel belong to
    int component1Pixel = labels.at<int>(150,150);
    int component2Pixel = labels.at<int>(300,550);

    printf("Show label values");
    printf("\npixel at(150,150): %d\n", component1Pixel);
    printf("pixel at(300,550): %d\n\n", component2Pixel);
  
    // Print Statistics and Centroids for each component
    for(int i=0; i<nLabels; i++){
	printf("Component %d stats\n", i);
	printf("  CC_STAT_LEFT: %d\n", stats.at<int>(i,CC_STAT_LEFT));
	printf("  CC_STAT_TOP: %d\n", stats.at<int>(i,CC_STAT_TOP));
	printf("  CC_STAT_WIDTH: %d\n", stats.at<int>(i,CC_STAT_WIDTH));
	printf("  CC_STAT_HEIGHT: %d\n", stats.at<int>(i,CC_STAT_HEIGHT));
	printf("  CC_STAT_AREA: %d\n", stats.at<int>(i,CC_STAT_AREA));
	printf("  Centroid (x,y): %f, %f\n\n", centroids.at<double>(i,0), centroids.at<double>(i,1));
    }

    waitKey(0);
    
}

//Reference: http://stackoverflow.com/questions/29108270/opencv-2-4-10-bwlabel-connected-components
