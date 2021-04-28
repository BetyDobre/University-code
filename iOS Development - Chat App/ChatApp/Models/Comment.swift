//
//  Comment.swift
//  ChatApp
//
//  Created by user191721 on 4/10/21.
//

import Foundation

class Comment: Decodable {
    let id: Int
    let name: String
    let body: String
    let email: String
}

