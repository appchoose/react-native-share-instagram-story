import * as React from 'react';
import { StyleSheet, View, Text, TouchableOpacity, ActivityIndicator } from 'react-native';
import ShareInstagramStory from 'react-native-share-instagram-story';

export default function App () {
  const [isLoading, setLoading] = React.useState(false);
  const [res, setRes] = React.useState('');
  async function onclick () {
    setLoading(true)
    try {
      const res = await ShareInstagramStory.shareBackgroundVideo(
        'https://appchoose.io',
        '17841402064409287',
        'https://images-f5ljwmnzga-ew.a.run.app/referral_video?name=titithebest');
      setRes(res)
    } catch (e) {
      setRes(e)
    } finally {
      setLoading(false)
    }

  }

  return (
    <View style={styles.container}>
      {isLoading ? <ActivityIndicator /> :
        <TouchableOpacity onPress={onclick}>
          <Text>Share</Text>
        </TouchableOpacity>}
      <Text>{res}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: 'red',
    justifyContent: 'center',
  },
});
