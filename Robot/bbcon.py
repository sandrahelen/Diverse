"""BBCON klassen"""
from arbitrator import Arbitrator
from whiteline import Whiteline
from moving_object import Moving_object
from redgreenlight import Redgreenlight
from motob import Motob
from lightdetector import LightDetector
from linedetector import LineDetector
from threatdetector import ThreatDetector
from zumo_button import ZumoButton

class BBcon:
    """Behaviour-Based Controller"""
    def __init__(self):
        self.behaviours = []
        self.active_behaviours = []
        self.inactive_behaviours = []
        self.sensobs = []
        self.motobs = []
        self.motob = Motob()
        self.arbitrator = Arbitrator(self)
        self.current_timestep = 0
        self.whiteline = None
        self.moving_object = None
        self.redgreenlight = None
        self.setup()

    def setup(self):
        """Setup for sensobs and behaviours"""
        linedetector = LineDetector()
        lightdetector = LightDetector()
        threatdetector = ThreatDetector()

        # Create behaviours
        self.sensobs = [linedetector, lightdetector, threatdetector]
        self.whiteline = Whiteline(self, linedetector)
        self.moving_object = Moving_object(self, threatdetector)
        self.redgreenlight = Redgreenlight(self, lightdetector, linedetector)

        # Updates sensobs
        linedetector.add_behavior(self.whiteline)
        linedetector.add_behavior(self.redgreenlight)
        lightdetector.add_behavior(self.redgreenlight)
        threatdetector.add_behavior(self.moving_object)


    def add_behaviour(self, behaviour):
        """Legger til handling"""
        self.behaviours.append(behaviour)

    def add_sensob(self, sensob):
        """Legger til sensob"""
        self.sensobs.append(sensob)

    def activate_behaviour(self, behaviour):
        """Legger til aktiv handling"""
        if behaviour not in self.active_behaviours:
            self.active_behaviours.append(behaviour)
            if behaviour in self.inactive_behaviours:
                self.inactive_behaviours.remove(behaviour)

    def deactivate_behaviour(self, behaviour):
        """Fjerner aktiv handling"""
        if behaviour in self.active_behaviours:
            self.active_behaviours.remove(behaviour)
            if behaviour not in self.inactive_behaviours:
                self.inactive_behaviours.append(behaviour)

    def run_one_step(self):
        """Spør om verdier til relevante sensorer,
        i tillegg til at den behandler disse verdiene(?)"""
        for i in self.sensobs:
            i.update()
        # Leser relevante sensob verdier, og produserer et motorforslag
        for j in self.behaviours:
            j.update()
        # Påberoper arbitratoren ved å kalle arbitrator.choose_action,
        # som vil velge en vinnende handling og returnere den handlingens motorforslag
        # og halt_request flagg
        motor_recommendations = self.arbitrator.choose_action()
        print(motor_recommendations)
        self.motob.update(motor_recommendations)

if __name__ == '__main__':
    bbcon = BBcon()
    print("Start")
    ZumoButton().wait_for_press()
    for i in range(1, 10000):
        bbcon.run_one_step()
