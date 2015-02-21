=====================
ColorPickerPreference
=====================

Generally used classes by Daniel Nilsson.
ColorPickerPreference class by Sergey Margaritov.
Packed by Sergey Margaritov.
Packed again and made Gradle compatible by Vincent Fischer.

Features
========

* Color Area
* Hue Slider
* Alpha Slider (disabled by default)
* Old & New Color
* Color Preview in Preferences List

Requirements
============

Tested with APIv7, but maybe will work with early versions

Installation
===========

Android Studio
--------------

1) Paste or clone this library into the ``/libs`` folder, in the root directory of your project. Create a new folder: ``/libs`` if not already present. (This step is not required - only for keeping cleaner project structure)
2) Edit ``settings.gradle`` by adding the library. You have also define a project directory for the library. Your ``settings.gradle`` should look like below:
  ::
  
	include ':app', ':ColorPickerPreference'
	project(':ColorPickerPreference').projectDir = new File('libs/ColorPickerPreference')

3) In ``app/build.gradle`` add the ColorPickerPreference library as a dependency:
  ::
  
	dependencies {
	    compile fileTree(dir: 'libs', include: ['*.jar'])
	    compile 'com.android.support:appcompat-v7:21.0.3'
	    compile project(":ColorPickerPreference")
	}
	

4) Sync project, clean and build. You can use the ``ColorPickerPreference`` library as part of your project now.

Eclipse
-------

1) Before you can add a ``ColorPickerPreference`` to your application, you must first add a library reference:
2) Clone or download a copy of the library
3) Import the library into Eclipse: File menu -> Import -> Existing Project into Workspace
4) Open your application's project properties and add a library reference to ``ColorPickerPreference``

Usage
=====

You can see some tests inside

::

    <com.c0br4.preference.colorpicker.ColorPickerPreference
        android:key="color1"
        android:title="@string/color1_title"
        android:summary="@string/color1_summary"
        android:defaultValue="@color/pumpkin_orange"    <!-- integer resources are also accepted -->
        alphaSlider="true"                              <!-- enable alpha slider via XML -->
    />

To enable Alpha Slider in your code use function:
::
    setAlphaSliderEnabled(boolean enable)

Screens
=======

* .. image:: https://github.com/C0br4/ColorPickerPreference/raw/master/screen_1.png

* .. image:: https://github.com/C0br4/ColorPickerPreference/raw/master/screen_2.png
