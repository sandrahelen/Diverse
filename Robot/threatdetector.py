# pylint: disable=E0401

"""Detects distance to objects"""
from ultrasonic import Ultrasonic


class ThreatDetector:
    """Detects any object in the vicinity"""

    def __init__(self):
        self.sonic_sensor = Ultrasonic()
        self.value = -1
        self.behaviours = []

    def update(self):
        """Calculates distance to closest object"""
        if self.check_active():
            self.sonic_sensor.update()
            self.value = self.sonic_sensor.get_value()

    def add_behavior(self, behaviour):
        if behaviour not in self.behaviours:
            self.behaviours.append(behaviour)

    def check_active(self):
        for behaviour in self.behaviours:
            if behaviour.get_active():
                return True
        return True

    def get_value(self):
        return self.value
