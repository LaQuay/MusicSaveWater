import sys
import spotipy
import spotipy.util as util

username = "jnssterrass"
scope = '-'
token = util.prompt_for_user_token(username,
                                   scope,
                                   client_id="53c5ac3223a84803bc0919cd2d160648",
                                   client_secret="2180f73011b849c3abb7fed496631e79",
                                   redirect_uri="http://localhost:8888/callback")

if token:
    sp = spotipy.Spotify(auth=token)
    sp.trace = False
    ranges = ['short_term', 'medium_term', 'long_term']
    for range in ranges:
        print("range:", range)
        results = sp.current_user_top_tracks(time_range=range, limit=50)
        for i, item in enumerate(results['items']):
            print(i, item['name'], '//', item['artists'][0]['name'])
        print()

else:
    print("Can't get token for", username)