#include "opencv2/opencv.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/imgproc/imgproc_c.h"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/opencv.hpp"
#include "stdlib.h"
#include "stdio.h"
#include "iostream"
#include "math.h"

#define CWIDTH 6		//width of chessboard pattern
#define CHEIGHT 5		//height of chessboard pattern

using namespace cv;
using namespace std;

int main ( int argc, char **argv ){

	Mat input = imread("ics_logo.jpg");
	VideoCapture cap(0);

	Size board_size(CWIDTH-1, CHEIGHT-1);
    
	vector<Point2f> corners;

	if (!input.data){
        	return -1;	//no image data
    	}
	
	if (!cap.isOpened()){
		return -1;	//close if webcam not opened or detected
	}
    
	while(true){
		Mat frame;

		if (!cap.read(frame)){
			break;	//close if no frames taken
		}

		Mat gray;
        	cvtColor(frame, gray, CV_BGR2GRAY);
        
		bool flag = findChessboardCorners(frame, board_size, corners);	//true if a chessboard pattern is detected

		if(flag == 1){            
			//identifies the chessboard pattern from the gray image and saves the coordinates of the corners
			cornerSubPix(gray, corners, Size(11,11), Size(-1,-1), TermCriteria(CV_TERMCRIT_EPS+CV_TERMCRIT_ITER, 30, 0.1));
				
			//push corner values on another vector
			vector<Point2f> cor;
			cor.push_back(corners[0]);
			cor.push_back(corners[CWIDTH-2]);
			cor.push_back(corners[(CWIDTH-1)*(CHEIGHT-1)-1]);
			cor.push_back(corners[(CWIDTH-1)*(CHEIGHT-2)]);
            
            float width  = sqrt(pow(cor.at(3).x - cor.at(0).x, 2) + pow(cor.at(3).y - cor.at(0).y, 2));
            
			//resize the image that we will overlay
			Mat resized_input;
			resize(input, resized_input, Size(width,width), 0, 0, INTER_CUBIC);
            
            // create a container matrx that we will place our ICS logo
            // the reason we do this is to preseve data when we rotate the matrix
            float containerModifier = 1.5;
            
            Mat container(width * containerModifier, width * containerModifier, CV_8UC4);
            
            // initialize transparent pixels on container
            int i, j;
            for(i = 0; i < container.cols; i++) {
                for(j = 0; j < container.rows; j++) {
                    container.at<Vec4b>(i, j)[0] = 0;
                    container.at<Vec4b>(i, j)[1] = 0;
                    container.at<Vec4b>(i, j)[2] = 0;
                    container.at<Vec4b>(i, j)[3] = 0;
                }
            }
            
            // convert ICS logo and frame to 4 channels (with alpha)
            cvtColor(resized_input, resized_input,CV_BGR2BGRA);
            cvtColor(frame, frame,CV_BGR2BGRA);
            
            // copy ICS logo to container
            resized_input.copyTo(container(Rect(container.cols/2 - width/2,container.rows/2 - width/2,width,width)));
            
            // compute for angle
            float angle = atan2(cor.at(1).y - cor.at(0).y, cor.at(1).x - cor.at(0).x) * 180 / M_PI;
            
            // rotate the container by angle
            Point2f src_center(container.cols/2.0F, container.rows/2.0F);
            Mat rot_mat = getRotationMatrix2D(src_center, -angle, 1.0);
            warpAffine(container, container, rot_mat, container.size());

			// identify starting pixels in frame for overlaying
            int colStart = (int)cor.at(0).x-50;
            int rowStart = (int)cor.at(0).y-50;
            
            // copy container to frame
            for(i = 0; i < container.rows; i++) {
                for(j = 0; j < container.cols; j++) {
                    if(rowStart + i < frame.rows && colStart + j < frame.cols) {
                        // copy only non-transparent pixels
                        if(container.at<Vec4b>(i, j)[3] > 0) {
                            frame.at<Vec4b>(rowStart + i, colStart + j)[0] = container.at<Vec4b>(i, j)[0];
                            frame.at<Vec4b>(rowStart + i, colStart + j)[1] = container.at<Vec4b>(i, j)[1];
                            frame.at<Vec4b>(rowStart + i, colStart + j)[2] = container.at<Vec4b>(i, j)[2];
                        }
                    }
                    
                }
            }
            
            // for debugging, display corners
            // circle(frame, cor.at(0), 5, Scalar(0, 0, 0), 1, 8, 0);
            // circle(frame, cor.at(1), 5, Scalar(255, 0, 0), 1, 8, 0);
            // circle(frame, cor.at(2), 5, Scalar(0, 255, 0), 1, 8, 0);
            // circle(frame, cor.at(3), 5, Scalar(0, 0, 255), 1, 8, 0);

		}

		imshow("Original", frame);

		if(waitKey(30) == 27){ //press 'esc' to exit
			break; 
		}	
	}
    
	return 0;
}
