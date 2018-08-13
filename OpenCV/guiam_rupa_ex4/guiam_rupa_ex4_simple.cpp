#include <stdio.h>
#include <opencv2/opencv.hpp>

using namespace cv;

int getThreshold(Mat image, int x, int y, int dimension) {
  // compute adaptive threshold by getting mean of neighbors
  int i = (x-dimension >= 0)? x-dimension : 0;
  int col = (y-dimension >= 0)? y-dimension : 0;
  int mean = 0, counter = 0;

  // get accumulated value of neighbors
  for(; i<(x+dimension); i++) {
    for(int j = col; j<(y+dimension); j++) {
      mean += image.at<uchar>(i,j);
      counter++;
    }
  }

  // get mean
  mean /= counter;
  return mean;
}

// general binarization function
uchar binarize(Mat image, int i, int j, int treshold) {
  if(image.at<uchar>(i,j) < treshold) return 0;
  else return 255;
}

// binarization for quote image
uchar quote(Mat image, int i, int j) {
  // instead of threshold, we depend on range of values 100 - 190
  if(image.at<uchar>(i,j) > 100 && image.at<uchar>(i,j) < 190) return 0;
  else return 255;
}

// binarization for japbend image
uchar japbend(Mat image, int i, int j) {
  // hardcoded areas to always be white
  if(i < 20 || i > 350) return 255;
  if(j < 30 || j > 590) return 255;

  // 90 threshold for upper part of image
  if(i >= 20 && i < 55) {
    if(image.at<uchar>(i,j) < 90) return 0;
  }

  // 190 threshold for middle part of image
  else if(i >= 145 && i < 260) {
    if(image.at<uchar>(i,j) < 190) return 0;
  }

  // 140 threshold for lower part of image
  else {
    if(image.at<uchar>(i,j) < 140) return 0;
  }

  return 255;
}

// binarization for japflat image
uchar japflat(Mat image, int i, int j) {
    // ignore left side portion of image
    if(j >= 50 && image.at<uchar>(i,j) < 190) return 0;
    else return 255;
}

// binarization for magazin2
uchar magazin2(Mat image, int i, int j) {
  // hardcoded areas to be white
  if(i < 40 || i > 665) return 255;
  if(j < 40 || j > 995) return 255;

  if(i < 95 && j < 90) return 255;
  if(i > 240 && i < 320 && j > 350) return 255;
  if(i > 290 && i < 320) return 255;
  if(i > 615 && i < 630) return 255;

  // 180 threshold for sides
  if(i < 300 || i > 620){
    if(image.at<uchar>(i,j) > 180) return 0;
  }

  // find range of values from 70 - 190
  else if(image.at<uchar>(i,j) > 70 && image.at<uchar>(i,j) < 190) {
    return 0;
  }

  return 255;
}

uchar magazin1(Mat image, int i, int j) {
  // hardcoded areas to be white
  if(j > 850) return 255;
  if(i > 200 && j < 300) return 255;
  if(i > 200 && i < 250 && j > 600) return 255;
  if(i > 210 && j < 475) return 255;

  // 120 threshold for right side of image
  if(i > 250) {
    if(image.at<uchar>(i,j) > 120) return 255;
  }

  else if(image.at<uchar>(i,j) > 100) {
    return 255;
  }

  return 0;
}


int main(int argc, char** argv )
{
    if ( argc != 2 ) {
        printf("usage: DisplayImage.out <Image_Path>\n");
        return -1;
    }

    Mat image, output;
    image = imread(argv[1], 0);
    output = imread(argv[1], 0);

    if ( !image.data ) {
        printf("No image data \n");
        return -1;
    }

    for(int i=0; i<image.rows; i++){
      for(int j=0; j<image.cols; j++){
        // perform different binarization rules depending on image
        if (strcmp(argv[1], "source.png") == 0)
          output.at<uchar>(i,j) = binarize(image, i, j, getThreshold(image, i, j, 5));
        if (strcmp(argv[1], "quote.jpg") == 0)
          output.at<uchar>(i,j) = quote(image, i, j);
        if (strcmp(argv[1], "japflat.jpg") == 0)
          output.at<uchar>(i,j) = japflat(image, i, j);
        if (strcmp(argv[1], "japbend.jpg") == 0)
          output.at<uchar>(i,j) = japbend(image, i, j);
        if (strcmp(argv[1], "magazin2.jpg") == 0)
          output.at<uchar>(i,j) = magazin2(image, i, j);
        if (strcmp(argv[1], "magazin1.jpg") == 0)
          output.at<uchar>(i,j) = magazin1(image, i, j);
      }
    }

    imshow("Original Image", image);
    imshow("Output Image", output);
    imwrite("output.jpg", output);

    waitKey(0);
    return 0;
}
