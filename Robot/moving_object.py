"""Moving-lobject klasse"""

class Moving_object:
    """Moving object"""
    def __init__(self, BBcon, threatdetector):
        self.bbcon = BBcon
        self.sensobs = []
        self.threatdetector = threatdetector
        self.motor_recommendations = []
        self.active_flag = False
        self.priority = 2
        self.match_degree = 0
        self.weight = self.priority * self.match_degree
        self.counter = 0
        self.bbcon.add_behaviour(self)
        self.bbcon.deactivate_behaviour(self)

    def get_weight(self):
        """get weight"""
        self.weight = self.priority*self.match_degree
        return self.weight

    def get_motor_recommendations(self):
        """get motor recs"""
        return self.motor_recommendations

    def consider_status(self):
        """status"""
        self.motor_recommendations = []
        self.match_degree = 0
        if self.counter < 1:
            self.active_flag = False
            self.bbcon.deactivate_behaviour(self)
            self.counter += 1
        elif self.counter == 1:
            if self.threatdetector.get_value() < 20:
                self.active_flag = True
                self.bbcon.activate_behaviour(self)
            self.counter = 0

    def update(self):
        """update"""
        if self.active_flag:
            self.sense_and_act()

    def sense_and_act(self):
        """get sensor and act"""
        distance = self.threatdetector.get_value()
        print(distance)
        if distance < 7:
            self.motor_recommendations = ["S", 0]
            self.match_degree = 1
        elif distance < 15:
            self.motor_recommendations = ["R", 0.75]
            self.match_degree = 0.5

    def get_active(self):
        """active status"""
        self.consider_status()
        return self.active_flag
