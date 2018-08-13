#include "opencv2/core/core.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include <opencv/highgui.h>
#include "iostream"
#include <stdlib.h>
#include <stdio.h>


using namespace cv;

/// Global variables
Mat src, src_gray, dst, erosion_dst, dilation_dst;

int erosion_elem = 0;
int erosion_size = 0;
int dilation_elem = 0;
int dilation_size = 0;
int const max_elem = 2;
int const max_kernel_size = 21;

int threshold_value = 171;
int const max_value = 255;
int const max_BINARY_value = 255;

/** Function Headers */
void Erosion( int, void* );
void Dilation( int, void* );

/** @function main */
int main( int argc, char** argv )
{
  /// Load an image
  src = imread( argv[1] );

  if( !src.data )
  { return -1; }

  cvtColor( src, src_gray, CV_RGB2GRAY );

  //binarize the image
  //threshold( src_gray, src, threshold_value, max_BINARY_value, 0);
  for(int i=0; i<src.rows; i++){
    for(int j=0; j<src.cols; j++){
       Vec3b intensity = src.at<Vec3b>(i, j);
       uchar blue = intensity.val[0];
       uchar green = intensity.val[1];
       uchar red = intensity.val[2];

       uchar gray = (blue+green+red)/3;

       if(gray>171){
          src.at<Vec3b>(i,j)[0] = 255;
          src.at<Vec3b>(i,j)[1] = 255;
          src.at<Vec3b>(i,j)[2] = 255;
       }
       else{
          src.at<Vec3b>(i,j)[0] = 0;
          src.at<Vec3b>(i,j)[1] = 0;
          src.at<Vec3b>(i,j)[2] = 0;
       }
    }
  }

  imwrite("obj.jpg", src);

  /// Create windows
  namedWindow( "Erosion Demo", CV_WINDOW_AUTOSIZE );
  namedWindow( "Dilation Demo", CV_WINDOW_AUTOSIZE );
  cvMoveWindow( "Dilation Demo", src.cols, 0 );

  /// Create Erosion Trackbar
  createTrackbar( "Element:\n 0: Rect \n 1: Cross \n 2: Ellipse", "Erosion Demo",
                  &erosion_elem, max_elem,
                  Erosion );

  createTrackbar( "Kernel size:\n 2n +1", "Erosion Demo",
                  &erosion_size, max_kernel_size,
                  Erosion );

  /// Create Dilation Trackbar
  createTrackbar( "Element:\n 0: Rect \n 1: Cross \n 2: Ellipse", "Dilation Demo",
                  &dilation_elem, max_elem,
                  Dilation );

  createTrackbar( "Kernel size:\n 2n +1", "Dilation Demo",
                  &dilation_size, max_kernel_size,
                  Dilation );

  /// Default start
  Erosion( 0, 0 );
  Dilation( 0, 0 );

  waitKey(0);
  return 0;
}

/**  @function Erosion  */
void Erosion( int, void* )
{
  int erosion_type;
  if( erosion_elem == 0 ){ erosion_type = MORPH_RECT; }
  else if( erosion_elem == 1 ){ erosion_type = MORPH_CROSS; }
  else if( erosion_elem == 2) { erosion_type = MORPH_ELLIPSE; }

  Mat element = getStructuringElement( erosion_type,
                                       //Size( 2*erosion_size + 1, 2*erosion_size+1 ),
				       Size( 2*erosion_size+1, 2*erosion_size+1 ),
                                       Point( erosion_size, erosion_size ) );

  /// Apply the erosion operation
  erode( src, erosion_dst, element );
  imshow( "Erosion Demo", erosion_dst );
}

/** @function Dilation */
void Dilation( int, void* )
{
  int dilation_type;
  if( dilation_elem == 0 ){ dilation_type = MORPH_RECT; }
  else if( dilation_elem == 1 ){ dilation_type = MORPH_CROSS; }
  else if( dilation_elem == 2) { dilation_type = MORPH_ELLIPSE; }

  Mat element = getStructuringElement( dilation_type,
                                       Size( 2*dilation_size + 1, 2*dilation_size+1 ),
                                       Point( dilation_size, dilation_size ) );
  /// Apply the dilation operation
  dilate( src, dilation_dst, element );
  imshow( "Dilation Demo", dilation_dst );
}


//src from: http://docs.opencv.org/doc/tutorials/imgproc/erosion_dilatation/erosion_dilatation.html
