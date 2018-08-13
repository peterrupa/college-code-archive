#include <stdio.h>
#include <opencv2/opencv.hpp>

using namespace cv;

int getThreshold(Mat image, int x, int y, int dimension) {
  int i = (x-dimension >= 0)? x-dimension : 0;
  int col = (y-dimension >= 0)? y-dimension : 0;
  int mean = 0, counter = 0;

  for(; i<(x+dimension); i++) {
    for(int j = col; j<(y+dimension); j++) {
      mean += image.at<uchar>(i,j);
      counter++;
    }
  }

  mean /= counter;
  return mean;
}

uchar binarize(Mat image, int i, int j, int threshold) {
  if(image.at<uchar>(i,j) < threshold) return 0;
  else return 255;
}

int main(int argc, char** argv )
{
    if ( argc != 2 ) {
        printf("usage: DisplayImage.out <Image_Path>\n");
        return -1;
    }

    Mat image, output, output2;
    image = imread(argv[1], 0);
    output = imread(argv[1], 0);
    output2 = imread(argv[1], 0);

    if ( !image.data ) {
        printf("No image data \n");
        return -1;
    }

    blur(image, image, Size(3,3));

    for(int i=0; i<image.rows; i++){
      for(int j=0; j<image.cols; j++){
          output.at<uchar>(i,j) = binarize(image, i, j, getThreshold(image, i, j, 5));
      }
    }

    int thresh = 10;
    Canny(output, output, thresh, thresh*3, 3);

    imshow("Original Image", image);
    imshow("Output Image", output);
    imwrite("output.jpg", output);

    waitKey(0);
    return 0;
}
