# pylint: disable=E0401

"""Line detector-klasse"""
from reflectance_sensors import ReflectanceSensors


class LineDetector:
    """Detects and avoid dark line"""

    def __init__(self, reflectance_sensor=False, threshold=0.05):
        # If reflectance sensor is not given, sets up a reflectance sensor
        # without calibration
        if not reflectance_sensor:
            reflectance_sensor = ReflectanceSensors()
        self.reflect_sensor = reflectance_sensor
        self.value = None
        self.threshold = threshold
        self.behaviours = []

    def update(self):
        """Returns an array that with start and end value for a detected line"""
        if self.check_active():
            values = self.reflect_sensor.update()
            line_start = -1
            line_end = -1
            for i in range(len(values)):
                if values[i] <= self.threshold and line_start == -1:
                    line_start = i
                    line_end = i
                elif values[i] <= self.threshold:
                    line_end = i
            self.value = [line_start, line_end]

    def add_behavior(self, behaviour):
        """add behaviour"""
        if behaviour not in self.behaviours:
            self.behaviours.append(behaviour)

    def check_active(self):
        """checks if active"""
        for behaviour in self.behaviours:
            if behaviour.get_active():
                return True
        return False

    def get_value(self):
        """gets value"""
        return self.value
