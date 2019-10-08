import os
import ctypes
import random
import threading
import time
import PIL.Image
from os import listdir
from os.path import isfile, join
from tkinter import *
import tkinter.font as font

SPI_SETDESKWALLPAPER = 20
wallpaper_directory = r'E:\Wallpapers\Wallpapers'


def get_wallpapers():
    files_list = [f for f in listdir(wallpaper_directory) if isfile(join(wallpaper_directory, f))]
    return files_list


def get_wallpaper(wallpapers_list):
    file_name = random.choice(wallpapers_list)
    wallpaper = os.path.join(wallpaper_directory, file_name)
    return wallpaper


def change_wallpaper_in_time(list_of_wallpapers):
    while True:
        change_desktop_wallpaper(list_of_wallpapers)
        time.sleep(180)


def change_desktop_wallpaper(list_of_wallpapers):
    wallpaper = get_wallpaper(list_of_wallpapers)
    wallpaper = resize_wallpaper(wallpaper)
    ctypes.windll.user32.SystemParametersInfoW(SPI_SETDESKWALLPAPER, 0, wallpaper, 0)


def resize_wallpaper(wallpaper):
    new_path = 'temp.jpg'
    image_obj = PIL.Image.open(wallpaper)

    while image_obj.size[0] < 1366 or image_obj.size[1] < 768:
        wallpaper = get_wallpaper(list_of_wallpapers)
        image_obj = PIL.Image.open(wallpaper)

    if image_obj.size[0] == 1366 or image_obj.size[1] == 768:
        return wallpaper

    image_obj = image_obj.resize((1366, 768), PIL.Image.ANTIALIAS)
    new_path = join(r'E:\Wallpapers\Wallpapers', new_path)
    image_obj.save(new_path)
    return new_path


def populate_main_window(main_window):
    myFont = font.Font(family='Calibri', size=20, weight='bold')
    button = Button(main_window,
                    text='Next',
                    height=1,
                    width=12,
                    command=lambda: change_desktop_wallpaper(list_of_wallpapers),
                    bg='gold',
                    justify='center',
                    relief=RAISED
                    )
    button['font'] = myFont
    button.grid(column=1, row=3)


def start_auto_changer(list_of_wallpapers):
    thread = threading.Thread(target=change_wallpaper_in_time, args=([list_of_wallpapers]))
    thread.daemon = True
    thread.start()


if __name__ == '__main__':
    list_of_wallpapers = get_wallpapers()
    start_auto_changer(list_of_wallpapers)
    main_window = Tk()
    main_window.title('Deepanshu Wallpaper Changer')
    main_window.resizable(False, False)
    canvas = Canvas(main_window)
    populate_main_window(canvas)
    canvas.pack()
    main_window.mainloop()
