# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.2

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/tricycle/Programs/OpenCV/Exer8

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/tricycle/Programs/OpenCV/Exer8

# Include any dependencies generated for this target.
include CMakeFiles/guiam_rupa_ex8.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/guiam_rupa_ex8.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/guiam_rupa_ex8.dir/flags.make

CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o: CMakeFiles/guiam_rupa_ex8.dir/flags.make
CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o: guiam_rupa_ex8.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o -c /home/tricycle/Programs/OpenCV/Exer8/guiam_rupa_ex8.cpp

CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/tricycle/Programs/OpenCV/Exer8/guiam_rupa_ex8.cpp > CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.i

CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/tricycle/Programs/OpenCV/Exer8/guiam_rupa_ex8.cpp -o CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.s

CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o.requires:
.PHONY : CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o.requires

CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o.provides: CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o.requires
	$(MAKE) -f CMakeFiles/guiam_rupa_ex8.dir/build.make CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o.provides.build
.PHONY : CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o.provides

CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o.provides.build: CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o

# Object files for target guiam_rupa_ex8
guiam_rupa_ex8_OBJECTS = \
"CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o"

# External object files for target guiam_rupa_ex8
guiam_rupa_ex8_EXTERNAL_OBJECTS =

guiam_rupa_ex8: CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o
guiam_rupa_ex8: CMakeFiles/guiam_rupa_ex8.dir/build.make
guiam_rupa_ex8: /usr/local/lib/libopencv_shape.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_stitching.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_superres.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_videostab.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_objdetect.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_calib3d.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_features2d.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_flann.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_highgui.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_ml.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_photo.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_video.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_videoio.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_imgcodecs.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_imgproc.so.3.1.0
guiam_rupa_ex8: /usr/local/lib/libopencv_core.so.3.1.0
guiam_rupa_ex8: CMakeFiles/guiam_rupa_ex8.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable guiam_rupa_ex8"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/guiam_rupa_ex8.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/guiam_rupa_ex8.dir/build: guiam_rupa_ex8
.PHONY : CMakeFiles/guiam_rupa_ex8.dir/build

CMakeFiles/guiam_rupa_ex8.dir/requires: CMakeFiles/guiam_rupa_ex8.dir/guiam_rupa_ex8.cpp.o.requires
.PHONY : CMakeFiles/guiam_rupa_ex8.dir/requires

CMakeFiles/guiam_rupa_ex8.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/guiam_rupa_ex8.dir/cmake_clean.cmake
.PHONY : CMakeFiles/guiam_rupa_ex8.dir/clean

CMakeFiles/guiam_rupa_ex8.dir/depend:
	cd /home/tricycle/Programs/OpenCV/Exer8 && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/tricycle/Programs/OpenCV/Exer8 /home/tricycle/Programs/OpenCV/Exer8 /home/tricycle/Programs/OpenCV/Exer8 /home/tricycle/Programs/OpenCV/Exer8 /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/guiam_rupa_ex8.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/guiam_rupa_ex8.dir/depend

