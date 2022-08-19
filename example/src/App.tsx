import * as React from 'react';

import { StyleSheet, View, Text, Button } from 'react-native';
import { multiply, play, getError, getPlayer, getState } from 'react-native-test-exoplayer';



export default function App() {
  const [result, setResult] = React.useState<number | undefined>();
  const [uri, setUri] = React.useState<string>();

  React.useEffect(() => {
    multiply(3, 7).then(setResult);
    setUri('https://youtubue-server.fly.dev/test')
  }, []);

  function doPlay() {
    console.log("Playing URI", uri ?? 'https://youtubue-server.fly.dev/test')
    play(uri ?? 'https://youtubue-server.fly.dev/test')
  }

  async function showThings() {
    const player = await getPlayer()
    const error = await getError()
    const state = await getState()

    console.log('player', player)
    console.log('error', error)
    console.log('state', state)
  }

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
      <Button title='play' onPress={doPlay}></Button>
      <Button title='show' onPress={showThings}></Button>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
