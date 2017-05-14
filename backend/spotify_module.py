import spotipy


class Spotishower:
    def __init__(self):
        self.scope = '-'
        self.token = "BQDHFVbGlutWEzf3rfI3xm4V_OGTSKOG7IxWz4Um9CE7ZiDJLUfFEPgPvLAHkjZbkBbS8U6tgT-hPogD9gXIhnp9XMCw1eyRlMMJGcEu5reKrggULEFMak0xSnGvVWBzOoyVFO2zVVbxPAO8W431XnYUvTT4Zaepv7KOq_Hbbf5-ksNdxXKkddWdhrJWxaHS8dP7_7ers07cRQRjBxFEGh25yLx1WW17Z9ofMjq7AO-hDXYpbPnNuAPlHoqvV8P2tsMxcBqtGH3rVCTho60tCtHqxgI-C6BTUuDoVLZQe-agK3kckRE5wLCO2LgYyw"
        self.limit = 10

    def set_user_token(self, token):
        self.token = token

    def set_limit(self,limit):
        self.limit = limit

    def current_user_top_tracks(self):
        if self.token:
            sp = spotipy.Spotify(auth=self.token)
            sp.trace = False
            # ranges = ['short_term', 'medium_term', 'long_term']
            ranges = ['medium_term']
            for range in ranges:
                results = sp.current_user_top_tracks(time_range=range, limit=self.limit)
                print(results['items'])
                return results['items']


# sp = Spotishower()
# sp.current_user_top_tracks()
