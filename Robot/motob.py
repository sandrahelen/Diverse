# pylint: disable=E0401

"""Motob-klasse"""
import time
from motors import Motors
from zumo_button import ZumoButton

class Motob:
    """Starting motors"""

    def __init__(self):
        ZumoButton().wait_for_press()
        self.motors = Motors()
        self.value = []  # motor recommendation (direction, speed)


    def update(self, motor_rec):
        """ubdate motors"""
        self.value = motor_rec
        self.operationalize()

    def operationalize(self):
        """operate motors"""
        dur = 0.8
        if self.value[0] == 'L':
            self.motors.set_value([0, -self.value[1]], dur)
            #self.motors.left(self.value[1], dur)
        elif self.value[0] == 'R':
            self.motors.set_value([-self.value[1], 0], dur)
            #self.motors.left(self.value[1], dur)
        elif self.value[0] == 'F':
            self.motors.forward(0.2, dur)
        elif self.value[0] == 'B':
            self.motors.set_value([self.value[1], self.value[1]], dur)
        elif self.value[0] == 'S':
            self.motors.stop()
            time.sleep(0.5)
