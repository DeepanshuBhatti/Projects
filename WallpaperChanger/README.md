# Wallpaper Changer

@author: Deepanshu Bhatti

## Introduction

The wallpaper changer is a small python application which provides users to change desktop wallpaper automatically from the list of wallpaeres provided in windows 10.

This is basically developed for non activated windows as the windows feature to change wallpaper automatically is disabled.

---

## Features

* Automatically change wallpapers randomly after a given interval (Default: 180s)
* Gives a UI with *NEXT* button to change wallpaper on wish
* Both above runs together on different threads
* Wallpaper Directory 'E:\Wallpapers\Wallpapers' by default
* By default size of wallpaper is 1366x768 pixels
* You can edit python code for changing time interval, wallpaper directory and size of wallpaper

---

## Deploy as Application

* Install _pyinstaller_ package
  * Command *pip install pyinstaller*
* To generate exe file for application
  * pyinstaller --onefile -w ChangeWallpaper.py
* This will generate an exe file in dist folder

---
