import { NativeModules } from 'react-native';

type ShareInstagramStoryType = {
  shareBackgroundVideo (
    attributionURL: string,
    appID: string,
    url: string
  ): Promise<'ok' | 'cannot share' | 'cannot open'>;
};

const { ShareInstagramStory } = NativeModules;

export default ShareInstagramStory as ShareInstagramStoryType;
