//
//  Message.swift
//  ChatApp
//
//  Created by user191721 on 4/9/21.
//

import Foundation
import MessageKit
import UIKit
import MessageKit
import SDWebImage
import AVFoundation
import AVKit
import CoreLocation

struct Message: MessageType{
    public var sender: SenderType
    public var messageId: String
    public var sentDate: Date
    public var kind: MessageKind
}

/*if !(UserDefaults.standard.value(forKey: "email") as? String == otherUserEmail) {
    let content = UNMutableNotificationContent()
    content.title = "Chat App"
    content.body = "hi"
    print("AAAAAAAAAAAAAAAAAA")
    let date = Date().addingTimeInterval(5)
    let dateComponents = Calendar.current.dateComponents([.year, .month, .day, .hour, .minute], from: date)
    let uuidString = UUID().uuidString
    let trigger = UNCalendarNotificationTrigger(dateMatching: dateComponents, repeats: false)
    let request = UNNotificationRequest(identifier: uuidString, content: content, trigger: trigger)
    let center = UNUserNotificationCenter.current()
    center.add(request, withCompletionHandler: { error in
        print("failed send notification: \(String(describing: error))")
        
    })
}*/


extension MessageKind {
    var messageKindString: String {
        switch self {
        case .text(_):
            return "text"
        case .attributedText(_):
            return "attributed_text"
        case .photo(_):
            return "photo"
        case .video(_):
            return "video"
        case .location(_):
            return "location"
        case .emoji(_):
            return "emoji"
        case .audio(_):
            return "audio"
        case .contact(_):
            return "contact"
        case .custom(_):
            return "customc"
        case .linkPreview(_):
            return "linkPreview"
        }
    }
}
