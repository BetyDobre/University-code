//
//  WKWebViewController.swift
//  ChatApp
//
//  Created by user191721 on 4/10/21.
//

import UIKit
import WebKit

class WKWebViewController: UIViewController, WKNavigationDelegate {
    
    private var webView: WKWebView!
    
    override func loadView() {
        webView = WKWebView()
        webView.navigationDelegate = self
        view = webView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        let url = URL(string: "https://developer.apple.com/")!
        webView.load(URLRequest(url: url))
        webView.allowsBackForwardNavigationGestures = true
    }
    

    

}
