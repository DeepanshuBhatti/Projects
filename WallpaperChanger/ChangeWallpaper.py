import os
import sys
import ctypes
import json
import logging
import random
import threading
import time
from PIL import Image
from os import listdir
from os.path import isfile, join
from PyQt5.QtWidgets import QApplication, QPushButton, QMainWindow

logging.basicConfig(
    format='[%(asctime)s] %(process)d [%(pathname)s:%(lineno)d | %(levelname)s] - %(''message)s',
    level=logging.INFO
)


class WallpaperChangerApp(QMainWindow):
    SPI_SETDESKWALLPAPER = 20
    config_path = './config.json'
    wallpapers_list = []
    backup_list = []

    def __init__(self):
        try:
            super().__init__()
            if not os.path.isfile(self.config_path):
                raise Exception("Config File Not found")

            with open(self.config_path) as json_data_file:
                data = json.load(json_data_file)
            self.wall_height = data["size"]["height"]
            self.wall_width = data["size"]["width"]
            self.min_height = data["size"]["min_height"]
            self.min_width = data["size"]["min_width"]
            self.wallpaper_directory = data["path"]["wallpaper_directory"]
            self.auto_change_after = data["auto_change"]["time"]
            self.wallpapers_list = self.get_wallpaper_path_list()
            self.init_ui()
        except Exception as e:
            logging.error(str(e))

    def init_ui(self):
        self.setWindowTitle("Changer")
        self.setFixedHeight(30)
        self.setFixedWidth(120)
        self.start_auto_changer()
        self.add_next_button()
        self.show()

    def add_next_button(self):
        next_button = QPushButton(self)
        next_button.setText("Next")
        next_button.setFixedHeight(30)
        next_button.setFixedWidth(120)
        next_button.clicked.connect(self.next_click)

    def next_click(self):
        try:
            self.change_desktop_wallpaper()
        except Exception as e:
            logging.error(str(e))

    def change_desktop_wallpaper(self):
        wallpaper = self.get_wallpaper()
        wallpaper = self.resize_wallpaper(wallpaper)
        ctypes.windll.user32.SystemParametersInfoW(self.SPI_SETDESKWALLPAPER, 0, wallpaper, 0)

    def get_wallpaper(self):
        if len(self.wallpapers_list) < 1:
            logging.info("Restarting List of Wallpapers")
            self.wallpapers_list = self.backup_list
            self.backup_list = []
        file_name = random.choice(self.wallpapers_list)
        logging.info("Found " + str(len(self.wallpapers_list)) + " Wallpapers, currently showing " + file_name)
        self.backup_list.append(file_name)
        self.wallpapers_list.remove(file_name)
        wallpaper = os.path.join(self.wallpaper_directory, file_name)
        return wallpaper

    def resize_wallpaper(self, wallpaper):
        image_obj = Image.open(wallpaper)
        width = image_obj.size[0]
        height = image_obj.size[1]
        if width == self.wall_width or height == self.wall_height:
            return wallpaper
        elif width == self.min_width or height == self.min_height:
            return self.resize_min_width(image_obj)
        else:
            wallpaper = self.get_wallpaper()
            return self.resize_wallpaper(wallpaper)

    def resize_min_width(self, image_obj):
        new_path = 'temp.jpg'
        image_obj = image_obj.resize((self.wall_width, self.wall_height), Image.ANTIALIAS)
        new_path = join(self.wallpaper_directory, new_path)
        image_obj.save(new_path)
        return new_path

    def get_wallpaper_path_list(self):
        files_list = [f for f in listdir(self.wallpaper_directory) if isfile(join(self.wallpaper_directory, f))]
        return files_list

    def change_wallpaper_in_time(self):
        while True:
            self.change_desktop_wallpaper()
            time.sleep(self.auto_change_after)

    def start_auto_changer(self):
        thread = threading.Thread(target=self.change_wallpaper_in_time, args=())
        thread.daemon = True
        thread.start()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = WallpaperChangerApp()
    sys.exit(app.exec_())
