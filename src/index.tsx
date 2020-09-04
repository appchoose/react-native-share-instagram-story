import { NativeModules } from 'react-native';

type ShareInstagramStoryType = {
  shareBackgroundVideo (attributionURL: string, appID: string, url: string): Promise<string>;
};

const { ShareInstagramStory } = NativeModules;

export default ShareInstagramStory as ShareInstagramStoryType;
