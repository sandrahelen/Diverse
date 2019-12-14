"""Whiteline-klasse"""
from linedetector import LineDetector


class Whiteline:
    """Whiteline"""
    def __init__(self, BBcon, linedetector):
        self.bbcon = BBcon
        self.sensobs = []
        self.linedetector = linedetector
        self.motor_recommendations = []
        self.active_flag = True
        self.priority = 2
        self.match_degree = 0
        self.weight = 0
        self.bbcon.add_behaviour(self)
        self.bbcon.activate_behaviour(self)

    def get_weight(self):
        """get weight"""
        return self.weight

    def get_motor_recommendations(self):
        """motor rec"""
        return self.motor_recommendations

    def update(self):
        """update"""
        self.sense_and_act()
        self.weight = self.match_degree * self.priority

    def sense_and_act(self):
        self.match_degree = 0.5
        linedetector = self.linedetector.get_value()
        if linedetector == [-1, -1]:
            self.motor_recommendations = ["F", 0]
            self.match_degree = 0.25
        else:
            if linedetector == [0, 5]:
                self.motor_recommendations = ["L", 0.75]
            elif linedetector[0] <= 2:
                self.motor_recommendations = ["L", 0.75]
            elif linedetector[0] >= 3:
                self.motor_recommendations = ["R", 0.75]

    def get_active(self):
        return self.active_flag
