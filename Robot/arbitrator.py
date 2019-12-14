"""Inneholder klassen arbritrator """


class Arbitrator:
    """Hjelpe klasse til bbcon som vekter behaviors"""

    def __init__(self, BBcon):
        """Konstruktør"""
        self.bbcon = BBcon

    def choose_action(self):
        """Velger hvilken action som er viktigst"""
        actions = self.bbcon.active_behaviours
        weight = []
        for i in range(len(actions)):
            priority = actions[i].get_weight()
            weight.append(priority)
        behavior = actions[weight.index(max(weight))]
        print("winner: ", behavior)
        recommendation = behavior.get_motor_recommendations()
        return recommendation
