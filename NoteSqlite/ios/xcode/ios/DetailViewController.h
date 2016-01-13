//
//  DetailViewController.h
//  ios
//
//  Copyright (c) 2015 intel. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailViewController : UIViewController
@property (weak, nonatomic) IBOutlet UITextView *noteView;
- (IBAction)doSaveNote:(id)sender;

@end
