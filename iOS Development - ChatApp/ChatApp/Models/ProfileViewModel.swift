//
//  ProfileViewModel.swift
//  ChatApp
//
//  Created by user191721 on 4/9/21.
//

import Foundation

enum ProfileViewModelType {
    case info, logout, link
}

struct ProfileViewModel {
    let viewModelType: ProfileViewModelType
    let title: String
    let handler: (() -> Void)?
}
