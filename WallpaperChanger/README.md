# Wallpaper Changer

@author: Deepanshu Bhatti

## Introduction

The wallpaper changer is a small python application which provides users to change desktop wallpaper automatically in windows 10 from the selected wallpaper directory.

This is basically developed for non activated windows as the windows feature to change wallpaper automatically is disabled.

---

## Features

* Automatically change wallpapers randomly after a given interval
* Gives a UI with *NEXT* button to change wallpaper on wish
* Both above runs together on different threads
* Height, width, Min Height, Min width, Wallpaper path and change time are configurable through config.json
* Wallpaper Directory 'E:\Wallpapers\Wallpapers' by default
* By default size of wallpaper is 1920x768 pixels

---

## Deploy as Application

* Install _pyinstaller_ package
  * Command *pip install pyinstaller*
* To generate exe file for application
  * pyinstaller --onefile -w ChangeWallpaper.py
* This will generate an exe file in dist folder
* Put this exe file in Startup folder to run this application on start

---

## Run on Startup

* Paste the generated WallpaperChanger.exe at "C:\Users\<UserName>\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup"
* Or Paste ExecuteChanger.cmd in "C:\Users\<UserName>\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup"
* Restart your computer

---