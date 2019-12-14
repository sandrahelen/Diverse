"""red-green-light klasse"""
from lightdetector import LightDetector


class Redgreenlight:
    """react to red or green light"""
    def __init__(self, BBcon, lightdetector, linedetector):
        self.bbcon = BBcon
        self.sensobs = []
        self.lightdetector = lightdetector
        self.linedetector = linedetector
        self.motor_recommendations = []
        self.active_flag = False
        self.priority = 2
        self.match_degree = 0
        self.weight = 0
        self.bbcon.add_behaviour(self)
        self.bbcon.deactivate_behaviour(self)

    def get_weight(self):
        """get weight"""
        return self.weight

    def get_motor_recommendations(self):
        """get motor rec"""
        return self.motor_recommendations

    def consider_deactivation(self):
        """deactivation"""
        if self.linedetector.get_value() == [-1, -1]:
            self.active_flag = False
            self.bbcon.deactivate_behaviour(self)

    def consider_activation(self):
        """activation"""
        if self.linedetector.get_value() != [-1, -1]:
            self.active_flag = True
            self.bbcon.activate_behaviour(self)
        else:
            self.consider_deactivation()

    def update(self):
        """update"""
        if self.active_flag:
            self.sense_and_act()
        if not self.active_flag:
            self.consider_activation()
        elif self.active_flag:
            self.consider_deactivation()
        self.weight = self.match_degree * self.priority

    def sense_and_act(self):
        """get sensor and act"""
        camera_value = self.lightdetector.get_value()
        print(self.lightdetector.colour_values)
        if camera_value == "red":
            self.match_degree = 0.25
            self.motor_recommendations = ["S", 0]
        elif camera_value == "green":
            self.match_degree = 1
            self.motor_recommendations = ["F", 1]
        else:
            self.match_degree = 0
            self.motor_recommendations = ["B", 0]

    def get_active(self):
        """active status"""
        self.consider_activation()
        return self.active_flag

