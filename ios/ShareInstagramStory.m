#import "ShareInstagramStory.h"

@implementation ShareInstagramStory

RCT_EXPORT_MODULE()

- (dispatch_queue_t)methodQueue
{
  return dispatch_queue_create("com.appchoose.choose.ShareInstagram", DISPATCH_QUEUE_SERIAL);
}

RCT_EXPORT_METHOD(shareBackgroundVideo:(NSString *)attributionURL appID:(NSString *)appID url:(NSString *)url shareBackgroundVideoWithResolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject)
{
   @try{
      dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
    NSData *const backgroundVideoData = [NSData dataWithContentsOfURL:[NSURL URLWithString:url]];


   NSURL *urlScheme = [NSURL URLWithString:@"instagram-stories://share?source_application=com.appchoose.choose.android"];

    if ([[UIApplication sharedApplication] canOpenURL:urlScheme]) {
        NSArray *pasteboardItems = @[@{@"com.instagram.sharedSticker.backgroundVideo" : backgroundVideoData,
                                       @"com.instagram.sharedSticker.contentURL" : attributionURL,
                                       @"com.instagram.sharedSticker.appID" : appID}];
        NSDictionary *pasteboardOptions = @{UIPasteboardOptionExpirationDate : [[NSDate date] dateByAddingTimeInterval:60 * 5]};

        [[UIPasteboard generalPasteboard] setItems:pasteboardItems options:pasteboardOptions];
        [[UIApplication sharedApplication] openURL:urlScheme options:@{} completionHandler:nil];
         resolve(@"ok");
    } else {
       resolve(@"cannot open");
    }
    });

 }
  @catch(NSException *exception){
   resolve(@"cannot share");
 }
}

@end
