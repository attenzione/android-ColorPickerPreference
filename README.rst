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

- AndroidX Preference 1.1.1 (or higher)


Installation
===========

Through Gradle
--------------
Add this to your repository blocks in your root build.gradle:
  ::

    maven { url "https://jitpack.io" }


And then add this to your submodule's dependencies block:
  ::

    implementation 'com.github.attenzione:android-ColorPickerPreference:x.y.z'


Usage
=====

Here is an example:

::

    <net.margaritov.preference.colorpicker.ColorPickerPreference
        android:key="color1"
        android:title="@string/color1_title"
        android:summary="@string/color1_summary"
        android:defaultValue="@color/pumpkin_orange"    <!-- integer resources are also accepted -->
        alphaSlider="true"                              <!-- enable alpha slider via XML -->
    />

To enable Alpha Slider in your code, use this function:
::
    setAlphaSliderEnabled(boolean enable)


Screens
=======

* .. image:: https://github.com/C0br4/ColorPickerPreference/raw/master/screen_1.png

* .. image:: https://github.com/C0br4/ColorPickerPreference/raw/master/screen_2.png
