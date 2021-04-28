//
//  Location.swift
//  ChatApp
//
//  Created by user191721 on 4/9/21.
//

import Foundation
import UIKit
import MessageKit
import InputBarAccessoryView
import SDWebImage
import AVFoundation
import AVKit
import CoreLocation

struct Location: LocationItem {
    var location: CLLocation
    var size: CGSize
}
