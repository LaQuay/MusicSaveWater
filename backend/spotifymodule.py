import spotipy


class Spotishower:
    def __init__(self):
        self.scope = '-'
        self.username = "ggrimm"
        self.token = "BQD7pccGeVEed8uMTDbsnKDl3Ml23QpxLsZu49TZHtX-Yxv8_G221haHDyGm7k7RL1Sqbp-zK2WbytMcMA5z2v9D3wKomsJZodpB0lObdrvlDkG-tDo9ocq__lZhHNmuRi8DRtQ0_Xp1iq6pKt1e7fU6b4Bb_f0vUOw7zcSsniw-sjltaQ0ZAKmRYOmnPtxz907YLjpq3TJ28O5lc-290tSK-9cDXhqO4wWlbuBZvwKefjx40RprZphqCntOa9Ln5gjizU1C0XN58cXm_jXoBha2zFMvR4h1ZOhHf09t6I2fY1nx67EYRZlvdS7YBw"

    def set_user_token(self, token):
        self.token = token

    def current_user_top_tracks(self):
        if self.token:
            sp = spotipy.Spotify(auth=self.token)
            sp.trace = False
            # ranges = ['short_term', 'medium_term', 'long_term']
            ranges = ['medium_term']
            for range in ranges:
                results = sp.current_user_top_tracks(time_range=range, limit=50)
                return results['items']

        else:
            print("Can't get token for", self.username)


sp = Spotishower()
sp.current_user_top_tracks()
