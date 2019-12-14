# pylint: disable=E0401
# pylint: disable=W0611

"""Detects a light"""
from PIL import Image
from camera import Camera
from imager2 import Imager


class LightDetector:
    """Takes a picture and manipulates the image to contain the
        most dominant colour (Red, green or blue)"""

    def __init__(self):
        # Create a camera object
        self.camera = Camera(64, 48)
        self.colour_values = {"red": 0, "green": 0, "blue": 0}
        self.value = None
        self.pixel_name = {(255, 0, 0): "red", (0, 255, 0): "green", (0, 0, 255): "blue"}
        self.behaviours = []

    def update(self):
        """Creates an imager object consisting of a image object from the RPI camera."""
        # This image is then manipulated to consist of extreme colours: red,
        # blue or green
        if self.check_active():
            imager = Imager(False, self.camera.update())
            imager = imager.map_color_wta(False, 0.45)
            self.colour_values = {"red": 0, "green": 0, "blue": 0}
            for i in range(imager.xmax):
                for j in range(imager.ymax):
                    pixel = imager.get_pixel(i, j)
                    if pixel[0] >= 50:
                        self.colour_values["red"] += 1
                    elif pixel[1] >= 50:
                        self.colour_values["green"] += 1
                    else:
                        self.colour_values["blue"] += 1
            max_value = 0
            max_colour = None
            for key in self.colour_values:
                if self.colour_values[key] > max_value and key != "blue":
                    max_value = self.colour_values[key]
                    max_colour = key
            if self.check_object():
                self.value = max_colour
            else:
                if self.colour_values["blue"] >= max_value:
                    self.value = "blue"
                self.value = max_colour

    def check_active(self):
        """check if behaviour is active"""
        for behaviour in self.behaviours:
            if behaviour.get_active():
                return True
        return False


    def check_object(self):
        """check if object"""
        red_value = self.colour_values["red"]
        green_value = self.colour_values["green"]
        if(green_value or red_value) > 600:
            return True
        return False

    def get_value(self):
        """gets value"""
        return self.value

    def add_behavior(self, behaviour):
        """add behaviour"""
        if behaviour not in self.behaviours:
            self.behaviours.append(behaviour)
