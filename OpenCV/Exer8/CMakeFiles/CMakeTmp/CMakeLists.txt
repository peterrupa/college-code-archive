cmake_minimum_required(VERSION 3.2.2.0)
project(CMAKE_TRY_COMPILE C)
set(CMAKE_VERBOSE_MAKEFILE 1)
set(CMAKE_C_FLAGS "")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${COMPILE_DEFINITIONS}")
set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} ${EXE_LINKER_FLAGS}")
include_directories(${INCLUDE_DIRECTORIES})
set(CMAKE_SUPPRESS_REGENERATION 1)
link_directories(${LINK_DIRECTORIES})
add_definitions( -std=c99 )
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "/home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/CMakeTmp")
add_executable(cmTryCompileExec1020322828 "/home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/feature_tests.c")
target_link_libraries(cmTryCompileExec1020322828 ${LINK_LIBRARIES})
