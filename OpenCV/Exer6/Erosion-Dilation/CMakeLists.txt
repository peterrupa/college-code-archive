cmake_minimum_required(VERSION 2.8)
project( e_d )
find_package( OpenCV REQUIRED )
add_executable( e_d e_d.cpp )
target_link_libraries( e_d ${OpenCV_LIBS} )
