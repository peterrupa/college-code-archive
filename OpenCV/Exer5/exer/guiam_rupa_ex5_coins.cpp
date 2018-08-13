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

    Mat img = imread(argv[1], 1);
    Mat binary, output;

    // binarize image
    cvtColor(img, output,COLOR_BGR2GRAY);
    threshold(output, binary, 200, 255, THRESH_BINARY_INV);

    if(!binary.data)
    {
        printf("No image data \n");
        return -1;
    }

    // Get connected components and stats
    const int connectivity_8 = 8;
    Mat labels, stats, centroids;

    int nLabels = connectedComponentsWithStats(binary, labels, stats, centroids, connectivity_8, CV_32S);

    // assign constant values of coin values
    float value = 0;
    const float coin0 = 5; //5 pesos
    const float coin1 = 1; //1 peso
    const float coin2 = 0.25; //25 cent
    const float coin3 = 0.10; //10 cent
    const float coin4 = 0.05; //5 cent

    // number of coins
    int count0 = 0;
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    int count4 = 0;

    for(int i=1; i<nLabels - 1; i++){
      int x1 = stats.at<int>(i,CC_STAT_LEFT),
          y1 = stats.at<int>(i,CC_STAT_TOP),
          x2 = x1 + stats.at<int>(i,CC_STAT_WIDTH),
          y2 = y1 + stats.at<int>(i,CC_STAT_HEIGHT),
          area = stats.at<int>(i,CC_STAT_AREA);

      // set label options
      ostringstream label_coin;
      String text_coin = "I'm a blob";
      int fontFace_coin = FONT_HERSHEY_SIMPLEX;
      double fontScale_coin = 0.3;
      int thickness_coin = 1;
      Point textOrg_coin(x1, y1 - 4);

      Scalar color(255,0,255);

      // classify coins according to area size
      if(area >= 3000){
        color = Scalar(0, 255, 0);
        value += coin0;
        count0 ++;
        text_coin = "5 pesos";
      }

      else if(area >= 2700){
        color = Scalar(0, 0, 255);
        value += coin1;
        count1++;
        text_coin = "1 peso";
      }

      else if(area >= 1900) {
        color = Scalar(255, 0, 0);
        value += coin2;
        count2++;
        text_coin = "15 cents";
      }

      else if(area >= 1400){
        color = Scalar(0, 255, 255);
          value += coin3;
          count3++;
          text_coin = "10 cents";
      }

      else {
        value += coin4;
        count4++;
        text_coin = "5 cents";
      }

      // draw box to component
      rectangle(img, Point(x1, y1), Point(x2, y2), color, 1, 8);
      label_coin << text_coin;
      putText(img, label_coin.str(), textOrg_coin, fontFace_coin, fontScale_coin, Scalar(0,0,0), thickness_coin, 8);

    }

    // draw total value on image
    ostringstream label;
    String text = "Total Value: ";
    label << text;

    if(value != 0) label << value;
    String val = label.str();

    int fontFace = FONT_HERSHEY_SIMPLEX;
    double fontScale = 0.4;
    int thickness = 1;
    Point textOrg(5, img.rows - 10);
    putText(img, val, textOrg, fontFace, fontScale, Scalar(0,0,0), thickness, 8);

    imshow("Output", img);
    imwrite("output.jpg", img);
    waitKey(0);

}
